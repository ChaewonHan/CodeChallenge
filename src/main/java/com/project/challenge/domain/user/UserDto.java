package com.project.challenge.domain.user;

import com.project.challenge.domain.BaseEntity;
import com.project.challenge.domain.user.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


public class UserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class addUser {

        @Pattern(regexp = "[a-zA-Z1-9]{5,20}")
        private String userId;

        @Setter
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$")
        private String password;

        @NotBlank
        @Email
        private String email;

        @NotBlank
        @Length(max=10)
        private String username;

        @Builder
        public addUser(String userId, String password, String email, String username) {
            this.userId = userId;
            this.password = password;
            this.email = email;
            this.username = username;
        }

        public User toEntity() {
            return User.builder()
                    .userId(userId)
                    .password(password)
                    .email(email)
                    .username(username)
                    .build();
        }

    }


}
