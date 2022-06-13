package com.capstone.feedme.repositories;

import com.capstone.feedme.models.Comment;
import com.capstone.feedme.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByRecipe(Recipe recipe);


    List<Comment> findByRecipe_Id(long id);

    @Modifying
    @Transactional
    @Query(value = "delete from comments where user_id = ?1", nativeQuery = true)
    void dropUserComments(long userId);


}
