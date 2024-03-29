package com.example.repository;
import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPARepository that will be used to preform persistence operations on Account objects
 */
public interface AccountRepository extends JpaRepository <Account, Integer> {
    boolean existsByUsername(String username);
    Account findByUsername(String username);
    // boolean existsById(Integer account_id);
    // boolean existsById(int account_id);
}
