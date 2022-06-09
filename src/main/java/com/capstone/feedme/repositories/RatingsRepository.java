package com.capstone.feedme.repositories;

import com.capstone.feedme.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingsRepository extends JpaRepository<Rating, Long> {

}
