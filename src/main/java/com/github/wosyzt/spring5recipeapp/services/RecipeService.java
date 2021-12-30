package com.github.wosyzt.spring5recipeapp.services;

import com.github.wosyzt.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService{
    Set<Recipe> getRecipes();
}
