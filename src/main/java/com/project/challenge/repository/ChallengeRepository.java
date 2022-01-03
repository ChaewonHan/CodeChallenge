package com.project.challenge.repository;

import com.project.challenge.domain.challenge.Challenge;
import com.project.challenge.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

}
