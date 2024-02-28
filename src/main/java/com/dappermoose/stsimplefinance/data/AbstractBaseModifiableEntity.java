package com.dappermoose.stsimplefinance.data;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import org.springframework.data.annotation.LastModifiedDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The BaseEntity class.
 */
@MappedSuperclass
@Getter
@Setter
@Slf4j
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
    @LastModifiedDate
    @Column (name = "MODIFIED_AT", nullable = false)
    private Instant modified;
}
