package com.example.login.utils;

import com.example.login.domain.UserToken;
import com.google.gson.Gson;

public class WebContextUtil {


    //本地线程缓存token
    private static final ThreadLocal<String> local = new ThreadLocal<>();


    /**
     * 设置token信息
     * @param content
     */
    public static void setUserToken(String content){
        removeUserToken();
        local.set(content);
    }


    /**
     * 获取token信息
     * @return
     */
    public static UserToken getUserToken(){
        if(local.get() != null){
            return new Gson().fromJson(local.get(), UserToken.class);
        }
        return null;
    }


    /**
     * 移除token信息
     * @return
     */
    public static void removeUserToken(){
        if(local.get() != null){
            local.remove();
        }
    }
}