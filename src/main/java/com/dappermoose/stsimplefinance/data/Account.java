package com.dappermoose.stsimplefinance.data;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

// TODO: Auto-generated Javadoc
/**
 * The Class Account.
 */
@Entity
@Table (name = "ACCOUNT")
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE, region = "accounts")
public class Account extends AbstractBaseModifiableEntity
{
    private static final long serialVersionUID = 7377180330885353950L;

    /** The account id. */
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_ID_SEQ")
    @SequenceGenerator (name = "ACCOUNT_ID_SEQ", sequenceName = "ACCOUNT_ID_SEQ", allocationSize = 1)
    @Column (name = "ACCOUNT_ID", nullable = false, updatable = false)
    private Long accountId;

    /** The user. */
    @ManyToOne (optional = false)
    @JoinColumn (name = "USER_ID", nullable = false, updatable = false, foreignKey = @ForeignKey (name = "FK_ACCOUNT_USER"))
    private LoginUser user;

    /** The starting balance. */
    @Column (name = "STARTING_BALANCE", nullable = false, precision = 10, scale = 2)
    private BigDecimal startingBalance;

    /** The account name. */
    @Column (name = "ACCOUNT_NAME", nullable = false, length = 32)
    private String accountName;

    /** The description. */
    @Column (name = "DESCRIPTION", nullable = false, length = 256)
    private String description;

    /**
     * Gets the account id.
     *
     * @return the accountId
     */
    public Long getAccountId ()
    {
        return accountId;
    }

    /**
     * Sets the account id.
     *
     * @param accountIdNew the accountId to set
     */
    public void setAccountId (final Long accountIdNew)
    {
        accountId = accountIdNew;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public LoginUser getUser ()
    {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param userNew the user to set
     */
    public void setUser (final LoginUser userNew)
    {
        user = userNew;
    }

    /**
     * Gets the starting balance.
     *
     * @return the startingBalance
     */
    public BigDecimal getStartingBalance ()
    {
        return startingBalance;
    }

    /**
     * Sets the starting balance.
     *
     * @param startingBalanceNew the startingBalance to set
     */
    public void setStartingBalance (final BigDecimal startingBalanceNew)
    {
        startingBalance = startingBalanceNew;
    }

    /**
     * Gets the account name.
     *
     * @return the accountName
     */
    public String getAccountName ()
    {
        return accountName;
    }

    /**
     * Sets the account name.
     *
     * @param accountNameNew the accountName to set
     */
    public void setAccountName (final String accountNameNew)
    {
        accountName = accountNameNew;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription ()
    {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param descriptionNew the description to set
     */
    public void setDescription (final String descriptionNew)
    {
        description = descriptionNew;
    }
}
