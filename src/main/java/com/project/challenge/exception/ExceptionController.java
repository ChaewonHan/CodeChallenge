package com.project.challenge.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionController {

   /* @ExceptionHandler(LoginFailException.class)
    public String loginFailException(Exception e, BindingResult result) {
        log.error("Exception: ", e);
        result.addError(new FieldError("field-error", "email", e.getMessage()));
        return "users/loginForm";
    }*/
}
