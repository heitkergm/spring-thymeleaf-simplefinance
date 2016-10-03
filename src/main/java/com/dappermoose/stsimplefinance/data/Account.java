package com.dappermoose.stsimplefinance.data;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
}
