package com.dappermoose.stsimplefinance.data;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class Account.
 */
@Entity
@Table (name = "ACCOUNT",
        indexes = @Index (columnList = "USER_ID", name = "ACCOUNT_FKEY_USER"))
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE, region = "accounts")
@Getter
@Setter
@EqualsAndHashCode (callSuper = true)
public class Account extends AbstractBaseModifiableEntity
{
    private static final long serialVersionUID = 7377180330885353950L;

    /**
     * The account id.
     *
     * @param accountId the new value
     * @return the value of the account ID
     */
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_ID_SEQ")
    @SequenceGenerator (name = "ACCOUNT_ID_SEQ", sequenceName = "ACCOUNT_ID_SEQ", allocationSize = 1)
    @Column (name = "ACCOUNT_ID", nullable = false, updatable = false)
    private Long accountId;

    /**
     * The user name.
     *
     * @param user the new value
     * @return the current user name.
     */
    @ManyToOne (optional = false)
    @JoinColumn (name = "USER_ID", nullable = false, updatable = false, foreignKey = @ForeignKey (name = "FK_ACCOUNT_USER"))
    private LoginUser user;

    /**
     * The starting balance.
     *
     * @param startingBalance the new value
     * @return the starting balance
     */
    @Column (name = "STARTING_BALANCE", nullable = false, precision = 10, scale = 2)
    private BigDecimal startingBalance;

    /**
     * The account name.
     *
     * @param accountName the new value
     * @return the account name
     */
    @Column (name = "ACCOUNT_NAME", nullable = false, length = 32)
    private String accountName;

    /**
     * The description.
     *
     * @param description the new value
     * @return the account description
     */
    @Column (name = "DESCRIPTION", nullable = false, length = 256)
    private String description;
}
