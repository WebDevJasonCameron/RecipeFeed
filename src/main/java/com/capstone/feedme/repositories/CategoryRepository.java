package com.capstone.feedme.repositories;

import com.capstone.feedme.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Ingredient, Long> {
}
