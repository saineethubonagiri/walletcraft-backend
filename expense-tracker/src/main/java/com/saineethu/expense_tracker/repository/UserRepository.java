package com.saineethu.expense_tracker.repository;

import com.saineethu.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    //Find user by email for login/registration checks
    Optional<User> findByEmail (String email);
}


