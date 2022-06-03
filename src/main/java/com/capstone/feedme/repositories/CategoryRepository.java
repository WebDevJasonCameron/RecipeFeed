package com.capstone.feedme.repositories;

import com.capstone.feedme.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByType(String categoryType);


}
