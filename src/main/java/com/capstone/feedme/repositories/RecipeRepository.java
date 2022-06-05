package com.capstone.feedme.repositories;

import com.capstone.feedme.models.Category;
import com.capstone.feedme.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findRecipeByApiId(long apiId);
    Recipe findRecipeById(long id);
    List<Recipe> findRecipesByRecipeCategories(Category category);

}
