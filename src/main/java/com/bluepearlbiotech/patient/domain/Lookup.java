package com.bluepearlbiotech.patient.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import org.hibernate.annotations.Type;
import java.util.UUID;

/**
 * Lookup Information.\n@company Blue Pearl Biotech Software.\n@author Santosh Telwane.\n@Copyright 2020 Blue Pearl Biotech Software.
 */
@ApiModel(description = "Lookup Information.\n@company Blue Pearl Biotech Software.\n@author Santosh Telwane.\n@Copyright 2020 Blue Pearl Biotech Software.")
@Entity
@Table(name = "iclookup")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Lookup extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "lookup", nullable = false)
    private String lookup;

    @NotNull
    @Column(name = "lookup_type", nullable = false)
    private String lookupType;

    @Column(name = "lookup_order")
    private Integer lookupOrder;

    @Size(max = 4096)
    @Column(name = "lookup_notes", length = 4096)
    private String lookupNotes;

    @Column(name = "voided")
    private Boolean voided;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLookup() {
        return lookup;
    }

    public Lookup lookup(String lookup) {
        this.lookup = lookup;
        return this;
    }

    public void setLookup(String lookup) {
        this.lookup = lookup;
    }

    public String getLookupType() {
        return lookupType;
    }

    public Lookup lookupType(String lookupType) {
        this.lookupType = lookupType;
        return this;
    }

    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
    }

    public Integer getLookupOrder() {
        return lookupOrder;
    }

    public Lookup lookupOrder(Integer lookupOrder) {
        this.lookupOrder = lookupOrder;
        return this;
    }

    public void setLookupOrder(Integer lookupOrder) {
        this.lookupOrder = lookupOrder;
    }

    public String getLookupNotes() {
        return lookupNotes;
    }

    public Lookup lookupNotes(String lookupNotes) {
        this.lookupNotes = lookupNotes;
        return this;
    }

    public void setLookupNotes(String lookupNotes) {
        this.lookupNotes = lookupNotes;
    }

    public Boolean isVoided() {
        return voided;
    }

    public Lookup voided(Boolean voided) {
        this.voided = voided;
        return this;
    }

    public void setVoided(Boolean voided) {
        this.voided = voided;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lookup)) {
            return false;
        }
        return id != null && id.equals(((Lookup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Lookup{" +
            "id=" + getId() +
            ", lookup='" + getLookup() + "'" +
            ", lookupType='" + getLookupType() + "'" +
            ", lookupOrder=" + getLookupOrder() +
            ", lookupNotes='" + getLookupNotes() + "'" +
            ", voided='" + isVoided() + "'" +
            "}";
    }
}
