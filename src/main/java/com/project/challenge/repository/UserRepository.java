package com.project.challenge.repository;


import com.project.challenge.domain.user.User;
import com.project.challenge.domain.user.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailAndUserStatus(String email, UserStatus userStatus);

    boolean existsByUsernameAndUserStatus(String username, UserStatus userStatus);

    Optional<User> findByEmailAndUserStatus(String email, UserStatus userStatus);
}
