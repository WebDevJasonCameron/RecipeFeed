package com.capstone.feedme.repositories;

import com.capstone.feedme.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RatingsRepository extends JpaRepository<Rating, Long> {

    List<Rating> findAllByRecipeId(long recipeId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ratings WHERE user_id = ?1", nativeQuery = true)
    void dropUserRatings(long userId);

//    update ratings set user_id = null where user_id
}
