package com.project.challenge.config.interceptor;

import com.project.challenge.config.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURL = request.getRequestURI();
        HttpSession session = request.getSession(false);
        log.info("loginCheck interceptor: [{}]", requestURL);

        if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
            response.sendRedirect("/users/login?redirectURL=" + requestURL);
            return false;
        }
        return true;
    }
}
