package com.capstone.feedme.repositories;

import com.capstone.feedme.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findRecipeByApiId(long apiId);
    Recipe findRecipeById(long id);
}
