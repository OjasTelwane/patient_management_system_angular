package com.bluepearlbiotech.patient.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.UUIDFilter;
import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.bluepearlbiotech.patient.domain.Lookup} entity. This class is used
 * in {@link com.bluepearlbiotech.patient.web.rest.LookupResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /lookups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LookupCriteria implements Serializable, Criteria {
  private static final long serialVersionUID = 1L;

  private UUIDFilter id;

  private StringFilter lookup;

  private StringFilter lookupType;

  private IntegerFilter lookupOrder;

  private StringFilter lookupNotes;

  private BooleanFilter voided;

  public LookupCriteria() {}

  public LookupCriteria(LookupCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.lookup = other.lookup == null ? null : other.lookup.copy();
    this.lookupType = other.lookupType == null ? null : other.lookupType.copy();
    this.lookupOrder = other.lookupOrder == null ? null : other.lookupOrder.copy();
    this.lookupNotes = other.lookupNotes == null ? null : other.lookupNotes.copy();
    this.voided = other.voided == null ? null : other.voided.copy();
  }

  @Override
  public LookupCriteria copy() {
    return new LookupCriteria(this);
  }

  public UUIDFilter getId() {
    return id;
  }

  public void setId(UUIDFilter id) {
    this.id = id;
  }

  public StringFilter getLookup() {
    return lookup;
  }

  public void setLookup(StringFilter lookup) {
    this.lookup = lookup;
  }

  public StringFilter getLookupType() {
    return lookupType;
  }

  public void setLookupType(StringFilter lookupType) {
    this.lookupType = lookupType;
  }

  public IntegerFilter getLookupOrder() {
    return lookupOrder;
  }

  public void setLookupOrder(IntegerFilter lookupOrder) {
    this.lookupOrder = lookupOrder;
  }

  public StringFilter getLookupNotes() {
    return lookupNotes;
  }

  public void setLookupNotes(StringFilter lookupNotes) {
    this.lookupNotes = lookupNotes;
  }

  public BooleanFilter getVoided() {
    return voided;
  }

  public void setVoided(BooleanFilter voided) {
    this.voided = voided;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final LookupCriteria that = (LookupCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(lookup, that.lookup) &&
      Objects.equals(lookupType, that.lookupType) &&
      Objects.equals(lookupOrder, that.lookupOrder) &&
      Objects.equals(lookupNotes, that.lookupNotes) &&
      Objects.equals(voided, that.voided)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lookup, lookupType, lookupOrder, lookupNotes, voided);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "LookupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (lookup != null ? "lookup=" + lookup + ", " : "") +
                (lookupType != null ? "lookupType=" + lookupType + ", " : "") +
                (lookupOrder != null ? "lookupOrder=" + lookupOrder + ", " : "") +
                (lookupNotes != null ? "lookupNotes=" + lookupNotes + ", " : "") +
                (voided != null ? "voided=" + voided + ", " : "") +
            "}";
    }
}
