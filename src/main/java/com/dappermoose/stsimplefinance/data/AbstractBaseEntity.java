package com.dappermoose.stsimplefinance.data;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The BaseEntity class.
 */
@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable
{
    private static final long serialVersionUID = 8250446686147979986L;
    
    private static final Logger LOG = LoggerFactory.getLogger (AbstractBaseEntity.class);

    /** The created. */
    @Column (name = "CREATED_AT", nullable = false, updatable = false)
    private Instant created;

    @Version
    @Column (name = "VERSION", nullable = false)
    private Long version;

    /**
     * Sets the timestamps.
     *<p>
     * Ensure that the time stored is ALWAYS GMT
     *</p>
     */
    @PrePersist
    @PreUpdate
    public void setupPersist ()
    {
        LOG.debug ("entering AbtractBaseEntity.setupPersist");
        if (created == null)
        {
            created = Instant.now ();
        }
        LOG.debug ("leaving AbtractBaseEntity.setupPersist");
    }

    /**
     * Gets the created.
     *
     * @return the created
     */
    public Instant getCreated ()
    {
        return created;
    }

    /**
     * Sets the created.
     *
     * @param createdNew the new created
     */
    public void setCreated (final Instant createdNew)
    {
        created = createdNew;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public Long getVersion ()
    {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param versionNew the new version
     */
    public void setVersion (final Long versionNew)
    {
        version = versionNew;
    }
}
