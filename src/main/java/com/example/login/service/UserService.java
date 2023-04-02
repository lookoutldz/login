package com.example.login.service;

import com.example.login.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.login.domain.UserToken;

/**
* @author aiden
* @description 针对表【user】的数据库操作Service
* @createDate 2023-04-01 22:22:39
*/
public interface UserService extends IService<User> {

    User createUser(User user);

    UserToken login(String username, String password);
}
