package com.dappermoose.stsimplefinance.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class PasswordMatchImpl.
 */
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
            return pw != null && rpw != null && pw.equals (rpw);
        }
        catch (final Exception ex)
        {
            return false;
        }
    }
}
