package com.kaba4cow.pastex.domain.paste.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Positive;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, PARAMETER, TYPE_USE })
@Constraint(validatedBy = {})
@Positive(message = "Expiration minutes must be positive")
public @interface ValidExpiration {

	String message() default "Invalid expiration";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
