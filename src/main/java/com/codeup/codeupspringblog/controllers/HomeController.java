package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String welcome() {
        return "hello";
    }

    @GetMapping("/")
    public String welcome2() {
        return "hello";
    }

    @GetMapping("/home1")
    @ResponseBody
    public String Home2() {
        return "This is the landing page! times 2";
    }

    @GetMapping("/home3/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return "the number is : " + number + " and the increment is: "+ (number + 1) + "!";
    }

    //Getting Data From Views to a controller we use the post method
    @GetMapping("/join")
    public String showJoinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "join";
    }


}
