package com.capstone.feedme.repositories;

import com.capstone.feedme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
