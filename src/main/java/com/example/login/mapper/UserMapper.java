package com.example.login.mapper;

import com.example.login.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author aiden
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-04-01 22:22:39
* @Entity com.example.login.domain.User
*/
public interface UserMapper extends BaseMapper<User> {
    User getLoginInfo(String username);
}




