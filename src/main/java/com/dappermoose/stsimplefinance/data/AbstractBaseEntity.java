package com.dappermoose.stsimplefinance.data;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * The BaseEntity class.
 */
@MappedSuperclass
@Log4j2
@Getter
@Setter
@EqualsAndHashCode
public abstract class AbstractBaseEntity implements Serializable
{
    private static final long serialVersionUID = 8250446686147979986L;

    /**
     * The created timestamp field.
     *
     * @param created new value
     * @return value of created
     */
    @Column (name = "CREATED_AT", nullable = false, updatable = false)
    private Instant created;

    /**
     * The version field.
     *
     * @param version new value
     * @return value of version
     */
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
}
