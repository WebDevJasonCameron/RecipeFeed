package com.capstone.feedme.repositories;

import com.capstone.feedme.models.Comment;
import com.capstone.feedme.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByRecipe(Recipe recipe);

    List<Comment> findByRecipe_Id(long id);



}
