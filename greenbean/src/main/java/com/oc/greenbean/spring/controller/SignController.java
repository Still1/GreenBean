package com.oc.greenbean.spring.controller;

import com.oc.greenbean.domain.User;
import com.oc.greenbean.dto.UserDto;
import com.oc.greenbean.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String signUp(@Validated UserDto userDto, BindingResult bindingResult) {
        String viewName = null;
        if(!bindingResult.hasErrors()) {
            // XXX 这里的BCryptPasswordEncoder是否可以单例
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = userDto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);

            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(encodedPassword);
            user.setEnabled(true);
            String userAuthority = "USER";
            List<String> authority = new ArrayList<>();
            authority.add(userAuthority);
            user.setAuthority(authority);
            userService.insertUser(user);
            viewName = "signUpSuccess";
        } else {
            viewName = "signUpFail";
        }
        return viewName;
    }

    @RequestMapping(value = "/signUp/validateUsername", method = RequestMethod.GET)
    @ResponseBody
    public String signUpValidateUsername(String username) {
        boolean result = userService.validateUsername(username);
        return String.valueOf(result);
    }
}
