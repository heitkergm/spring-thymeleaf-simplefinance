package com.dappermoose.stsimplefinance.formbean;

import javax.validation.constraints.Size;

import com.dappermoose.stsimplefinance.validation.PasswordMatch;

import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterUser.
 */
@Getter
@Setter
@PasswordMatch (password = "password", repassword = "repeatedPassword", message = "{register.pwd.notmatch}")
public class RegisterUser extends BaseFormBean
{
    private static final long serialVersionUID = 1L;

    @Size (min = 1, max = 32, message = "{login.user.size}")
    private String userName;

    // the password
    @Size (min = 1, max = 32, message = "{login.password.size}")
    protected String password;

    /** The repeated password. */
    @Size (min = 1, max = 32, message = "{register.secondPassword.size}")
    private String repeatedPassword;

    /** the time zone. */
    @Size (min = 1, max = 128, message = "{register.tzone.size}")
    private String tzone;
}
