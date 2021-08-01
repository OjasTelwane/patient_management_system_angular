package com.bluepearlbiotech.patient.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.bluepearlbiotech.patient.domain.enumeration.Gender;
import com.bluepearlbiotech.patient.domain.enumeration.DiscountType;
import com.bluepearlbiotech.patient.domain.enumeration.HealingLevel;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.UUIDFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.UUIDFilter;

/**
 * Criteria class for the {@link com.bluepearlbiotech.patient.domain.Patient}
 * entity. This class is used in
 * {@link com.bluepearlbiotech.patient.web.rest.PatientResource} to receive all
 * the possible filtering options from the Http GET request parameters. For
 * example the following could be a valid request:
 * {@code /patients?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific
 * {@link Filter} class are used, we need to use fix type specific filters.
 */
public class PatientCriteria implements Serializable, Criteria {
    /**
     * Class for filtering Gender
     */
    public static class GenderFilter extends Filter<Gender> {

        public GenderFilter() {
        }

        public GenderFilter(GenderFilter filter) {
            super(filter);
        }

        @Override
        public GenderFilter copy() {
            return new GenderFilter(this);
        }

    }

    /**
     * Class for filtering DiscountType
     */
    public static class DiscountTypeFilter extends Filter<DiscountType> {

        public DiscountTypeFilter() {
        }

        public DiscountTypeFilter(DiscountTypeFilter filter) {
            super(filter);
        }

        @Override
        public DiscountTypeFilter copy() {
            return new DiscountTypeFilter(this);
        }

    }

    /**
     * Class for filtering HealingLevel
     */
    public static class HealingLevelFilter extends Filter<HealingLevel> {

        public HealingLevelFilter() {
        }

        public HealingLevelFilter(HealingLevelFilter filter) {
            super(filter);
        }

