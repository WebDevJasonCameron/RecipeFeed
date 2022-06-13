package com.capstone.feedme.repositories;

import com.capstone.feedme.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByRecipeId(long recipeId);
}
