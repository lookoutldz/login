package com.example.login.interceptor;

import com.example.login.annotation.JwtIgnore;
import com.example.login.utils.JwtTokenUtil;
import com.example.login.utils.WebContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        log.warn("preHandle, token:" + token);
        //如果不是映射到方法，直接通过
        if(!(handler instanceof HandlerMethod handlerMethod)){
            return true;
        }
        //如果是方法探测，直接通过
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        //如果方法有JwtIgnore注解，直接通过
        Method method=handlerMethod.getMethod();
        if (method.isAnnotationPresent(JwtIgnore.class)) {
            JwtIgnore jwtIgnore = method.getAnnotation(JwtIgnore.class);
            if(jwtIgnore.value()){
                return true;
            }
        }
        if (token == null || token.trim().equals("")) {
            log.warn("token为空，鉴权失败！");
            return false;
        }
//        LocalAssert.isStringEmpty(token, "token为空，鉴权失败！");
        //验证，并获取token内部信息
        String userToken = JwtTokenUtil.verifyToken(token);

        //将token放入本地缓存
        WebContextUtil.setUserToken(userToken);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //方法结束后，移除缓存的token
        WebContextUtil.removeUserToken();
        log.warn("afterHandle, removeUserToken");
    }
}
