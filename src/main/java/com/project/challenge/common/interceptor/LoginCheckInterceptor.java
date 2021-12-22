package com.project.challenge.common.interceptor;

import com.project.challenge.common.annotaion.LoginCheck;
import com.project.challenge.common.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURL = request.getRequestURI();
        HttpSession session = request.getSession(false);
        log.info("loginCheck interceptor: [{}]", requestURL);

        if (handler instanceof HandlerMethod) {
            //HandlerMethod: 실행할 컨트롤러의 메서드
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);

            if (loginCheck == null) {
                return true;
            }

            if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
                response.sendRedirect("/users/login?redirectURL=" + requestURL);
                return false;
            }
        }
        return true;
    }
}
