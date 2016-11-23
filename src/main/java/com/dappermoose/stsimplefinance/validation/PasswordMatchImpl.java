package com.dappermoose.stsimplefinance.validation;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class PasswordMatchImpl.
 */
@Slf4j
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
     * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
     * Annotation)
     */
    @Override
    public void initialize (final PasswordMatch pm)
    {
        password = pm.password ();
        repassword = pm.repassword ();
        LOG.debug ("password is: " + password);
        LOG.debug ("repassword is: " + repassword);
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
     * javax.validation.ConstraintValidatorContext)
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
            LOG.debug ("password is " + ((String) pw));
            LOG.debug ("repeated password is " + ((String) rpw));
            return pw != null && rpw != null && pw.equals (rpw);
        }
        catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException iae)
        {
            return false;
        }
    }
}
