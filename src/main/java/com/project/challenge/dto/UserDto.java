package com.project.challenge.dto;

import com.project.challenge.domain.BaseEntity;
import com.project.challenge.domain.user.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto extends BaseEntity {

    @Pattern(regexp = "[a-zA-Z1-9]{5,20}")
    private String userId;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$")
    private String password;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(max=10)
    private String username;

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
