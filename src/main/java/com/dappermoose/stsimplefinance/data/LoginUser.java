package com.dappermoose.stsimplefinance.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Check;

import org.mindrot.jbcrypt.BCrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginUser.
 */
@Entity
@Table (name = "USER", uniqueConstraints = @UniqueConstraint (columnNames = "USER_NAME"))
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE, region = "users")
@Check (constraints = "ENABLED IN ('Y', 'N')")
public class LoginUser extends AbstractBaseModifiableEntity
{
    private static final long serialVersionUID = 8751044063686185076L;
    
    private static final Logger LOG = LoggerFactory.getLogger (LoginUser.class);

    /** The user id. */
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
    @SequenceGenerator (name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    @Column (name = "USER_ID", nullable = false)
    private Long userId;

    /** The user name. */
    @Column (name = "USER_NAME", nullable = false, length = 32)
    private String userName;

    // passwords are stored as 60 character bcypt'd hashs
    /** The password. */
    @Column (name = "PASSWORD", nullable = false, length = 60)
    private String password;

    /** the time zone. */
    @Column (name = "TZONE", nullable = false, length = 128)
    private String tzone;

    /**
     * is this user enabled.
     */
    @Column (name = "ENABLED", nullable = false, length = 1)
    private YesNoEnum enabled;

    /**
     * Sets the timestamps.
     *<p>
     * Ensure that the time stored is ALWAYS GMT
     * and that the tzone is set to UTC if empty or null.
     * </p>
     */
    @Override
    @PrePersist
    @PreUpdate
    public void setupPersist ()
    {
        LOG.debug ("entering LoginUser.setupPersist");        
        super.setupPersist ();

        if (enabled == null)
        {
            enabled = YesNoEnum.YES;
        }
        LOG.debug ("leaving LoginUser.setupPersist");        
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public Long getUserId ()
    {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userIdNew the new user id
     */
    public void setUserId (final Long userIdNew)
    {
        userId = userIdNew;
    }

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
     * Validate the password.
     *
     * @param inputPassword - the supplied password
     * @return true/false - the password is correct
     */
    public boolean checkpw (final String inputPassword)
    {
        return BCrypt.checkpw (inputPassword, password);
    }

    /**
     * Sets the password.
     *
     * @param passwordNew the new password
     */
    public void setPassword (final String passwordNew)
    {
        password = BCrypt.hashpw (passwordNew, BCrypt.gensalt ());
    }

    /**
     * Gets the Time Zone.
     *
     * @return the time zone
     */
    public String getTzone ()
    {
        return tzone;
    }

    /**
     * Sets the time zone.
     *
     * @param tzoneNew the new tzone
     */
    public void setTzone (final String tzoneNew)
    {
        tzone = tzoneNew;
    }

    /**
     * Gets the enabled flag.
     *
     * @return the enabled flag
     */
    public YesNoEnum getEnabled ()
    {
        return enabled;
    }

    /**
     * Sets the enabled flag.
     *
     * @param enabledNew the new enabled flag
     */
    public void setEnabled (final YesNoEnum enabledNew)
    {
        enabled = enabledNew;
    }
}
