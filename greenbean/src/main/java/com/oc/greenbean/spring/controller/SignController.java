package com.oc.greenbean.spring.controller;

import com.oc.greenbean.domain.User;
import com.oc.greenbean.mybatis.mapper.UserMapper;
import com.oc.greenbean.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SignController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String signIn() {
        return "signIn";
    }

    @RequestMapping("/signInError")
    public String signInError(Model model) {
        model.addAttribute("signInError", true);
        return "signIn";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(User user) {
        // XXX 这里的BCryptPasswordEncoder是否可以单例
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        String userAuthority = "USER";
        List<String> authority = new ArrayList<>();
        authority.add(userAuthority);
        user.setAuthority(authority);
        userService.insertUser(user);
        return "signUpSuccess";
    }
}
