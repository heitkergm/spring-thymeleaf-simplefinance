package com.dappermoose.stsimplefinance.data;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.extern.slf4j.Slf4j;

/**
 * The BaseEntity class.
 */
@MappedSuperclass
@Slf4j
public abstract class AbstractBaseModifiableEntity extends AbstractBaseEntity
{
    private static final long serialVersionUID = 1446802725141477516L;

    // database will set a default value
    @Column (name = "MODIFIED_AT", nullable = false)
    private Instant modified;

    /**
     * Sets the timestamps.
     * <p>
     * Ensure that the time stored is ALWAYS GMT
     *</p>
     */
    @Override
    @PrePersist
    @PreUpdate
    public void setupPersist ()
    {
        log.debug ("entering AbtractBaseModifiableEntity.setupPersist");
        super.setupPersist ();
        if (modified == null)
        {
            modified = getCreated ();
        }
        else
        {
            modified = Instant.now ();
        }
        log.debug ("leaving AbtractBaseModifiableEntity.setupPersist");
    }

    /**
     * Gets the modified.
     *
     * @return the modified
     */
    public Instant getModified ()
    {
        return modified;
    }

    /**
     * Sets the modified.
     *
     * @param modifiedNew the new modified
     */
    public void setModified (final Instant modifiedNew)
    {
        modified = modifiedNew;
    }
}
