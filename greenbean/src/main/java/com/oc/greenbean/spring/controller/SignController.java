package com.oc.greenbean.spring.controller;

import com.oc.greenbean.domain.User;
import com.oc.greenbean.dto.UserDto;
import com.oc.greenbean.exception.UsernameDuplicatedException;
import com.oc.greenbean.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SignController {

    private UserService userService;

    @Autowired
    public SignController(UserService userService) {
        this.userService = userService;
    }

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
    public String signUp(@Validated UserDto userDto, BindingResult bindingResult, HttpServletResponse response) {
        String viewName;
        if(!bindingResult.hasErrors()) {
            User user = this.generateUser(userDto);
            userService.insertUser(user);
            viewName = "signUpSuccess";
        } else {
            response.setStatus(403);
            viewName = "signUpFail";
        }
        return viewName;
    }

    private String encode(String password) {
        // XXX 这里的BCryptPasswordEncoder是否可以单例
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        return encodedPassword;
    }

    private User generateUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        String password = userDto.getPassword();
        String encodedPassword = this.encode(password);
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        String userAuthority = "USER";
        List<String> authority = new ArrayList<>();
        authority.add(userAuthority);
        user.setAuthority(authority);
        return user;
    }

    @RequestMapping(value = "/signUp/validateUsername", method = RequestMethod.GET)
    @ResponseBody
    public String signUpValidateUsername(String username) {
        boolean result = userService.validateUsernameDuplicated(username);
        return String.valueOf(result);
    }

    @ExceptionHandler(UsernameDuplicatedException.class)
    public String handleUsernameDuplicatedException(HttpServletResponse response) {
        response.setStatus(403);
        return "signUpFail";
    }
}
