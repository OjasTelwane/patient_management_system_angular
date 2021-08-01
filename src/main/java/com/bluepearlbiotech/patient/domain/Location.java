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
 * Location Information.\n@company Blue Pearl Biotech Software.\n@author Santosh Telwane.\n@Copyright 2020 Blue Pearl Biotech Software.
 */
@ApiModel(description = "Location Information.\n@company Blue Pearl Biotech Software.\n@author Santosh Telwane.\n@Copyright 2020 Blue Pearl Biotech Software.")
@Entity
@Table(name = "iclocation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Location extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "country")
    private String country;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "region")
    private String region;

    @Column(name = "con_state")
    private String conState;

    @Size(max = 14)
    @Column(name = "state_code", length = 14)
    private String stateCode;

    @Column(name = "district")
    private String district;

    @Column(name = "city")
    private String city;

    @Column(name = "area")
    private String area;

    @Size(max = 14)
    @Column(name = "pincode", length = 14)
    private String pincode;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public Location country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Location countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegion() {
        return region;
    }

    public Location region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getConState() {
        return conState;
    }

    public Location conState(String conState) {
        this.conState = conState;
        return this;
    }

    public void setConState(String conState) {
        this.conState = conState;
    }

    public String getStateCode() {
        return stateCode;
    }

    public Location stateCode(String stateCode) {
        this.stateCode = stateCode;
        return this;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDistrict() {
        return district;
    }

    public Location district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public Location city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public Location area(String area) {
        this.area = area;
        return this;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPincode() {
        return pincode;
    }

    public Location pincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        return id != null && id.equals(((Location) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Location{" +
            "id=" + getId() +
            ", country='" + getCountry() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", region='" + getRegion() + "'" +
            ", conState='" + getConState() + "'" +
            ", stateCode='" + getStateCode() + "'" +
            ", district='" + getDistrict() + "'" +
            ", city='" + getCity() + "'" +
            ", area='" + getArea() + "'" +
            ", pincode='" + getPincode() + "'" +
            "}";
    }
}
