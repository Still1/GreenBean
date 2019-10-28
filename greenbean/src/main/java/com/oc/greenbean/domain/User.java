package com.oc.greenbean.domain;

import java.util.List;
import java.util.Objects;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
    private List<String> authority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getAuthority() {
        return authority;
    }

    public void setAuthority(List<String> authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
            Objects.equals(getUsername(), user.getUsername()) &&
            Objects.equals(getPassword(), user.getPassword()) &&
            Objects.equals(getEnabled(), user.getEnabled()) &&
            Objects.equals(getAuthority(), user.getAuthority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getEnabled(), getAuthority());
    }
}
