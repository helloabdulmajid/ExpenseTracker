package com.abdulmajid.expensetracker.repository;

import com.abdulmajid.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByUserName(String userName);

    // Optional<User> findByEmail(String email);

    Optional<User> findByUserName(String username);

    Optional<User> findByEmail(String email);
}
