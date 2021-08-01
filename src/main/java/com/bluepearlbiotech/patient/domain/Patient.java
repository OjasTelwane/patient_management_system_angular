package com.bluepearlbiotech.patient.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import org.hibernate.annotations.Type;
import java.util.UUID;
import java.util.UUID;

import com.bluepearlbiotech.patient.domain.enumeration.Gender;

import com.bluepearlbiotech.patient.domain.enumeration.DiscountType;

import com.bluepearlbiotech.patient.domain.enumeration.HealingLevel;

/**
 * A Patient.
 */
@Entity
@Table(name = "icpatient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Patient extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "form_number", nullable = false)
    private String formNumber;

    @NotNull
    @Column(name = "center_id", nullable = false)
    private UUID centerId;

    @NotNull
    @Column(name = "center_name", nullable = false)
    private String centerName;

    @NotNull
    @Size(max = 20)
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Size(max = 20)
    @Column(name = "middle_name", length = 20)
    private String middleName;

    @NotNull
    @Size(max = 20)
    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    @Size(max = 64)
    @Column(name = "display_name", length = 64)
    private String displayName;

    @Column(name = "date_of_birth")
    private Instant dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "age")
    private Integer age;

    @Size(max = 255)
    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "area")
    private String area;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "con_state")
    private String conState;

    @Size(max = 14)
    @Column(name = "state_code", length = 14)
    private String stateCode;

    @Column(name = "region")
    private String region;

    @Column(name = "country")
    private String country;

    @Column(name = "country_code")
    private String countryCode;

    @Size(max = 14)
    @Column(name = "pincode", length = 14)
    private String pincode;

    @Lob
    @Column(name = "photo_1")
    private byte[] photo1;

    @Column(name = "photo_1_content_type")
    private String photo1ContentType;

    @Lob
    @Column(name = "photo_2")
    private byte[] photo2;

    @Column(name = "photo_2_content_type")
    private String photo2ContentType;

    @NotNull
    @Size(max = 15)
    @Column(name = "mobile_number", length = 15, nullable = false)
    private String mobileNumber;

    @Size(max = 20)
    @Column(name = "contact_no", length = 20)
    private String contactNo;

    @Size(max = 50)
    @Column(name = "education", length = 50)
    private String education;

    @Size(max = 50)
    @Column(name = "blood_group", length = 50)
    private String bloodGroup;

    @Column(name = "email")
    private String email;

    @Column(name = "reg_date")
    private Instant regDate;

    @Size(max = 50)
    @Column(name = "profession", length = 50)
    private String profession;

    @Size(max = 20)
    @Column(name = "marriage_status", length = 20)
    private String marriageStatus;

    @Column(name = "expired")
    private Boolean expired;

    @Size(max = 20)
    @Column(name = "language_name", length = 20)
    private String languageName;

    @Column(name = "veg_non_veg")
    private Boolean vegNonVeg;

    @Size(max = 255)
    @Column(name = "refered_by", length = 255)
    private String referedBy;

    @Size(max = 255)
    @Column(name = "referd_by_name", length = 255)
    private String referdByName;

    @Column(name = "exercise_regularly")
    private Boolean exerciseRegularly;

    @Column(name = "addiction")
    private Boolean addiction;

    @Column(name = "hours_sleep")
    private Integer hoursSleep;

    @Column(name = "wake_rested")
    private Boolean wakeRested;

    @Column(name = "water_intake")
    private Integer waterIntake;

    @Column(name = "hours_work")
    private Integer hoursWork;

    @Column(name = "shift_work")
    private Boolean shiftWork;

    @Size(max = 20)
    @Column(name = "level_of_stress", length = 20)
    private String levelOfStress;

    @Size(max = 4096)
    @Column(name = "patient_condition", length = 4096)
    private String patientCondition;

    @Size(max = 4096)
    @Column(name = "current_medication", length = 4096)
    private String currentMedication;

    @Size(max = 4096)
    @Column(name = "chronological_history", length = 4096)
    private String chronologicalHistory;

    @Size(max = 4096)
    @Column(name = "symptomatic_changes", length = 4096)
    private String symptomaticChanges;

    @Column(name = "level_of_discomfort")
    private Integer levelOfDiscomfort;

    @Column(name = "fees_discount")
    private Boolean feesDiscount;

    @Column(name = "fees_discount_given_by")
    private String feesDiscountGivenBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type")
    private DiscountType discountType;

    @Column(name = "discount", precision = 21, scale = 2)
    private BigDecimal discount;

    @Column(name = "allow_email")
    private Boolean allowEmail;

    @Column(name = "allow_sms")
    private Boolean allowSMS;

    @Column(name = "allow_whats_app")
    private Boolean allowWhatsApp;

    @Column(name = "allow_promotions")
    private Boolean allowPromotions;

    @Column(name = "i_agree")
    private Boolean iAgree;

    @Enumerated(EnumType.STRING)
    @Column(name = "healing_level")
    private HealingLevel healingLevel;

    @Column(name = "allow_login")
    private Boolean allowLogin;

    @Column(name = "password")
    private String password;

    @Column(name = "voided")
    private Boolean voided;

    @Column(name = "user_id")
    private UUID userId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public Patient formNumber(String formNumber) {
        this.formNumber = formNumber;
        return this;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public UUID getCenterId() {
        return centerId;
    }

    public Patient centerId(UUID centerId) {
        this.centerId = centerId;
        return this;
    }

    public void setCenterId(UUID centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public Patient centerName(String centerName) {
        this.centerName = centerName;
        return this;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Patient firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Patient middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Patient lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Patient displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public Patient dateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public Patient gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public Patient age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public Patient address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public Patient area(String area) {
        this.area = area;
        return this;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public Patient city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public Patient district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getConState() {
        return conState;
    }

    public Patient conState(String conState) {
        this.conState = conState;
        return this;
    }

    public void setConState(String conState) {
        this.conState = conState;
    }

    public String getStateCode() {
        return stateCode;
    }

    public Patient stateCode(String stateCode) {
        this.stateCode = stateCode;
        return this;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getRegion() {
        return region;
    }

    public Patient region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public Patient country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Patient countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPincode() {
        return pincode;
    }

    public Patient pincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public byte[] getPhoto1() {
        return photo1;
    }

    public Patient photo1(byte[] photo1) {
        this.photo1 = photo1;
        return this;
    }

    public void setPhoto1(byte[] photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto1ContentType() {
        return photo1ContentType;
    }

    public Patient photo1ContentType(String photo1ContentType) {
        this.photo1ContentType = photo1ContentType;
        return this;
    }

    public void setPhoto1ContentType(String photo1ContentType) {
        this.photo1ContentType = photo1ContentType;
    }

    public byte[] getPhoto2() {
        return photo2;
    }

    public Patient photo2(byte[] photo2) {
        this.photo2 = photo2;
        return this;
    }

    public void setPhoto2(byte[] photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto2ContentType() {
        return photo2ContentType;
    }

    public Patient photo2ContentType(String photo2ContentType) {
        this.photo2ContentType = photo2ContentType;
        return this;
    }

    public void setPhoto2ContentType(String photo2ContentType) {
        this.photo2ContentType = photo2ContentType;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public Patient mobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getContactNo() {
        return contactNo;
    }

    public Patient contactNo(String contactNo) {
        this.contactNo = contactNo;
        return this;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEducation() {
        return education;
    }

    public Patient education(String education) {
        this.education = education;
        return this;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public Patient bloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmail() {
        return email;
    }

    public Patient email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getRegDate() {
        return regDate;
    }

    public Patient regDate(Instant regDate) {
        this.regDate = regDate;
        return this;
    }

    public void setRegDate(Instant regDate) {
        this.regDate = regDate;
    }

    public String getProfession() {
        return profession;
    }

    public Patient profession(String profession) {
        this.profession = profession;
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getMarriageStatus() {
        return marriageStatus;
    }

    public Patient marriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus;
        return this;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public Boolean isExpired() {
        return expired;
    }

    public Patient expired(Boolean expired) {
        this.expired = expired;
        return this;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public String getLanguageName() {
        return languageName;
    }

    public Patient languageName(String languageName) {
        this.languageName = languageName;
        return this;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Boolean isVegNonVeg() {
        return vegNonVeg;
    }

    public Patient vegNonVeg(Boolean vegNonVeg) {
        this.vegNonVeg = vegNonVeg;
        return this;
    }

    public void setVegNonVeg(Boolean vegNonVeg) {
        this.vegNonVeg = vegNonVeg;
    }

    public String getReferedBy() {
        return referedBy;
    }

    public Patient referedBy(String referedBy) {
        this.referedBy = referedBy;
        return this;
    }

    public void setReferedBy(String referedBy) {
        this.referedBy = referedBy;
    }

    public String getReferdByName() {
        return referdByName;
    }

    public Patient referdByName(String referdByName) {
        this.referdByName = referdByName;
        return this;
    }

    public void setReferdByName(String referdByName) {
        this.referdByName = referdByName;
    }

    public Boolean isExerciseRegularly() {
        return exerciseRegularly;
    }

    public Patient exerciseRegularly(Boolean exerciseRegularly) {
        this.exerciseRegularly = exerciseRegularly;
        return this;
    }

    public void setExerciseRegularly(Boolean exerciseRegularly) {
        this.exerciseRegularly = exerciseRegularly;
    }

    public Boolean isAddiction() {
        return addiction;
    }

    public Patient addiction(Boolean addiction) {
        this.addiction = addiction;
        return this;
    }

    public void setAddiction(Boolean addiction) {
        this.addiction = addiction;
    }

    public Integer getHoursSleep() {
        return hoursSleep;
    }

    public Patient hoursSleep(Integer hoursSleep) {
        this.hoursSleep = hoursSleep;
        return this;
    }

    public void setHoursSleep(Integer hoursSleep) {
        this.hoursSleep = hoursSleep;
    }

    public Boolean isWakeRested() {
        return wakeRested;
    }

    public Patient wakeRested(Boolean wakeRested) {
        this.wakeRested = wakeRested;
        return this;
    }

    public void setWakeRested(Boolean wakeRested) {
        this.wakeRested = wakeRested;
    }

    public Integer getWaterIntake() {
        return waterIntake;
    }

    public Patient waterIntake(Integer waterIntake) {
        this.waterIntake = waterIntake;
        return this;
    }

    public void setWaterIntake(Integer waterIntake) {
        this.waterIntake = waterIntake;
    }

    public Integer getHoursWork() {
        return hoursWork;
    }

    public Patient hoursWork(Integer hoursWork) {
        this.hoursWork = hoursWork;
        return this;
    }

    public void setHoursWork(Integer hoursWork) {
        this.hoursWork = hoursWork;
    }

    public Boolean isShiftWork() {
        return shiftWork;
    }

    public Patient shiftWork(Boolean shiftWork) {
        this.shiftWork = shiftWork;
        return this;
    }

    public void setShiftWork(Boolean shiftWork) {
        this.shiftWork = shiftWork;
    }

    public String getLevelOfStress() {
        return levelOfStress;
    }

    public Patient levelOfStress(String levelOfStress) {
        this.levelOfStress = levelOfStress;
        return this;
    }

    public void setLevelOfStress(String levelOfStress) {
        this.levelOfStress = levelOfStress;
    }

    public String getPatientCondition() {
        return patientCondition;
    }

    public Patient patientCondition(String patientCondition) {
        this.patientCondition = patientCondition;
        return this;
    }

    public void setPatientCondition(String patientCondition) {
        this.patientCondition = patientCondition;
    }

    public String getCurrentMedication() {
        return currentMedication;
    }

    public Patient currentMedication(String currentMedication) {
        this.currentMedication = currentMedication;
        return this;
    }

    public void setCurrentMedication(String currentMedication) {
        this.currentMedication = currentMedication;
    }

    public String getChronologicalHistory() {
        return chronologicalHistory;
    }

    public Patient chronologicalHistory(String chronologicalHistory) {
        this.chronologicalHistory = chronologicalHistory;
        return this;
    }

    public void setChronologicalHistory(String chronologicalHistory) {
        this.chronologicalHistory = chronologicalHistory;
    }

    public String getSymptomaticChanges() {
        return symptomaticChanges;
    }

    public Patient symptomaticChanges(String symptomaticChanges) {
        this.symptomaticChanges = symptomaticChanges;
        return this;
    }

    public void setSymptomaticChanges(String symptomaticChanges) {
        this.symptomaticChanges = symptomaticChanges;
    }

    public Integer getLevelOfDiscomfort() {
        return levelOfDiscomfort;
    }

    public Patient levelOfDiscomfort(Integer levelOfDiscomfort) {
        this.levelOfDiscomfort = levelOfDiscomfort;
        return this;
    }

    public void setLevelOfDiscomfort(Integer levelOfDiscomfort) {
        this.levelOfDiscomfort = levelOfDiscomfort;
    }

    public Boolean isFeesDiscount() {
        return feesDiscount;
    }

    public Patient feesDiscount(Boolean feesDiscount) {
        this.feesDiscount = feesDiscount;
        return this;
    }

    public void setFeesDiscount(Boolean feesDiscount) {
        this.feesDiscount = feesDiscount;
    }

    public String getFeesDiscountGivenBy() {
        return feesDiscountGivenBy;
    }

    public Patient feesDiscountGivenBy(String feesDiscountGivenBy) {
        this.feesDiscountGivenBy = feesDiscountGivenBy;
        return this;
    }

    public void setFeesDiscountGivenBy(String feesDiscountGivenBy) {
        this.feesDiscountGivenBy = feesDiscountGivenBy;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public Patient discountType(DiscountType discountType) {
        this.discountType = discountType;
        return this;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public Patient discount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Boolean isAllowEmail() {
        return allowEmail;
    }

    public Patient allowEmail(Boolean allowEmail) {
        this.allowEmail = allowEmail;
        return this;
    }

    public void setAllowEmail(Boolean allowEmail) {
        this.allowEmail = allowEmail;
    }

    public Boolean isAllowSMS() {
        return allowSMS;
    }

    public Patient allowSMS(Boolean allowSMS) {
        this.allowSMS = allowSMS;
        return this;
    }

    public void setAllowSMS(Boolean allowSMS) {
        this.allowSMS = allowSMS;
    }

    public Boolean isAllowWhatsApp() {
        return allowWhatsApp;
    }

    public Patient allowWhatsApp(Boolean allowWhatsApp) {
        this.allowWhatsApp = allowWhatsApp;
        return this;
    }

    public void setAllowWhatsApp(Boolean allowWhatsApp) {
        this.allowWhatsApp = allowWhatsApp;
    }

    public Boolean isAllowPromotions() {
        return allowPromotions;
    }

    public Patient allowPromotions(Boolean allowPromotions) {
        this.allowPromotions = allowPromotions;
        return this;
    }

    public void setAllowPromotions(Boolean allowPromotions) {
        this.allowPromotions = allowPromotions;
    }

    public Boolean isiAgree() {
        return iAgree;
    }

    public Patient iAgree(Boolean iAgree) {
        this.iAgree = iAgree;
        return this;
    }

    public void setiAgree(Boolean iAgree) {
        this.iAgree = iAgree;
    }

    public HealingLevel getHealingLevel() {
        return healingLevel;
    }

    public Patient healingLevel(HealingLevel healingLevel) {
        this.healingLevel = healingLevel;
        return this;
    }

    public void setHealingLevel(HealingLevel healingLevel) {
        this.healingLevel = healingLevel;
    }

    public Boolean isAllowLogin() {
        return allowLogin;
    }

    public Patient allowLogin(Boolean allowLogin) {
        this.allowLogin = allowLogin;
        return this;
    }

    public void setAllowLogin(Boolean allowLogin) {
        this.allowLogin = allowLogin;
    }

    public String getPassword() {
        return password;
    }

    public Patient password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isVoided() {
        return voided;
    }

    public Patient voided(Boolean voided) {
        this.voided = voided;
        return this;
    }

    public void setVoided(Boolean voided) {
        this.voided = voided;
    }

    public UUID getUserId() {
        return userId;
    }

    public Patient userId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        return id != null && id.equals(((Patient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Patient{" +
            "id=" + getId() +
            ", formNumber='" + getFormNumber() + "'" +
            ", centerId='" + getCenterId() + "'" +
            ", centerName='" + getCenterName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", gender='" + getGender() + "'" +
            ", age=" + getAge() +
            ", address='" + getAddress() + "'" +
            ", area='" + getArea() + "'" +
            ", city='" + getCity() + "'" +
            ", district='" + getDistrict() + "'" +
            ", conState='" + getConState() + "'" +
            ", stateCode='" + getStateCode() + "'" +
            ", region='" + getRegion() + "'" +
            ", country='" + getCountry() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", pincode='" + getPincode() + "'" +
            ", photo1='" + getPhoto1() + "'" +
            ", photo1ContentType='" + getPhoto1ContentType() + "'" +
            ", photo2='" + getPhoto2() + "'" +
            ", photo2ContentType='" + getPhoto2ContentType() + "'" +
            ", mobileNumber='" + getMobileNumber() + "'" +
            ", contactNo='" + getContactNo() + "'" +
            ", education='" + getEducation() + "'" +
            ", bloodGroup='" + getBloodGroup() + "'" +
            ", email='" + getEmail() + "'" +
            ", regDate='" + getRegDate() + "'" +
            ", profession='" + getProfession() + "'" +
            ", marriageStatus='" + getMarriageStatus() + "'" +
            ", expired='" + isExpired() + "'" +
            ", languageName='" + getLanguageName() + "'" +
            ", vegNonVeg='" + isVegNonVeg() + "'" +
            ", referedBy='" + getReferedBy() + "'" +
            ", referdByName='" + getReferdByName() + "'" +
            ", exerciseRegularly='" + isExerciseRegularly() + "'" +
            ", addiction='" + isAddiction() + "'" +
            ", hoursSleep=" + getHoursSleep() +
            ", wakeRested='" + isWakeRested() + "'" +
            ", waterIntake=" + getWaterIntake() +
            ", hoursWork=" + getHoursWork() +
            ", shiftWork='" + isShiftWork() + "'" +
            ", levelOfStress='" + getLevelOfStress() + "'" +
            ", patientCondition='" + getPatientCondition() + "'" +
            ", currentMedication='" + getCurrentMedication() + "'" +
            ", chronologicalHistory='" + getChronologicalHistory() + "'" +
            ", symptomaticChanges='" + getSymptomaticChanges() + "'" +
            ", levelOfDiscomfort=" + getLevelOfDiscomfort() +
            ", feesDiscount='" + isFeesDiscount() + "'" +
            ", feesDiscountGivenBy='" + getFeesDiscountGivenBy() + "'" +
            ", discountType='" + getDiscountType() + "'" +
            ", discount=" + getDiscount() +
            ", allowEmail='" + isAllowEmail() + "'" +
            ", allowSMS='" + isAllowSMS() + "'" +
            ", allowWhatsApp='" + isAllowWhatsApp() + "'" +
            ", allowPromotions='" + isAllowPromotions() + "'" +
            ", iAgree='" + isiAgree() + "'" +
            ", healingLevel='" + getHealingLevel() + "'" +
            ", allowLogin='" + isAllowLogin() + "'" +
            ", password='" + getPassword() + "'" +
            ", voided='" + isVoided() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }
}
