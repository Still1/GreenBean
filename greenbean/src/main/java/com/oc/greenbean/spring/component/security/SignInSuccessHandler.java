package com.oc.greenbean.spring.component.security;

import com.oc.greenbean.domain.User;
import com.oc.greenbean.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SignInSuccessHandler implements AuthenticationSuccessHandler {

    private UserService userService;

    @Autowired
    public SignInSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    public SignInSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("userNickname", user.getNickname());
        session.setAttribute("userAvatar", user.getAvatar());

        httpServletResponse.sendRedirect("home");
    }
}
