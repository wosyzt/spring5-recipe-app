package com.github.wosyzt.spring5recipeapp.services.impl;

import com.github.wosyzt.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.github.wosyzt.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.github.wosyzt.spring5recipeapp.domain.Recipe;
import com.github.wosyzt.spring5recipeapp.exceptions.NotFoundException;
import com.github.wosyzt.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    void getRecipeByIdNotFound() {
        Optional<Recipe> recipeOptional = Optional.empty();
        when(recipeRepository.findById(any())).thenReturn(recipeOptional);
        assertThrows(NotFoundException.class, () -> {
            Recipe recipe = recipeService.findById(1l);
        });
    }

    @Test
    void getRecipeByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);
        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }


    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById(){
        Long idToDelete = Long.valueOf(2L);
        recipeService.deleteById(idToDelete);

        //no 'when', since method has void return type

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}