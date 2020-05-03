package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.notified = false " +
            "AND u.profession IS NOT NULL AND u.level IS NOT NULL AND u.city IS NOT NULL")
    List<User> findNewUsers();
    User findByChatId(Long id);
}
