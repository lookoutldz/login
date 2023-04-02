package com.example.login.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.login.domain.User;
import com.example.login.domain.UserToken;
import com.example.login.service.UserService;
import com.example.login.mapper.UserMapper;
import com.example.login.utils.JwtTokenUtil;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

/**
* @author aiden
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-04-01 22:22:39
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public User createUser(User user) {
        baseMapper.insert(user);
        return user;
    }

    @Override
    public UserToken login(String username, String password) {
        User user = baseMapper.getLoginInfo(username);
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("用户名或密码不正确");
        }
        UserToken userToken = new UserToken();
        userToken.setUserid(user.getId().toString());
        userToken.setUsername(user.getUsername());

        return userToken;
    }
}




