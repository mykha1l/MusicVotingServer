package com.haw.mvsspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("password")
public class User {

    private String username;
    private String password;
    private String authority;
    private boolean isEnabled;

    public User (String username, String password, String authority, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.isEnabled = isEnabled;
    }

    public User(String username, String password, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
    }
    
    public User() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthority() {
        return authority;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setAuthority(final String authority) {
        this.authority = authority;
    }

    public void setEnabled(final boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

}
