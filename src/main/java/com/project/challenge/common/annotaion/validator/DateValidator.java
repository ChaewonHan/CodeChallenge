package com.project.challenge.common.annotaion.validator;

import com.project.challenge.common.annotaion.DateCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<DateCheck, LocalDate> {

    @Override
    public void initialize(DateCheck constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().plusMonths(1))) {
            return false;
        }
        return true;
    }
}
