package com.bluepearlbiotech.patient.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.UUIDFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.bluepearlbiotech.patient.domain.Location}
 * entity. This class is used in
 * {@link com.bluepearlbiotech.patient.web.rest.LocationResource} to receive all
 * the possible filtering options from the Http GET request parameters. For
 * example the following could be a valid request:
 * {@code /locations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific
 * {@link Filter} class are used, we need to use fix type specific filters.
 */
public class LocationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private UUIDFilter id;

    private StringFilter country;

    private StringFilter countryCode;

    private StringFilter region;

    private StringFilter conState;

    private StringFilter stateCode;

    private StringFilter district;

    private StringFilter city;

    private StringFilter area;

    private StringFilter pincode;

    public LocationCriteria() {
    }

    public LocationCriteria(LocationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.country = other.country == null ? null : other.country.copy();
        this.countryCode = other.countryCode == null ? null : other.countryCode.copy();
        this.region = other.region == null ? null : other.region.copy();
        this.conState = other.conState == null ? null : other.conState.copy();
        this.stateCode = other.stateCode == null ? null : other.stateCode.copy();
        this.district = other.district == null ? null : other.district.copy();
        this.city = other.city == null ? null : other.city.copy();
        this.area = other.area == null ? null : other.area.copy();
        this.pincode = other.pincode == null ? null : other.pincode.copy();
    }

    @Override
    public LocationCriteria copy() {
        return new LocationCriteria(this);
    }

    public UUIDFilter getId() {
        return id;
    }

    public void setId(UUIDFilter id) {
        this.id = id;
    }

    public StringFilter getCountry() {
        return country;
    }

    public void setCountry(StringFilter country) {
        this.country = country;
    }

    public StringFilter getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(StringFilter countryCode) {
        this.countryCode = countryCode;
    }

    public StringFilter getRegion() {
        return region;
    }

    public void setRegion(StringFilter region) {
        this.region = region;
    }

    public StringFilter getConState() {
        return conState;
    }

    public void setConState(StringFilter conState) {
        this.conState = conState;
    }

    public StringFilter getStateCode() {
        return stateCode;
    }

    public void setStateCode(StringFilter stateCode) {
        this.stateCode = stateCode;
    }

    public StringFilter getDistrict() {
        return district;
    }

    public void setDistrict(StringFilter district) {
        this.district = district;
    }

    public StringFilter getCity() {
        return city;
    }

    public void setCity(StringFilter city) {
        this.city = city;
    }

    public StringFilter getArea() {
        return area;
    }

    public void setArea(StringFilter area) {
        this.area = area;
    }

    public StringFilter getPincode() {
        return pincode;
    }

    public void setPincode(StringFilter pincode) {
        this.pincode = pincode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LocationCriteria that = (LocationCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(country, that.country)
                && Objects.equals(countryCode, that.countryCode) && Objects.equals(region, that.region)
                && Objects.equals(conState, that.conState) && Objects.equals(stateCode, that.stateCode)
                && Objects.equals(district, that.district) && Objects.equals(city, that.city)
                && Objects.equals(area, that.area) && Objects.equals(pincode, that.pincode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, countryCode, region, conState, stateCode, district, city, area, pincode);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LocationCriteria{" + (id != null ? "id=" + id + ", " : "")
                + (country != null ? "country=" + country + ", " : "")
                + (countryCode != null ? "countryCode=" + countryCode + ", " : "")
                + (region != null ? "region=" + region + ", " : "")
                + (conState != null ? "conState=" + conState + ", " : "")
                + (stateCode != null ? "stateCode=" + stateCode + ", " : "")
                + (district != null ? "district=" + district + ", " : "") + (city != null ? "city=" + city + ", " : "")
                + (area != null ? "area=" + area + ", " : "") + (pincode != null ? "pincode=" + pincode + ", " : "")
                + "}";
    }

}
