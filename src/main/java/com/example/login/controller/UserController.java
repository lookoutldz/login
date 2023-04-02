package com.example.login.controller;

import com.example.login.annotation.JwtIgnore;
import com.example.login.domain.User;
import com.example.login.domain.UserToken;
import com.example.login.service.UserService;
import com.example.login.utils.JwtTokenUtil;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @JwtIgnore
    @PostMapping("/login")
    public Object login(@RequestBody User user, HttpServletResponse response) {
        if (user.getUsername() == null || user.getPassword() == null) {
            throw new RuntimeException("用户名或密码不正确");
        }
        UserToken userToken = userService.login(user.getUsername(), user.getPassword());
        String jwtToken = JwtTokenUtil.createToken(new Gson().toJson(userToken));
        response.addHeader(JwtTokenUtil.AUTH_HEADER_KEY, jwtToken);

        return "ok";
    }
}
