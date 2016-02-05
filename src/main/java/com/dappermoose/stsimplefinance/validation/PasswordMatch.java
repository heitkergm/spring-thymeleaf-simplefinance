package com.dappermoose.stsimplefinance.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// TODO: Auto-generated Javadoc
/**
 * The Interface PasswordMatch.
 */
@Retention (RetentionPolicy.RUNTIME)
@Target ({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Constraint (validatedBy = PasswordMatchImpl.class)
public @interface PasswordMatch
{
    /**
     * Message.
     *
     * @return the error message
     */
    String message() default "{notmatch.password}";

    /**
     * Groups.
     *
     * @return the groups
     */
    Class<?>[] groups() default {};

    /**
     * Payload.
     *
     * @return the payload
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * First field.
     *
     * @return the password
     */

    String password();

    /**
     * Second field.
     *
     * @return the repeated password
     */
    String repassword();
}
