package com.haw.mvsspring.model;

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
    
}
