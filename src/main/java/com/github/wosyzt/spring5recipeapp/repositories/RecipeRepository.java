package com.github.wosyzt.spring5recipeapp.repositories;

import com.github.wosyzt.spring5recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
