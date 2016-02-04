package com.dappermoose.stsimplefinance.data;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The BaseEntity class.
 */
@MappedSuperclass
public abstract class AbstractBaseModifiableEntity extends AbstractBaseEntity
{
    private static final long serialVersionUID = 1446802725141477516L;
    
    private static final Logger LOG = LoggerFactory.getLogger (AbstractBaseModifiableEntity.class);
    
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
        LOG.debug ("entering AbtractBaseModifiableEntity.setupPersist");
        super.setupPersist ();
        if (modified == null)
        {
            modified = getCreated ();
        }
        else
        {
            modified = Instant.now ();
        }
        LOG.debug ("leaving AbtractBaseModifiableEntity.setupPersist");
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
