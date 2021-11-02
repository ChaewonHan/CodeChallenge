package com.project.challenge.exception;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class ControllerException {

    /**
     * WebDataBinder의 기본값 할당 방법인 Java Bean 방식 대신
     * Field에 직접 접근하는 방식으로 설정한다.
    * */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }
}
