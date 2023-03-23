package com.vti.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Documented
@Target({FIELD,PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameNotExistsValidator.class)
public @interface UsernameNotExists {
    String message() default "{Account.createAccount.form.username.NotExists}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
