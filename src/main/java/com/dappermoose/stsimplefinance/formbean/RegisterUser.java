package com.dappermoose.stsimplefinance.formbean;

import javax.validation.constraints.Size;

import com.dappermoose.stsimplefinance.validation.PasswordMatch;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterUser.
 */
@PasswordMatch (password = "password", repassword = "repeatedPassword", message = "{register.pwd.notmatch}")
public class RegisterUser extends BaseFormBean
{
    @Size (min = 1, max = 32, message = "{login.user.size}")
    private String userName;

    // the password
    @Size (min = 1, max = 32, message = "{login.password.size}")
    protected String password;

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName ()
    {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userNameNew the new user name
     */
    public void setUserName (final String userNameNew)
    {
        userName = userNameNew;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword ()
    {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param passwordNew the new password
     */
    public void setPassword (final String passwordNew)
    {
        password = passwordNew;
    }

    /** The repeated password. */
    @Size (min = 1, max = 32, message = "{register.secondPassword.size}")
    private String repeatedPassword;

    /** the time zone. */
    @Size (min = 1, max = 128, message = "{register.tzone.size}")
    private String tzone;

    /**
     * Gets the repeated password.
     *
     * @return the repeated password
     */
    public String getRepeatedPassword ()
    {
        return repeatedPassword;
    }

    /**
     * Sets the repeated password.
     *
     * @param repeatedPasswordNew the new repeated password
     */
    public void setRepeatedPassword (final String repeatedPasswordNew)
    {
        repeatedPassword = repeatedPasswordNew;
    }

    /**
     * Get the time zone.
     *
     * @return the time zone
     */
    public String getTzone ()
    {
        return tzone;
    }

    /**
     * Set the time zone.
     *
     * @param tzoneNew the new value for the time zone
     */
    public void setTzone (final String tzoneNew)
    {
        tzone = tzoneNew;
    }
}
