package com.example.petstorebootc51.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiInterceptor implements HandlerInterceptor {
    public static final String HEADER_X_API_TOKEN = "X-API-Token";

//    private final UserRepository userRepository;
//
//    public ApiInterceptor(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (userRepository.findByToken(request.getHeader(HEADER_X_API_TOKEN)).isPresent()) {
//            return true;
//        }
        return true;
//        response.setStatus(401);
//        return false;
    }
}
