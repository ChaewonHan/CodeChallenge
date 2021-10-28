package com.project.challenge.repository;


import com.project.challenge.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserIdAndDeleteAccount(String userId, boolean yn);

    boolean existsByEmailAndDeleteAccount(String email, boolean yn);

    boolean existsByUsernameAndDeleteAccount(String username, boolean yn);

}
