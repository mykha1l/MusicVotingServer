package com.haw.mvsspring.controller;

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
    RedirectView registerUser(@ModelAttribute final UserDTO userDTO, HttpServletRequest req) {
        final User user = new User(userDTO.getUsername(),
                securityConfig.passwordEncoder().encode(userDTO.getPassword()), userDTO.getAge(),
                userDTO.getNationality(), userDTO.getGenre(), "ROLE_USER", true);
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
