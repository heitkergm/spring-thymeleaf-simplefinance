package com.dappermoose.stsimplefinance.validation;

import java.lang.reflect.InvocationTargetException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import lombok.extern.log4j.Log4j2;

// TODO: Auto-generated Javadoc
/**
 * The Class PasswordMatchImpl.
 */
@Log4j2
public class PasswordMatchImpl implements
    ConstraintValidator<PasswordMatch, Object>
{
    /** The password. */
    private String password;

    /** The repassword. */
    private String repassword;

    /*
     * (non-Javadoc)
     *
     * @see
     * jakarta.validation.ConstraintValidator#initialize(java.lang.annotation.
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
    public boolean isValid (final Object obj,
            final ConstraintValidatorContext ctx)
    {
        try
        {
            // get field value
            final Object pw = BeanUtils.getProperty (obj, password);
            final Object rpw = BeanUtils.getProperty (obj, repassword);
            log.debug ("password is " + ((String) pw));
            log.debug ("repeated password is " + ((String) rpw));
            return pw != null && rpw != null && pw.equals (rpw);
        }
        catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException iae)
        {
            return false;
        }
    }
}
