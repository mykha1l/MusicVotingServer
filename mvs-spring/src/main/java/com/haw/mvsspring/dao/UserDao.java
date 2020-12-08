package com.haw.mvsspring.dao;

import java.util.List;

import com.haw.mvsspring.model.User;

public interface UserDao {
    
    int insertUser(User user);

    List<User> getAllUsers();

    int init();

}
