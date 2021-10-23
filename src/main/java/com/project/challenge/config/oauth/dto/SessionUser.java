package com.project.challenge.config.oauth.dto;

import com.project.challenge.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

//인증된 사용자 정보를 가져오는 클래스
@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
