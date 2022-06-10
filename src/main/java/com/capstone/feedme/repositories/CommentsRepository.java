package com.capstone.feedme.repositories;

import com.capstone.feedme.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

}
