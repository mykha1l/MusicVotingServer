package com.haw.mvsspring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.haw.mvsspring.dao.UserDao;
import com.haw.mvsspring.model.User;
import com.haw.mvsspring.model.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private AuthenticationManager authManager;

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

    public void login(UserDTO userDTO, HttpServletRequest req) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(userDTO.getUsername(),
                userDTO.getPassword());
        Authentication auth = authManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
    }

    public User getUser(final String username) {
        return userDao.getUser(username);
    }

}
