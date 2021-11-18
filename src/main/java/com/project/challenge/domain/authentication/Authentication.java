package com.project.challenge.domain.authentication;

import com.project.challenge.domain.challenge.Challenge;
import com.project.challenge.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Authentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long AuthenticationNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_no")
    private Challenge challenge;

    @Column(insertable = false)
    @Enumerated(EnumType.STRING)
    private AuthenticationStatus authenticationStatus;
}
