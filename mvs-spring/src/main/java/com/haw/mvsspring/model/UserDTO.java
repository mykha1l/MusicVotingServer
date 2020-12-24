package com.haw.mvsspring.model;

public class UserDTO {

    private String username, password, nationality, genre;
    private int age;

    public UserDTO(final String username, final String password, final int age, final String nationality,
            final String genre) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.nationality = nationality;
        this.genre = genre;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getAge() {
        return this.age;
    }

    public String getNationality() {
        return this.nationality;
    }

    public String getGenre() {
        return this.genre;
    }

}
