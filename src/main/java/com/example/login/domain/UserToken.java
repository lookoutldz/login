package com.example.login.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserToken implements Serializable {

    private String userid;

    private String username;
}
