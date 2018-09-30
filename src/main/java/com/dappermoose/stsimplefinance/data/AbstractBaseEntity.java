package com.dappermoose.stsimplefinance.data;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import lombok.EqualsAndHashCode;
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
