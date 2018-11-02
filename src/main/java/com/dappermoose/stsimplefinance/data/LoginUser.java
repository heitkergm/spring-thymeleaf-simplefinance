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
import org.hibernate.envers.Audited;

import org.mindrot.jbcrypt.BCrypt;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginUser.
 */
@Entity
@Table (name = "LOGIN_USER", uniqueConstraints = @UniqueConstraint (columnNames = "USER_NAME", name = "unique_user_name"))
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE, region = "users")
@Check (constraints = "ENABLED IN ('Y', 'N')")
@Slf4j
@Getter
@Setter
@EqualsAndHashCode (callSuper = true)
public class LoginUser extends AbstractBaseModifiableEntity
{
    private static final long serialVersionUID = 8751044063686185076L;

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
    @Audited
    @Column (name = "PASSWORD", nullable = false, length = 60)
    private String password;

    /** the time zone. */
    @Audited
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
     * Sets the user name.
     *
     * @param userNameNew the new user name
     */
    public void setUserName (final String userNameNew)
    {
        userName = userNameNew;
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
}
