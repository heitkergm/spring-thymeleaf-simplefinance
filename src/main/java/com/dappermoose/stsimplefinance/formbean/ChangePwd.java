package com.dappermoose.stsimplefinance.formbean;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class ChangePwd.
 */
@Getter
@Setter
public class ChangePwd extends RegisterUser
{
    private static final long serialVersionUID = 1L;

    /** The current password. */
    @Size (min = 1, max = 32, message = "{changePwd.currentPassword.size}")
    private String currentPassword;
}
