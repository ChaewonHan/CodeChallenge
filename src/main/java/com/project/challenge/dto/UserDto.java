package com.project.challenge.dto;

import com.project.challenge.domain.BaseEntity;
import com.project.challenge.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDto extends BaseEntity {

    private String userId;
    private String password;
    private String email;
    private String username;

    public UserDto(String userId, String password, String email, String username) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.username = username;
    }

    @Builder
    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .email(email)
                .username(username)
                .build();
    }
}
