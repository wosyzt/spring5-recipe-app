package com.github.wosyzt.spring5recipeapp.controllers;

import com.github.wosyzt.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String index(Model model){
        log.debug("I'm now in index Controller");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
