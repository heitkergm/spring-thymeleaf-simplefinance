package com.dappermoose.stsimplefinance.formbean;

import jakarta.validation.constraints.Size;

import com.dappermoose.stsimplefinance.validation.PasswordMatch;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterUser.
 */
@Getter
@Setter
@EqualsAndHashCode (callSuper = true)
@PasswordMatch (password = "password", repassword = "repeatedPassword", message = "{register.pwd.notmatch}")
public class RegisterUser extends BaseFormBean
{
    private static final long serialVersionUID = 1L;

    /**
     * The user name.
     *
     * @param userName the new value
     * @return the user name
     */
    @Size (min = 1, max = 32, message = "{login.user.size}")
    private String userName;

    /**
     * the proposed password.
     *
     * @param password the new value
     * @return the value of the proposed password
     */
    @Size (min = 1, max = 32, message = "{login.password.size}")
    protected String password;

    /**
     * The repeated copy of the proposed password.
     *
     * @param repeatedPassword the new value
     * @return the proposed value of the password, repeated
     */
    @Size (min = 1, max = 32, message = "{register.secondPassword.size}")
    private String repeatedPassword;

    /**
     * the time zone.
     *
     * @param tzone the new value
     * @return the time zone value
     */
    @Size (min = 1, max = 128, message = "{register.tzone.size}")
    private String tzone;
}
