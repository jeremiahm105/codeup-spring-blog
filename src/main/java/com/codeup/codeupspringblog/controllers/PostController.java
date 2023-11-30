package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.Services.EmailService;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final UserRepository userDao;
    private final PostRepository postDao;
    private final EmailService emailService;

    // constructor injection
    public PostController(UserRepository userDao, PostRepository postDao, EmailService emailService) {

        this.userDao = userDao;
        this.postDao = postDao;
        this.emailService = emailService;
    }

    //    GET	/posts	posts index page


    @GetMapping("/posts")
    public String getPost(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }
//works
//    @GetMapping("/posts")
//    public String posts(Model model) {
//        List<Post> posts = postDao.findAll();
//        model.addAttribute("posts", posts);
//        return "posts/index";
//    }

//    GET	/posts/{id}	view an individual post


    //works
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getReferenceById(id));
        return "posts/show";
    }

    //    Implement edit and delete functionality.
    @GetMapping("/posts/update/{id}/{title}")
    @ResponseBody
    public String updatePost(@PathVariable long id, @PathVariable String title) {
        Post post = postDao.getReferenceById(id);
        post.setTitle(title);
        postDao.save(post);
        return "Updating post";

    }

    //Does not work
//    @GetMapping("/posts/delete/{id}")
//    public String deletePostById(@PathVariable(name= "id") long id) {
//        postDao.deleteById(id);
//        return "redirect:/posts/index";
//    }

    //refactor K
//    @GetMapping("/posts/{id}/edit")
//    public String editForm(@PathVariable long id, Model model) {
//        Post postToEdit = postDao.getReferenceById(id);
//        model.addAttribute("post", postToEdit);
//        return "posts/edit";
//    }

    //good-works
    @GetMapping("/posts/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);
        return "/posts/edit";
    }

//good works
//    @PostMapping("/posts/{id}/edit")
//    public String updatePost(@ModelAttribute Post post, @PathVariable long id) {
//        Post p = postDao.getReferenceById(id);
//        p.setTitle(p.getTitle());
//        p.setBody(p.getBody());
//        postDao.save(post);
//        return "redirect:/posts";
//    }

    //good -works
    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable Long id, @ModelAttribute Post post) {
        Post editPost = postDao.findById(id).get();
        editPost.setBody(post.getBody());
        editPost.setTitle(post.getTitle());
        postDao.save(editPost);
        return "redirect:/posts";
    }

    //good
    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id, @ModelAttribute Post post) {
        Post deletePost = postDao.findById(id).get();
        postDao.delete(deletePost);
        return "redirect:/posts";
    }

    //good -works
    @GetMapping("/posts/create")
    public String createForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }


    //    post has everything from user and not missing anything extra layer of protection

    //good -works
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getReferenceById(loggedInUser.getId());
        post.setUser(user);
        postDao.save(post);
        emailService.prepareAndSend(post, "Post Created", "Hello, your post has been created!");
        return "redirect:/posts";
    }
}