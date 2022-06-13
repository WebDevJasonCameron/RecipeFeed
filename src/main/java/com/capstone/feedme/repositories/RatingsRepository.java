package com.capstone.feedme.repositories;

import com.capstone.feedme.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingsRepository extends JpaRepository<Rating, Long> {

    List<Rating> findAllByRecipeId(long recipeId);

}
