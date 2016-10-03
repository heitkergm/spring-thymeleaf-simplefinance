package com.dappermoose.stsimplefinance.data;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The BaseEntity class.
 */
@MappedSuperclass
@Slf4j
@Getter
@Setter
public abstract class AbstractBaseEntity implements Serializable
{
    private static final long serialVersionUID = 8250446686147979986L;

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
        log.debug ("entering AbtractBaseEntity.setupPersist");
        if (created == null)
        {
            created = Instant.now ();
        }
        log.debug ("leaving AbtractBaseEntity.setupPersist");
    }
}
