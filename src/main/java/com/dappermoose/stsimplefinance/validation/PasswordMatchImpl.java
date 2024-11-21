package com.dappermoose.stsimplefinance.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class PasswordMatchImpl.
 */
@Slf4j
public class PasswordMatchImpl implements ConstraintValidator<PasswordMatch, Object>
{
    /** The password. */
    private String password;

    /** The repassword. */
    private String repassword;

    /*
     * (non-Javadoc)
     *
     * @see jakarta.validation.ConstraintValidator#initialize(java.lang.annotation.
     * Annotation)
     */
    @Override
    public void initialize (final PasswordMatch pm)
    {
        password = pm.password ();
        repassword = pm.repassword ();
        log.debug ("password is: " + password);
        log.debug ("repassword is: " + repassword);
    }

    /*
     * (non-Javadoc)
     *
     * @see jakarta.validation.ConstraintValidator#isValid(java.lang.Object,
     * jakarta.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid (final Object obj, final ConstraintValidatorContext ctx)
    {
        // get field value

        BeanWrapper bean = PropertyAccessorFactory.forBeanPropertyAccess (obj);
        final Object pw = bean.getPropertyValue (password);
        final Object rpw = bean.getPropertyValue (repassword);
        log.debug ("password is " + ((String) pw));
        log.debug ("repeated password is " + ((String) rpw));
        if (pw == null || rpw == null)
        {
            return false;
        }
        return pw.equals (rpw);
    }
}
