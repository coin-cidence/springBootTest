package com.lgdx.example.service;
import com.lgdx.example.domain.User;
import com.lgdx.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User join (User user){ //register에서 사용

        //아이디 유효성 검사
        if (user.getUserId().length() > 10) {//userID.length <= 10
            System.out.println("ID는 최대 10자리입니다.");
            return null;
        }

        //아이디 유효성 검사
        if (user.getPassword().length() > 10) {//password.length <= 10
            System.out.println("비밀번호는 최대 10자리입니다.");
            return null;
        }

        User joinedUser = userRepository.save(user);
        if(joinedUser == null){
            return null;
        }
        return joinedUser;
    }

    public User loginCheck(String userId){
        Optional<User> loginedUser = userRepository.findById(userId);
        return loginedUser.orElse(null); //값, 없으면 null

    }

    public User login(String userId, String password){ //login에서 사용
        User user = userRepository.login(userId, password);
        return user;
    }

}
