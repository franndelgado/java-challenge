package com.project.java_challenge.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistsByUsernameValidation.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByUsername {
    String message() default "Already exists in the database. Choose another username.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}