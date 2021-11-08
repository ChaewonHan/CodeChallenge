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

        @NotBlank
        @Email
        private String email;

        @Setter
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$")
        private String password;

        @NotBlank
        @Length(max = 10)
        private String username;

        @Builder
        public addUser(String password, String email, String username) {
            this.password = password;
            this.email = email;
            this.username = username;
        }

        public User toEntity() {
            return User.builder()
                    .password(password)
                    .email(email)
                    .username(username)
                    .build();
        }

    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class loginUser {

        @NotBlank
        private String email;

        @Setter
        @NotBlank
        private String password;
    }
}
