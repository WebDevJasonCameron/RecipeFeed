package com.capstone.feedme.repositories;

import com.capstone.feedme.models.Category;
import com.capstone.feedme.models.Recipe;
import com.capstone.feedme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findRecipeByApiId(long apiId);
    Recipe findRecipeById(long id);
    List<Recipe> findRecipesByRecipeCategories(Category category);

    @Modifying
    @Transactional
    @Query(value = "update recipes set user_id = null where user_id = ?1", nativeQuery = true)
    void dropUserRecipes(long userId);
}
