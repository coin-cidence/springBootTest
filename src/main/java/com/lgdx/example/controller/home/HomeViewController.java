package com.lgdx.example.controller.home;

import com.lgdx.example.service.UserService;
import com.lgdx.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeViewController {

    @Autowired
    UserService userService;

    @GetMapping("/")  //localhost:8090 접속하면 로그인 화면
    public String connect() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-process")
    public String loginProcess(@RequestParam("userId") String userId,
                               @RequestParam("password") String password, Model model){
        User user = userService.login(userId,password);
        if (user != null ){
            model.addAttribute("username",user.getName());
            return "/home";
        }else{
            model.addAttribute("error","존재하지 않는 사용자입니다.");
            return "/login";
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model){
        User joinedUser = userService.join(user);

        if (joinedUser != null){
            return "/login"; //성공 시 로그인 화면
        }else{
            model.addAttribute("error","회원가입 실패");
            return "/register"; //실패 시 오류멘트 후 회원가입창
        }
    }
}
