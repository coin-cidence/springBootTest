package com.lgdx.example.controller.user;


import com.lgdx.example.domain.User;
import com.lgdx.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDataController {

    @Autowired
    UserService userService;

    @GetMapping("/checkUserId")
    public boolean checkUserId(@RequestParam(name="userId")String userId){
        System.out.println("사용자 중복 확인 요청 ID : "+userId);
        User user = userService.loginCheck(userId);

        if(user != null){
            return true;
        }else{
            return false;
        }
    }
}