        @Override
        public HealingLevelFilter copy() {
            return new HealingLevelFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private UUIDFilter id;

    private StringFilter formNumber;

    private UUIDFilter centerId;

    private StringFilter centerName;

    private StringFilter firstName;

    private StringFilter middleName;

    private StringFilter lastName;

    private StringFilter displayName;

    private InstantFilter dateOfBirth;

    private GenderFilter gender;

    private IntegerFilter age;

    private StringFilter address;

    private StringFilter area;

    private StringFilter city;

    private StringFilter district;

    private StringFilter conState;

    private StringFilter stateCode;

    private StringFilter region;

    private StringFilter country;

    private StringFilter countryCode;

    private StringFilter pincode;

    private StringFilter mobileNumber;

    private StringFilter contactNo;

    private StringFilter education;

    private StringFilter bloodGroup;

    private StringFilter email;

    private InstantFilter regDate;

    private StringFilter profession;

    private StringFilter marriageStatus;

    private BooleanFilter expired;

    private StringFilter languageName;

    private BooleanFilter vegNonVeg;

    private StringFilter referedBy;

    private StringFilter referdByName;

    private BooleanFilter exerciseRegularly;

    private BooleanFilter addiction;

    private IntegerFilter hoursSleep;

    private BooleanFilter wakeRested;

    private IntegerFilter waterIntake;

    private IntegerFilter hoursWork;

    private BooleanFilter shiftWork;

    private StringFilter levelOfStress;

    private StringFilter patientCondition;

    private StringFilter currentMedication;

    private StringFilter chronologicalHistory;

    private StringFilter symptomaticChanges;

    private IntegerFilter levelOfDiscomfort;

    private BooleanFilter feesDiscount;

    private StringFilter feesDiscountGivenBy;

    private DiscountTypeFilter discountType;

    private BigDecimalFilter discount;

    private BooleanFilter allowEmail;

    private BooleanFilter allowSMS;

    private BooleanFilter allowWhatsApp;

    private BooleanFilter allowPromotions;

    private BooleanFilter iAgree;

    private HealingLevelFilter healingLevel;

    private BooleanFilter allowLogin;

    private StringFilter password;

    private BooleanFilter voided;

    private UUIDFilter userId;

    public PatientCriteria() {
    }

    public PatientCriteria(PatientCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.formNumber = other.formNumber == null ? null : other.formNumber.copy();
        this.centerId = other.centerId == null ? null : other.centerId.copy();
        this.centerName = other.centerName == null ? null : other.centerName.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.middleName = other.middleName == null ? null : other.middleName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.displayName = other.displayName == null ? null : other.displayName.copy();
        this.dateOfBirth = other.dateOfBirth == null ? null : other.dateOfBirth.copy();
        this.gender = other.gender == null ? null : other.gender.copy();
        this.age = other.age == null ? null : other.age.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.area = other.area == null ? null : other.area.copy();
        this.city = other.city == null ? null : other.city.copy();
        this.district = other.district == null ? null : other.district.copy();
        this.conState = other.conState == null ? null : other.conState.copy();
        this.stateCode = other.stateCode == null ? null : other.stateCode.copy();
        this.region = other.region == null ? null : other.region.copy();
        this.country = other.country == null ? null : other.country.copy();
        this.countryCode = other.countryCode == null ? null : other.countryCode.copy();
        this.pincode = other.pincode == null ? null : other.pincode.copy();
        this.mobileNumber = other.mobileNumber == null ? null : other.mobileNumber.copy();
        this.contactNo = other.contactNo == null ? null : other.contactNo.copy();
        this.education = other.education == null ? null : other.education.copy();
        this.bloodGroup = other.bloodGroup == null ? null : other.bloodGroup.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.regDate = other.regDate == null ? null : other.regDate.copy();
        this.profession = other.profession == null ? null : other.profession.copy();
        this.marriageStatus = other.marriageStatus == null ? null : other.marriageStatus.copy();
        this.expired = other.expired == null ? null : other.expired.copy();
        this.languageName = other.languageName == null ? null : other.languageName.copy();
        this.vegNonVeg = other.vegNonVeg == null ? null : other.vegNonVeg.copy();
        this.referedBy = other.referedBy == null ? null : other.referedBy.copy();
        this.referdByName = other.referdByName == null ? null : other.referdByName.copy();
        this.exerciseRegularly = other.exerciseRegularly == null ? null : other.exerciseRegularly.copy();
        this.addiction = other.addiction == null ? null : other.addiction.copy();
        this.hoursSleep = other.hoursSleep == null ? null : other.hoursSleep.copy();
        this.wakeRested = other.wakeRested == null ? null : other.wakeRested.copy();
        this.waterIntake = other.waterIntake == null ? null : other.waterIntake.copy();
        this.hoursWork = other.hoursWork == null ? null : other.hoursWork.copy();
        this.shiftWork = other.shiftWork == null ? null : other.shiftWork.copy();
        this.levelOfStress = other.levelOfStress == null ? null : other.levelOfStress.copy();
        this.patientCondition = other.patientCondition == null ? null : other.patientCondition.copy();
        this.currentMedication = other.currentMedication == null ? null : other.currentMedication.copy();
        this.chronologicalHistory = other.chronologicalHistory == null ? null : other.chronologicalHistory.copy();
        this.symptomaticChanges = other.symptomaticChanges == null ? null : other.symptomaticChanges.copy();
        this.levelOfDiscomfort = other.levelOfDiscomfort == null ? null : other.levelOfDiscomfort.copy();
        this.feesDiscount = other.feesDiscount == null ? null : other.feesDiscount.copy();
        this.feesDiscountGivenBy = other.feesDiscountGivenBy == null ? null : other.feesDiscountGivenBy.copy();
        this.discountType = other.discountType == null ? null : other.discountType.copy();
        this.discount = other.discount == null ? null : other.discount.copy();
        this.allowEmail = other.allowEmail == null ? null : other.allowEmail.copy();
        this.allowSMS = other.allowSMS == null ? null : other.allowSMS.copy();
        this.allowWhatsApp = other.allowWhatsApp == null ? null : other.allowWhatsApp.copy();
        this.allowPromotions = other.allowPromotions == null ? null : other.allowPromotions.copy();
        this.iAgree = other.iAgree == null ? null : other.iAgree.copy();
        this.healingLevel = other.healingLevel == null ? null : other.healingLevel.copy();
        this.allowLogin = other.allowLogin == null ? null : other.allowLogin.copy();
        this.password = other.password == null ? null : other.password.copy();
        this.voided = other.voided == null ? null : other.voided.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
    }

    @Override
    public PatientCriteria copy() {
        return new PatientCriteria(this);
    }

    public UUIDFilter getId() {
        return id;
    }

    public void setId(UUIDFilter id) {
        this.id = id;
    }

    public StringFilter getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(StringFilter formNumber) {
        this.formNumber = formNumber;
    }

    public UUIDFilter getCenterId() {
        return centerId;
    }

    public void setCenterId(UUIDFilter centerId) {
        this.centerId = centerId;
    }

    public StringFilter getCenterName() {
        return centerName;
    }

    public void setCenterName(StringFilter centerName) {
        this.centerName = centerName;
    }

    public StringFilter getFirstName() {
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getMiddleName() {
        return middleName;
    }

    public void setMiddleName(StringFilter middleName) {
        this.middleName = middleName;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public StringFilter getDisplayName() {
        return displayName;
    }

    public void setDisplayName(StringFilter displayName) {
        this.displayName = displayName;
    }

    public InstantFilter getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(InstantFilter dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public GenderFilter getGender() {
        return gender;
    }

    public void setGender(GenderFilter gender) {
        this.gender = gender;
    }

    public IntegerFilter getAge() {
        return age;
    }

    public void setAge(IntegerFilter age) {
        this.age = age;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getArea() {
        return area;
    }

    public void setArea(StringFilter area) {
        this.area = area;
    }

    public StringFilter getCity() {
        return city;
    }

    public void setCity(StringFilter city) {
        this.city = city;
    }

    public StringFilter getDistrict() {
        return district;
    }

    public void setDistrict(StringFilter district) {
        this.district = district;
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

    public StringFilter getRegion() {
        return region;
    }

    public void setRegion(StringFilter region) {
        this.region = region;
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

    public StringFilter getPincode() {
        return pincode;
    }

    public void setPincode(StringFilter pincode) {
        this.pincode = pincode;
    }

    public StringFilter getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(StringFilter mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public StringFilter getContactNo() {
        return contactNo;
    }

    public void setContactNo(StringFilter contactNo) {
        this.contactNo = contactNo;
    }

    public StringFilter getEducation() {
        return education;
    }

    public void setEducation(StringFilter education) {
        this.education = education;
    }

    public StringFilter getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(StringFilter bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public InstantFilter getRegDate() {
        return regDate;
    }

    public void setRegDate(InstantFilter regDate) {
        this.regDate = regDate;
    }

    public StringFilter getProfession() {
        return profession;
    }

    public void setProfession(StringFilter profession) {
        this.profession = profession;
    }

    public StringFilter getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(StringFilter marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public BooleanFilter getExpired() {
        return expired;
    }

    public void setExpired(BooleanFilter expired) {
        this.expired = expired;
    }

    public StringFilter getLanguageName() {
        return languageName;
    }

    public void setLanguageName(StringFilter languageName) {
        this.languageName = languageName;
    }

    public BooleanFilter getVegNonVeg() {
        return vegNonVeg;
    }

    public void setVegNonVeg(BooleanFilter vegNonVeg) {
        this.vegNonVeg = vegNonVeg;
    }

    public StringFilter getReferedBy() {
        return referedBy;
    }

    public void setReferedBy(StringFilter referedBy) {
        this.referedBy = referedBy;
    }

    public StringFilter getReferdByName() {
        return referdByName;
    }

    public void setReferdByName(StringFilter referdByName) {
        this.referdByName = referdByName;
    }

    public BooleanFilter getExerciseRegularly() {
        return exerciseRegularly;
    }

    public void setExerciseRegularly(BooleanFilter exerciseRegularly) {
        this.exerciseRegularly = exerciseRegularly;
    }

    public BooleanFilter getAddiction() {
        return addiction;
    }

    public void setAddiction(BooleanFilter addiction) {
        this.addiction = addiction;
    }

    public IntegerFilter getHoursSleep() {
        return hoursSleep;
    }

    public void setHoursSleep(IntegerFilter hoursSleep) {
        this.hoursSleep = hoursSleep;
    }

    public BooleanFilter getWakeRested() {
        return wakeRested;
    }

    public void setWakeRested(BooleanFilter wakeRested) {
        this.wakeRested = wakeRested;
    }

    public IntegerFilter getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(IntegerFilter waterIntake) {
        this.waterIntake = waterIntake;
    }

    public IntegerFilter getHoursWork() {
        return hoursWork;
    }

    public void setHoursWork(IntegerFilter hoursWork) {
        this.hoursWork = hoursWork;
    }

    public BooleanFilter getShiftWork() {
        return shiftWork;
    }

    public void setShiftWork(BooleanFilter shiftWork) {
        this.shiftWork = shiftWork;
    }

    public StringFilter getLevelOfStress() {
        return levelOfStress;
    }

    public void setLevelOfStress(StringFilter levelOfStress) {
        this.levelOfStress = levelOfStress;
    }

    public StringFilter getPatientCondition() {
        return patientCondition;
    }

    public void setPatientCondition(StringFilter patientCondition) {
        this.patientCondition = patientCondition;
    }

    public StringFilter getCurrentMedication() {
        return currentMedication;
    }

    public void setCurrentMedication(StringFilter currentMedication) {
        this.currentMedication = currentMedication;
    }

    public StringFilter getChronologicalHistory() {
        return chronologicalHistory;
    }

    public void setChronologicalHistory(StringFilter chronologicalHistory) {
        this.chronologicalHistory = chronologicalHistory;
    }

    public StringFilter getSymptomaticChanges() {
        return symptomaticChanges;
    }

    public void setSymptomaticChanges(StringFilter symptomaticChanges) {
        this.symptomaticChanges = symptomaticChanges;
    }

    public IntegerFilter getLevelOfDiscomfort() {
        return levelOfDiscomfort;
    }

    public void setLevelOfDiscomfort(IntegerFilter levelOfDiscomfort) {
        this.levelOfDiscomfort = levelOfDiscomfort;
    }

    public BooleanFilter getFeesDiscount() {
        return feesDiscount;
    }

    public void setFeesDiscount(BooleanFilter feesDiscount) {
        this.feesDiscount = feesDiscount;
    }

    public StringFilter getFeesDiscountGivenBy() {
        return feesDiscountGivenBy;
    }

    public void setFeesDiscountGivenBy(StringFilter feesDiscountGivenBy) {
        this.feesDiscountGivenBy = feesDiscountGivenBy;
    }

    public DiscountTypeFilter getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountTypeFilter discountType) {
        this.discountType = discountType;
    }

    public BigDecimalFilter getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimalFilter discount) {
        this.discount = discount;
    }

    public BooleanFilter getAllowEmail() {
        return allowEmail;
    }

    public void setAllowEmail(BooleanFilter allowEmail) {
        this.allowEmail = allowEmail;
    }

    public BooleanFilter getAllowSMS() {
        return allowSMS;
    }

    public void setAllowSMS(BooleanFilter allowSMS) {
        this.allowSMS = allowSMS;
    }

    public BooleanFilter getAllowWhatsApp() {
        return allowWhatsApp;
    }

    public void setAllowWhatsApp(BooleanFilter allowWhatsApp) {
        this.allowWhatsApp = allowWhatsApp;
    }

    public BooleanFilter getAllowPromotions() {
        return allowPromotions;
    }

    public void setAllowPromotions(BooleanFilter allowPromotions) {
        this.allowPromotions = allowPromotions;
    }

    public BooleanFilter getiAgree() {
        return iAgree;
    }

    public void setiAgree(BooleanFilter iAgree) {
        this.iAgree = iAgree;
    }

    public HealingLevelFilter getHealingLevel() {
        return healingLevel;
    }

    public void setHealingLevel(HealingLevelFilter healingLevel) {
        this.healingLevel = healingLevel;
    }

    public BooleanFilter getAllowLogin() {
        return allowLogin;
    }

    public void setAllowLogin(BooleanFilter allowLogin) {
        this.allowLogin = allowLogin;
    }

    public StringFilter getPassword() {
        return password;
    }

    public void setPassword(StringFilter password) {
        this.password = password;
    }

    public BooleanFilter getVoided() {
        return voided;
    }

    public void setVoided(BooleanFilter voided) {
        this.voided = voided;
    }

    public UUIDFilter getUserId() {
        return userId;
    }

    public void setUserId(UUIDFilter userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PatientCriteria that = (PatientCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(formNumber, that.formNumber)
                && Objects.equals(centerId, that.centerId) && Objects.equals(centerName, that.centerName)
                && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName)
                && Objects.equals(lastName, that.lastName) && Objects.equals(displayName, that.displayName)
                && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(gender, that.gender)
                && Objects.equals(age, that.age) && Objects.equals(address, that.address)
                && Objects.equals(area, that.area) && Objects.equals(city, that.city)
                && Objects.equals(district, that.district) && Objects.equals(conState, that.conState)
                && Objects.equals(stateCode, that.stateCode) && Objects.equals(region, that.region)
                && Objects.equals(country, that.country) && Objects.equals(countryCode, that.countryCode)
                && Objects.equals(pincode, that.pincode) && Objects.equals(mobileNumber, that.mobileNumber)
                && Objects.equals(contactNo, that.contactNo) && Objects.equals(education, that.education)
                && Objects.equals(bloodGroup, that.bloodGroup) && Objects.equals(email, that.email)
                && Objects.equals(regDate, that.regDate) && Objects.equals(profession, that.profession)
                && Objects.equals(marriageStatus, that.marriageStatus) && Objects.equals(expired, that.expired)
                && Objects.equals(languageName, that.languageName) && Objects.equals(vegNonVeg, that.vegNonVeg)
                && Objects.equals(referedBy, that.referedBy) && Objects.equals(referdByName, that.referdByName)
                && Objects.equals(exerciseRegularly, that.exerciseRegularly)
                && Objects.equals(addiction, that.addiction) && Objects.equals(hoursSleep, that.hoursSleep)
                && Objects.equals(wakeRested, that.wakeRested) && Objects.equals(waterIntake, that.waterIntake)
                && Objects.equals(hoursWork, that.hoursWork) && Objects.equals(shiftWork, that.shiftWork)
                && Objects.equals(levelOfStress, that.levelOfStress)
                && Objects.equals(patientCondition, that.patientCondition)
                && Objects.equals(currentMedication, that.currentMedication)
                && Objects.equals(chronologicalHistory, that.chronologicalHistory)
                && Objects.equals(symptomaticChanges, that.symptomaticChanges)
                && Objects.equals(levelOfDiscomfort, that.levelOfDiscomfort)
                && Objects.equals(feesDiscount, that.feesDiscount)
                && Objects.equals(feesDiscountGivenBy, that.feesDiscountGivenBy)
                && Objects.equals(discountType, that.discountType) && Objects.equals(discount, that.discount)
                && Objects.equals(allowEmail, that.allowEmail) && Objects.equals(allowSMS, that.allowSMS)
                && Objects.equals(allowWhatsApp, that.allowWhatsApp)
                && Objects.equals(allowPromotions, that.allowPromotions) && Objects.equals(iAgree, that.iAgree)
                && Objects.equals(healingLevel, that.healingLevel) && Objects.equals(allowLogin, that.allowLogin)
                && Objects.equals(password, that.password) && Objects.equals(voided, that.voided)
                && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, formNumber, centerId, centerName, firstName, middleName, lastName, displayName,
                dateOfBirth, gender, age, address, area, city, district, conState, stateCode, region, country,
                countryCode, pincode, mobileNumber, contactNo, education, bloodGroup, email, regDate, profession,
                marriageStatus, expired, languageName, vegNonVeg, referedBy, referdByName, exerciseRegularly, addiction,
                hoursSleep, wakeRested, waterIntake, hoursWork, shiftWork, levelOfStress, patientCondition,
                currentMedication, chronologicalHistory, symptomaticChanges, levelOfDiscomfort, feesDiscount,
                feesDiscountGivenBy, discountType, discount, allowEmail, allowSMS, allowWhatsApp, allowPromotions,
                iAgree, healingLevel, allowLogin, password, voided, userId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientCriteria{" + (id != null ? "id=" + id + ", " : "")
                + (formNumber != null ? "formNumber=" + formNumber + ", " : "")
                + (centerId != null ? "centerId=" + centerId + ", " : "")
                + (centerName != null ? "centerName=" + centerName + ", " : "")
                + (firstName != null ? "firstName=" + firstName + ", " : "")
                + (middleName != null ? "middleName=" + middleName + ", " : "")
                + (lastName != null ? "lastName=" + lastName + ", " : "")
                + (displayName != null ? "displayName=" + displayName + ", " : "")
                + (dateOfBirth != null ? "dateOfBirth=" + dateOfBirth + ", " : "")
                + (gender != null ? "gender=" + gender + ", " : "") + (age != null ? "age=" + age + ", " : "")
                + (address != null ? "address=" + address + ", " : "") + (area != null ? "area=" + area + ", " : "")
                + (city != null ? "city=" + city + ", " : "") + (district != null ? "district=" + district + ", " : "")
                + (conState != null ? "conState=" + conState + ", " : "")
                + (stateCode != null ? "stateCode=" + stateCode + ", " : "")
                + (region != null ? "region=" + region + ", " : "")
                + (country != null ? "country=" + country + ", " : "")
                + (countryCode != null ? "countryCode=" + countryCode + ", " : "")
                + (pincode != null ? "pincode=" + pincode + ", " : "")
                + (mobileNumber != null ? "mobileNumber=" + mobileNumber + ", " : "")
                + (contactNo != null ? "contactNo=" + contactNo + ", " : "")
                + (education != null ? "education=" + education + ", " : "")
                + (bloodGroup != null ? "bloodGroup=" + bloodGroup + ", " : "")
                + (email != null ? "email=" + email + ", " : "") + (regDate != null ? "regDate=" + regDate + ", " : "")
                + (profession != null ? "profession=" + profession + ", " : "")
                + (marriageStatus != null ? "marriageStatus=" + marriageStatus + ", " : "")
                + (expired != null ? "expired=" + expired + ", " : "")
                + (languageName != null ? "languageName=" + languageName + ", " : "")
                + (vegNonVeg != null ? "vegNonVeg=" + vegNonVeg + ", " : "")
                + (referedBy != null ? "referedBy=" + referedBy + ", " : "")
                + (referdByName != null ? "referdByName=" + referdByName + ", " : "")
                + (exerciseRegularly != null ? "exerciseRegularly=" + exerciseRegularly + ", " : "")
                + (addiction != null ? "addiction=" + addiction + ", " : "")
                + (hoursSleep != null ? "hoursSleep=" + hoursSleep + ", " : "")
                + (wakeRested != null ? "wakeRested=" + wakeRested + ", " : "")
                + (waterIntake != null ? "waterIntake=" + waterIntake + ", " : "")
                + (hoursWork != null ? "hoursWork=" + hoursWork + ", " : "")
                + (shiftWork != null ? "shiftWork=" + shiftWork + ", " : "")
                + (levelOfStress != null ? "levelOfStress=" + levelOfStress + ", " : "")
                + (patientCondition != null ? "patientCondition=" + patientCondition + ", " : "")
                + (currentMedication != null ? "currentMedication=" + currentMedication + ", " : "")
                + (chronologicalHistory != null ? "chronologicalHistory=" + chronologicalHistory + ", " : "")
                + (symptomaticChanges != null ? "symptomaticChanges=" + symptomaticChanges + ", " : "")
                + (levelOfDiscomfort != null ? "levelOfDiscomfort=" + levelOfDiscomfort + ", " : "")
                + (feesDiscount != null ? "feesDiscount=" + feesDiscount + ", " : "")
                + (feesDiscountGivenBy != null ? "feesDiscountGivenBy=" + feesDiscountGivenBy + ", " : "")
                + (discountType != null ? "discountType=" + discountType + ", " : "")
                + (discount != null ? "discount=" + discount + ", " : "")
                + (allowEmail != null ? "allowEmail=" + allowEmail + ", " : "")
                + (allowSMS != null ? "allowSMS=" + allowSMS + ", " : "")
                + (allowWhatsApp != null ? "allowWhatsApp=" + allowWhatsApp + ", " : "")
                + (allowPromotions != null ? "allowPromotions=" + allowPromotions + ", " : "")
                + (iAgree != null ? "iAgree=" + iAgree + ", " : "")
                + (healingLevel != null ? "healingLevel=" + healingLevel + ", " : "")
                + (allowLogin != null ? "allowLogin=" + allowLogin + ", " : "")
                + (password != null ? "password=" + password + ", " : "")
                + (voided != null ? "voided=" + voided + ", " : "") + (userId != null ? "userId=" + userId + ", " : "")
                + "}";
    }

}
