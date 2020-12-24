package com.haw.mvsspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("password")
public class User {

    private String username;
    private String password;
    private int age;
    private String nationality;
    private String genre;
    private String authority;
    private boolean isEnabled;

    public User(final String username, final String password, final int age, final String nationality,
            final String genre, final String authority, final boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.nationality = nationality;
        this.genre = genre;
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

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    public String getGenre() {
        return genre;
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

    public void setAge(final int age) {
        this.age = age;
    }

    public void setNationality(final String nationality) {
        this.nationality = nationality;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public void setAuthority(final String authority) {
        this.authority = authority;
    }

    public void setEnabled(final boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

}
