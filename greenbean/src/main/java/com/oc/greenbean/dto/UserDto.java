package com.oc.greenbean.dto;

import com.oc.greenbean.spring.validation.UserDtoPasswordEqualsConstraint;
import com.oc.greenbean.spring.validation.UserDtoUsernameNotExistsConstraint;

import javax.validation.constraints.NotBlank;

@UserDtoPasswordEqualsConstraint
public class UserDto {

//TODO 验证username password的长度

    @NotBlank
    //XXX 用户名重复验证应该转移到service层
    @UserDtoUsernameNotExistsConstraint
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

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
