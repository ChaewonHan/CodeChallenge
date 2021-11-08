package com.project.challenge.domain.user;

import com.project.challenge.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userNo;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(insertable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Builder
    public User(long userNo, String userId, String password, String email, String username, UserStatus userStatus) {
        this.userNo = userNo;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.username = username;
        this.userStatus = userStatus;
    }
}
