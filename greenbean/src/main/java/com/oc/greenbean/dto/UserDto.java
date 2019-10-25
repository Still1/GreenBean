package com.oc.greenbean.dto;

import com.oc.greenbean.spring.validation.UserDtoPasswordEqualsConstraint;

import javax.validation.constraints.NotBlank;

@UserDtoPasswordEqualsConstraint
public class UserDto {

    //TODO 验证username password的长度
    //TODO 验证username password不包含空格

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    @SuppressWarnings("unused")
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
