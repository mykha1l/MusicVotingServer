package com.haw.mvsspring.service;

import java.util.List;

import com.haw.mvsspring.dao.UserDao;
import com.haw.mvsspring.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("sqliteUser") UserDao userDao) {
        userDao.init();
        this.userDao = userDao;
    }

    public int addUser(User user) {
        return userDao.insertUser(user);
    }

    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

}
