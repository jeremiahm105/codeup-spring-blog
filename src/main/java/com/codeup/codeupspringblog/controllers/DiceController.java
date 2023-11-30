package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class DiceController {

    @GetMapping("/roll-dice") //represents initial page for user to guess
    public String diceRollGuess() {
        return "roll-dice";
    }


    //The guess is the placeholder for guessed number as a path variable and model object usd to add attributes for rending view
    @GetMapping("/roll-dice/{guess}")
    public String rollDice(@PathVariable int guess, Model model) {
        Random random = new Random();
        int diceRoll = random.nextInt(6) + 1;

        //attributes to render: user guess, random number, boolean value to check if numbers match
        model.addAttribute("diceRoll", diceRoll);
        model.addAttribute("guess", guess);
        model.addAttribute("isCorrect", guess == diceRoll);

        return "roll-result";
    }

}
