package com.project.challenge.common.annotaion;

import com.project.challenge.common.annotaion.validator.DateValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface DateCheck {
    String message() default "";
    Class[] groups() default {};
    Class[] payload() default {};
}
