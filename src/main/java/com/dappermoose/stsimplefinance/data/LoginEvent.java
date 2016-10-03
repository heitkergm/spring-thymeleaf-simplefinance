package com.dappermoose.stsimplefinance.data;

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

import org.hibernate.annotations.Check;

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
public class LoginEvent extends AbstractBaseEntity
{
    private static final long serialVersionUID = -5740191601882965493L;

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "LOGIN_EVENT_ID_SEQ")
    @SequenceGenerator (name = "LOGIN_EVENT_ID_SEQ", sequenceName = "LOGIN_EVENT_ID_SEQ", allocationSize = 1)
    @Column (name = "LOGIN_EVENT_ID", nullable = false, updatable = false)
    private Long loginEventId;

    @ManyToOne (optional = false)
    @JoinColumn (name = "USER_ID", nullable = false, updatable = false, foreignKey = @ForeignKey (name = "FK_LOGIN_EVENT_USER"))
    private LoginUser user;

    @Column (name = "SUCCESS", nullable = false, length = 1, updatable = false)
    private YesNoEnum success;
}
