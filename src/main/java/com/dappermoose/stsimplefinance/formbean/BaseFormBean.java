package com.dappermoose.stsimplefinance.formbean;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class BaseFormBean.
 */
@Getter
@Setter
@EqualsAndHashCode
public class BaseFormBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /**
     * The nonce.
     * 
     * @param nonce the new value
     * @return the nonce value
     */
    private String nonce;
}
