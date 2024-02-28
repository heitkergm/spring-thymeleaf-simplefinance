package com.dappermoose.stsimplefinance.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Check;
import org.hibernate.envers.Audited;

import org.mindrot.jbcrypt.BCrypt;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginUser.
 */
@Entity
@Table (name = "logIN_USER", uniqueConstraints = @UniqueConstraint (columnNames = "USER_NAME", name = "unique_user_name"))
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE, region = "users")
@Check (constraints = "ENABLED IN ('Y', 'N')")
@Log4j2
@Getter
@Setter
@EqualsAndHashCode (callSuper = true)
public class LoginUser extends AbstractBaseModifiableEntity
{
    private static final long serialVersionUID = 8751044063686185076L;

    /**
     *  The user id.
     *
     * @param userId The new value for the user ID.
     * @return the User ID.
     */
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
    @SequenceGenerator (name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    @Column (name = "USER_ID", nullable = false)
    private Long userId;

    /**
     * The user name.
     *
     * @return the user name.
     */
    @Column (name = "USER_NAME", nullable = false, length = 32)
    private String userName;

    // passwords are stored as 60 character bcypt'd hashs
    /**
     * The password.
     *
     * @return the encrypted password
     */
    @Audited
    @Column (name = "PASSWORD", nullable = false, length = 60)
    private String password;

    /**
     * the time zone.
     *
     * @param tzone the new value for the time zone.
     * @return the time zone
     */
    @Audited
    @Column (name = "TZONE", nullable = false, length = 128)
    private String tzone;

    /**
     * is this user enabled.
     *
     * @param enabled the new YesNo value for enabled.
     * @return the YesNo value for enabled.
     */
    @Column (name = "ENABLED", nullable = false, length = 1)
    private YesNoEnum enabled;

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
