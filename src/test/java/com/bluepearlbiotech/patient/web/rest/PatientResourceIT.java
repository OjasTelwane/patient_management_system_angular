package com.bluepearlbiotech.patient.web.rest;

import com.bluepearlbiotech.patient.PatientApp;
import com.bluepearlbiotech.patient.domain.Patient;
import com.bluepearlbiotech.patient.repository.PatientRepository;
import com.bluepearlbiotech.patient.service.PatientService;
import com.bluepearlbiotech.patient.service.dto.PatientCriteria;
import com.bluepearlbiotech.patient.service.PatientQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bluepearlbiotech.patient.domain.enumeration.Gender;
import com.bluepearlbiotech.patient.domain.enumeration.DiscountType;
import com.bluepearlbiotech.patient.domain.enumeration.HealingLevel;
/**
 * Integration tests for the {@link PatientResource} REST controller.
 */
@SpringBootTest(classes = PatientApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PatientResourceIT {

    private static final String DEFAULT_FORM_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FORM_NUMBER = "BBBBBBBBBB";

    private static final UUID DEFAULT_CENTER_ID = UUID.randomUUID();
    private static final UUID UPDATED_CENTER_ID = UUID.randomUUID();

    private static final String DEFAULT_CENTER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CENTER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_OF_BIRTH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OF_BIRTH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Gender DEFAULT_GENDER = Gender.Male;
    private static final Gender UPDATED_GENDER = Gender.Female;

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;
    private static final Integer SMALLER_AGE = 1 - 1;

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_AREA = "AAAAAAAAAA";
    private static final String UPDATED_AREA = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_CON_STATE = "AAAAAAAAAA";
    private static final String UPDATED_CON_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PINCODE = "AAAAAAAAAA";
    private static final String UPDATED_PINCODE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO_1 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_1_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_PHOTO_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_2_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_MOBILE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EDUCATION = "AAAAAAAAAA";
    private static final String UPDATED_EDUCATION = "BBBBBBBBBB";

    private static final String DEFAULT_BLOOD_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_BLOOD_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Instant DEFAULT_REG_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REG_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PROFESSION = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION = "BBBBBBBBBB";

    private static final String DEFAULT_MARRIAGE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_MARRIAGE_STATUS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXPIRED = false;
    private static final Boolean UPDATED_EXPIRED = true;

    private static final String DEFAULT_LANGUAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VEG_NON_VEG = false;
    private static final Boolean UPDATED_VEG_NON_VEG = true;

    private static final String DEFAULT_REFERED_BY = "AAAAAAAAAA";
    private static final String UPDATED_REFERED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_REFERD_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REFERD_BY_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXERCISE_REGULARLY = false;
    private static final Boolean UPDATED_EXERCISE_REGULARLY = true;

    private static final Boolean DEFAULT_ADDICTION = false;
    private static final Boolean UPDATED_ADDICTION = true;

    private static final Integer DEFAULT_HOURS_SLEEP = 1;
    private static final Integer UPDATED_HOURS_SLEEP = 2;
    private static final Integer SMALLER_HOURS_SLEEP = 1 - 1;

    private static final Boolean DEFAULT_WAKE_RESTED = false;
    private static final Boolean UPDATED_WAKE_RESTED = true;

    private static final Integer DEFAULT_WATER_INTAKE = 1;
    private static final Integer UPDATED_WATER_INTAKE = 2;
    private static final Integer SMALLER_WATER_INTAKE = 1 - 1;

    private static final Integer DEFAULT_HOURS_WORK = 1;
    private static final Integer UPDATED_HOURS_WORK = 2;
    private static final Integer SMALLER_HOURS_WORK = 1 - 1;

    private static final Boolean DEFAULT_SHIFT_WORK = false;
    private static final Boolean UPDATED_SHIFT_WORK = true;

    private static final String DEFAULT_LEVEL_OF_STRESS = "AAAAAAAAAA";
    private static final String UPDATED_LEVEL_OF_STRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_CONDITION = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_CONDITION = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENT_MEDICATION = "AAAAAAAAAA";
    private static final String UPDATED_CURRENT_MEDICATION = "BBBBBBBBBB";

    private static final String DEFAULT_CHRONOLOGICAL_HISTORY = "AAAAAAAAAA";
    private static final String UPDATED_CHRONOLOGICAL_HISTORY = "BBBBBBBBBB";

    private static final String DEFAULT_SYMPTOMATIC_CHANGES = "AAAAAAAAAA";
    private static final String UPDATED_SYMPTOMATIC_CHANGES = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL_OF_DISCOMFORT = 1;
    private static final Integer UPDATED_LEVEL_OF_DISCOMFORT = 2;
    private static final Integer SMALLER_LEVEL_OF_DISCOMFORT = 1 - 1;

    private static final Boolean DEFAULT_FEES_DISCOUNT = false;
    private static final Boolean UPDATED_FEES_DISCOUNT = true;

    private static final String DEFAULT_FEES_DISCOUNT_GIVEN_BY = "AAAAAAAAAA";
    private static final String UPDATED_FEES_DISCOUNT_GIVEN_BY = "BBBBBBBBBB";

    private static final DiscountType DEFAULT_DISCOUNT_TYPE = DiscountType.NoDiscount;
    private static final DiscountType UPDATED_DISCOUNT_TYPE = DiscountType.OnlyVisitFees;

    private static final BigDecimal DEFAULT_DISCOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DISCOUNT = new BigDecimal(2);
    private static final BigDecimal SMALLER_DISCOUNT = new BigDecimal(1 - 1);

    private static final Boolean DEFAULT_ALLOW_EMAIL = false;
    private static final Boolean UPDATED_ALLOW_EMAIL = true;

    private static final Boolean DEFAULT_ALLOW_SMS = false;
    private static final Boolean UPDATED_ALLOW_SMS = true;

    private static final Boolean DEFAULT_ALLOW_WHATS_APP = false;
    private static final Boolean UPDATED_ALLOW_WHATS_APP = true;

    private static final Boolean DEFAULT_ALLOW_PROMOTIONS = false;
    private static final Boolean UPDATED_ALLOW_PROMOTIONS = true;

    private static final Boolean DEFAULT_I_AGREE = false;
    private static final Boolean UPDATED_I_AGREE = true;

    private static final HealingLevel DEFAULT_HEALING_LEVEL = HealingLevel.Simple;
    private static final HealingLevel UPDATED_HEALING_LEVEL = HealingLevel.Complex;

    private static final Boolean DEFAULT_ALLOW_LOGIN = false;
    private static final Boolean UPDATED_ALLOW_LOGIN = true;

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VOIDED = false;
    private static final Boolean UPDATED_VOIDED = true;

    private static final UUID DEFAULT_USER_ID = UUID.randomUUID();
    private static final UUID UPDATED_USER_ID = UUID.randomUUID();

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientQueryService patientQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPatientMockMvc;

    private Patient patient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patient createEntity(EntityManager em) {
        Patient patient = new Patient()
            .formNumber(DEFAULT_FORM_NUMBER)
            .centerId(DEFAULT_CENTER_ID)
            .centerName(DEFAULT_CENTER_NAME)
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .displayName(DEFAULT_DISPLAY_NAME)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .gender(DEFAULT_GENDER)
            .age(DEFAULT_AGE)
            .address(DEFAULT_ADDRESS)
            .area(DEFAULT_AREA)
            .city(DEFAULT_CITY)
            .district(DEFAULT_DISTRICT)
            .conState(DEFAULT_CON_STATE)
            .stateCode(DEFAULT_STATE_CODE)
            .region(DEFAULT_REGION)
            .country(DEFAULT_COUNTRY)
            .countryCode(DEFAULT_COUNTRY_CODE)
            .pincode(DEFAULT_PINCODE)
            .photo1(DEFAULT_PHOTO_1)
            .photo1ContentType(DEFAULT_PHOTO_1_CONTENT_TYPE)
            .photo2(DEFAULT_PHOTO_2)
            .photo2ContentType(DEFAULT_PHOTO_2_CONTENT_TYPE)
            .mobileNumber(DEFAULT_MOBILE_NUMBER)
            .contactNo(DEFAULT_CONTACT_NO)
            .education(DEFAULT_EDUCATION)
            .bloodGroup(DEFAULT_BLOOD_GROUP)
            .email(DEFAULT_EMAIL)
            .regDate(DEFAULT_REG_DATE)
            .profession(DEFAULT_PROFESSION)
            .marriageStatus(DEFAULT_MARRIAGE_STATUS)
            .expired(DEFAULT_EXPIRED)
            .languageName(DEFAULT_LANGUAGE_NAME)
            .vegNonVeg(DEFAULT_VEG_NON_VEG)
            .referedBy(DEFAULT_REFERED_BY)
            .referdByName(DEFAULT_REFERD_BY_NAME)
            .exerciseRegularly(DEFAULT_EXERCISE_REGULARLY)
            .addiction(DEFAULT_ADDICTION)
            .hoursSleep(DEFAULT_HOURS_SLEEP)
            .wakeRested(DEFAULT_WAKE_RESTED)
            .waterIntake(DEFAULT_WATER_INTAKE)
            .hoursWork(DEFAULT_HOURS_WORK)
            .shiftWork(DEFAULT_SHIFT_WORK)
            .levelOfStress(DEFAULT_LEVEL_OF_STRESS)
            .patientCondition(DEFAULT_PATIENT_CONDITION)
            .currentMedication(DEFAULT_CURRENT_MEDICATION)
            .chronologicalHistory(DEFAULT_CHRONOLOGICAL_HISTORY)
            .symptomaticChanges(DEFAULT_SYMPTOMATIC_CHANGES)
            .levelOfDiscomfort(DEFAULT_LEVEL_OF_DISCOMFORT)
            .feesDiscount(DEFAULT_FEES_DISCOUNT)
            .feesDiscountGivenBy(DEFAULT_FEES_DISCOUNT_GIVEN_BY)
            .discountType(DEFAULT_DISCOUNT_TYPE)
            .discount(DEFAULT_DISCOUNT)
            .allowEmail(DEFAULT_ALLOW_EMAIL)
            .allowSMS(DEFAULT_ALLOW_SMS)
            .allowWhatsApp(DEFAULT_ALLOW_WHATS_APP)
            .allowPromotions(DEFAULT_ALLOW_PROMOTIONS)
            .iAgree(DEFAULT_I_AGREE)
            .healingLevel(DEFAULT_HEALING_LEVEL)
            .allowLogin(DEFAULT_ALLOW_LOGIN)
            .password(DEFAULT_PASSWORD)
            .voided(DEFAULT_VOIDED)
            .userId(DEFAULT_USER_ID);
        return patient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patient createUpdatedEntity(EntityManager em) {
        Patient patient = new Patient()
            .formNumber(UPDATED_FORM_NUMBER)
            .centerId(UPDATED_CENTER_ID)
            .centerName(UPDATED_CENTER_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .displayName(UPDATED_DISPLAY_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .gender(UPDATED_GENDER)
            .age(UPDATED_AGE)
            .address(UPDATED_ADDRESS)
            .area(UPDATED_AREA)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .conState(UPDATED_CON_STATE)
            .stateCode(UPDATED_STATE_CODE)
            .region(UPDATED_REGION)
            .country(UPDATED_COUNTRY)
            .countryCode(UPDATED_COUNTRY_CODE)
            .pincode(UPDATED_PINCODE)
            .photo1(UPDATED_PHOTO_1)
            .photo1ContentType(UPDATED_PHOTO_1_CONTENT_TYPE)
            .photo2(UPDATED_PHOTO_2)
            .photo2ContentType(UPDATED_PHOTO_2_CONTENT_TYPE)
            .mobileNumber(UPDATED_MOBILE_NUMBER)
            .contactNo(UPDATED_CONTACT_NO)
            .education(UPDATED_EDUCATION)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .email(UPDATED_EMAIL)
            .regDate(UPDATED_REG_DATE)
            .profession(UPDATED_PROFESSION)
            .marriageStatus(UPDATED_MARRIAGE_STATUS)
            .expired(UPDATED_EXPIRED)
            .languageName(UPDATED_LANGUAGE_NAME)
            .vegNonVeg(UPDATED_VEG_NON_VEG)
            .referedBy(UPDATED_REFERED_BY)
            .referdByName(UPDATED_REFERD_BY_NAME)
            .exerciseRegularly(UPDATED_EXERCISE_REGULARLY)
            .addiction(UPDATED_ADDICTION)
            .hoursSleep(UPDATED_HOURS_SLEEP)
            .wakeRested(UPDATED_WAKE_RESTED)
            .waterIntake(UPDATED_WATER_INTAKE)
            .hoursWork(UPDATED_HOURS_WORK)
            .shiftWork(UPDATED_SHIFT_WORK)
            .levelOfStress(UPDATED_LEVEL_OF_STRESS)
            .patientCondition(UPDATED_PATIENT_CONDITION)
            .currentMedication(UPDATED_CURRENT_MEDICATION)
            .chronologicalHistory(UPDATED_CHRONOLOGICAL_HISTORY)
            .symptomaticChanges(UPDATED_SYMPTOMATIC_CHANGES)
            .levelOfDiscomfort(UPDATED_LEVEL_OF_DISCOMFORT)
            .feesDiscount(UPDATED_FEES_DISCOUNT)
            .feesDiscountGivenBy(UPDATED_FEES_DISCOUNT_GIVEN_BY)
            .discountType(UPDATED_DISCOUNT_TYPE)
            .discount(UPDATED_DISCOUNT)
            .allowEmail(UPDATED_ALLOW_EMAIL)
            .allowSMS(UPDATED_ALLOW_SMS)
            .allowWhatsApp(UPDATED_ALLOW_WHATS_APP)
            .allowPromotions(UPDATED_ALLOW_PROMOTIONS)
            .iAgree(UPDATED_I_AGREE)
            .healingLevel(UPDATED_HEALING_LEVEL)
            .allowLogin(UPDATED_ALLOW_LOGIN)
            .password(UPDATED_PASSWORD)
            .voided(UPDATED_VOIDED)
            .userId(UPDATED_USER_ID);
        return patient;
    }

    @BeforeEach
    public void initTest() {
        patient = createEntity(em);
    }

    @Test
    @Transactional
    public void createPatient() throws Exception {
        int databaseSizeBeforeCreate = patientRepository.findAll().size();
        // Create the Patient
        restPatientMockMvc.perform(post("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isCreated());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate + 1);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getFormNumber()).isEqualTo(DEFAULT_FORM_NUMBER);
        assertThat(testPatient.getCenterId()).isEqualTo(DEFAULT_CENTER_ID);
        assertThat(testPatient.getCenterName()).isEqualTo(DEFAULT_CENTER_NAME);
        assertThat(testPatient.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testPatient.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testPatient.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testPatient.getDisplayName()).isEqualTo(DEFAULT_DISPLAY_NAME);
        assertThat(testPatient.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testPatient.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPatient.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testPatient.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testPatient.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testPatient.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testPatient.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testPatient.getConState()).isEqualTo(DEFAULT_CON_STATE);
        assertThat(testPatient.getStateCode()).isEqualTo(DEFAULT_STATE_CODE);
        assertThat(testPatient.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testPatient.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testPatient.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testPatient.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testPatient.getPhoto1()).isEqualTo(DEFAULT_PHOTO_1);
        assertThat(testPatient.getPhoto1ContentType()).isEqualTo(DEFAULT_PHOTO_1_CONTENT_TYPE);
        assertThat(testPatient.getPhoto2()).isEqualTo(DEFAULT_PHOTO_2);
        assertThat(testPatient.getPhoto2ContentType()).isEqualTo(DEFAULT_PHOTO_2_CONTENT_TYPE);
        assertThat(testPatient.getMobileNumber()).isEqualTo(DEFAULT_MOBILE_NUMBER);
        assertThat(testPatient.getContactNo()).isEqualTo(DEFAULT_CONTACT_NO);
        assertThat(testPatient.getEducation()).isEqualTo(DEFAULT_EDUCATION);
        assertThat(testPatient.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
        assertThat(testPatient.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPatient.getRegDate()).isEqualTo(DEFAULT_REG_DATE);
        assertThat(testPatient.getProfession()).isEqualTo(DEFAULT_PROFESSION);
        assertThat(testPatient.getMarriageStatus()).isEqualTo(DEFAULT_MARRIAGE_STATUS);
        assertThat(testPatient.isExpired()).isEqualTo(DEFAULT_EXPIRED);
        assertThat(testPatient.getLanguageName()).isEqualTo(DEFAULT_LANGUAGE_NAME);
        assertThat(testPatient.isVegNonVeg()).isEqualTo(DEFAULT_VEG_NON_VEG);
        assertThat(testPatient.getReferedBy()).isEqualTo(DEFAULT_REFERED_BY);
        assertThat(testPatient.getReferdByName()).isEqualTo(DEFAULT_REFERD_BY_NAME);
        assertThat(testPatient.isExerciseRegularly()).isEqualTo(DEFAULT_EXERCISE_REGULARLY);
        assertThat(testPatient.isAddiction()).isEqualTo(DEFAULT_ADDICTION);
        assertThat(testPatient.getHoursSleep()).isEqualTo(DEFAULT_HOURS_SLEEP);
        assertThat(testPatient.isWakeRested()).isEqualTo(DEFAULT_WAKE_RESTED);
        assertThat(testPatient.getWaterIntake()).isEqualTo(DEFAULT_WATER_INTAKE);
        assertThat(testPatient.getHoursWork()).isEqualTo(DEFAULT_HOURS_WORK);
        assertThat(testPatient.isShiftWork()).isEqualTo(DEFAULT_SHIFT_WORK);
        assertThat(testPatient.getLevelOfStress()).isEqualTo(DEFAULT_LEVEL_OF_STRESS);
        assertThat(testPatient.getPatientCondition()).isEqualTo(DEFAULT_PATIENT_CONDITION);
        assertThat(testPatient.getCurrentMedication()).isEqualTo(DEFAULT_CURRENT_MEDICATION);
        assertThat(testPatient.getChronologicalHistory()).isEqualTo(DEFAULT_CHRONOLOGICAL_HISTORY);
        assertThat(testPatient.getSymptomaticChanges()).isEqualTo(DEFAULT_SYMPTOMATIC_CHANGES);
        assertThat(testPatient.getLevelOfDiscomfort()).isEqualTo(DEFAULT_LEVEL_OF_DISCOMFORT);
        assertThat(testPatient.isFeesDiscount()).isEqualTo(DEFAULT_FEES_DISCOUNT);
        assertThat(testPatient.getFeesDiscountGivenBy()).isEqualTo(DEFAULT_FEES_DISCOUNT_GIVEN_BY);
        assertThat(testPatient.getDiscountType()).isEqualTo(DEFAULT_DISCOUNT_TYPE);
        assertThat(testPatient.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testPatient.isAllowEmail()).isEqualTo(DEFAULT_ALLOW_EMAIL);
        assertThat(testPatient.isAllowSMS()).isEqualTo(DEFAULT_ALLOW_SMS);
        assertThat(testPatient.isAllowWhatsApp()).isEqualTo(DEFAULT_ALLOW_WHATS_APP);
        assertThat(testPatient.isAllowPromotions()).isEqualTo(DEFAULT_ALLOW_PROMOTIONS);
        assertThat(testPatient.isiAgree()).isEqualTo(DEFAULT_I_AGREE);
        assertThat(testPatient.getHealingLevel()).isEqualTo(DEFAULT_HEALING_LEVEL);
        assertThat(testPatient.isAllowLogin()).isEqualTo(DEFAULT_ALLOW_LOGIN);
        assertThat(testPatient.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testPatient.isVoided()).isEqualTo(DEFAULT_VOIDED);
        assertThat(testPatient.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createPatientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = patientRepository.findAll().size();

        // Create the Patient with an existing ID
        patient.setId("00000000-0000-0000-0000-000000000001");

        // An entity with an existing ID cannot be created, so this API call must fail
        restPatientMockMvc.perform(post("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFormNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setFormNumber(null);

        // Create the Patient, which fails.


        restPatientMockMvc.perform(post("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCenterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setCenterId(null);

        // Create the Patient, which fails.


        restPatientMockMvc.perform(post("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCenterNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setCenterName(null);

        // Create the Patient, which fails.


        restPatientMockMvc.perform(post("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setFirstName(null);

        // Create the Patient, which fails.


        restPatientMockMvc.perform(post("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setLastName(null);

        // Create the Patient, which fails.


        restPatientMockMvc.perform(post("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobileNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setMobileNumber(null);

        // Create the Patient, which fails.


        restPatientMockMvc.perform(post("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPatients() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList
        restPatientMockMvc.perform(get("/api/patients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(patient.getId().toString())))
            .andExpect(jsonPath("$.[*].formNumber").value(hasItem(DEFAULT_FORM_NUMBER)))
            .andExpect(jsonPath("$.[*].centerId").value(hasItem(DEFAULT_CENTER_ID.toString())))
            .andExpect(jsonPath("$.[*].centerName").value(hasItem(DEFAULT_CENTER_NAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].displayName").value(hasItem(DEFAULT_DISPLAY_NAME)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].conState").value(hasItem(DEFAULT_CON_STATE)))
            .andExpect(jsonPath("$.[*].stateCode").value(hasItem(DEFAULT_STATE_CODE)))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE)))
            .andExpect(jsonPath("$.[*].photo1ContentType").value(hasItem(DEFAULT_PHOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo1").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO_1))))
            .andExpect(jsonPath("$.[*].photo2ContentType").value(hasItem(DEFAULT_PHOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo2").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO_2))))
            .andExpect(jsonPath("$.[*].mobileNumber").value(hasItem(DEFAULT_MOBILE_NUMBER)))
            .andExpect(jsonPath("$.[*].contactNo").value(hasItem(DEFAULT_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].education").value(hasItem(DEFAULT_EDUCATION)))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].regDate").value(hasItem(DEFAULT_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION)))
            .andExpect(jsonPath("$.[*].marriageStatus").value(hasItem(DEFAULT_MARRIAGE_STATUS)))
            .andExpect(jsonPath("$.[*].expired").value(hasItem(DEFAULT_EXPIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].languageName").value(hasItem(DEFAULT_LANGUAGE_NAME)))
            .andExpect(jsonPath("$.[*].vegNonVeg").value(hasItem(DEFAULT_VEG_NON_VEG.booleanValue())))
            .andExpect(jsonPath("$.[*].referedBy").value(hasItem(DEFAULT_REFERED_BY)))
            .andExpect(jsonPath("$.[*].referdByName").value(hasItem(DEFAULT_REFERD_BY_NAME)))
            .andExpect(jsonPath("$.[*].exerciseRegularly").value(hasItem(DEFAULT_EXERCISE_REGULARLY.booleanValue())))
            .andExpect(jsonPath("$.[*].addiction").value(hasItem(DEFAULT_ADDICTION.booleanValue())))
            .andExpect(jsonPath("$.[*].hoursSleep").value(hasItem(DEFAULT_HOURS_SLEEP)))
            .andExpect(jsonPath("$.[*].wakeRested").value(hasItem(DEFAULT_WAKE_RESTED.booleanValue())))
            .andExpect(jsonPath("$.[*].waterIntake").value(hasItem(DEFAULT_WATER_INTAKE)))
            .andExpect(jsonPath("$.[*].hoursWork").value(hasItem(DEFAULT_HOURS_WORK)))
            .andExpect(jsonPath("$.[*].shiftWork").value(hasItem(DEFAULT_SHIFT_WORK.booleanValue())))
            .andExpect(jsonPath("$.[*].levelOfStress").value(hasItem(DEFAULT_LEVEL_OF_STRESS)))
            .andExpect(jsonPath("$.[*].patientCondition").value(hasItem(DEFAULT_PATIENT_CONDITION)))
            .andExpect(jsonPath("$.[*].currentMedication").value(hasItem(DEFAULT_CURRENT_MEDICATION)))
            .andExpect(jsonPath("$.[*].chronologicalHistory").value(hasItem(DEFAULT_CHRONOLOGICAL_HISTORY)))
            .andExpect(jsonPath("$.[*].symptomaticChanges").value(hasItem(DEFAULT_SYMPTOMATIC_CHANGES)))
            .andExpect(jsonPath("$.[*].levelOfDiscomfort").value(hasItem(DEFAULT_LEVEL_OF_DISCOMFORT)))
            .andExpect(jsonPath("$.[*].feesDiscount").value(hasItem(DEFAULT_FEES_DISCOUNT.booleanValue())))
            .andExpect(jsonPath("$.[*].feesDiscountGivenBy").value(hasItem(DEFAULT_FEES_DISCOUNT_GIVEN_BY)))
            .andExpect(jsonPath("$.[*].discountType").value(hasItem(DEFAULT_DISCOUNT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.toString())))
            .andExpect(jsonPath("$.[*].allowEmail").value(hasItem(DEFAULT_ALLOW_EMAIL.booleanValue())))
            .andExpect(jsonPath("$.[*].allowSMS").value(hasItem(DEFAULT_ALLOW_SMS.booleanValue())))
            .andExpect(jsonPath("$.[*].allowWhatsApp").value(hasItem(DEFAULT_ALLOW_WHATS_APP.booleanValue())))
            .andExpect(jsonPath("$.[*].allowPromotions").value(hasItem(DEFAULT_ALLOW_PROMOTIONS.booleanValue())))
            .andExpect(jsonPath("$.[*].iAgree").value(hasItem(DEFAULT_I_AGREE.booleanValue())))
            .andExpect(jsonPath("$.[*].healingLevel").value(hasItem(DEFAULT_HEALING_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].allowLogin").value(hasItem(DEFAULT_ALLOW_LOGIN.booleanValue())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].voided").value(hasItem(DEFAULT_VOIDED.booleanValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())));
    }
    
    @Test
    @Transactional
    public void getPatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get the patient
        restPatientMockMvc.perform(get("/api/patients/{id}", patient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(patient.getId().toString()))
            .andExpect(jsonPath("$.formNumber").value(DEFAULT_FORM_NUMBER))
            .andExpect(jsonPath("$.centerId").value(DEFAULT_CENTER_ID.toString()))
            .andExpect(jsonPath("$.centerName").value(DEFAULT_CENTER_NAME))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.displayName").value(DEFAULT_DISPLAY_NAME))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.conState").value(DEFAULT_CON_STATE))
            .andExpect(jsonPath("$.stateCode").value(DEFAULT_STATE_CODE))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE))
            .andExpect(jsonPath("$.photo1ContentType").value(DEFAULT_PHOTO_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo1").value(Base64Utils.encodeToString(DEFAULT_PHOTO_1)))
            .andExpect(jsonPath("$.photo2ContentType").value(DEFAULT_PHOTO_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo2").value(Base64Utils.encodeToString(DEFAULT_PHOTO_2)))
            .andExpect(jsonPath("$.mobileNumber").value(DEFAULT_MOBILE_NUMBER))
            .andExpect(jsonPath("$.contactNo").value(DEFAULT_CONTACT_NO))
            .andExpect(jsonPath("$.education").value(DEFAULT_EDUCATION))
            .andExpect(jsonPath("$.bloodGroup").value(DEFAULT_BLOOD_GROUP))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.regDate").value(DEFAULT_REG_DATE.toString()))
            .andExpect(jsonPath("$.profession").value(DEFAULT_PROFESSION))
            .andExpect(jsonPath("$.marriageStatus").value(DEFAULT_MARRIAGE_STATUS))
            .andExpect(jsonPath("$.expired").value(DEFAULT_EXPIRED.booleanValue()))
            .andExpect(jsonPath("$.languageName").value(DEFAULT_LANGUAGE_NAME))
            .andExpect(jsonPath("$.vegNonVeg").value(DEFAULT_VEG_NON_VEG.booleanValue()))
            .andExpect(jsonPath("$.referedBy").value(DEFAULT_REFERED_BY))
            .andExpect(jsonPath("$.referdByName").value(DEFAULT_REFERD_BY_NAME))
            .andExpect(jsonPath("$.exerciseRegularly").value(DEFAULT_EXERCISE_REGULARLY.booleanValue()))
            .andExpect(jsonPath("$.addiction").value(DEFAULT_ADDICTION.booleanValue()))
            .andExpect(jsonPath("$.hoursSleep").value(DEFAULT_HOURS_SLEEP))
            .andExpect(jsonPath("$.wakeRested").value(DEFAULT_WAKE_RESTED.booleanValue()))
            .andExpect(jsonPath("$.waterIntake").value(DEFAULT_WATER_INTAKE))
            .andExpect(jsonPath("$.hoursWork").value(DEFAULT_HOURS_WORK))
            .andExpect(jsonPath("$.shiftWork").value(DEFAULT_SHIFT_WORK.booleanValue()))
            .andExpect(jsonPath("$.levelOfStress").value(DEFAULT_LEVEL_OF_STRESS))
            .andExpect(jsonPath("$.patientCondition").value(DEFAULT_PATIENT_CONDITION))
            .andExpect(jsonPath("$.currentMedication").value(DEFAULT_CURRENT_MEDICATION))
            .andExpect(jsonPath("$.chronologicalHistory").value(DEFAULT_CHRONOLOGICAL_HISTORY))
            .andExpect(jsonPath("$.symptomaticChanges").value(DEFAULT_SYMPTOMATIC_CHANGES))
            .andExpect(jsonPath("$.levelOfDiscomfort").value(DEFAULT_LEVEL_OF_DISCOMFORT))
            .andExpect(jsonPath("$.feesDiscount").value(DEFAULT_FEES_DISCOUNT.booleanValue()))
            .andExpect(jsonPath("$.feesDiscountGivenBy").value(DEFAULT_FEES_DISCOUNT_GIVEN_BY))
            .andExpect(jsonPath("$.discountType").value(DEFAULT_DISCOUNT_TYPE.toString()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.toString()))
            .andExpect(jsonPath("$.allowEmail").value(DEFAULT_ALLOW_EMAIL.booleanValue()))
            .andExpect(jsonPath("$.allowSMS").value(DEFAULT_ALLOW_SMS.booleanValue()))
            .andExpect(jsonPath("$.allowWhatsApp").value(DEFAULT_ALLOW_WHATS_APP.booleanValue()))
            .andExpect(jsonPath("$.allowPromotions").value(DEFAULT_ALLOW_PROMOTIONS.booleanValue()))
            .andExpect(jsonPath("$.iAgree").value(DEFAULT_I_AGREE.booleanValue()))
            .andExpect(jsonPath("$.healingLevel").value(DEFAULT_HEALING_LEVEL.toString()))
            .andExpect(jsonPath("$.allowLogin").value(DEFAULT_ALLOW_LOGIN.booleanValue()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.voided").value(DEFAULT_VOIDED.booleanValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()));
    }


    @Test
    @Transactional
    public void getPatientsByIdFiltering() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        UUID id = patient.getId();

        defaultPatientShouldBeFound("id.equals=" + id);
        defaultPatientShouldNotBeFound("id.notEquals=" + id);

        defaultPatientShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultPatientShouldNotBeFound("id.greaterThan=" + id);

        defaultPatientShouldBeFound("id.lessThanOrEqual=" + id);
        defaultPatientShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllPatientsByFormNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where formNumber equals to DEFAULT_FORM_NUMBER
        defaultPatientShouldBeFound("formNumber.equals=" + DEFAULT_FORM_NUMBER);

        // Get all the patientList where formNumber equals to UPDATED_FORM_NUMBER
        defaultPatientShouldNotBeFound("formNumber.equals=" + UPDATED_FORM_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPatientsByFormNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where formNumber not equals to DEFAULT_FORM_NUMBER
        defaultPatientShouldNotBeFound("formNumber.notEquals=" + DEFAULT_FORM_NUMBER);

        // Get all the patientList where formNumber not equals to UPDATED_FORM_NUMBER
        defaultPatientShouldBeFound("formNumber.notEquals=" + UPDATED_FORM_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPatientsByFormNumberIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where formNumber in DEFAULT_FORM_NUMBER or UPDATED_FORM_NUMBER
        defaultPatientShouldBeFound("formNumber.in=" + DEFAULT_FORM_NUMBER + "," + UPDATED_FORM_NUMBER);

        // Get all the patientList where formNumber equals to UPDATED_FORM_NUMBER
        defaultPatientShouldNotBeFound("formNumber.in=" + UPDATED_FORM_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPatientsByFormNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where formNumber is not null
        defaultPatientShouldBeFound("formNumber.specified=true");

        // Get all the patientList where formNumber is null
        defaultPatientShouldNotBeFound("formNumber.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByFormNumberContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where formNumber contains DEFAULT_FORM_NUMBER
        defaultPatientShouldBeFound("formNumber.contains=" + DEFAULT_FORM_NUMBER);

        // Get all the patientList where formNumber contains UPDATED_FORM_NUMBER
        defaultPatientShouldNotBeFound("formNumber.contains=" + UPDATED_FORM_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPatientsByFormNumberNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where formNumber does not contain DEFAULT_FORM_NUMBER
        defaultPatientShouldNotBeFound("formNumber.doesNotContain=" + DEFAULT_FORM_NUMBER);

        // Get all the patientList where formNumber does not contain UPDATED_FORM_NUMBER
        defaultPatientShouldBeFound("formNumber.doesNotContain=" + UPDATED_FORM_NUMBER);
    }


    @Test
    @Transactional
    public void getAllPatientsByCenterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where centerId equals to DEFAULT_CENTER_ID
        defaultPatientShouldBeFound("centerId.equals=" + DEFAULT_CENTER_ID);

        // Get all the patientList where centerId equals to UPDATED_CENTER_ID
        defaultPatientShouldNotBeFound("centerId.equals=" + UPDATED_CENTER_ID);
    }

    @Test
    @Transactional
    public void getAllPatientsByCenterIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where centerId not equals to DEFAULT_CENTER_ID
        defaultPatientShouldNotBeFound("centerId.notEquals=" + DEFAULT_CENTER_ID);

        // Get all the patientList where centerId not equals to UPDATED_CENTER_ID
        defaultPatientShouldBeFound("centerId.notEquals=" + UPDATED_CENTER_ID);
    }

    @Test
    @Transactional
    public void getAllPatientsByCenterIdIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where centerId in DEFAULT_CENTER_ID or UPDATED_CENTER_ID
        defaultPatientShouldBeFound("centerId.in=" + DEFAULT_CENTER_ID + "," + UPDATED_CENTER_ID);

        // Get all the patientList where centerId equals to UPDATED_CENTER_ID
        defaultPatientShouldNotBeFound("centerId.in=" + UPDATED_CENTER_ID);
    }

    @Test
    @Transactional
    public void getAllPatientsByCenterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where centerId is not null
        defaultPatientShouldBeFound("centerId.specified=true");

        // Get all the patientList where centerId is null
        defaultPatientShouldNotBeFound("centerId.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByCenterNameIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where centerName equals to DEFAULT_CENTER_NAME
        defaultPatientShouldBeFound("centerName.equals=" + DEFAULT_CENTER_NAME);

        // Get all the patientList where centerName equals to UPDATED_CENTER_NAME
        defaultPatientShouldNotBeFound("centerName.equals=" + UPDATED_CENTER_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByCenterNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where centerName not equals to DEFAULT_CENTER_NAME
        defaultPatientShouldNotBeFound("centerName.notEquals=" + DEFAULT_CENTER_NAME);

        // Get all the patientList where centerName not equals to UPDATED_CENTER_NAME
        defaultPatientShouldBeFound("centerName.notEquals=" + UPDATED_CENTER_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByCenterNameIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where centerName in DEFAULT_CENTER_NAME or UPDATED_CENTER_NAME
        defaultPatientShouldBeFound("centerName.in=" + DEFAULT_CENTER_NAME + "," + UPDATED_CENTER_NAME);

        // Get all the patientList where centerName equals to UPDATED_CENTER_NAME
        defaultPatientShouldNotBeFound("centerName.in=" + UPDATED_CENTER_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByCenterNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where centerName is not null
        defaultPatientShouldBeFound("centerName.specified=true");

        // Get all the patientList where centerName is null
        defaultPatientShouldNotBeFound("centerName.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByCenterNameContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where centerName contains DEFAULT_CENTER_NAME
        defaultPatientShouldBeFound("centerName.contains=" + DEFAULT_CENTER_NAME);

        // Get all the patientList where centerName contains UPDATED_CENTER_NAME
        defaultPatientShouldNotBeFound("centerName.contains=" + UPDATED_CENTER_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByCenterNameNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where centerName does not contain DEFAULT_CENTER_NAME
        defaultPatientShouldNotBeFound("centerName.doesNotContain=" + DEFAULT_CENTER_NAME);

        // Get all the patientList where centerName does not contain UPDATED_CENTER_NAME
        defaultPatientShouldBeFound("centerName.doesNotContain=" + UPDATED_CENTER_NAME);
    }


    @Test
    @Transactional
    public void getAllPatientsByFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where firstName equals to DEFAULT_FIRST_NAME
        defaultPatientShouldBeFound("firstName.equals=" + DEFAULT_FIRST_NAME);

        // Get all the patientList where firstName equals to UPDATED_FIRST_NAME
        defaultPatientShouldNotBeFound("firstName.equals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByFirstNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where firstName not equals to DEFAULT_FIRST_NAME
        defaultPatientShouldNotBeFound("firstName.notEquals=" + DEFAULT_FIRST_NAME);

        // Get all the patientList where firstName not equals to UPDATED_FIRST_NAME
        defaultPatientShouldBeFound("firstName.notEquals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where firstName in DEFAULT_FIRST_NAME or UPDATED_FIRST_NAME
        defaultPatientShouldBeFound("firstName.in=" + DEFAULT_FIRST_NAME + "," + UPDATED_FIRST_NAME);

        // Get all the patientList where firstName equals to UPDATED_FIRST_NAME
        defaultPatientShouldNotBeFound("firstName.in=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where firstName is not null
        defaultPatientShouldBeFound("firstName.specified=true");

        // Get all the patientList where firstName is null
        defaultPatientShouldNotBeFound("firstName.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByFirstNameContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where firstName contains DEFAULT_FIRST_NAME
        defaultPatientShouldBeFound("firstName.contains=" + DEFAULT_FIRST_NAME);

        // Get all the patientList where firstName contains UPDATED_FIRST_NAME
        defaultPatientShouldNotBeFound("firstName.contains=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByFirstNameNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where firstName does not contain DEFAULT_FIRST_NAME
        defaultPatientShouldNotBeFound("firstName.doesNotContain=" + DEFAULT_FIRST_NAME);

        // Get all the patientList where firstName does not contain UPDATED_FIRST_NAME
        defaultPatientShouldBeFound("firstName.doesNotContain=" + UPDATED_FIRST_NAME);
    }


    @Test
    @Transactional
    public void getAllPatientsByMiddleNameIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where middleName equals to DEFAULT_MIDDLE_NAME
        defaultPatientShouldBeFound("middleName.equals=" + DEFAULT_MIDDLE_NAME);

        // Get all the patientList where middleName equals to UPDATED_MIDDLE_NAME
        defaultPatientShouldNotBeFound("middleName.equals=" + UPDATED_MIDDLE_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByMiddleNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where middleName not equals to DEFAULT_MIDDLE_NAME
        defaultPatientShouldNotBeFound("middleName.notEquals=" + DEFAULT_MIDDLE_NAME);

        // Get all the patientList where middleName not equals to UPDATED_MIDDLE_NAME
        defaultPatientShouldBeFound("middleName.notEquals=" + UPDATED_MIDDLE_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByMiddleNameIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where middleName in DEFAULT_MIDDLE_NAME or UPDATED_MIDDLE_NAME
        defaultPatientShouldBeFound("middleName.in=" + DEFAULT_MIDDLE_NAME + "," + UPDATED_MIDDLE_NAME);

        // Get all the patientList where middleName equals to UPDATED_MIDDLE_NAME
        defaultPatientShouldNotBeFound("middleName.in=" + UPDATED_MIDDLE_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByMiddleNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where middleName is not null
        defaultPatientShouldBeFound("middleName.specified=true");

        // Get all the patientList where middleName is null
        defaultPatientShouldNotBeFound("middleName.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByMiddleNameContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where middleName contains DEFAULT_MIDDLE_NAME
        defaultPatientShouldBeFound("middleName.contains=" + DEFAULT_MIDDLE_NAME);

        // Get all the patientList where middleName contains UPDATED_MIDDLE_NAME
        defaultPatientShouldNotBeFound("middleName.contains=" + UPDATED_MIDDLE_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByMiddleNameNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where middleName does not contain DEFAULT_MIDDLE_NAME
        defaultPatientShouldNotBeFound("middleName.doesNotContain=" + DEFAULT_MIDDLE_NAME);

        // Get all the patientList where middleName does not contain UPDATED_MIDDLE_NAME
        defaultPatientShouldBeFound("middleName.doesNotContain=" + UPDATED_MIDDLE_NAME);
    }


    @Test
    @Transactional
    public void getAllPatientsByLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where lastName equals to DEFAULT_LAST_NAME
        defaultPatientShouldBeFound("lastName.equals=" + DEFAULT_LAST_NAME);

        // Get all the patientList where lastName equals to UPDATED_LAST_NAME
        defaultPatientShouldNotBeFound("lastName.equals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByLastNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where lastName not equals to DEFAULT_LAST_NAME
        defaultPatientShouldNotBeFound("lastName.notEquals=" + DEFAULT_LAST_NAME);

        // Get all the patientList where lastName not equals to UPDATED_LAST_NAME
        defaultPatientShouldBeFound("lastName.notEquals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where lastName in DEFAULT_LAST_NAME or UPDATED_LAST_NAME
        defaultPatientShouldBeFound("lastName.in=" + DEFAULT_LAST_NAME + "," + UPDATED_LAST_NAME);

        // Get all the patientList where lastName equals to UPDATED_LAST_NAME
        defaultPatientShouldNotBeFound("lastName.in=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where lastName is not null
        defaultPatientShouldBeFound("lastName.specified=true");

        // Get all the patientList where lastName is null
        defaultPatientShouldNotBeFound("lastName.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByLastNameContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where lastName contains DEFAULT_LAST_NAME
        defaultPatientShouldBeFound("lastName.contains=" + DEFAULT_LAST_NAME);

        // Get all the patientList where lastName contains UPDATED_LAST_NAME
        defaultPatientShouldNotBeFound("lastName.contains=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByLastNameNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where lastName does not contain DEFAULT_LAST_NAME
        defaultPatientShouldNotBeFound("lastName.doesNotContain=" + DEFAULT_LAST_NAME);

        // Get all the patientList where lastName does not contain UPDATED_LAST_NAME
        defaultPatientShouldBeFound("lastName.doesNotContain=" + UPDATED_LAST_NAME);
    }


    @Test
    @Transactional
    public void getAllPatientsByDisplayNameIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where displayName equals to DEFAULT_DISPLAY_NAME
        defaultPatientShouldBeFound("displayName.equals=" + DEFAULT_DISPLAY_NAME);

        // Get all the patientList where displayName equals to UPDATED_DISPLAY_NAME
        defaultPatientShouldNotBeFound("displayName.equals=" + UPDATED_DISPLAY_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByDisplayNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where displayName not equals to DEFAULT_DISPLAY_NAME
        defaultPatientShouldNotBeFound("displayName.notEquals=" + DEFAULT_DISPLAY_NAME);

        // Get all the patientList where displayName not equals to UPDATED_DISPLAY_NAME
        defaultPatientShouldBeFound("displayName.notEquals=" + UPDATED_DISPLAY_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByDisplayNameIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where displayName in DEFAULT_DISPLAY_NAME or UPDATED_DISPLAY_NAME
        defaultPatientShouldBeFound("displayName.in=" + DEFAULT_DISPLAY_NAME + "," + UPDATED_DISPLAY_NAME);

        // Get all the patientList where displayName equals to UPDATED_DISPLAY_NAME
        defaultPatientShouldNotBeFound("displayName.in=" + UPDATED_DISPLAY_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByDisplayNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where displayName is not null
        defaultPatientShouldBeFound("displayName.specified=true");

        // Get all the patientList where displayName is null
        defaultPatientShouldNotBeFound("displayName.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByDisplayNameContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where displayName contains DEFAULT_DISPLAY_NAME
        defaultPatientShouldBeFound("displayName.contains=" + DEFAULT_DISPLAY_NAME);

        // Get all the patientList where displayName contains UPDATED_DISPLAY_NAME
        defaultPatientShouldNotBeFound("displayName.contains=" + UPDATED_DISPLAY_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByDisplayNameNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where displayName does not contain DEFAULT_DISPLAY_NAME
        defaultPatientShouldNotBeFound("displayName.doesNotContain=" + DEFAULT_DISPLAY_NAME);

        // Get all the patientList where displayName does not contain UPDATED_DISPLAY_NAME
        defaultPatientShouldBeFound("displayName.doesNotContain=" + UPDATED_DISPLAY_NAME);
    }


    @Test
    @Transactional
    public void getAllPatientsByDateOfBirthIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where dateOfBirth equals to DEFAULT_DATE_OF_BIRTH
        defaultPatientShouldBeFound("dateOfBirth.equals=" + DEFAULT_DATE_OF_BIRTH);

        // Get all the patientList where dateOfBirth equals to UPDATED_DATE_OF_BIRTH
        defaultPatientShouldNotBeFound("dateOfBirth.equals=" + UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    public void getAllPatientsByDateOfBirthIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where dateOfBirth not equals to DEFAULT_DATE_OF_BIRTH
        defaultPatientShouldNotBeFound("dateOfBirth.notEquals=" + DEFAULT_DATE_OF_BIRTH);

        // Get all the patientList where dateOfBirth not equals to UPDATED_DATE_OF_BIRTH
        defaultPatientShouldBeFound("dateOfBirth.notEquals=" + UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    public void getAllPatientsByDateOfBirthIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where dateOfBirth in DEFAULT_DATE_OF_BIRTH or UPDATED_DATE_OF_BIRTH
        defaultPatientShouldBeFound("dateOfBirth.in=" + DEFAULT_DATE_OF_BIRTH + "," + UPDATED_DATE_OF_BIRTH);

        // Get all the patientList where dateOfBirth equals to UPDATED_DATE_OF_BIRTH
        defaultPatientShouldNotBeFound("dateOfBirth.in=" + UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    public void getAllPatientsByDateOfBirthIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where dateOfBirth is not null
        defaultPatientShouldBeFound("dateOfBirth.specified=true");

        // Get all the patientList where dateOfBirth is null
        defaultPatientShouldNotBeFound("dateOfBirth.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByGenderIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where gender equals to DEFAULT_GENDER
        defaultPatientShouldBeFound("gender.equals=" + DEFAULT_GENDER);

        // Get all the patientList where gender equals to UPDATED_GENDER
        defaultPatientShouldNotBeFound("gender.equals=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    public void getAllPatientsByGenderIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where gender not equals to DEFAULT_GENDER
        defaultPatientShouldNotBeFound("gender.notEquals=" + DEFAULT_GENDER);

        // Get all the patientList where gender not equals to UPDATED_GENDER
        defaultPatientShouldBeFound("gender.notEquals=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    public void getAllPatientsByGenderIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where gender in DEFAULT_GENDER or UPDATED_GENDER
        defaultPatientShouldBeFound("gender.in=" + DEFAULT_GENDER + "," + UPDATED_GENDER);

        // Get all the patientList where gender equals to UPDATED_GENDER
        defaultPatientShouldNotBeFound("gender.in=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    public void getAllPatientsByGenderIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where gender is not null
        defaultPatientShouldBeFound("gender.specified=true");

        // Get all the patientList where gender is null
        defaultPatientShouldNotBeFound("gender.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByAgeIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where age equals to DEFAULT_AGE
        defaultPatientShouldBeFound("age.equals=" + DEFAULT_AGE);

        // Get all the patientList where age equals to UPDATED_AGE
        defaultPatientShouldNotBeFound("age.equals=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    public void getAllPatientsByAgeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where age not equals to DEFAULT_AGE
        defaultPatientShouldNotBeFound("age.notEquals=" + DEFAULT_AGE);

        // Get all the patientList where age not equals to UPDATED_AGE
        defaultPatientShouldBeFound("age.notEquals=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    public void getAllPatientsByAgeIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where age in DEFAULT_AGE or UPDATED_AGE
        defaultPatientShouldBeFound("age.in=" + DEFAULT_AGE + "," + UPDATED_AGE);

        // Get all the patientList where age equals to UPDATED_AGE
        defaultPatientShouldNotBeFound("age.in=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    public void getAllPatientsByAgeIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where age is not null
        defaultPatientShouldBeFound("age.specified=true");

        // Get all the patientList where age is null
        defaultPatientShouldNotBeFound("age.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByAgeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where age is greater than or equal to DEFAULT_AGE
        defaultPatientShouldBeFound("age.greaterThanOrEqual=" + DEFAULT_AGE);

        // Get all the patientList where age is greater than or equal to UPDATED_AGE
        defaultPatientShouldNotBeFound("age.greaterThanOrEqual=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    public void getAllPatientsByAgeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where age is less than or equal to DEFAULT_AGE
        defaultPatientShouldBeFound("age.lessThanOrEqual=" + DEFAULT_AGE);

        // Get all the patientList where age is less than or equal to SMALLER_AGE
        defaultPatientShouldNotBeFound("age.lessThanOrEqual=" + SMALLER_AGE);
    }

    @Test
    @Transactional
    public void getAllPatientsByAgeIsLessThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where age is less than DEFAULT_AGE
        defaultPatientShouldNotBeFound("age.lessThan=" + DEFAULT_AGE);

        // Get all the patientList where age is less than UPDATED_AGE
        defaultPatientShouldBeFound("age.lessThan=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    public void getAllPatientsByAgeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where age is greater than DEFAULT_AGE
        defaultPatientShouldNotBeFound("age.greaterThan=" + DEFAULT_AGE);

        // Get all the patientList where age is greater than SMALLER_AGE
        defaultPatientShouldBeFound("age.greaterThan=" + SMALLER_AGE);
    }


    @Test
    @Transactional
    public void getAllPatientsByAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where address equals to DEFAULT_ADDRESS
        defaultPatientShouldBeFound("address.equals=" + DEFAULT_ADDRESS);

        // Get all the patientList where address equals to UPDATED_ADDRESS
        defaultPatientShouldNotBeFound("address.equals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllPatientsByAddressIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where address not equals to DEFAULT_ADDRESS
        defaultPatientShouldNotBeFound("address.notEquals=" + DEFAULT_ADDRESS);

        // Get all the patientList where address not equals to UPDATED_ADDRESS
        defaultPatientShouldBeFound("address.notEquals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllPatientsByAddressIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where address in DEFAULT_ADDRESS or UPDATED_ADDRESS
        defaultPatientShouldBeFound("address.in=" + DEFAULT_ADDRESS + "," + UPDATED_ADDRESS);

        // Get all the patientList where address equals to UPDATED_ADDRESS
        defaultPatientShouldNotBeFound("address.in=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllPatientsByAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where address is not null
        defaultPatientShouldBeFound("address.specified=true");

        // Get all the patientList where address is null
        defaultPatientShouldNotBeFound("address.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByAddressContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where address contains DEFAULT_ADDRESS
        defaultPatientShouldBeFound("address.contains=" + DEFAULT_ADDRESS);

        // Get all the patientList where address contains UPDATED_ADDRESS
        defaultPatientShouldNotBeFound("address.contains=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllPatientsByAddressNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where address does not contain DEFAULT_ADDRESS
        defaultPatientShouldNotBeFound("address.doesNotContain=" + DEFAULT_ADDRESS);

        // Get all the patientList where address does not contain UPDATED_ADDRESS
        defaultPatientShouldBeFound("address.doesNotContain=" + UPDATED_ADDRESS);
    }


    @Test
    @Transactional
    public void getAllPatientsByAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where area equals to DEFAULT_AREA
        defaultPatientShouldBeFound("area.equals=" + DEFAULT_AREA);

        // Get all the patientList where area equals to UPDATED_AREA
        defaultPatientShouldNotBeFound("area.equals=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllPatientsByAreaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where area not equals to DEFAULT_AREA
        defaultPatientShouldNotBeFound("area.notEquals=" + DEFAULT_AREA);

        // Get all the patientList where area not equals to UPDATED_AREA
        defaultPatientShouldBeFound("area.notEquals=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllPatientsByAreaIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where area in DEFAULT_AREA or UPDATED_AREA
        defaultPatientShouldBeFound("area.in=" + DEFAULT_AREA + "," + UPDATED_AREA);

        // Get all the patientList where area equals to UPDATED_AREA
        defaultPatientShouldNotBeFound("area.in=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllPatientsByAreaIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where area is not null
        defaultPatientShouldBeFound("area.specified=true");

        // Get all the patientList where area is null
        defaultPatientShouldNotBeFound("area.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByAreaContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where area contains DEFAULT_AREA
        defaultPatientShouldBeFound("area.contains=" + DEFAULT_AREA);

        // Get all the patientList where area contains UPDATED_AREA
        defaultPatientShouldNotBeFound("area.contains=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllPatientsByAreaNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where area does not contain DEFAULT_AREA
        defaultPatientShouldNotBeFound("area.doesNotContain=" + DEFAULT_AREA);

        // Get all the patientList where area does not contain UPDATED_AREA
        defaultPatientShouldBeFound("area.doesNotContain=" + UPDATED_AREA);
    }


    @Test
    @Transactional
    public void getAllPatientsByCityIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where city equals to DEFAULT_CITY
        defaultPatientShouldBeFound("city.equals=" + DEFAULT_CITY);

        // Get all the patientList where city equals to UPDATED_CITY
        defaultPatientShouldNotBeFound("city.equals=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllPatientsByCityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where city not equals to DEFAULT_CITY
        defaultPatientShouldNotBeFound("city.notEquals=" + DEFAULT_CITY);

        // Get all the patientList where city not equals to UPDATED_CITY
        defaultPatientShouldBeFound("city.notEquals=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllPatientsByCityIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where city in DEFAULT_CITY or UPDATED_CITY
        defaultPatientShouldBeFound("city.in=" + DEFAULT_CITY + "," + UPDATED_CITY);

        // Get all the patientList where city equals to UPDATED_CITY
        defaultPatientShouldNotBeFound("city.in=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllPatientsByCityIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where city is not null
        defaultPatientShouldBeFound("city.specified=true");

        // Get all the patientList where city is null
        defaultPatientShouldNotBeFound("city.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByCityContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where city contains DEFAULT_CITY
        defaultPatientShouldBeFound("city.contains=" + DEFAULT_CITY);

        // Get all the patientList where city contains UPDATED_CITY
        defaultPatientShouldNotBeFound("city.contains=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllPatientsByCityNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where city does not contain DEFAULT_CITY
        defaultPatientShouldNotBeFound("city.doesNotContain=" + DEFAULT_CITY);

        // Get all the patientList where city does not contain UPDATED_CITY
        defaultPatientShouldBeFound("city.doesNotContain=" + UPDATED_CITY);
    }


    @Test
    @Transactional
    public void getAllPatientsByDistrictIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where district equals to DEFAULT_DISTRICT
        defaultPatientShouldBeFound("district.equals=" + DEFAULT_DISTRICT);

        // Get all the patientList where district equals to UPDATED_DISTRICT
        defaultPatientShouldNotBeFound("district.equals=" + UPDATED_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllPatientsByDistrictIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where district not equals to DEFAULT_DISTRICT
        defaultPatientShouldNotBeFound("district.notEquals=" + DEFAULT_DISTRICT);

        // Get all the patientList where district not equals to UPDATED_DISTRICT
        defaultPatientShouldBeFound("district.notEquals=" + UPDATED_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllPatientsByDistrictIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where district in DEFAULT_DISTRICT or UPDATED_DISTRICT
        defaultPatientShouldBeFound("district.in=" + DEFAULT_DISTRICT + "," + UPDATED_DISTRICT);

        // Get all the patientList where district equals to UPDATED_DISTRICT
        defaultPatientShouldNotBeFound("district.in=" + UPDATED_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllPatientsByDistrictIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where district is not null
        defaultPatientShouldBeFound("district.specified=true");

        // Get all the patientList where district is null
        defaultPatientShouldNotBeFound("district.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByDistrictContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where district contains DEFAULT_DISTRICT
        defaultPatientShouldBeFound("district.contains=" + DEFAULT_DISTRICT);

        // Get all the patientList where district contains UPDATED_DISTRICT
        defaultPatientShouldNotBeFound("district.contains=" + UPDATED_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllPatientsByDistrictNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where district does not contain DEFAULT_DISTRICT
        defaultPatientShouldNotBeFound("district.doesNotContain=" + DEFAULT_DISTRICT);

        // Get all the patientList where district does not contain UPDATED_DISTRICT
        defaultPatientShouldBeFound("district.doesNotContain=" + UPDATED_DISTRICT);
    }


    @Test
    @Transactional
    public void getAllPatientsByConStateIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where conState equals to DEFAULT_CON_STATE
        defaultPatientShouldBeFound("conState.equals=" + DEFAULT_CON_STATE);

        // Get all the patientList where conState equals to UPDATED_CON_STATE
        defaultPatientShouldNotBeFound("conState.equals=" + UPDATED_CON_STATE);
    }

    @Test
    @Transactional
    public void getAllPatientsByConStateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where conState not equals to DEFAULT_CON_STATE
        defaultPatientShouldNotBeFound("conState.notEquals=" + DEFAULT_CON_STATE);

        // Get all the patientList where conState not equals to UPDATED_CON_STATE
        defaultPatientShouldBeFound("conState.notEquals=" + UPDATED_CON_STATE);
    }

    @Test
    @Transactional
    public void getAllPatientsByConStateIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where conState in DEFAULT_CON_STATE or UPDATED_CON_STATE
        defaultPatientShouldBeFound("conState.in=" + DEFAULT_CON_STATE + "," + UPDATED_CON_STATE);

        // Get all the patientList where conState equals to UPDATED_CON_STATE
        defaultPatientShouldNotBeFound("conState.in=" + UPDATED_CON_STATE);
    }

    @Test
    @Transactional
    public void getAllPatientsByConStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where conState is not null
        defaultPatientShouldBeFound("conState.specified=true");

        // Get all the patientList where conState is null
        defaultPatientShouldNotBeFound("conState.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByConStateContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where conState contains DEFAULT_CON_STATE
        defaultPatientShouldBeFound("conState.contains=" + DEFAULT_CON_STATE);

        // Get all the patientList where conState contains UPDATED_CON_STATE
        defaultPatientShouldNotBeFound("conState.contains=" + UPDATED_CON_STATE);
    }

    @Test
    @Transactional
    public void getAllPatientsByConStateNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where conState does not contain DEFAULT_CON_STATE
        defaultPatientShouldNotBeFound("conState.doesNotContain=" + DEFAULT_CON_STATE);

        // Get all the patientList where conState does not contain UPDATED_CON_STATE
        defaultPatientShouldBeFound("conState.doesNotContain=" + UPDATED_CON_STATE);
    }


    @Test
    @Transactional
    public void getAllPatientsByStateCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where stateCode equals to DEFAULT_STATE_CODE
        defaultPatientShouldBeFound("stateCode.equals=" + DEFAULT_STATE_CODE);

        // Get all the patientList where stateCode equals to UPDATED_STATE_CODE
        defaultPatientShouldNotBeFound("stateCode.equals=" + UPDATED_STATE_CODE);
    }

    @Test
    @Transactional
    public void getAllPatientsByStateCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where stateCode not equals to DEFAULT_STATE_CODE
        defaultPatientShouldNotBeFound("stateCode.notEquals=" + DEFAULT_STATE_CODE);

        // Get all the patientList where stateCode not equals to UPDATED_STATE_CODE
        defaultPatientShouldBeFound("stateCode.notEquals=" + UPDATED_STATE_CODE);
    }

    @Test
    @Transactional
    public void getAllPatientsByStateCodeIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where stateCode in DEFAULT_STATE_CODE or UPDATED_STATE_CODE
        defaultPatientShouldBeFound("stateCode.in=" + DEFAULT_STATE_CODE + "," + UPDATED_STATE_CODE);

        // Get all the patientList where stateCode equals to UPDATED_STATE_CODE
        defaultPatientShouldNotBeFound("stateCode.in=" + UPDATED_STATE_CODE);
    }

    @Test
    @Transactional
    public void getAllPatientsByStateCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where stateCode is not null
        defaultPatientShouldBeFound("stateCode.specified=true");

        // Get all the patientList where stateCode is null
        defaultPatientShouldNotBeFound("stateCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByStateCodeContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where stateCode contains DEFAULT_STATE_CODE
        defaultPatientShouldBeFound("stateCode.contains=" + DEFAULT_STATE_CODE);

        // Get all the patientList where stateCode contains UPDATED_STATE_CODE
        defaultPatientShouldNotBeFound("stateCode.contains=" + UPDATED_STATE_CODE);
    }

    @Test
    @Transactional
    public void getAllPatientsByStateCodeNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where stateCode does not contain DEFAULT_STATE_CODE
        defaultPatientShouldNotBeFound("stateCode.doesNotContain=" + DEFAULT_STATE_CODE);

        // Get all the patientList where stateCode does not contain UPDATED_STATE_CODE
        defaultPatientShouldBeFound("stateCode.doesNotContain=" + UPDATED_STATE_CODE);
    }


    @Test
    @Transactional
    public void getAllPatientsByRegionIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where region equals to DEFAULT_REGION
        defaultPatientShouldBeFound("region.equals=" + DEFAULT_REGION);

        // Get all the patientList where region equals to UPDATED_REGION
        defaultPatientShouldNotBeFound("region.equals=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllPatientsByRegionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where region not equals to DEFAULT_REGION
        defaultPatientShouldNotBeFound("region.notEquals=" + DEFAULT_REGION);

        // Get all the patientList where region not equals to UPDATED_REGION
        defaultPatientShouldBeFound("region.notEquals=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllPatientsByRegionIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where region in DEFAULT_REGION or UPDATED_REGION
        defaultPatientShouldBeFound("region.in=" + DEFAULT_REGION + "," + UPDATED_REGION);

        // Get all the patientList where region equals to UPDATED_REGION
        defaultPatientShouldNotBeFound("region.in=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllPatientsByRegionIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where region is not null
        defaultPatientShouldBeFound("region.specified=true");

        // Get all the patientList where region is null
        defaultPatientShouldNotBeFound("region.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByRegionContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where region contains DEFAULT_REGION
        defaultPatientShouldBeFound("region.contains=" + DEFAULT_REGION);

        // Get all the patientList where region contains UPDATED_REGION
        defaultPatientShouldNotBeFound("region.contains=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllPatientsByRegionNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where region does not contain DEFAULT_REGION
        defaultPatientShouldNotBeFound("region.doesNotContain=" + DEFAULT_REGION);

        // Get all the patientList where region does not contain UPDATED_REGION
        defaultPatientShouldBeFound("region.doesNotContain=" + UPDATED_REGION);
    }


    @Test
    @Transactional
    public void getAllPatientsByCountryIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where country equals to DEFAULT_COUNTRY
        defaultPatientShouldBeFound("country.equals=" + DEFAULT_COUNTRY);

        // Get all the patientList where country equals to UPDATED_COUNTRY
        defaultPatientShouldNotBeFound("country.equals=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllPatientsByCountryIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where country not equals to DEFAULT_COUNTRY
        defaultPatientShouldNotBeFound("country.notEquals=" + DEFAULT_COUNTRY);

        // Get all the patientList where country not equals to UPDATED_COUNTRY
        defaultPatientShouldBeFound("country.notEquals=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllPatientsByCountryIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where country in DEFAULT_COUNTRY or UPDATED_COUNTRY
        defaultPatientShouldBeFound("country.in=" + DEFAULT_COUNTRY + "," + UPDATED_COUNTRY);

        // Get all the patientList where country equals to UPDATED_COUNTRY
        defaultPatientShouldNotBeFound("country.in=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllPatientsByCountryIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where country is not null
        defaultPatientShouldBeFound("country.specified=true");

        // Get all the patientList where country is null
        defaultPatientShouldNotBeFound("country.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByCountryContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where country contains DEFAULT_COUNTRY
        defaultPatientShouldBeFound("country.contains=" + DEFAULT_COUNTRY);

        // Get all the patientList where country contains UPDATED_COUNTRY
        defaultPatientShouldNotBeFound("country.contains=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllPatientsByCountryNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where country does not contain DEFAULT_COUNTRY
        defaultPatientShouldNotBeFound("country.doesNotContain=" + DEFAULT_COUNTRY);

        // Get all the patientList where country does not contain UPDATED_COUNTRY
        defaultPatientShouldBeFound("country.doesNotContain=" + UPDATED_COUNTRY);
    }


    @Test
    @Transactional
    public void getAllPatientsByCountryCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where countryCode equals to DEFAULT_COUNTRY_CODE
        defaultPatientShouldBeFound("countryCode.equals=" + DEFAULT_COUNTRY_CODE);

        // Get all the patientList where countryCode equals to UPDATED_COUNTRY_CODE
        defaultPatientShouldNotBeFound("countryCode.equals=" + UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    public void getAllPatientsByCountryCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where countryCode not equals to DEFAULT_COUNTRY_CODE
        defaultPatientShouldNotBeFound("countryCode.notEquals=" + DEFAULT_COUNTRY_CODE);

        // Get all the patientList where countryCode not equals to UPDATED_COUNTRY_CODE
        defaultPatientShouldBeFound("countryCode.notEquals=" + UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    public void getAllPatientsByCountryCodeIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where countryCode in DEFAULT_COUNTRY_CODE or UPDATED_COUNTRY_CODE
        defaultPatientShouldBeFound("countryCode.in=" + DEFAULT_COUNTRY_CODE + "," + UPDATED_COUNTRY_CODE);

        // Get all the patientList where countryCode equals to UPDATED_COUNTRY_CODE
        defaultPatientShouldNotBeFound("countryCode.in=" + UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    public void getAllPatientsByCountryCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where countryCode is not null
        defaultPatientShouldBeFound("countryCode.specified=true");

        // Get all the patientList where countryCode is null
        defaultPatientShouldNotBeFound("countryCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByCountryCodeContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where countryCode contains DEFAULT_COUNTRY_CODE
        defaultPatientShouldBeFound("countryCode.contains=" + DEFAULT_COUNTRY_CODE);

        // Get all the patientList where countryCode contains UPDATED_COUNTRY_CODE
        defaultPatientShouldNotBeFound("countryCode.contains=" + UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    public void getAllPatientsByCountryCodeNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where countryCode does not contain DEFAULT_COUNTRY_CODE
        defaultPatientShouldNotBeFound("countryCode.doesNotContain=" + DEFAULT_COUNTRY_CODE);

        // Get all the patientList where countryCode does not contain UPDATED_COUNTRY_CODE
        defaultPatientShouldBeFound("countryCode.doesNotContain=" + UPDATED_COUNTRY_CODE);
    }


    @Test
    @Transactional
    public void getAllPatientsByPincodeIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where pincode equals to DEFAULT_PINCODE
        defaultPatientShouldBeFound("pincode.equals=" + DEFAULT_PINCODE);

        // Get all the patientList where pincode equals to UPDATED_PINCODE
        defaultPatientShouldNotBeFound("pincode.equals=" + UPDATED_PINCODE);
    }

    @Test
    @Transactional
    public void getAllPatientsByPincodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where pincode not equals to DEFAULT_PINCODE
        defaultPatientShouldNotBeFound("pincode.notEquals=" + DEFAULT_PINCODE);

        // Get all the patientList where pincode not equals to UPDATED_PINCODE
        defaultPatientShouldBeFound("pincode.notEquals=" + UPDATED_PINCODE);
    }

    @Test
    @Transactional
    public void getAllPatientsByPincodeIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where pincode in DEFAULT_PINCODE or UPDATED_PINCODE
        defaultPatientShouldBeFound("pincode.in=" + DEFAULT_PINCODE + "," + UPDATED_PINCODE);

        // Get all the patientList where pincode equals to UPDATED_PINCODE
        defaultPatientShouldNotBeFound("pincode.in=" + UPDATED_PINCODE);
    }

    @Test
    @Transactional
    public void getAllPatientsByPincodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where pincode is not null
        defaultPatientShouldBeFound("pincode.specified=true");

        // Get all the patientList where pincode is null
        defaultPatientShouldNotBeFound("pincode.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByPincodeContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where pincode contains DEFAULT_PINCODE
        defaultPatientShouldBeFound("pincode.contains=" + DEFAULT_PINCODE);

        // Get all the patientList where pincode contains UPDATED_PINCODE
        defaultPatientShouldNotBeFound("pincode.contains=" + UPDATED_PINCODE);
    }

    @Test
    @Transactional
    public void getAllPatientsByPincodeNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where pincode does not contain DEFAULT_PINCODE
        defaultPatientShouldNotBeFound("pincode.doesNotContain=" + DEFAULT_PINCODE);

        // Get all the patientList where pincode does not contain UPDATED_PINCODE
        defaultPatientShouldBeFound("pincode.doesNotContain=" + UPDATED_PINCODE);
    }


    @Test
    @Transactional
    public void getAllPatientsByMobileNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where mobileNumber equals to DEFAULT_MOBILE_NUMBER
        defaultPatientShouldBeFound("mobileNumber.equals=" + DEFAULT_MOBILE_NUMBER);

        // Get all the patientList where mobileNumber equals to UPDATED_MOBILE_NUMBER
        defaultPatientShouldNotBeFound("mobileNumber.equals=" + UPDATED_MOBILE_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPatientsByMobileNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where mobileNumber not equals to DEFAULT_MOBILE_NUMBER
        defaultPatientShouldNotBeFound("mobileNumber.notEquals=" + DEFAULT_MOBILE_NUMBER);

        // Get all the patientList where mobileNumber not equals to UPDATED_MOBILE_NUMBER
        defaultPatientShouldBeFound("mobileNumber.notEquals=" + UPDATED_MOBILE_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPatientsByMobileNumberIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where mobileNumber in DEFAULT_MOBILE_NUMBER or UPDATED_MOBILE_NUMBER
        defaultPatientShouldBeFound("mobileNumber.in=" + DEFAULT_MOBILE_NUMBER + "," + UPDATED_MOBILE_NUMBER);

        // Get all the patientList where mobileNumber equals to UPDATED_MOBILE_NUMBER
        defaultPatientShouldNotBeFound("mobileNumber.in=" + UPDATED_MOBILE_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPatientsByMobileNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where mobileNumber is not null
        defaultPatientShouldBeFound("mobileNumber.specified=true");

        // Get all the patientList where mobileNumber is null
        defaultPatientShouldNotBeFound("mobileNumber.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByMobileNumberContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where mobileNumber contains DEFAULT_MOBILE_NUMBER
        defaultPatientShouldBeFound("mobileNumber.contains=" + DEFAULT_MOBILE_NUMBER);

        // Get all the patientList where mobileNumber contains UPDATED_MOBILE_NUMBER
        defaultPatientShouldNotBeFound("mobileNumber.contains=" + UPDATED_MOBILE_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPatientsByMobileNumberNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where mobileNumber does not contain DEFAULT_MOBILE_NUMBER
        defaultPatientShouldNotBeFound("mobileNumber.doesNotContain=" + DEFAULT_MOBILE_NUMBER);

        // Get all the patientList where mobileNumber does not contain UPDATED_MOBILE_NUMBER
        defaultPatientShouldBeFound("mobileNumber.doesNotContain=" + UPDATED_MOBILE_NUMBER);
    }


    @Test
    @Transactional
    public void getAllPatientsByContactNoIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where contactNo equals to DEFAULT_CONTACT_NO
        defaultPatientShouldBeFound("contactNo.equals=" + DEFAULT_CONTACT_NO);

        // Get all the patientList where contactNo equals to UPDATED_CONTACT_NO
        defaultPatientShouldNotBeFound("contactNo.equals=" + UPDATED_CONTACT_NO);
    }

    @Test
    @Transactional
    public void getAllPatientsByContactNoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where contactNo not equals to DEFAULT_CONTACT_NO
        defaultPatientShouldNotBeFound("contactNo.notEquals=" + DEFAULT_CONTACT_NO);

        // Get all the patientList where contactNo not equals to UPDATED_CONTACT_NO
        defaultPatientShouldBeFound("contactNo.notEquals=" + UPDATED_CONTACT_NO);
    }

    @Test
    @Transactional
    public void getAllPatientsByContactNoIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where contactNo in DEFAULT_CONTACT_NO or UPDATED_CONTACT_NO
        defaultPatientShouldBeFound("contactNo.in=" + DEFAULT_CONTACT_NO + "," + UPDATED_CONTACT_NO);

        // Get all the patientList where contactNo equals to UPDATED_CONTACT_NO
        defaultPatientShouldNotBeFound("contactNo.in=" + UPDATED_CONTACT_NO);
    }

    @Test
    @Transactional
    public void getAllPatientsByContactNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where contactNo is not null
        defaultPatientShouldBeFound("contactNo.specified=true");

        // Get all the patientList where contactNo is null
        defaultPatientShouldNotBeFound("contactNo.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByContactNoContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where contactNo contains DEFAULT_CONTACT_NO
        defaultPatientShouldBeFound("contactNo.contains=" + DEFAULT_CONTACT_NO);

        // Get all the patientList where contactNo contains UPDATED_CONTACT_NO
        defaultPatientShouldNotBeFound("contactNo.contains=" + UPDATED_CONTACT_NO);
    }

    @Test
    @Transactional
    public void getAllPatientsByContactNoNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where contactNo does not contain DEFAULT_CONTACT_NO
        defaultPatientShouldNotBeFound("contactNo.doesNotContain=" + DEFAULT_CONTACT_NO);

        // Get all the patientList where contactNo does not contain UPDATED_CONTACT_NO
        defaultPatientShouldBeFound("contactNo.doesNotContain=" + UPDATED_CONTACT_NO);
    }


    @Test
    @Transactional
    public void getAllPatientsByEducationIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where education equals to DEFAULT_EDUCATION
        defaultPatientShouldBeFound("education.equals=" + DEFAULT_EDUCATION);

        // Get all the patientList where education equals to UPDATED_EDUCATION
        defaultPatientShouldNotBeFound("education.equals=" + UPDATED_EDUCATION);
    }

    @Test
    @Transactional
    public void getAllPatientsByEducationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where education not equals to DEFAULT_EDUCATION
        defaultPatientShouldNotBeFound("education.notEquals=" + DEFAULT_EDUCATION);

        // Get all the patientList where education not equals to UPDATED_EDUCATION
        defaultPatientShouldBeFound("education.notEquals=" + UPDATED_EDUCATION);
    }

    @Test
    @Transactional
    public void getAllPatientsByEducationIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where education in DEFAULT_EDUCATION or UPDATED_EDUCATION
        defaultPatientShouldBeFound("education.in=" + DEFAULT_EDUCATION + "," + UPDATED_EDUCATION);

        // Get all the patientList where education equals to UPDATED_EDUCATION
        defaultPatientShouldNotBeFound("education.in=" + UPDATED_EDUCATION);
    }

    @Test
    @Transactional
    public void getAllPatientsByEducationIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where education is not null
        defaultPatientShouldBeFound("education.specified=true");

        // Get all the patientList where education is null
        defaultPatientShouldNotBeFound("education.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByEducationContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where education contains DEFAULT_EDUCATION
        defaultPatientShouldBeFound("education.contains=" + DEFAULT_EDUCATION);

        // Get all the patientList where education contains UPDATED_EDUCATION
        defaultPatientShouldNotBeFound("education.contains=" + UPDATED_EDUCATION);
    }

    @Test
    @Transactional
    public void getAllPatientsByEducationNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where education does not contain DEFAULT_EDUCATION
        defaultPatientShouldNotBeFound("education.doesNotContain=" + DEFAULT_EDUCATION);

        // Get all the patientList where education does not contain UPDATED_EDUCATION
        defaultPatientShouldBeFound("education.doesNotContain=" + UPDATED_EDUCATION);
    }


    @Test
    @Transactional
    public void getAllPatientsByBloodGroupIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where bloodGroup equals to DEFAULT_BLOOD_GROUP
        defaultPatientShouldBeFound("bloodGroup.equals=" + DEFAULT_BLOOD_GROUP);

        // Get all the patientList where bloodGroup equals to UPDATED_BLOOD_GROUP
        defaultPatientShouldNotBeFound("bloodGroup.equals=" + UPDATED_BLOOD_GROUP);
    }

    @Test
    @Transactional
    public void getAllPatientsByBloodGroupIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where bloodGroup not equals to DEFAULT_BLOOD_GROUP
        defaultPatientShouldNotBeFound("bloodGroup.notEquals=" + DEFAULT_BLOOD_GROUP);

        // Get all the patientList where bloodGroup not equals to UPDATED_BLOOD_GROUP
        defaultPatientShouldBeFound("bloodGroup.notEquals=" + UPDATED_BLOOD_GROUP);
    }

    @Test
    @Transactional
    public void getAllPatientsByBloodGroupIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where bloodGroup in DEFAULT_BLOOD_GROUP or UPDATED_BLOOD_GROUP
        defaultPatientShouldBeFound("bloodGroup.in=" + DEFAULT_BLOOD_GROUP + "," + UPDATED_BLOOD_GROUP);

        // Get all the patientList where bloodGroup equals to UPDATED_BLOOD_GROUP
        defaultPatientShouldNotBeFound("bloodGroup.in=" + UPDATED_BLOOD_GROUP);
    }

    @Test
    @Transactional
    public void getAllPatientsByBloodGroupIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where bloodGroup is not null
        defaultPatientShouldBeFound("bloodGroup.specified=true");

        // Get all the patientList where bloodGroup is null
        defaultPatientShouldNotBeFound("bloodGroup.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByBloodGroupContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where bloodGroup contains DEFAULT_BLOOD_GROUP
        defaultPatientShouldBeFound("bloodGroup.contains=" + DEFAULT_BLOOD_GROUP);

        // Get all the patientList where bloodGroup contains UPDATED_BLOOD_GROUP
        defaultPatientShouldNotBeFound("bloodGroup.contains=" + UPDATED_BLOOD_GROUP);
    }

    @Test
    @Transactional
    public void getAllPatientsByBloodGroupNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where bloodGroup does not contain DEFAULT_BLOOD_GROUP
        defaultPatientShouldNotBeFound("bloodGroup.doesNotContain=" + DEFAULT_BLOOD_GROUP);

        // Get all the patientList where bloodGroup does not contain UPDATED_BLOOD_GROUP
        defaultPatientShouldBeFound("bloodGroup.doesNotContain=" + UPDATED_BLOOD_GROUP);
    }


    @Test
    @Transactional
    public void getAllPatientsByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where email equals to DEFAULT_EMAIL
        defaultPatientShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the patientList where email equals to UPDATED_EMAIL
        defaultPatientShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPatientsByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where email not equals to DEFAULT_EMAIL
        defaultPatientShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the patientList where email not equals to UPDATED_EMAIL
        defaultPatientShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPatientsByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultPatientShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the patientList where email equals to UPDATED_EMAIL
        defaultPatientShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPatientsByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where email is not null
        defaultPatientShouldBeFound("email.specified=true");

        // Get all the patientList where email is null
        defaultPatientShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByEmailContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where email contains DEFAULT_EMAIL
        defaultPatientShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the patientList where email contains UPDATED_EMAIL
        defaultPatientShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPatientsByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where email does not contain DEFAULT_EMAIL
        defaultPatientShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the patientList where email does not contain UPDATED_EMAIL
        defaultPatientShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllPatientsByRegDateIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where regDate equals to DEFAULT_REG_DATE
        defaultPatientShouldBeFound("regDate.equals=" + DEFAULT_REG_DATE);

        // Get all the patientList where regDate equals to UPDATED_REG_DATE
        defaultPatientShouldNotBeFound("regDate.equals=" + UPDATED_REG_DATE);
    }

    @Test
    @Transactional
    public void getAllPatientsByRegDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where regDate not equals to DEFAULT_REG_DATE
        defaultPatientShouldNotBeFound("regDate.notEquals=" + DEFAULT_REG_DATE);

        // Get all the patientList where regDate not equals to UPDATED_REG_DATE
        defaultPatientShouldBeFound("regDate.notEquals=" + UPDATED_REG_DATE);
    }

    @Test
    @Transactional
    public void getAllPatientsByRegDateIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where regDate in DEFAULT_REG_DATE or UPDATED_REG_DATE
        defaultPatientShouldBeFound("regDate.in=" + DEFAULT_REG_DATE + "," + UPDATED_REG_DATE);

        // Get all the patientList where regDate equals to UPDATED_REG_DATE
        defaultPatientShouldNotBeFound("regDate.in=" + UPDATED_REG_DATE);
    }

    @Test
    @Transactional
    public void getAllPatientsByRegDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where regDate is not null
        defaultPatientShouldBeFound("regDate.specified=true");

        // Get all the patientList where regDate is null
        defaultPatientShouldNotBeFound("regDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByProfessionIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where profession equals to DEFAULT_PROFESSION
        defaultPatientShouldBeFound("profession.equals=" + DEFAULT_PROFESSION);

        // Get all the patientList where profession equals to UPDATED_PROFESSION
        defaultPatientShouldNotBeFound("profession.equals=" + UPDATED_PROFESSION);
    }

    @Test
    @Transactional
    public void getAllPatientsByProfessionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where profession not equals to DEFAULT_PROFESSION
        defaultPatientShouldNotBeFound("profession.notEquals=" + DEFAULT_PROFESSION);

        // Get all the patientList where profession not equals to UPDATED_PROFESSION
        defaultPatientShouldBeFound("profession.notEquals=" + UPDATED_PROFESSION);
    }

    @Test
    @Transactional
    public void getAllPatientsByProfessionIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where profession in DEFAULT_PROFESSION or UPDATED_PROFESSION
        defaultPatientShouldBeFound("profession.in=" + DEFAULT_PROFESSION + "," + UPDATED_PROFESSION);

        // Get all the patientList where profession equals to UPDATED_PROFESSION
        defaultPatientShouldNotBeFound("profession.in=" + UPDATED_PROFESSION);
    }

    @Test
    @Transactional
    public void getAllPatientsByProfessionIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where profession is not null
        defaultPatientShouldBeFound("profession.specified=true");

        // Get all the patientList where profession is null
        defaultPatientShouldNotBeFound("profession.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByProfessionContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where profession contains DEFAULT_PROFESSION
        defaultPatientShouldBeFound("profession.contains=" + DEFAULT_PROFESSION);

        // Get all the patientList where profession contains UPDATED_PROFESSION
        defaultPatientShouldNotBeFound("profession.contains=" + UPDATED_PROFESSION);
    }

    @Test
    @Transactional
    public void getAllPatientsByProfessionNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where profession does not contain DEFAULT_PROFESSION
        defaultPatientShouldNotBeFound("profession.doesNotContain=" + DEFAULT_PROFESSION);

        // Get all the patientList where profession does not contain UPDATED_PROFESSION
        defaultPatientShouldBeFound("profession.doesNotContain=" + UPDATED_PROFESSION);
    }


    @Test
    @Transactional
    public void getAllPatientsByMarriageStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where marriageStatus equals to DEFAULT_MARRIAGE_STATUS
        defaultPatientShouldBeFound("marriageStatus.equals=" + DEFAULT_MARRIAGE_STATUS);

        // Get all the patientList where marriageStatus equals to UPDATED_MARRIAGE_STATUS
        defaultPatientShouldNotBeFound("marriageStatus.equals=" + UPDATED_MARRIAGE_STATUS);
    }

    @Test
    @Transactional
    public void getAllPatientsByMarriageStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where marriageStatus not equals to DEFAULT_MARRIAGE_STATUS
        defaultPatientShouldNotBeFound("marriageStatus.notEquals=" + DEFAULT_MARRIAGE_STATUS);

        // Get all the patientList where marriageStatus not equals to UPDATED_MARRIAGE_STATUS
        defaultPatientShouldBeFound("marriageStatus.notEquals=" + UPDATED_MARRIAGE_STATUS);
    }

    @Test
    @Transactional
    public void getAllPatientsByMarriageStatusIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where marriageStatus in DEFAULT_MARRIAGE_STATUS or UPDATED_MARRIAGE_STATUS
        defaultPatientShouldBeFound("marriageStatus.in=" + DEFAULT_MARRIAGE_STATUS + "," + UPDATED_MARRIAGE_STATUS);

        // Get all the patientList where marriageStatus equals to UPDATED_MARRIAGE_STATUS
        defaultPatientShouldNotBeFound("marriageStatus.in=" + UPDATED_MARRIAGE_STATUS);
    }

    @Test
    @Transactional
    public void getAllPatientsByMarriageStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where marriageStatus is not null
        defaultPatientShouldBeFound("marriageStatus.specified=true");

        // Get all the patientList where marriageStatus is null
        defaultPatientShouldNotBeFound("marriageStatus.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByMarriageStatusContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where marriageStatus contains DEFAULT_MARRIAGE_STATUS
        defaultPatientShouldBeFound("marriageStatus.contains=" + DEFAULT_MARRIAGE_STATUS);

        // Get all the patientList where marriageStatus contains UPDATED_MARRIAGE_STATUS
        defaultPatientShouldNotBeFound("marriageStatus.contains=" + UPDATED_MARRIAGE_STATUS);
    }

    @Test
    @Transactional
    public void getAllPatientsByMarriageStatusNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where marriageStatus does not contain DEFAULT_MARRIAGE_STATUS
        defaultPatientShouldNotBeFound("marriageStatus.doesNotContain=" + DEFAULT_MARRIAGE_STATUS);

        // Get all the patientList where marriageStatus does not contain UPDATED_MARRIAGE_STATUS
        defaultPatientShouldBeFound("marriageStatus.doesNotContain=" + UPDATED_MARRIAGE_STATUS);
    }


    @Test
    @Transactional
    public void getAllPatientsByExpiredIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where expired equals to DEFAULT_EXPIRED
        defaultPatientShouldBeFound("expired.equals=" + DEFAULT_EXPIRED);

        // Get all the patientList where expired equals to UPDATED_EXPIRED
        defaultPatientShouldNotBeFound("expired.equals=" + UPDATED_EXPIRED);
    }

    @Test
    @Transactional
    public void getAllPatientsByExpiredIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where expired not equals to DEFAULT_EXPIRED
        defaultPatientShouldNotBeFound("expired.notEquals=" + DEFAULT_EXPIRED);

        // Get all the patientList where expired not equals to UPDATED_EXPIRED
        defaultPatientShouldBeFound("expired.notEquals=" + UPDATED_EXPIRED);
    }

    @Test
    @Transactional
    public void getAllPatientsByExpiredIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where expired in DEFAULT_EXPIRED or UPDATED_EXPIRED
        defaultPatientShouldBeFound("expired.in=" + DEFAULT_EXPIRED + "," + UPDATED_EXPIRED);

        // Get all the patientList where expired equals to UPDATED_EXPIRED
        defaultPatientShouldNotBeFound("expired.in=" + UPDATED_EXPIRED);
    }

    @Test
    @Transactional
    public void getAllPatientsByExpiredIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where expired is not null
        defaultPatientShouldBeFound("expired.specified=true");

        // Get all the patientList where expired is null
        defaultPatientShouldNotBeFound("expired.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByLanguageNameIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where languageName equals to DEFAULT_LANGUAGE_NAME
        defaultPatientShouldBeFound("languageName.equals=" + DEFAULT_LANGUAGE_NAME);

        // Get all the patientList where languageName equals to UPDATED_LANGUAGE_NAME
        defaultPatientShouldNotBeFound("languageName.equals=" + UPDATED_LANGUAGE_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByLanguageNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where languageName not equals to DEFAULT_LANGUAGE_NAME
        defaultPatientShouldNotBeFound("languageName.notEquals=" + DEFAULT_LANGUAGE_NAME);

        // Get all the patientList where languageName not equals to UPDATED_LANGUAGE_NAME
        defaultPatientShouldBeFound("languageName.notEquals=" + UPDATED_LANGUAGE_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByLanguageNameIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where languageName in DEFAULT_LANGUAGE_NAME or UPDATED_LANGUAGE_NAME
        defaultPatientShouldBeFound("languageName.in=" + DEFAULT_LANGUAGE_NAME + "," + UPDATED_LANGUAGE_NAME);

        // Get all the patientList where languageName equals to UPDATED_LANGUAGE_NAME
        defaultPatientShouldNotBeFound("languageName.in=" + UPDATED_LANGUAGE_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByLanguageNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where languageName is not null
        defaultPatientShouldBeFound("languageName.specified=true");

        // Get all the patientList where languageName is null
        defaultPatientShouldNotBeFound("languageName.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByLanguageNameContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where languageName contains DEFAULT_LANGUAGE_NAME
        defaultPatientShouldBeFound("languageName.contains=" + DEFAULT_LANGUAGE_NAME);

        // Get all the patientList where languageName contains UPDATED_LANGUAGE_NAME
        defaultPatientShouldNotBeFound("languageName.contains=" + UPDATED_LANGUAGE_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByLanguageNameNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where languageName does not contain DEFAULT_LANGUAGE_NAME
        defaultPatientShouldNotBeFound("languageName.doesNotContain=" + DEFAULT_LANGUAGE_NAME);

        // Get all the patientList where languageName does not contain UPDATED_LANGUAGE_NAME
        defaultPatientShouldBeFound("languageName.doesNotContain=" + UPDATED_LANGUAGE_NAME);
    }


    @Test
    @Transactional
    public void getAllPatientsByVegNonVegIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where vegNonVeg equals to DEFAULT_VEG_NON_VEG
        defaultPatientShouldBeFound("vegNonVeg.equals=" + DEFAULT_VEG_NON_VEG);

        // Get all the patientList where vegNonVeg equals to UPDATED_VEG_NON_VEG
        defaultPatientShouldNotBeFound("vegNonVeg.equals=" + UPDATED_VEG_NON_VEG);
    }

    @Test
    @Transactional
    public void getAllPatientsByVegNonVegIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where vegNonVeg not equals to DEFAULT_VEG_NON_VEG
        defaultPatientShouldNotBeFound("vegNonVeg.notEquals=" + DEFAULT_VEG_NON_VEG);

        // Get all the patientList where vegNonVeg not equals to UPDATED_VEG_NON_VEG
        defaultPatientShouldBeFound("vegNonVeg.notEquals=" + UPDATED_VEG_NON_VEG);
    }

    @Test
    @Transactional
    public void getAllPatientsByVegNonVegIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where vegNonVeg in DEFAULT_VEG_NON_VEG or UPDATED_VEG_NON_VEG
        defaultPatientShouldBeFound("vegNonVeg.in=" + DEFAULT_VEG_NON_VEG + "," + UPDATED_VEG_NON_VEG);

        // Get all the patientList where vegNonVeg equals to UPDATED_VEG_NON_VEG
        defaultPatientShouldNotBeFound("vegNonVeg.in=" + UPDATED_VEG_NON_VEG);
    }

    @Test
    @Transactional
    public void getAllPatientsByVegNonVegIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where vegNonVeg is not null
        defaultPatientShouldBeFound("vegNonVeg.specified=true");

        // Get all the patientList where vegNonVeg is null
        defaultPatientShouldNotBeFound("vegNonVeg.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByReferedByIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where referedBy equals to DEFAULT_REFERED_BY
        defaultPatientShouldBeFound("referedBy.equals=" + DEFAULT_REFERED_BY);

        // Get all the patientList where referedBy equals to UPDATED_REFERED_BY
        defaultPatientShouldNotBeFound("referedBy.equals=" + UPDATED_REFERED_BY);
    }

    @Test
    @Transactional
    public void getAllPatientsByReferedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where referedBy not equals to DEFAULT_REFERED_BY
        defaultPatientShouldNotBeFound("referedBy.notEquals=" + DEFAULT_REFERED_BY);

        // Get all the patientList where referedBy not equals to UPDATED_REFERED_BY
        defaultPatientShouldBeFound("referedBy.notEquals=" + UPDATED_REFERED_BY);
    }

    @Test
    @Transactional
    public void getAllPatientsByReferedByIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where referedBy in DEFAULT_REFERED_BY or UPDATED_REFERED_BY
        defaultPatientShouldBeFound("referedBy.in=" + DEFAULT_REFERED_BY + "," + UPDATED_REFERED_BY);

        // Get all the patientList where referedBy equals to UPDATED_REFERED_BY
        defaultPatientShouldNotBeFound("referedBy.in=" + UPDATED_REFERED_BY);
    }

    @Test
    @Transactional
    public void getAllPatientsByReferedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where referedBy is not null
        defaultPatientShouldBeFound("referedBy.specified=true");

        // Get all the patientList where referedBy is null
        defaultPatientShouldNotBeFound("referedBy.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByReferedByContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where referedBy contains DEFAULT_REFERED_BY
        defaultPatientShouldBeFound("referedBy.contains=" + DEFAULT_REFERED_BY);

        // Get all the patientList where referedBy contains UPDATED_REFERED_BY
        defaultPatientShouldNotBeFound("referedBy.contains=" + UPDATED_REFERED_BY);
    }

    @Test
    @Transactional
    public void getAllPatientsByReferedByNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where referedBy does not contain DEFAULT_REFERED_BY
        defaultPatientShouldNotBeFound("referedBy.doesNotContain=" + DEFAULT_REFERED_BY);

        // Get all the patientList where referedBy does not contain UPDATED_REFERED_BY
        defaultPatientShouldBeFound("referedBy.doesNotContain=" + UPDATED_REFERED_BY);
    }


    @Test
    @Transactional
    public void getAllPatientsByReferdByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where referdByName equals to DEFAULT_REFERD_BY_NAME
        defaultPatientShouldBeFound("referdByName.equals=" + DEFAULT_REFERD_BY_NAME);

        // Get all the patientList where referdByName equals to UPDATED_REFERD_BY_NAME
        defaultPatientShouldNotBeFound("referdByName.equals=" + UPDATED_REFERD_BY_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByReferdByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where referdByName not equals to DEFAULT_REFERD_BY_NAME
        defaultPatientShouldNotBeFound("referdByName.notEquals=" + DEFAULT_REFERD_BY_NAME);

        // Get all the patientList where referdByName not equals to UPDATED_REFERD_BY_NAME
        defaultPatientShouldBeFound("referdByName.notEquals=" + UPDATED_REFERD_BY_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByReferdByNameIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where referdByName in DEFAULT_REFERD_BY_NAME or UPDATED_REFERD_BY_NAME
        defaultPatientShouldBeFound("referdByName.in=" + DEFAULT_REFERD_BY_NAME + "," + UPDATED_REFERD_BY_NAME);

        // Get all the patientList where referdByName equals to UPDATED_REFERD_BY_NAME
        defaultPatientShouldNotBeFound("referdByName.in=" + UPDATED_REFERD_BY_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByReferdByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where referdByName is not null
        defaultPatientShouldBeFound("referdByName.specified=true");

        // Get all the patientList where referdByName is null
        defaultPatientShouldNotBeFound("referdByName.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByReferdByNameContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where referdByName contains DEFAULT_REFERD_BY_NAME
        defaultPatientShouldBeFound("referdByName.contains=" + DEFAULT_REFERD_BY_NAME);

        // Get all the patientList where referdByName contains UPDATED_REFERD_BY_NAME
        defaultPatientShouldNotBeFound("referdByName.contains=" + UPDATED_REFERD_BY_NAME);
    }

    @Test
    @Transactional
    public void getAllPatientsByReferdByNameNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where referdByName does not contain DEFAULT_REFERD_BY_NAME
        defaultPatientShouldNotBeFound("referdByName.doesNotContain=" + DEFAULT_REFERD_BY_NAME);

        // Get all the patientList where referdByName does not contain UPDATED_REFERD_BY_NAME
        defaultPatientShouldBeFound("referdByName.doesNotContain=" + UPDATED_REFERD_BY_NAME);
    }


    @Test
    @Transactional
    public void getAllPatientsByExerciseRegularlyIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where exerciseRegularly equals to DEFAULT_EXERCISE_REGULARLY
        defaultPatientShouldBeFound("exerciseRegularly.equals=" + DEFAULT_EXERCISE_REGULARLY);

        // Get all the patientList where exerciseRegularly equals to UPDATED_EXERCISE_REGULARLY
        defaultPatientShouldNotBeFound("exerciseRegularly.equals=" + UPDATED_EXERCISE_REGULARLY);
    }

    @Test
    @Transactional
    public void getAllPatientsByExerciseRegularlyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where exerciseRegularly not equals to DEFAULT_EXERCISE_REGULARLY
        defaultPatientShouldNotBeFound("exerciseRegularly.notEquals=" + DEFAULT_EXERCISE_REGULARLY);

        // Get all the patientList where exerciseRegularly not equals to UPDATED_EXERCISE_REGULARLY
        defaultPatientShouldBeFound("exerciseRegularly.notEquals=" + UPDATED_EXERCISE_REGULARLY);
    }

    @Test
    @Transactional
    public void getAllPatientsByExerciseRegularlyIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where exerciseRegularly in DEFAULT_EXERCISE_REGULARLY or UPDATED_EXERCISE_REGULARLY
        defaultPatientShouldBeFound("exerciseRegularly.in=" + DEFAULT_EXERCISE_REGULARLY + "," + UPDATED_EXERCISE_REGULARLY);

        // Get all the patientList where exerciseRegularly equals to UPDATED_EXERCISE_REGULARLY
        defaultPatientShouldNotBeFound("exerciseRegularly.in=" + UPDATED_EXERCISE_REGULARLY);
    }

    @Test
    @Transactional
    public void getAllPatientsByExerciseRegularlyIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where exerciseRegularly is not null
        defaultPatientShouldBeFound("exerciseRegularly.specified=true");

        // Get all the patientList where exerciseRegularly is null
        defaultPatientShouldNotBeFound("exerciseRegularly.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByAddictionIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where addiction equals to DEFAULT_ADDICTION
        defaultPatientShouldBeFound("addiction.equals=" + DEFAULT_ADDICTION);

        // Get all the patientList where addiction equals to UPDATED_ADDICTION
        defaultPatientShouldNotBeFound("addiction.equals=" + UPDATED_ADDICTION);
    }

    @Test
    @Transactional
    public void getAllPatientsByAddictionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where addiction not equals to DEFAULT_ADDICTION
        defaultPatientShouldNotBeFound("addiction.notEquals=" + DEFAULT_ADDICTION);

        // Get all the patientList where addiction not equals to UPDATED_ADDICTION
        defaultPatientShouldBeFound("addiction.notEquals=" + UPDATED_ADDICTION);
    }

    @Test
    @Transactional
    public void getAllPatientsByAddictionIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where addiction in DEFAULT_ADDICTION or UPDATED_ADDICTION
        defaultPatientShouldBeFound("addiction.in=" + DEFAULT_ADDICTION + "," + UPDATED_ADDICTION);

        // Get all the patientList where addiction equals to UPDATED_ADDICTION
        defaultPatientShouldNotBeFound("addiction.in=" + UPDATED_ADDICTION);
    }

    @Test
    @Transactional
    public void getAllPatientsByAddictionIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where addiction is not null
        defaultPatientShouldBeFound("addiction.specified=true");

        // Get all the patientList where addiction is null
        defaultPatientShouldNotBeFound("addiction.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursSleepIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursSleep equals to DEFAULT_HOURS_SLEEP
        defaultPatientShouldBeFound("hoursSleep.equals=" + DEFAULT_HOURS_SLEEP);

        // Get all the patientList where hoursSleep equals to UPDATED_HOURS_SLEEP
        defaultPatientShouldNotBeFound("hoursSleep.equals=" + UPDATED_HOURS_SLEEP);
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursSleepIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursSleep not equals to DEFAULT_HOURS_SLEEP
        defaultPatientShouldNotBeFound("hoursSleep.notEquals=" + DEFAULT_HOURS_SLEEP);

        // Get all the patientList where hoursSleep not equals to UPDATED_HOURS_SLEEP
        defaultPatientShouldBeFound("hoursSleep.notEquals=" + UPDATED_HOURS_SLEEP);
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursSleepIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursSleep in DEFAULT_HOURS_SLEEP or UPDATED_HOURS_SLEEP
        defaultPatientShouldBeFound("hoursSleep.in=" + DEFAULT_HOURS_SLEEP + "," + UPDATED_HOURS_SLEEP);

        // Get all the patientList where hoursSleep equals to UPDATED_HOURS_SLEEP
        defaultPatientShouldNotBeFound("hoursSleep.in=" + UPDATED_HOURS_SLEEP);
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursSleepIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursSleep is not null
        defaultPatientShouldBeFound("hoursSleep.specified=true");

        // Get all the patientList where hoursSleep is null
        defaultPatientShouldNotBeFound("hoursSleep.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursSleepIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursSleep is greater than or equal to DEFAULT_HOURS_SLEEP
        defaultPatientShouldBeFound("hoursSleep.greaterThanOrEqual=" + DEFAULT_HOURS_SLEEP);

        // Get all the patientList where hoursSleep is greater than or equal to UPDATED_HOURS_SLEEP
        defaultPatientShouldNotBeFound("hoursSleep.greaterThanOrEqual=" + UPDATED_HOURS_SLEEP);
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursSleepIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursSleep is less than or equal to DEFAULT_HOURS_SLEEP
        defaultPatientShouldBeFound("hoursSleep.lessThanOrEqual=" + DEFAULT_HOURS_SLEEP);

        // Get all the patientList where hoursSleep is less than or equal to SMALLER_HOURS_SLEEP
        defaultPatientShouldNotBeFound("hoursSleep.lessThanOrEqual=" + SMALLER_HOURS_SLEEP);
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursSleepIsLessThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursSleep is less than DEFAULT_HOURS_SLEEP
        defaultPatientShouldNotBeFound("hoursSleep.lessThan=" + DEFAULT_HOURS_SLEEP);

        // Get all the patientList where hoursSleep is less than UPDATED_HOURS_SLEEP
        defaultPatientShouldBeFound("hoursSleep.lessThan=" + UPDATED_HOURS_SLEEP);
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursSleepIsGreaterThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursSleep is greater than DEFAULT_HOURS_SLEEP
        defaultPatientShouldNotBeFound("hoursSleep.greaterThan=" + DEFAULT_HOURS_SLEEP);

        // Get all the patientList where hoursSleep is greater than SMALLER_HOURS_SLEEP
        defaultPatientShouldBeFound("hoursSleep.greaterThan=" + SMALLER_HOURS_SLEEP);
    }


    @Test
    @Transactional
    public void getAllPatientsByWakeRestedIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where wakeRested equals to DEFAULT_WAKE_RESTED
        defaultPatientShouldBeFound("wakeRested.equals=" + DEFAULT_WAKE_RESTED);

        // Get all the patientList where wakeRested equals to UPDATED_WAKE_RESTED
        defaultPatientShouldNotBeFound("wakeRested.equals=" + UPDATED_WAKE_RESTED);
    }

    @Test
    @Transactional
    public void getAllPatientsByWakeRestedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where wakeRested not equals to DEFAULT_WAKE_RESTED
        defaultPatientShouldNotBeFound("wakeRested.notEquals=" + DEFAULT_WAKE_RESTED);

        // Get all the patientList where wakeRested not equals to UPDATED_WAKE_RESTED
        defaultPatientShouldBeFound("wakeRested.notEquals=" + UPDATED_WAKE_RESTED);
    }

    @Test
    @Transactional
    public void getAllPatientsByWakeRestedIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where wakeRested in DEFAULT_WAKE_RESTED or UPDATED_WAKE_RESTED
        defaultPatientShouldBeFound("wakeRested.in=" + DEFAULT_WAKE_RESTED + "," + UPDATED_WAKE_RESTED);

        // Get all the patientList where wakeRested equals to UPDATED_WAKE_RESTED
        defaultPatientShouldNotBeFound("wakeRested.in=" + UPDATED_WAKE_RESTED);
    }

    @Test
    @Transactional
    public void getAllPatientsByWakeRestedIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where wakeRested is not null
        defaultPatientShouldBeFound("wakeRested.specified=true");

        // Get all the patientList where wakeRested is null
        defaultPatientShouldNotBeFound("wakeRested.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByWaterIntakeIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where waterIntake equals to DEFAULT_WATER_INTAKE
        defaultPatientShouldBeFound("waterIntake.equals=" + DEFAULT_WATER_INTAKE);

        // Get all the patientList where waterIntake equals to UPDATED_WATER_INTAKE
        defaultPatientShouldNotBeFound("waterIntake.equals=" + UPDATED_WATER_INTAKE);
    }

    @Test
    @Transactional
    public void getAllPatientsByWaterIntakeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where waterIntake not equals to DEFAULT_WATER_INTAKE
        defaultPatientShouldNotBeFound("waterIntake.notEquals=" + DEFAULT_WATER_INTAKE);

        // Get all the patientList where waterIntake not equals to UPDATED_WATER_INTAKE
        defaultPatientShouldBeFound("waterIntake.notEquals=" + UPDATED_WATER_INTAKE);
    }

    @Test
    @Transactional
    public void getAllPatientsByWaterIntakeIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where waterIntake in DEFAULT_WATER_INTAKE or UPDATED_WATER_INTAKE
        defaultPatientShouldBeFound("waterIntake.in=" + DEFAULT_WATER_INTAKE + "," + UPDATED_WATER_INTAKE);

        // Get all the patientList where waterIntake equals to UPDATED_WATER_INTAKE
        defaultPatientShouldNotBeFound("waterIntake.in=" + UPDATED_WATER_INTAKE);
    }

    @Test
    @Transactional
    public void getAllPatientsByWaterIntakeIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where waterIntake is not null
        defaultPatientShouldBeFound("waterIntake.specified=true");

        // Get all the patientList where waterIntake is null
        defaultPatientShouldNotBeFound("waterIntake.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByWaterIntakeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where waterIntake is greater than or equal to DEFAULT_WATER_INTAKE
        defaultPatientShouldBeFound("waterIntake.greaterThanOrEqual=" + DEFAULT_WATER_INTAKE);

        // Get all the patientList where waterIntake is greater than or equal to UPDATED_WATER_INTAKE
        defaultPatientShouldNotBeFound("waterIntake.greaterThanOrEqual=" + UPDATED_WATER_INTAKE);
    }

    @Test
    @Transactional
    public void getAllPatientsByWaterIntakeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where waterIntake is less than or equal to DEFAULT_WATER_INTAKE
        defaultPatientShouldBeFound("waterIntake.lessThanOrEqual=" + DEFAULT_WATER_INTAKE);

        // Get all the patientList where waterIntake is less than or equal to SMALLER_WATER_INTAKE
        defaultPatientShouldNotBeFound("waterIntake.lessThanOrEqual=" + SMALLER_WATER_INTAKE);
    }

    @Test
    @Transactional
    public void getAllPatientsByWaterIntakeIsLessThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where waterIntake is less than DEFAULT_WATER_INTAKE
        defaultPatientShouldNotBeFound("waterIntake.lessThan=" + DEFAULT_WATER_INTAKE);

        // Get all the patientList where waterIntake is less than UPDATED_WATER_INTAKE
        defaultPatientShouldBeFound("waterIntake.lessThan=" + UPDATED_WATER_INTAKE);
    }

    @Test
    @Transactional
    public void getAllPatientsByWaterIntakeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where waterIntake is greater than DEFAULT_WATER_INTAKE
        defaultPatientShouldNotBeFound("waterIntake.greaterThan=" + DEFAULT_WATER_INTAKE);

        // Get all the patientList where waterIntake is greater than SMALLER_WATER_INTAKE
        defaultPatientShouldBeFound("waterIntake.greaterThan=" + SMALLER_WATER_INTAKE);
    }


    @Test
    @Transactional
    public void getAllPatientsByHoursWorkIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursWork equals to DEFAULT_HOURS_WORK
        defaultPatientShouldBeFound("hoursWork.equals=" + DEFAULT_HOURS_WORK);

        // Get all the patientList where hoursWork equals to UPDATED_HOURS_WORK
        defaultPatientShouldNotBeFound("hoursWork.equals=" + UPDATED_HOURS_WORK);
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursWorkIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursWork not equals to DEFAULT_HOURS_WORK
        defaultPatientShouldNotBeFound("hoursWork.notEquals=" + DEFAULT_HOURS_WORK);

        // Get all the patientList where hoursWork not equals to UPDATED_HOURS_WORK
        defaultPatientShouldBeFound("hoursWork.notEquals=" + UPDATED_HOURS_WORK);
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursWorkIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursWork in DEFAULT_HOURS_WORK or UPDATED_HOURS_WORK
        defaultPatientShouldBeFound("hoursWork.in=" + DEFAULT_HOURS_WORK + "," + UPDATED_HOURS_WORK);

        // Get all the patientList where hoursWork equals to UPDATED_HOURS_WORK
        defaultPatientShouldNotBeFound("hoursWork.in=" + UPDATED_HOURS_WORK);
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursWorkIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursWork is not null
        defaultPatientShouldBeFound("hoursWork.specified=true");

        // Get all the patientList where hoursWork is null
        defaultPatientShouldNotBeFound("hoursWork.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursWorkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursWork is greater than or equal to DEFAULT_HOURS_WORK
        defaultPatientShouldBeFound("hoursWork.greaterThanOrEqual=" + DEFAULT_HOURS_WORK);

        // Get all the patientList where hoursWork is greater than or equal to UPDATED_HOURS_WORK
        defaultPatientShouldNotBeFound("hoursWork.greaterThanOrEqual=" + UPDATED_HOURS_WORK);
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursWorkIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursWork is less than or equal to DEFAULT_HOURS_WORK
        defaultPatientShouldBeFound("hoursWork.lessThanOrEqual=" + DEFAULT_HOURS_WORK);

        // Get all the patientList where hoursWork is less than or equal to SMALLER_HOURS_WORK
        defaultPatientShouldNotBeFound("hoursWork.lessThanOrEqual=" + SMALLER_HOURS_WORK);
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursWorkIsLessThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursWork is less than DEFAULT_HOURS_WORK
        defaultPatientShouldNotBeFound("hoursWork.lessThan=" + DEFAULT_HOURS_WORK);

        // Get all the patientList where hoursWork is less than UPDATED_HOURS_WORK
        defaultPatientShouldBeFound("hoursWork.lessThan=" + UPDATED_HOURS_WORK);
    }

    @Test
    @Transactional
    public void getAllPatientsByHoursWorkIsGreaterThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where hoursWork is greater than DEFAULT_HOURS_WORK
        defaultPatientShouldNotBeFound("hoursWork.greaterThan=" + DEFAULT_HOURS_WORK);

        // Get all the patientList where hoursWork is greater than SMALLER_HOURS_WORK
        defaultPatientShouldBeFound("hoursWork.greaterThan=" + SMALLER_HOURS_WORK);
    }


    @Test
    @Transactional
    public void getAllPatientsByShiftWorkIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where shiftWork equals to DEFAULT_SHIFT_WORK
        defaultPatientShouldBeFound("shiftWork.equals=" + DEFAULT_SHIFT_WORK);

        // Get all the patientList where shiftWork equals to UPDATED_SHIFT_WORK
        defaultPatientShouldNotBeFound("shiftWork.equals=" + UPDATED_SHIFT_WORK);
    }

    @Test
    @Transactional
    public void getAllPatientsByShiftWorkIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where shiftWork not equals to DEFAULT_SHIFT_WORK
        defaultPatientShouldNotBeFound("shiftWork.notEquals=" + DEFAULT_SHIFT_WORK);

        // Get all the patientList where shiftWork not equals to UPDATED_SHIFT_WORK
        defaultPatientShouldBeFound("shiftWork.notEquals=" + UPDATED_SHIFT_WORK);
    }

    @Test
    @Transactional
    public void getAllPatientsByShiftWorkIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where shiftWork in DEFAULT_SHIFT_WORK or UPDATED_SHIFT_WORK
        defaultPatientShouldBeFound("shiftWork.in=" + DEFAULT_SHIFT_WORK + "," + UPDATED_SHIFT_WORK);

        // Get all the patientList where shiftWork equals to UPDATED_SHIFT_WORK
        defaultPatientShouldNotBeFound("shiftWork.in=" + UPDATED_SHIFT_WORK);
    }

    @Test
    @Transactional
    public void getAllPatientsByShiftWorkIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where shiftWork is not null
        defaultPatientShouldBeFound("shiftWork.specified=true");

        // Get all the patientList where shiftWork is null
        defaultPatientShouldNotBeFound("shiftWork.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByLevelOfStressIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfStress equals to DEFAULT_LEVEL_OF_STRESS
        defaultPatientShouldBeFound("levelOfStress.equals=" + DEFAULT_LEVEL_OF_STRESS);

        // Get all the patientList where levelOfStress equals to UPDATED_LEVEL_OF_STRESS
        defaultPatientShouldNotBeFound("levelOfStress.equals=" + UPDATED_LEVEL_OF_STRESS);
    }

    @Test
    @Transactional
    public void getAllPatientsByLevelOfStressIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfStress not equals to DEFAULT_LEVEL_OF_STRESS
        defaultPatientShouldNotBeFound("levelOfStress.notEquals=" + DEFAULT_LEVEL_OF_STRESS);

        // Get all the patientList where levelOfStress not equals to UPDATED_LEVEL_OF_STRESS
        defaultPatientShouldBeFound("levelOfStress.notEquals=" + UPDATED_LEVEL_OF_STRESS);
    }

    @Test
    @Transactional
    public void getAllPatientsByLevelOfStressIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfStress in DEFAULT_LEVEL_OF_STRESS or UPDATED_LEVEL_OF_STRESS
        defaultPatientShouldBeFound("levelOfStress.in=" + DEFAULT_LEVEL_OF_STRESS + "," + UPDATED_LEVEL_OF_STRESS);

        // Get all the patientList where levelOfStress equals to UPDATED_LEVEL_OF_STRESS
        defaultPatientShouldNotBeFound("levelOfStress.in=" + UPDATED_LEVEL_OF_STRESS);
    }

    @Test
    @Transactional
    public void getAllPatientsByLevelOfStressIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfStress is not null
        defaultPatientShouldBeFound("levelOfStress.specified=true");

        // Get all the patientList where levelOfStress is null
        defaultPatientShouldNotBeFound("levelOfStress.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByLevelOfStressContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfStress contains DEFAULT_LEVEL_OF_STRESS
        defaultPatientShouldBeFound("levelOfStress.contains=" + DEFAULT_LEVEL_OF_STRESS);

        // Get all the patientList where levelOfStress contains UPDATED_LEVEL_OF_STRESS
        defaultPatientShouldNotBeFound("levelOfStress.contains=" + UPDATED_LEVEL_OF_STRESS);
    }

    @Test
    @Transactional
    public void getAllPatientsByLevelOfStressNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfStress does not contain DEFAULT_LEVEL_OF_STRESS
        defaultPatientShouldNotBeFound("levelOfStress.doesNotContain=" + DEFAULT_LEVEL_OF_STRESS);

        // Get all the patientList where levelOfStress does not contain UPDATED_LEVEL_OF_STRESS
        defaultPatientShouldBeFound("levelOfStress.doesNotContain=" + UPDATED_LEVEL_OF_STRESS);
    }


    @Test
    @Transactional
    public void getAllPatientsByPatientConditionIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where patientCondition equals to DEFAULT_PATIENT_CONDITION
        defaultPatientShouldBeFound("patientCondition.equals=" + DEFAULT_PATIENT_CONDITION);

        // Get all the patientList where patientCondition equals to UPDATED_PATIENT_CONDITION
        defaultPatientShouldNotBeFound("patientCondition.equals=" + UPDATED_PATIENT_CONDITION);
    }

    @Test
    @Transactional
    public void getAllPatientsByPatientConditionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where patientCondition not equals to DEFAULT_PATIENT_CONDITION
        defaultPatientShouldNotBeFound("patientCondition.notEquals=" + DEFAULT_PATIENT_CONDITION);

        // Get all the patientList where patientCondition not equals to UPDATED_PATIENT_CONDITION
        defaultPatientShouldBeFound("patientCondition.notEquals=" + UPDATED_PATIENT_CONDITION);
    }

    @Test
    @Transactional
    public void getAllPatientsByPatientConditionIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where patientCondition in DEFAULT_PATIENT_CONDITION or UPDATED_PATIENT_CONDITION
        defaultPatientShouldBeFound("patientCondition.in=" + DEFAULT_PATIENT_CONDITION + "," + UPDATED_PATIENT_CONDITION);

        // Get all the patientList where patientCondition equals to UPDATED_PATIENT_CONDITION
        defaultPatientShouldNotBeFound("patientCondition.in=" + UPDATED_PATIENT_CONDITION);
    }

    @Test
    @Transactional
    public void getAllPatientsByPatientConditionIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where patientCondition is not null
        defaultPatientShouldBeFound("patientCondition.specified=true");

        // Get all the patientList where patientCondition is null
        defaultPatientShouldNotBeFound("patientCondition.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByPatientConditionContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where patientCondition contains DEFAULT_PATIENT_CONDITION
        defaultPatientShouldBeFound("patientCondition.contains=" + DEFAULT_PATIENT_CONDITION);

        // Get all the patientList where patientCondition contains UPDATED_PATIENT_CONDITION
        defaultPatientShouldNotBeFound("patientCondition.contains=" + UPDATED_PATIENT_CONDITION);
    }

    @Test
    @Transactional
    public void getAllPatientsByPatientConditionNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where patientCondition does not contain DEFAULT_PATIENT_CONDITION
        defaultPatientShouldNotBeFound("patientCondition.doesNotContain=" + DEFAULT_PATIENT_CONDITION);

        // Get all the patientList where patientCondition does not contain UPDATED_PATIENT_CONDITION
        defaultPatientShouldBeFound("patientCondition.doesNotContain=" + UPDATED_PATIENT_CONDITION);
    }


    @Test
    @Transactional
    public void getAllPatientsByCurrentMedicationIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where currentMedication equals to DEFAULT_CURRENT_MEDICATION
        defaultPatientShouldBeFound("currentMedication.equals=" + DEFAULT_CURRENT_MEDICATION);

        // Get all the patientList where currentMedication equals to UPDATED_CURRENT_MEDICATION
        defaultPatientShouldNotBeFound("currentMedication.equals=" + UPDATED_CURRENT_MEDICATION);
    }

    @Test
    @Transactional
    public void getAllPatientsByCurrentMedicationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where currentMedication not equals to DEFAULT_CURRENT_MEDICATION
        defaultPatientShouldNotBeFound("currentMedication.notEquals=" + DEFAULT_CURRENT_MEDICATION);

        // Get all the patientList where currentMedication not equals to UPDATED_CURRENT_MEDICATION
        defaultPatientShouldBeFound("currentMedication.notEquals=" + UPDATED_CURRENT_MEDICATION);
    }

    @Test
    @Transactional
    public void getAllPatientsByCurrentMedicationIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where currentMedication in DEFAULT_CURRENT_MEDICATION or UPDATED_CURRENT_MEDICATION
        defaultPatientShouldBeFound("currentMedication.in=" + DEFAULT_CURRENT_MEDICATION + "," + UPDATED_CURRENT_MEDICATION);

        // Get all the patientList where currentMedication equals to UPDATED_CURRENT_MEDICATION
        defaultPatientShouldNotBeFound("currentMedication.in=" + UPDATED_CURRENT_MEDICATION);
    }

    @Test
    @Transactional
    public void getAllPatientsByCurrentMedicationIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where currentMedication is not null
        defaultPatientShouldBeFound("currentMedication.specified=true");

        // Get all the patientList where currentMedication is null
        defaultPatientShouldNotBeFound("currentMedication.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByCurrentMedicationContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where currentMedication contains DEFAULT_CURRENT_MEDICATION
        defaultPatientShouldBeFound("currentMedication.contains=" + DEFAULT_CURRENT_MEDICATION);

        // Get all the patientList where currentMedication contains UPDATED_CURRENT_MEDICATION
        defaultPatientShouldNotBeFound("currentMedication.contains=" + UPDATED_CURRENT_MEDICATION);
    }

    @Test
    @Transactional
    public void getAllPatientsByCurrentMedicationNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where currentMedication does not contain DEFAULT_CURRENT_MEDICATION
        defaultPatientShouldNotBeFound("currentMedication.doesNotContain=" + DEFAULT_CURRENT_MEDICATION);

        // Get all the patientList where currentMedication does not contain UPDATED_CURRENT_MEDICATION
        defaultPatientShouldBeFound("currentMedication.doesNotContain=" + UPDATED_CURRENT_MEDICATION);
    }


    @Test
    @Transactional
    public void getAllPatientsByChronologicalHistoryIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where chronologicalHistory equals to DEFAULT_CHRONOLOGICAL_HISTORY
        defaultPatientShouldBeFound("chronologicalHistory.equals=" + DEFAULT_CHRONOLOGICAL_HISTORY);

        // Get all the patientList where chronologicalHistory equals to UPDATED_CHRONOLOGICAL_HISTORY
        defaultPatientShouldNotBeFound("chronologicalHistory.equals=" + UPDATED_CHRONOLOGICAL_HISTORY);
    }

    @Test
    @Transactional
    public void getAllPatientsByChronologicalHistoryIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where chronologicalHistory not equals to DEFAULT_CHRONOLOGICAL_HISTORY
        defaultPatientShouldNotBeFound("chronologicalHistory.notEquals=" + DEFAULT_CHRONOLOGICAL_HISTORY);

        // Get all the patientList where chronologicalHistory not equals to UPDATED_CHRONOLOGICAL_HISTORY
        defaultPatientShouldBeFound("chronologicalHistory.notEquals=" + UPDATED_CHRONOLOGICAL_HISTORY);
    }

    @Test
    @Transactional
    public void getAllPatientsByChronologicalHistoryIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where chronologicalHistory in DEFAULT_CHRONOLOGICAL_HISTORY or UPDATED_CHRONOLOGICAL_HISTORY
        defaultPatientShouldBeFound("chronologicalHistory.in=" + DEFAULT_CHRONOLOGICAL_HISTORY + "," + UPDATED_CHRONOLOGICAL_HISTORY);

        // Get all the patientList where chronologicalHistory equals to UPDATED_CHRONOLOGICAL_HISTORY
        defaultPatientShouldNotBeFound("chronologicalHistory.in=" + UPDATED_CHRONOLOGICAL_HISTORY);
    }

    @Test
    @Transactional
    public void getAllPatientsByChronologicalHistoryIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where chronologicalHistory is not null
        defaultPatientShouldBeFound("chronologicalHistory.specified=true");

        // Get all the patientList where chronologicalHistory is null
        defaultPatientShouldNotBeFound("chronologicalHistory.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByChronologicalHistoryContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where chronologicalHistory contains DEFAULT_CHRONOLOGICAL_HISTORY
        defaultPatientShouldBeFound("chronologicalHistory.contains=" + DEFAULT_CHRONOLOGICAL_HISTORY);

        // Get all the patientList where chronologicalHistory contains UPDATED_CHRONOLOGICAL_HISTORY
        defaultPatientShouldNotBeFound("chronologicalHistory.contains=" + UPDATED_CHRONOLOGICAL_HISTORY);
    }

    @Test
    @Transactional
    public void getAllPatientsByChronologicalHistoryNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where chronologicalHistory does not contain DEFAULT_CHRONOLOGICAL_HISTORY
        defaultPatientShouldNotBeFound("chronologicalHistory.doesNotContain=" + DEFAULT_CHRONOLOGICAL_HISTORY);

        // Get all the patientList where chronologicalHistory does not contain UPDATED_CHRONOLOGICAL_HISTORY
        defaultPatientShouldBeFound("chronologicalHistory.doesNotContain=" + UPDATED_CHRONOLOGICAL_HISTORY);
    }


    @Test
    @Transactional
    public void getAllPatientsBySymptomaticChangesIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where symptomaticChanges equals to DEFAULT_SYMPTOMATIC_CHANGES
        defaultPatientShouldBeFound("symptomaticChanges.equals=" + DEFAULT_SYMPTOMATIC_CHANGES);

        // Get all the patientList where symptomaticChanges equals to UPDATED_SYMPTOMATIC_CHANGES
        defaultPatientShouldNotBeFound("symptomaticChanges.equals=" + UPDATED_SYMPTOMATIC_CHANGES);
    }

    @Test
    @Transactional
    public void getAllPatientsBySymptomaticChangesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where symptomaticChanges not equals to DEFAULT_SYMPTOMATIC_CHANGES
        defaultPatientShouldNotBeFound("symptomaticChanges.notEquals=" + DEFAULT_SYMPTOMATIC_CHANGES);

        // Get all the patientList where symptomaticChanges not equals to UPDATED_SYMPTOMATIC_CHANGES
        defaultPatientShouldBeFound("symptomaticChanges.notEquals=" + UPDATED_SYMPTOMATIC_CHANGES);
    }

    @Test
    @Transactional
    public void getAllPatientsBySymptomaticChangesIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where symptomaticChanges in DEFAULT_SYMPTOMATIC_CHANGES or UPDATED_SYMPTOMATIC_CHANGES
        defaultPatientShouldBeFound("symptomaticChanges.in=" + DEFAULT_SYMPTOMATIC_CHANGES + "," + UPDATED_SYMPTOMATIC_CHANGES);

        // Get all the patientList where symptomaticChanges equals to UPDATED_SYMPTOMATIC_CHANGES
        defaultPatientShouldNotBeFound("symptomaticChanges.in=" + UPDATED_SYMPTOMATIC_CHANGES);
    }

    @Test
    @Transactional
    public void getAllPatientsBySymptomaticChangesIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where symptomaticChanges is not null
        defaultPatientShouldBeFound("symptomaticChanges.specified=true");

        // Get all the patientList where symptomaticChanges is null
        defaultPatientShouldNotBeFound("symptomaticChanges.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsBySymptomaticChangesContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where symptomaticChanges contains DEFAULT_SYMPTOMATIC_CHANGES
        defaultPatientShouldBeFound("symptomaticChanges.contains=" + DEFAULT_SYMPTOMATIC_CHANGES);

        // Get all the patientList where symptomaticChanges contains UPDATED_SYMPTOMATIC_CHANGES
        defaultPatientShouldNotBeFound("symptomaticChanges.contains=" + UPDATED_SYMPTOMATIC_CHANGES);
    }

    @Test
    @Transactional
    public void getAllPatientsBySymptomaticChangesNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where symptomaticChanges does not contain DEFAULT_SYMPTOMATIC_CHANGES
        defaultPatientShouldNotBeFound("symptomaticChanges.doesNotContain=" + DEFAULT_SYMPTOMATIC_CHANGES);

        // Get all the patientList where symptomaticChanges does not contain UPDATED_SYMPTOMATIC_CHANGES
        defaultPatientShouldBeFound("symptomaticChanges.doesNotContain=" + UPDATED_SYMPTOMATIC_CHANGES);
    }


    @Test
    @Transactional
    public void getAllPatientsByLevelOfDiscomfortIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfDiscomfort equals to DEFAULT_LEVEL_OF_DISCOMFORT
        defaultPatientShouldBeFound("levelOfDiscomfort.equals=" + DEFAULT_LEVEL_OF_DISCOMFORT);

        // Get all the patientList where levelOfDiscomfort equals to UPDATED_LEVEL_OF_DISCOMFORT
        defaultPatientShouldNotBeFound("levelOfDiscomfort.equals=" + UPDATED_LEVEL_OF_DISCOMFORT);
    }

    @Test
    @Transactional
    public void getAllPatientsByLevelOfDiscomfortIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfDiscomfort not equals to DEFAULT_LEVEL_OF_DISCOMFORT
        defaultPatientShouldNotBeFound("levelOfDiscomfort.notEquals=" + DEFAULT_LEVEL_OF_DISCOMFORT);

        // Get all the patientList where levelOfDiscomfort not equals to UPDATED_LEVEL_OF_DISCOMFORT
        defaultPatientShouldBeFound("levelOfDiscomfort.notEquals=" + UPDATED_LEVEL_OF_DISCOMFORT);
    }

    @Test
    @Transactional
    public void getAllPatientsByLevelOfDiscomfortIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfDiscomfort in DEFAULT_LEVEL_OF_DISCOMFORT or UPDATED_LEVEL_OF_DISCOMFORT
        defaultPatientShouldBeFound("levelOfDiscomfort.in=" + DEFAULT_LEVEL_OF_DISCOMFORT + "," + UPDATED_LEVEL_OF_DISCOMFORT);

        // Get all the patientList where levelOfDiscomfort equals to UPDATED_LEVEL_OF_DISCOMFORT
        defaultPatientShouldNotBeFound("levelOfDiscomfort.in=" + UPDATED_LEVEL_OF_DISCOMFORT);
    }

    @Test
    @Transactional
    public void getAllPatientsByLevelOfDiscomfortIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfDiscomfort is not null
        defaultPatientShouldBeFound("levelOfDiscomfort.specified=true");

        // Get all the patientList where levelOfDiscomfort is null
        defaultPatientShouldNotBeFound("levelOfDiscomfort.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByLevelOfDiscomfortIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfDiscomfort is greater than or equal to DEFAULT_LEVEL_OF_DISCOMFORT
        defaultPatientShouldBeFound("levelOfDiscomfort.greaterThanOrEqual=" + DEFAULT_LEVEL_OF_DISCOMFORT);

        // Get all the patientList where levelOfDiscomfort is greater than or equal to UPDATED_LEVEL_OF_DISCOMFORT
        defaultPatientShouldNotBeFound("levelOfDiscomfort.greaterThanOrEqual=" + UPDATED_LEVEL_OF_DISCOMFORT);
    }

    @Test
    @Transactional
    public void getAllPatientsByLevelOfDiscomfortIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfDiscomfort is less than or equal to DEFAULT_LEVEL_OF_DISCOMFORT
        defaultPatientShouldBeFound("levelOfDiscomfort.lessThanOrEqual=" + DEFAULT_LEVEL_OF_DISCOMFORT);

        // Get all the patientList where levelOfDiscomfort is less than or equal to SMALLER_LEVEL_OF_DISCOMFORT
        defaultPatientShouldNotBeFound("levelOfDiscomfort.lessThanOrEqual=" + SMALLER_LEVEL_OF_DISCOMFORT);
    }

    @Test
    @Transactional
    public void getAllPatientsByLevelOfDiscomfortIsLessThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfDiscomfort is less than DEFAULT_LEVEL_OF_DISCOMFORT
        defaultPatientShouldNotBeFound("levelOfDiscomfort.lessThan=" + DEFAULT_LEVEL_OF_DISCOMFORT);

        // Get all the patientList where levelOfDiscomfort is less than UPDATED_LEVEL_OF_DISCOMFORT
        defaultPatientShouldBeFound("levelOfDiscomfort.lessThan=" + UPDATED_LEVEL_OF_DISCOMFORT);
    }

    @Test
    @Transactional
    public void getAllPatientsByLevelOfDiscomfortIsGreaterThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where levelOfDiscomfort is greater than DEFAULT_LEVEL_OF_DISCOMFORT
        defaultPatientShouldNotBeFound("levelOfDiscomfort.greaterThan=" + DEFAULT_LEVEL_OF_DISCOMFORT);

        // Get all the patientList where levelOfDiscomfort is greater than SMALLER_LEVEL_OF_DISCOMFORT
        defaultPatientShouldBeFound("levelOfDiscomfort.greaterThan=" + SMALLER_LEVEL_OF_DISCOMFORT);
    }


    @Test
    @Transactional
    public void getAllPatientsByFeesDiscountIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where feesDiscount equals to DEFAULT_FEES_DISCOUNT
        defaultPatientShouldBeFound("feesDiscount.equals=" + DEFAULT_FEES_DISCOUNT);

        // Get all the patientList where feesDiscount equals to UPDATED_FEES_DISCOUNT
        defaultPatientShouldNotBeFound("feesDiscount.equals=" + UPDATED_FEES_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllPatientsByFeesDiscountIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where feesDiscount not equals to DEFAULT_FEES_DISCOUNT
        defaultPatientShouldNotBeFound("feesDiscount.notEquals=" + DEFAULT_FEES_DISCOUNT);

        // Get all the patientList where feesDiscount not equals to UPDATED_FEES_DISCOUNT
        defaultPatientShouldBeFound("feesDiscount.notEquals=" + UPDATED_FEES_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllPatientsByFeesDiscountIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where feesDiscount in DEFAULT_FEES_DISCOUNT or UPDATED_FEES_DISCOUNT
        defaultPatientShouldBeFound("feesDiscount.in=" + DEFAULT_FEES_DISCOUNT + "," + UPDATED_FEES_DISCOUNT);

        // Get all the patientList where feesDiscount equals to UPDATED_FEES_DISCOUNT
        defaultPatientShouldNotBeFound("feesDiscount.in=" + UPDATED_FEES_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllPatientsByFeesDiscountIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where feesDiscount is not null
        defaultPatientShouldBeFound("feesDiscount.specified=true");

        // Get all the patientList where feesDiscount is null
        defaultPatientShouldNotBeFound("feesDiscount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByFeesDiscountGivenByIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where feesDiscountGivenBy equals to DEFAULT_FEES_DISCOUNT_GIVEN_BY
        defaultPatientShouldBeFound("feesDiscountGivenBy.equals=" + DEFAULT_FEES_DISCOUNT_GIVEN_BY);

        // Get all the patientList where feesDiscountGivenBy equals to UPDATED_FEES_DISCOUNT_GIVEN_BY
        defaultPatientShouldNotBeFound("feesDiscountGivenBy.equals=" + UPDATED_FEES_DISCOUNT_GIVEN_BY);
    }

    @Test
    @Transactional
    public void getAllPatientsByFeesDiscountGivenByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where feesDiscountGivenBy not equals to DEFAULT_FEES_DISCOUNT_GIVEN_BY
        defaultPatientShouldNotBeFound("feesDiscountGivenBy.notEquals=" + DEFAULT_FEES_DISCOUNT_GIVEN_BY);

        // Get all the patientList where feesDiscountGivenBy not equals to UPDATED_FEES_DISCOUNT_GIVEN_BY
        defaultPatientShouldBeFound("feesDiscountGivenBy.notEquals=" + UPDATED_FEES_DISCOUNT_GIVEN_BY);
    }

    @Test
    @Transactional
    public void getAllPatientsByFeesDiscountGivenByIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where feesDiscountGivenBy in DEFAULT_FEES_DISCOUNT_GIVEN_BY or UPDATED_FEES_DISCOUNT_GIVEN_BY
        defaultPatientShouldBeFound("feesDiscountGivenBy.in=" + DEFAULT_FEES_DISCOUNT_GIVEN_BY + "," + UPDATED_FEES_DISCOUNT_GIVEN_BY);

        // Get all the patientList where feesDiscountGivenBy equals to UPDATED_FEES_DISCOUNT_GIVEN_BY
        defaultPatientShouldNotBeFound("feesDiscountGivenBy.in=" + UPDATED_FEES_DISCOUNT_GIVEN_BY);
    }

    @Test
    @Transactional
    public void getAllPatientsByFeesDiscountGivenByIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where feesDiscountGivenBy is not null
        defaultPatientShouldBeFound("feesDiscountGivenBy.specified=true");

        // Get all the patientList where feesDiscountGivenBy is null
        defaultPatientShouldNotBeFound("feesDiscountGivenBy.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByFeesDiscountGivenByContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where feesDiscountGivenBy contains DEFAULT_FEES_DISCOUNT_GIVEN_BY
        defaultPatientShouldBeFound("feesDiscountGivenBy.contains=" + DEFAULT_FEES_DISCOUNT_GIVEN_BY);

        // Get all the patientList where feesDiscountGivenBy contains UPDATED_FEES_DISCOUNT_GIVEN_BY
        defaultPatientShouldNotBeFound("feesDiscountGivenBy.contains=" + UPDATED_FEES_DISCOUNT_GIVEN_BY);
    }

    @Test
    @Transactional
    public void getAllPatientsByFeesDiscountGivenByNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where feesDiscountGivenBy does not contain DEFAULT_FEES_DISCOUNT_GIVEN_BY
        defaultPatientShouldNotBeFound("feesDiscountGivenBy.doesNotContain=" + DEFAULT_FEES_DISCOUNT_GIVEN_BY);

        // Get all the patientList where feesDiscountGivenBy does not contain UPDATED_FEES_DISCOUNT_GIVEN_BY
        defaultPatientShouldBeFound("feesDiscountGivenBy.doesNotContain=" + UPDATED_FEES_DISCOUNT_GIVEN_BY);
    }


    @Test
    @Transactional
    public void getAllPatientsByDiscountTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where discountType equals to DEFAULT_DISCOUNT_TYPE
        defaultPatientShouldBeFound("discountType.equals=" + DEFAULT_DISCOUNT_TYPE);

        // Get all the patientList where discountType equals to UPDATED_DISCOUNT_TYPE
        defaultPatientShouldNotBeFound("discountType.equals=" + UPDATED_DISCOUNT_TYPE);
    }

    @Test
    @Transactional
    public void getAllPatientsByDiscountTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where discountType not equals to DEFAULT_DISCOUNT_TYPE
        defaultPatientShouldNotBeFound("discountType.notEquals=" + DEFAULT_DISCOUNT_TYPE);

        // Get all the patientList where discountType not equals to UPDATED_DISCOUNT_TYPE
        defaultPatientShouldBeFound("discountType.notEquals=" + UPDATED_DISCOUNT_TYPE);
    }

    @Test
    @Transactional
    public void getAllPatientsByDiscountTypeIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where discountType in DEFAULT_DISCOUNT_TYPE or UPDATED_DISCOUNT_TYPE
        defaultPatientShouldBeFound("discountType.in=" + DEFAULT_DISCOUNT_TYPE + "," + UPDATED_DISCOUNT_TYPE);

        // Get all the patientList where discountType equals to UPDATED_DISCOUNT_TYPE
        defaultPatientShouldNotBeFound("discountType.in=" + UPDATED_DISCOUNT_TYPE);
    }

    @Test
    @Transactional
    public void getAllPatientsByDiscountTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where discountType is not null
        defaultPatientShouldBeFound("discountType.specified=true");

        // Get all the patientList where discountType is null
        defaultPatientShouldNotBeFound("discountType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByDiscountIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where discount equals to DEFAULT_DISCOUNT
        defaultPatientShouldBeFound("discount.equals=" + DEFAULT_DISCOUNT);

        // Get all the patientList where discount equals to UPDATED_DISCOUNT
        defaultPatientShouldNotBeFound("discount.equals=" + UPDATED_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllPatientsByDiscountIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where discount not equals to DEFAULT_DISCOUNT
        defaultPatientShouldNotBeFound("discount.notEquals=" + DEFAULT_DISCOUNT);

        // Get all the patientList where discount not equals to UPDATED_DISCOUNT
        defaultPatientShouldBeFound("discount.notEquals=" + UPDATED_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllPatientsByDiscountIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where discount in DEFAULT_DISCOUNT or UPDATED_DISCOUNT
        defaultPatientShouldBeFound("discount.in=" + DEFAULT_DISCOUNT + "," + UPDATED_DISCOUNT);

        // Get all the patientList where discount equals to UPDATED_DISCOUNT
        defaultPatientShouldNotBeFound("discount.in=" + UPDATED_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllPatientsByDiscountIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where discount is not null
        defaultPatientShouldBeFound("discount.specified=true");

        // Get all the patientList where discount is null
        defaultPatientShouldNotBeFound("discount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByDiscountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where discount is greater than or equal to DEFAULT_DISCOUNT
        defaultPatientShouldBeFound("discount.greaterThanOrEqual=" + DEFAULT_DISCOUNT);

        // Get all the patientList where discount is greater than or equal to UPDATED_DISCOUNT
        defaultPatientShouldNotBeFound("discount.greaterThanOrEqual=" + UPDATED_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllPatientsByDiscountIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where discount is less than or equal to DEFAULT_DISCOUNT
        defaultPatientShouldBeFound("discount.lessThanOrEqual=" + DEFAULT_DISCOUNT);

        // Get all the patientList where discount is less than or equal to SMALLER_DISCOUNT
        defaultPatientShouldNotBeFound("discount.lessThanOrEqual=" + SMALLER_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllPatientsByDiscountIsLessThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where discount is less than DEFAULT_DISCOUNT
        defaultPatientShouldNotBeFound("discount.lessThan=" + DEFAULT_DISCOUNT);

        // Get all the patientList where discount is less than UPDATED_DISCOUNT
        defaultPatientShouldBeFound("discount.lessThan=" + UPDATED_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllPatientsByDiscountIsGreaterThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where discount is greater than DEFAULT_DISCOUNT
        defaultPatientShouldNotBeFound("discount.greaterThan=" + DEFAULT_DISCOUNT);

        // Get all the patientList where discount is greater than SMALLER_DISCOUNT
        defaultPatientShouldBeFound("discount.greaterThan=" + SMALLER_DISCOUNT);
    }


    @Test
    @Transactional
    public void getAllPatientsByAllowEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowEmail equals to DEFAULT_ALLOW_EMAIL
        defaultPatientShouldBeFound("allowEmail.equals=" + DEFAULT_ALLOW_EMAIL);

        // Get all the patientList where allowEmail equals to UPDATED_ALLOW_EMAIL
        defaultPatientShouldNotBeFound("allowEmail.equals=" + UPDATED_ALLOW_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowEmail not equals to DEFAULT_ALLOW_EMAIL
        defaultPatientShouldNotBeFound("allowEmail.notEquals=" + DEFAULT_ALLOW_EMAIL);

        // Get all the patientList where allowEmail not equals to UPDATED_ALLOW_EMAIL
        defaultPatientShouldBeFound("allowEmail.notEquals=" + UPDATED_ALLOW_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowEmailIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowEmail in DEFAULT_ALLOW_EMAIL or UPDATED_ALLOW_EMAIL
        defaultPatientShouldBeFound("allowEmail.in=" + DEFAULT_ALLOW_EMAIL + "," + UPDATED_ALLOW_EMAIL);

        // Get all the patientList where allowEmail equals to UPDATED_ALLOW_EMAIL
        defaultPatientShouldNotBeFound("allowEmail.in=" + UPDATED_ALLOW_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowEmail is not null
        defaultPatientShouldBeFound("allowEmail.specified=true");

        // Get all the patientList where allowEmail is null
        defaultPatientShouldNotBeFound("allowEmail.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowSMSIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowSMS equals to DEFAULT_ALLOW_SMS
        defaultPatientShouldBeFound("allowSMS.equals=" + DEFAULT_ALLOW_SMS);

        // Get all the patientList where allowSMS equals to UPDATED_ALLOW_SMS
        defaultPatientShouldNotBeFound("allowSMS.equals=" + UPDATED_ALLOW_SMS);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowSMSIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowSMS not equals to DEFAULT_ALLOW_SMS
        defaultPatientShouldNotBeFound("allowSMS.notEquals=" + DEFAULT_ALLOW_SMS);

        // Get all the patientList where allowSMS not equals to UPDATED_ALLOW_SMS
        defaultPatientShouldBeFound("allowSMS.notEquals=" + UPDATED_ALLOW_SMS);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowSMSIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowSMS in DEFAULT_ALLOW_SMS or UPDATED_ALLOW_SMS
        defaultPatientShouldBeFound("allowSMS.in=" + DEFAULT_ALLOW_SMS + "," + UPDATED_ALLOW_SMS);

        // Get all the patientList where allowSMS equals to UPDATED_ALLOW_SMS
        defaultPatientShouldNotBeFound("allowSMS.in=" + UPDATED_ALLOW_SMS);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowSMSIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowSMS is not null
        defaultPatientShouldBeFound("allowSMS.specified=true");

        // Get all the patientList where allowSMS is null
        defaultPatientShouldNotBeFound("allowSMS.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowWhatsAppIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowWhatsApp equals to DEFAULT_ALLOW_WHATS_APP
        defaultPatientShouldBeFound("allowWhatsApp.equals=" + DEFAULT_ALLOW_WHATS_APP);

        // Get all the patientList where allowWhatsApp equals to UPDATED_ALLOW_WHATS_APP
        defaultPatientShouldNotBeFound("allowWhatsApp.equals=" + UPDATED_ALLOW_WHATS_APP);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowWhatsAppIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowWhatsApp not equals to DEFAULT_ALLOW_WHATS_APP
        defaultPatientShouldNotBeFound("allowWhatsApp.notEquals=" + DEFAULT_ALLOW_WHATS_APP);

        // Get all the patientList where allowWhatsApp not equals to UPDATED_ALLOW_WHATS_APP
        defaultPatientShouldBeFound("allowWhatsApp.notEquals=" + UPDATED_ALLOW_WHATS_APP);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowWhatsAppIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowWhatsApp in DEFAULT_ALLOW_WHATS_APP or UPDATED_ALLOW_WHATS_APP
        defaultPatientShouldBeFound("allowWhatsApp.in=" + DEFAULT_ALLOW_WHATS_APP + "," + UPDATED_ALLOW_WHATS_APP);

        // Get all the patientList where allowWhatsApp equals to UPDATED_ALLOW_WHATS_APP
        defaultPatientShouldNotBeFound("allowWhatsApp.in=" + UPDATED_ALLOW_WHATS_APP);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowWhatsAppIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowWhatsApp is not null
        defaultPatientShouldBeFound("allowWhatsApp.specified=true");

        // Get all the patientList where allowWhatsApp is null
        defaultPatientShouldNotBeFound("allowWhatsApp.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowPromotionsIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowPromotions equals to DEFAULT_ALLOW_PROMOTIONS
        defaultPatientShouldBeFound("allowPromotions.equals=" + DEFAULT_ALLOW_PROMOTIONS);

        // Get all the patientList where allowPromotions equals to UPDATED_ALLOW_PROMOTIONS
        defaultPatientShouldNotBeFound("allowPromotions.equals=" + UPDATED_ALLOW_PROMOTIONS);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowPromotionsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowPromotions not equals to DEFAULT_ALLOW_PROMOTIONS
        defaultPatientShouldNotBeFound("allowPromotions.notEquals=" + DEFAULT_ALLOW_PROMOTIONS);

        // Get all the patientList where allowPromotions not equals to UPDATED_ALLOW_PROMOTIONS
        defaultPatientShouldBeFound("allowPromotions.notEquals=" + UPDATED_ALLOW_PROMOTIONS);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowPromotionsIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowPromotions in DEFAULT_ALLOW_PROMOTIONS or UPDATED_ALLOW_PROMOTIONS
        defaultPatientShouldBeFound("allowPromotions.in=" + DEFAULT_ALLOW_PROMOTIONS + "," + UPDATED_ALLOW_PROMOTIONS);

        // Get all the patientList where allowPromotions equals to UPDATED_ALLOW_PROMOTIONS
        defaultPatientShouldNotBeFound("allowPromotions.in=" + UPDATED_ALLOW_PROMOTIONS);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowPromotionsIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowPromotions is not null
        defaultPatientShouldBeFound("allowPromotions.specified=true");

        // Get all the patientList where allowPromotions is null
        defaultPatientShouldNotBeFound("allowPromotions.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByiAgreeIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where iAgree equals to DEFAULT_I_AGREE
        defaultPatientShouldBeFound("iAgree.equals=" + DEFAULT_I_AGREE);

        // Get all the patientList where iAgree equals to UPDATED_I_AGREE
        defaultPatientShouldNotBeFound("iAgree.equals=" + UPDATED_I_AGREE);
    }

    @Test
    @Transactional
    public void getAllPatientsByiAgreeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where iAgree not equals to DEFAULT_I_AGREE
        defaultPatientShouldNotBeFound("iAgree.notEquals=" + DEFAULT_I_AGREE);

        // Get all the patientList where iAgree not equals to UPDATED_I_AGREE
        defaultPatientShouldBeFound("iAgree.notEquals=" + UPDATED_I_AGREE);
    }

    @Test
    @Transactional
    public void getAllPatientsByiAgreeIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where iAgree in DEFAULT_I_AGREE or UPDATED_I_AGREE
        defaultPatientShouldBeFound("iAgree.in=" + DEFAULT_I_AGREE + "," + UPDATED_I_AGREE);

        // Get all the patientList where iAgree equals to UPDATED_I_AGREE
        defaultPatientShouldNotBeFound("iAgree.in=" + UPDATED_I_AGREE);
    }

    @Test
    @Transactional
    public void getAllPatientsByiAgreeIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where iAgree is not null
        defaultPatientShouldBeFound("iAgree.specified=true");

        // Get all the patientList where iAgree is null
        defaultPatientShouldNotBeFound("iAgree.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByHealingLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where healingLevel equals to DEFAULT_HEALING_LEVEL
        defaultPatientShouldBeFound("healingLevel.equals=" + DEFAULT_HEALING_LEVEL);

        // Get all the patientList where healingLevel equals to UPDATED_HEALING_LEVEL
        defaultPatientShouldNotBeFound("healingLevel.equals=" + UPDATED_HEALING_LEVEL);
    }

    @Test
    @Transactional
    public void getAllPatientsByHealingLevelIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where healingLevel not equals to DEFAULT_HEALING_LEVEL
        defaultPatientShouldNotBeFound("healingLevel.notEquals=" + DEFAULT_HEALING_LEVEL);

        // Get all the patientList where healingLevel not equals to UPDATED_HEALING_LEVEL
        defaultPatientShouldBeFound("healingLevel.notEquals=" + UPDATED_HEALING_LEVEL);
    }

    @Test
    @Transactional
    public void getAllPatientsByHealingLevelIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where healingLevel in DEFAULT_HEALING_LEVEL or UPDATED_HEALING_LEVEL
        defaultPatientShouldBeFound("healingLevel.in=" + DEFAULT_HEALING_LEVEL + "," + UPDATED_HEALING_LEVEL);

        // Get all the patientList where healingLevel equals to UPDATED_HEALING_LEVEL
        defaultPatientShouldNotBeFound("healingLevel.in=" + UPDATED_HEALING_LEVEL);
    }

    @Test
    @Transactional
    public void getAllPatientsByHealingLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where healingLevel is not null
        defaultPatientShouldBeFound("healingLevel.specified=true");

        // Get all the patientList where healingLevel is null
        defaultPatientShouldNotBeFound("healingLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowLoginIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowLogin equals to DEFAULT_ALLOW_LOGIN
        defaultPatientShouldBeFound("allowLogin.equals=" + DEFAULT_ALLOW_LOGIN);

        // Get all the patientList where allowLogin equals to UPDATED_ALLOW_LOGIN
        defaultPatientShouldNotBeFound("allowLogin.equals=" + UPDATED_ALLOW_LOGIN);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowLoginIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowLogin not equals to DEFAULT_ALLOW_LOGIN
        defaultPatientShouldNotBeFound("allowLogin.notEquals=" + DEFAULT_ALLOW_LOGIN);

        // Get all the patientList where allowLogin not equals to UPDATED_ALLOW_LOGIN
        defaultPatientShouldBeFound("allowLogin.notEquals=" + UPDATED_ALLOW_LOGIN);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowLoginIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowLogin in DEFAULT_ALLOW_LOGIN or UPDATED_ALLOW_LOGIN
        defaultPatientShouldBeFound("allowLogin.in=" + DEFAULT_ALLOW_LOGIN + "," + UPDATED_ALLOW_LOGIN);

        // Get all the patientList where allowLogin equals to UPDATED_ALLOW_LOGIN
        defaultPatientShouldNotBeFound("allowLogin.in=" + UPDATED_ALLOW_LOGIN);
    }

    @Test
    @Transactional
    public void getAllPatientsByAllowLoginIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where allowLogin is not null
        defaultPatientShouldBeFound("allowLogin.specified=true");

        // Get all the patientList where allowLogin is null
        defaultPatientShouldNotBeFound("allowLogin.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByPasswordIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where password equals to DEFAULT_PASSWORD
        defaultPatientShouldBeFound("password.equals=" + DEFAULT_PASSWORD);

        // Get all the patientList where password equals to UPDATED_PASSWORD
        defaultPatientShouldNotBeFound("password.equals=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    public void getAllPatientsByPasswordIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where password not equals to DEFAULT_PASSWORD
        defaultPatientShouldNotBeFound("password.notEquals=" + DEFAULT_PASSWORD);

        // Get all the patientList where password not equals to UPDATED_PASSWORD
        defaultPatientShouldBeFound("password.notEquals=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    public void getAllPatientsByPasswordIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where password in DEFAULT_PASSWORD or UPDATED_PASSWORD
        defaultPatientShouldBeFound("password.in=" + DEFAULT_PASSWORD + "," + UPDATED_PASSWORD);

        // Get all the patientList where password equals to UPDATED_PASSWORD
        defaultPatientShouldNotBeFound("password.in=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    public void getAllPatientsByPasswordIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where password is not null
        defaultPatientShouldBeFound("password.specified=true");

        // Get all the patientList where password is null
        defaultPatientShouldNotBeFound("password.specified=false");
    }
                @Test
    @Transactional
    public void getAllPatientsByPasswordContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where password contains DEFAULT_PASSWORD
        defaultPatientShouldBeFound("password.contains=" + DEFAULT_PASSWORD);

        // Get all the patientList where password contains UPDATED_PASSWORD
        defaultPatientShouldNotBeFound("password.contains=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    public void getAllPatientsByPasswordNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where password does not contain DEFAULT_PASSWORD
        defaultPatientShouldNotBeFound("password.doesNotContain=" + DEFAULT_PASSWORD);

        // Get all the patientList where password does not contain UPDATED_PASSWORD
        defaultPatientShouldBeFound("password.doesNotContain=" + UPDATED_PASSWORD);
    }


    @Test
    @Transactional
    public void getAllPatientsByVoidedIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where voided equals to DEFAULT_VOIDED
        defaultPatientShouldBeFound("voided.equals=" + DEFAULT_VOIDED);

        // Get all the patientList where voided equals to UPDATED_VOIDED
        defaultPatientShouldNotBeFound("voided.equals=" + UPDATED_VOIDED);
    }

    @Test
    @Transactional
    public void getAllPatientsByVoidedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where voided not equals to DEFAULT_VOIDED
        defaultPatientShouldNotBeFound("voided.notEquals=" + DEFAULT_VOIDED);

        // Get all the patientList where voided not equals to UPDATED_VOIDED
        defaultPatientShouldBeFound("voided.notEquals=" + UPDATED_VOIDED);
    }

    @Test
    @Transactional
    public void getAllPatientsByVoidedIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where voided in DEFAULT_VOIDED or UPDATED_VOIDED
        defaultPatientShouldBeFound("voided.in=" + DEFAULT_VOIDED + "," + UPDATED_VOIDED);

        // Get all the patientList where voided equals to UPDATED_VOIDED
        defaultPatientShouldNotBeFound("voided.in=" + UPDATED_VOIDED);
    }

    @Test
    @Transactional
    public void getAllPatientsByVoidedIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where voided is not null
        defaultPatientShouldBeFound("voided.specified=true");

        // Get all the patientList where voided is null
        defaultPatientShouldNotBeFound("voided.specified=false");
    }

    @Test
    @Transactional
    public void getAllPatientsByUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where userId equals to DEFAULT_USER_ID
        defaultPatientShouldBeFound("userId.equals=" + DEFAULT_USER_ID);

        // Get all the patientList where userId equals to UPDATED_USER_ID
        defaultPatientShouldNotBeFound("userId.equals=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllPatientsByUserIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where userId not equals to DEFAULT_USER_ID
        defaultPatientShouldNotBeFound("userId.notEquals=" + DEFAULT_USER_ID);

        // Get all the patientList where userId not equals to UPDATED_USER_ID
        defaultPatientShouldBeFound("userId.notEquals=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllPatientsByUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where userId in DEFAULT_USER_ID or UPDATED_USER_ID
        defaultPatientShouldBeFound("userId.in=" + DEFAULT_USER_ID + "," + UPDATED_USER_ID);

        // Get all the patientList where userId equals to UPDATED_USER_ID
        defaultPatientShouldNotBeFound("userId.in=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllPatientsByUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where userId is not null
        defaultPatientShouldBeFound("userId.specified=true");

        // Get all the patientList where userId is null
        defaultPatientShouldNotBeFound("userId.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPatientShouldBeFound(String filter) throws Exception {
        restPatientMockMvc.perform(get("/api/patients?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(patient.getId().toString())))
            .andExpect(jsonPath("$.[*].formNumber").value(hasItem(DEFAULT_FORM_NUMBER)))
            .andExpect(jsonPath("$.[*].centerId").value(hasItem(DEFAULT_CENTER_ID.toString())))
            .andExpect(jsonPath("$.[*].centerName").value(hasItem(DEFAULT_CENTER_NAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].displayName").value(hasItem(DEFAULT_DISPLAY_NAME)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].conState").value(hasItem(DEFAULT_CON_STATE)))
            .andExpect(jsonPath("$.[*].stateCode").value(hasItem(DEFAULT_STATE_CODE)))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE)))
            .andExpect(jsonPath("$.[*].photo1ContentType").value(hasItem(DEFAULT_PHOTO_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo1").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO_1))))
            .andExpect(jsonPath("$.[*].photo2ContentType").value(hasItem(DEFAULT_PHOTO_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo2").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO_2))))
            .andExpect(jsonPath("$.[*].mobileNumber").value(hasItem(DEFAULT_MOBILE_NUMBER)))
            .andExpect(jsonPath("$.[*].contactNo").value(hasItem(DEFAULT_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].education").value(hasItem(DEFAULT_EDUCATION)))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].regDate").value(hasItem(DEFAULT_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION)))
            .andExpect(jsonPath("$.[*].marriageStatus").value(hasItem(DEFAULT_MARRIAGE_STATUS)))
            .andExpect(jsonPath("$.[*].expired").value(hasItem(DEFAULT_EXPIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].languageName").value(hasItem(DEFAULT_LANGUAGE_NAME)))
            .andExpect(jsonPath("$.[*].vegNonVeg").value(hasItem(DEFAULT_VEG_NON_VEG.booleanValue())))
            .andExpect(jsonPath("$.[*].referedBy").value(hasItem(DEFAULT_REFERED_BY)))
            .andExpect(jsonPath("$.[*].referdByName").value(hasItem(DEFAULT_REFERD_BY_NAME)))
            .andExpect(jsonPath("$.[*].exerciseRegularly").value(hasItem(DEFAULT_EXERCISE_REGULARLY.booleanValue())))
            .andExpect(jsonPath("$.[*].addiction").value(hasItem(DEFAULT_ADDICTION.booleanValue())))
            .andExpect(jsonPath("$.[*].hoursSleep").value(hasItem(DEFAULT_HOURS_SLEEP)))
            .andExpect(jsonPath("$.[*].wakeRested").value(hasItem(DEFAULT_WAKE_RESTED.booleanValue())))
            .andExpect(jsonPath("$.[*].waterIntake").value(hasItem(DEFAULT_WATER_INTAKE)))
            .andExpect(jsonPath("$.[*].hoursWork").value(hasItem(DEFAULT_HOURS_WORK)))
            .andExpect(jsonPath("$.[*].shiftWork").value(hasItem(DEFAULT_SHIFT_WORK.booleanValue())))
            .andExpect(jsonPath("$.[*].levelOfStress").value(hasItem(DEFAULT_LEVEL_OF_STRESS)))
            .andExpect(jsonPath("$.[*].patientCondition").value(hasItem(DEFAULT_PATIENT_CONDITION)))
            .andExpect(jsonPath("$.[*].currentMedication").value(hasItem(DEFAULT_CURRENT_MEDICATION)))
            .andExpect(jsonPath("$.[*].chronologicalHistory").value(hasItem(DEFAULT_CHRONOLOGICAL_HISTORY)))
            .andExpect(jsonPath("$.[*].symptomaticChanges").value(hasItem(DEFAULT_SYMPTOMATIC_CHANGES)))
            .andExpect(jsonPath("$.[*].levelOfDiscomfort").value(hasItem(DEFAULT_LEVEL_OF_DISCOMFORT)))
            .andExpect(jsonPath("$.[*].feesDiscount").value(hasItem(DEFAULT_FEES_DISCOUNT.booleanValue())))
            .andExpect(jsonPath("$.[*].feesDiscountGivenBy").value(hasItem(DEFAULT_FEES_DISCOUNT_GIVEN_BY)))
            .andExpect(jsonPath("$.[*].discountType").value(hasItem(DEFAULT_DISCOUNT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.toString())))
            .andExpect(jsonPath("$.[*].allowEmail").value(hasItem(DEFAULT_ALLOW_EMAIL.booleanValue())))
            .andExpect(jsonPath("$.[*].allowSMS").value(hasItem(DEFAULT_ALLOW_SMS.booleanValue())))
            .andExpect(jsonPath("$.[*].allowWhatsApp").value(hasItem(DEFAULT_ALLOW_WHATS_APP.booleanValue())))
            .andExpect(jsonPath("$.[*].allowPromotions").value(hasItem(DEFAULT_ALLOW_PROMOTIONS.booleanValue())))
            .andExpect(jsonPath("$.[*].iAgree").value(hasItem(DEFAULT_I_AGREE.booleanValue())))
            .andExpect(jsonPath("$.[*].healingLevel").value(hasItem(DEFAULT_HEALING_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].allowLogin").value(hasItem(DEFAULT_ALLOW_LOGIN.booleanValue())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].voided").value(hasItem(DEFAULT_VOIDED.booleanValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())));

        // Check, that the count call also returns 1
        restPatientMockMvc.perform(get("/api/patients/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPatientShouldNotBeFound(String filter) throws Exception {
        restPatientMockMvc.perform(get("/api/patients?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPatientMockMvc.perform(get("/api/patients/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingPatient() throws Exception {
        // Get the patient
        restPatientMockMvc.perform(get("/api/patients/{id}", UUID.randomUUID()))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePatient() throws Exception {
        // Initialize the database
        patientService.save(patient);

        int databaseSizeBeforeUpdate = patientRepository.findAll().size();

        // Update the patient
        Patient updatedPatient = patientRepository.findById(patient.getId()).get();
        // Disconnect from session so that the updates on updatedPatient are not directly saved in db
        em.detach(updatedPatient);
        updatedPatient
            .formNumber(UPDATED_FORM_NUMBER)
            .centerId(UPDATED_CENTER_ID)
            .centerName(UPDATED_CENTER_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .displayName(UPDATED_DISPLAY_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .gender(UPDATED_GENDER)
            .age(UPDATED_AGE)
            .address(UPDATED_ADDRESS)
            .area(UPDATED_AREA)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .conState(UPDATED_CON_STATE)
            .stateCode(UPDATED_STATE_CODE)
            .region(UPDATED_REGION)
            .country(UPDATED_COUNTRY)
            .countryCode(UPDATED_COUNTRY_CODE)
            .pincode(UPDATED_PINCODE)
            .photo1(UPDATED_PHOTO_1)
            .photo1ContentType(UPDATED_PHOTO_1_CONTENT_TYPE)
            .photo2(UPDATED_PHOTO_2)
            .photo2ContentType(UPDATED_PHOTO_2_CONTENT_TYPE)
            .mobileNumber(UPDATED_MOBILE_NUMBER)
            .contactNo(UPDATED_CONTACT_NO)
            .education(UPDATED_EDUCATION)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .email(UPDATED_EMAIL)
            .regDate(UPDATED_REG_DATE)
            .profession(UPDATED_PROFESSION)
            .marriageStatus(UPDATED_MARRIAGE_STATUS)
            .expired(UPDATED_EXPIRED)
            .languageName(UPDATED_LANGUAGE_NAME)
            .vegNonVeg(UPDATED_VEG_NON_VEG)
            .referedBy(UPDATED_REFERED_BY)
            .referdByName(UPDATED_REFERD_BY_NAME)
            .exerciseRegularly(UPDATED_EXERCISE_REGULARLY)
            .addiction(UPDATED_ADDICTION)
            .hoursSleep(UPDATED_HOURS_SLEEP)
            .wakeRested(UPDATED_WAKE_RESTED)
            .waterIntake(UPDATED_WATER_INTAKE)
            .hoursWork(UPDATED_HOURS_WORK)
            .shiftWork(UPDATED_SHIFT_WORK)
            .levelOfStress(UPDATED_LEVEL_OF_STRESS)
            .patientCondition(UPDATED_PATIENT_CONDITION)
            .currentMedication(UPDATED_CURRENT_MEDICATION)
            .chronologicalHistory(UPDATED_CHRONOLOGICAL_HISTORY)
            .symptomaticChanges(UPDATED_SYMPTOMATIC_CHANGES)
            .levelOfDiscomfort(UPDATED_LEVEL_OF_DISCOMFORT)
            .feesDiscount(UPDATED_FEES_DISCOUNT)
            .feesDiscountGivenBy(UPDATED_FEES_DISCOUNT_GIVEN_BY)
            .discountType(UPDATED_DISCOUNT_TYPE)
            .discount(UPDATED_DISCOUNT)
            .allowEmail(UPDATED_ALLOW_EMAIL)
            .allowSMS(UPDATED_ALLOW_SMS)
            .allowWhatsApp(UPDATED_ALLOW_WHATS_APP)
            .allowPromotions(UPDATED_ALLOW_PROMOTIONS)
            .iAgree(UPDATED_I_AGREE)
            .healingLevel(UPDATED_HEALING_LEVEL)
            .allowLogin(UPDATED_ALLOW_LOGIN)
            .password(UPDATED_PASSWORD)
            .voided(UPDATED_VOIDED)
            .userId(UPDATED_USER_ID);

        restPatientMockMvc.perform(put("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPatient)))
            .andExpect(status().isOk());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getFormNumber()).isEqualTo(UPDATED_FORM_NUMBER);
        assertThat(testPatient.getCenterId()).isEqualTo(UPDATED_CENTER_ID);
        assertThat(testPatient.getCenterName()).isEqualTo(UPDATED_CENTER_NAME);
        assertThat(testPatient.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testPatient.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testPatient.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testPatient.getDisplayName()).isEqualTo(UPDATED_DISPLAY_NAME);
        assertThat(testPatient.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testPatient.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPatient.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testPatient.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testPatient.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testPatient.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testPatient.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testPatient.getConState()).isEqualTo(UPDATED_CON_STATE);
        assertThat(testPatient.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
        assertThat(testPatient.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testPatient.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testPatient.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testPatient.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testPatient.getPhoto1()).isEqualTo(UPDATED_PHOTO_1);
        assertThat(testPatient.getPhoto1ContentType()).isEqualTo(UPDATED_PHOTO_1_CONTENT_TYPE);
        assertThat(testPatient.getPhoto2()).isEqualTo(UPDATED_PHOTO_2);
        assertThat(testPatient.getPhoto2ContentType()).isEqualTo(UPDATED_PHOTO_2_CONTENT_TYPE);
        assertThat(testPatient.getMobileNumber()).isEqualTo(UPDATED_MOBILE_NUMBER);
        assertThat(testPatient.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testPatient.getEducation()).isEqualTo(UPDATED_EDUCATION);
        assertThat(testPatient.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
        assertThat(testPatient.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPatient.getRegDate()).isEqualTo(UPDATED_REG_DATE);
        assertThat(testPatient.getProfession()).isEqualTo(UPDATED_PROFESSION);
        assertThat(testPatient.getMarriageStatus()).isEqualTo(UPDATED_MARRIAGE_STATUS);
        assertThat(testPatient.isExpired()).isEqualTo(UPDATED_EXPIRED);
        assertThat(testPatient.getLanguageName()).isEqualTo(UPDATED_LANGUAGE_NAME);
        assertThat(testPatient.isVegNonVeg()).isEqualTo(UPDATED_VEG_NON_VEG);
        assertThat(testPatient.getReferedBy()).isEqualTo(UPDATED_REFERED_BY);
        assertThat(testPatient.getReferdByName()).isEqualTo(UPDATED_REFERD_BY_NAME);
        assertThat(testPatient.isExerciseRegularly()).isEqualTo(UPDATED_EXERCISE_REGULARLY);
        assertThat(testPatient.isAddiction()).isEqualTo(UPDATED_ADDICTION);
        assertThat(testPatient.getHoursSleep()).isEqualTo(UPDATED_HOURS_SLEEP);
        assertThat(testPatient.isWakeRested()).isEqualTo(UPDATED_WAKE_RESTED);
        assertThat(testPatient.getWaterIntake()).isEqualTo(UPDATED_WATER_INTAKE);
        assertThat(testPatient.getHoursWork()).isEqualTo(UPDATED_HOURS_WORK);
        assertThat(testPatient.isShiftWork()).isEqualTo(UPDATED_SHIFT_WORK);
        assertThat(testPatient.getLevelOfStress()).isEqualTo(UPDATED_LEVEL_OF_STRESS);
        assertThat(testPatient.getPatientCondition()).isEqualTo(UPDATED_PATIENT_CONDITION);
        assertThat(testPatient.getCurrentMedication()).isEqualTo(UPDATED_CURRENT_MEDICATION);
        assertThat(testPatient.getChronologicalHistory()).isEqualTo(UPDATED_CHRONOLOGICAL_HISTORY);
        assertThat(testPatient.getSymptomaticChanges()).isEqualTo(UPDATED_SYMPTOMATIC_CHANGES);
        assertThat(testPatient.getLevelOfDiscomfort()).isEqualTo(UPDATED_LEVEL_OF_DISCOMFORT);
        assertThat(testPatient.isFeesDiscount()).isEqualTo(UPDATED_FEES_DISCOUNT);
        assertThat(testPatient.getFeesDiscountGivenBy()).isEqualTo(UPDATED_FEES_DISCOUNT_GIVEN_BY);
        assertThat(testPatient.getDiscountType()).isEqualTo(UPDATED_DISCOUNT_TYPE);
        assertThat(testPatient.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testPatient.isAllowEmail()).isEqualTo(UPDATED_ALLOW_EMAIL);
        assertThat(testPatient.isAllowSMS()).isEqualTo(UPDATED_ALLOW_SMS);
        assertThat(testPatient.isAllowWhatsApp()).isEqualTo(UPDATED_ALLOW_WHATS_APP);
        assertThat(testPatient.isAllowPromotions()).isEqualTo(UPDATED_ALLOW_PROMOTIONS);
        assertThat(testPatient.isiAgree()).isEqualTo(UPDATED_I_AGREE);
        assertThat(testPatient.getHealingLevel()).isEqualTo(UPDATED_HEALING_LEVEL);
        assertThat(testPatient.isAllowLogin()).isEqualTo(UPDATED_ALLOW_LOGIN);
        assertThat(testPatient.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testPatient.isVoided()).isEqualTo(UPDATED_VOIDED);
        assertThat(testPatient.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPatientMockMvc.perform(put("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePatient() throws Exception {
        // Initialize the database
        patientService.save(patient);

        int databaseSizeBeforeDelete = patientRepository.findAll().size();

        // Delete the patient
        restPatientMockMvc.perform(delete("/api/patients/{id}", patient.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
