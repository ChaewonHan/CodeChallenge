package com.project.challenge.repository;


import com.project.challenge.domain.user.User;
import com.project.challenge.domain.user.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailAndUserStatus(String email, UserStatus userStatus);

    boolean existsByUsernameAndUserStatus(String username, UserStatus userStatus);

    Optional<User> findByEmail(String email, UserStatus userStatus);
}
