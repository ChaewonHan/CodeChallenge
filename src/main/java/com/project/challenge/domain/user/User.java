package com.project.challenge.domain.user;

import com.project.challenge.config.BooleanToYNConverter;
import com.project.challenge.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    //탈퇴 여부
    @Convert(converter = BooleanToYNConverter.class)
    @Column(nullable = false)
    private boolean deleteAccount;

    @Builder
    public User(long userNo, String userId, String password, String email, String username, boolean deleteAccount) {
        this.userNo = userNo;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.username = username;
        this.deleteAccount = deleteAccount;
    }
}
