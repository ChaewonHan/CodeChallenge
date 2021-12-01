package com.project.challenge.domain.user;

import com.project.challenge.domain.BaseEntity;
import com.project.challenge.domain.authentication.Authentication;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userNo;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(insertable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "user")
    private List<Authentication> authentication;

    @Builder
    public User(long userNo, String password, String email, String username, UserStatus userStatus) {
        this.userNo = userNo;
        this.password = password;
        this.email = email;
        this.username = username;
        this.userStatus = userStatus;
    }
}
