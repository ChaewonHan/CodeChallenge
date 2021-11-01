package com.project.challenge.service.user;

import com.project.challenge.domain.user.User;
import com.project.challenge.dto.UserDto;
import com.project.challenge.exception.DuplicateException;
import com.project.challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원가입
    public String userSave(UserDto userDto){
        duplicateCheckId(userDto.getUserId());
        duplicateCheckEmail(userDto.getEmail());
        duplicateCheckName(userDto.getUsername());

        User user = userDto.toEntity();
        String saveId = userRepository.save(user).getUserId();

        return saveId;
    }

    /**
     * 탈퇴하지 않은 사용자의 아이디와 중복되면 exception 발생
     */
    public void duplicateCheckId(String userId){
        if (userRepository.existsByUserIdAndDeleteAccount(userId, false)) {
            throw new DuplicateException("이미 사용중인 아이디입니다.");
        }
    }

    /**
     * 탈퇴하지 않은 사용자의 아메일과 중복되면 exception 발생
     */
    public void duplicateCheckEmail(String email){
        if (userRepository.existsByEmailAndDeleteAccount(email, false)) {
            //To do: exception 처리 코드 작성하기
            System.out.println("이메일 중복");
        }
    }

    /**
     * 탈퇴하지 않은 사용자의 닉네임과 중복되면 exception 발생
     */
    public void duplicateCheckName(String name){
        if (userRepository.existsByUsernameAndDeleteAccount(name, false)) {
            //To do: exception 처리 코드 작성하기
            System.out.println("닉네임 중복");
        }
    }
}
