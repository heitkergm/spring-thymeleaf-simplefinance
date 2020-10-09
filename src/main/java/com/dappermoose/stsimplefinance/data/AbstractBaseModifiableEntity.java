package com.dappermoose.stsimplefinance.data;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * The BaseEntity class.
 */
@MappedSuperclass
@Getter
@Setter
@Log4j2
@EqualsAndHashCode (callSuper = true)
public abstract class AbstractBaseModifiableEntity extends AbstractBaseEntity
{
    private static final long serialVersionUID = 1446802725141477516L;

    /**
     * The modified timestamp.
     * 
     * @param modified the new value
     * @return the value of modified
     */
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
}
