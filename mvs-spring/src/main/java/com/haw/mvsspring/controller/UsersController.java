package com.haw.mvsspring.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.haw.mvsspring.authentication.SecurityConfig;
import com.haw.mvsspring.model.User;
import com.haw.mvsspring.model.UserDTO;
import com.haw.mvsspring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping
@RestController
public class UsersController {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserService userService;

    @GetMapping("/api/v1/user")
    public User getUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String name = authentication.getName();
        if (name.equals("anonymousUser")) {
            return new User(name, null, false);
        }

        return userService.getUser(name);
    }

    @PostMapping("/api/v1/register")
    RedirectView registerUser(@RequestParam HashMap<String, Object> formData, HttpServletRequest req) {
        
        final String username = formData.get("username").toString();
        final String password = formData.get("password").toString();
        final String age = formData.get("age").toString();
        final String nationality = formData.get("nationality").toString();
        final String genre = formData.get("genre").toString();
        final UserDTO userDTO = new UserDTO(username, password);
        
        final User user = new User(username, securityConfig.passwordEncoder().encode(password), Integer.valueOf(age),
                nationality, genre, "ROLE_USER", true);
        userService.addUser(user);
        userService.login(userDTO, req);
        return new RedirectView("/index.html", false);
    }

    @PostMapping("/login")
    RedirectView login(@ModelAttribute final UserDTO userDTO, HttpServletRequest req) {
        userService.login(userDTO, req);
        return new RedirectView("/index.html", false);
    }

}
