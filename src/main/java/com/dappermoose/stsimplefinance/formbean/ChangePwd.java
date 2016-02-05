package com.dappermoose.stsimplefinance.formbean;

import javax.validation.constraints.Size;

// TODO: Auto-generated Javadoc
/**
 * The Class ChangePwd.
 */
public class ChangePwd extends RegisterUser
{
    /** The current password. */
    @Size (min = 1, max = 32, message = "{changePwd.currentPassword.size}")
    private String currentPassword;
    
    /**
     * Gets the current password.
     *
     * @return the current password
     */
    public String getCurrentPassword ()
    {
        return currentPassword;
    }

    /**
     * Sets the current password.
     *
     * @param currentPasswordNew the new current password
     */
    public void setCurrentPassword (final String currentPasswordNew)
    {
        currentPassword = currentPasswordNew;
    }
}
