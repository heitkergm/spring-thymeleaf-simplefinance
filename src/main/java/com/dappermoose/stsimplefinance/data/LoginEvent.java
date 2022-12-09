package com.dappermoose.stsimplefinance.data;

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

import org.hibernate.annotations.Check;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginEvent.
 */
@Entity
@Table (name = "LOGIN_EVENT",
        indexes = @Index (columnList = "USER_ID", name = "LOGIN_EVENT_FKEY_USER"))
@Check (constraints = "SUCCESS IN ('Y', 'N')")
@Getter
@Setter
@EqualsAndHashCode (callSuper = true)
public class LoginEvent extends AbstractBaseEntity
{
    private static final long serialVersionUID = -5740191601882965493L;

    /**
     * The login event ID.
     *
     * @param loginEventId the new value
     * @return the login event ID
     */
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "LOGIN_EVENT_ID_SEQ")
    @SequenceGenerator (name = "LOGIN_EVENT_ID_SEQ", sequenceName = "LOGIN_EVENT_ID_SEQ", allocationSize = 1)
    @Column (name = "LOGIN_EVENT_ID", nullable = false, updatable = false)
    private Long loginEventId;

    /**
     * The user.
     *
     * @param user the new value
     * @return the user record
     */
    @ManyToOne (optional = false)
    @JoinColumn (name = "USER_ID", nullable = false, updatable = false, foreignKey = @ForeignKey (name = "FK_LOGIN_EVENT_USER"))
    private LoginUser user;

    /**
     * The success flag.
     *
     * @param success the new value
     * @return the success flag YES or NO
     */
    @Column (name = "SUCCESS", nullable = false, length = 1, updatable = false)
    private YesNoEnum success;
}
