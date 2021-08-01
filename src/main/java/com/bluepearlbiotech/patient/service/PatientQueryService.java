package com.bluepearlbiotech.patient.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.bluepearlbiotech.patient.domain.Patient;
import com.bluepearlbiotech.patient.domain.*; // for static metamodels
import com.bluepearlbiotech.patient.repository.PatientRepository;
import com.bluepearlbiotech.patient.service.dto.PatientCriteria;

/**
 * Service for executing complex queries for {@link Patient} entities in the
 * database. The main input is a {@link PatientCriteria} which gets converted to
 * {@link Specification}, in a way that all the filters must apply. It returns a
 * {@link List} of {@link Patient} or a {@link Page} of {@link Patient} which
 * fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PatientQueryService extends QueryService<Patient> {

    private final Logger log = LoggerFactory.getLogger(PatientQueryService.class);

    private final PatientRepository patientRepository;

    public PatientQueryService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Return a {@link List} of {@link Patient} which matches the criteria from the
     * database.
     * 
     * @param criteria The object which holds all the filters, which the entities
     *                 should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Patient> findByCriteria(PatientCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Patient> specification = createSpecification(criteria);
        return patientRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Patient} which matches the criteria from the
     * database.
     * 
     * @param criteria The object which holds all the filters, which the entities
     *                 should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Patient> findByCriteria(PatientCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Patient> specification = createSpecification(criteria);
        return patientRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * 
     * @param criteria The object which holds all the filters, which the entities
     *                 should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PatientCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Patient> specification = createSpecification(criteria);
        return patientRepository.count(specification);
    }

    /**
     * Function to convert {@link PatientCriteria} to a {@link Specification}
     * 
     * @param criteria The object which holds all the filters, which the entities
     *                 should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Patient> createSpecification(PatientCriteria criteria) {
        Specification<Patient> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Patient_.id));
            }
            if (criteria.getFormNumber() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getFormNumber(), Patient_.formNumber));
            }
            if (criteria.getCenterId() != null) {
                specification = specification.and(buildSpecification(criteria.getCenterId(), Patient_.centerId));
            }
            if (criteria.getCenterName() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getCenterName(), Patient_.centerName));
            }
            if (criteria.getFirstName() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getFirstName(), Patient_.firstName));
            }
            if (criteria.getMiddleName() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getMiddleName(), Patient_.middleName));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), Patient_.lastName));
            }
            if (criteria.getDisplayName() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getDisplayName(), Patient_.displayName));
            }
            if (criteria.getDateOfBirth() != null) {
                specification = specification
                        .and(buildRangeSpecification(criteria.getDateOfBirth(), Patient_.dateOfBirth));
            }
            if (criteria.getGender() != null) {
                specification = specification.and(buildSpecification(criteria.getGender(), Patient_.gender));
            }
            if (criteria.getAge() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAge(), Patient_.age));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Patient_.address));
            }
            if (criteria.getArea() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArea(), Patient_.area));
            }
            if (criteria.getCity() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCity(), Patient_.city));
            }
            if (criteria.getDistrict() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDistrict(), Patient_.district));
            }
            if (criteria.getConState() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConState(), Patient_.conState));
            }
            if (criteria.getStateCode() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getStateCode(), Patient_.stateCode));
            }
            if (criteria.getRegion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRegion(), Patient_.region));
            }
            if (criteria.getCountry() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCountry(), Patient_.country));
            }
            if (criteria.getCountryCode() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getCountryCode(), Patient_.countryCode));
            }
            if (criteria.getPincode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPincode(), Patient_.pincode));
            }
            if (criteria.getMobileNumber() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getMobileNumber(), Patient_.mobileNumber));
            }
            if (criteria.getContactNo() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getContactNo(), Patient_.contactNo));
            }
            if (criteria.getEducation() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getEducation(), Patient_.education));
            }
            if (criteria.getBloodGroup() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getBloodGroup(), Patient_.bloodGroup));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Patient_.email));
            }
            if (criteria.getRegDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegDate(), Patient_.regDate));
            }
            if (criteria.getProfession() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getProfession(), Patient_.profession));
            }
            if (criteria.getMarriageStatus() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getMarriageStatus(), Patient_.marriageStatus));
            }
            if (criteria.getExpired() != null) {
                specification = specification.and(buildSpecification(criteria.getExpired(), Patient_.expired));
            }
            if (criteria.getLanguageName() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getLanguageName(), Patient_.languageName));
            }
            if (criteria.getVegNonVeg() != null) {
                specification = specification.and(buildSpecification(criteria.getVegNonVeg(), Patient_.vegNonVeg));
            }
            if (criteria.getReferedBy() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getReferedBy(), Patient_.referedBy));
            }
            if (criteria.getReferdByName() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getReferdByName(), Patient_.referdByName));
            }
            if (criteria.getExerciseRegularly() != null) {
                specification = specification
                        .and(buildSpecification(criteria.getExerciseRegularly(), Patient_.exerciseRegularly));
            }
            if (criteria.getAddiction() != null) {
                specification = specification.and(buildSpecification(criteria.getAddiction(), Patient_.addiction));
            }
            if (criteria.getHoursSleep() != null) {
                specification = specification
                        .and(buildRangeSpecification(criteria.getHoursSleep(), Patient_.hoursSleep));
            }
            if (criteria.getWakeRested() != null) {
                specification = specification.and(buildSpecification(criteria.getWakeRested(), Patient_.wakeRested));
            }
            if (criteria.getWaterIntake() != null) {
                specification = specification
                        .and(buildRangeSpecification(criteria.getWaterIntake(), Patient_.waterIntake));
            }
            if (criteria.getHoursWork() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHoursWork(), Patient_.hoursWork));
            }
            if (criteria.getShiftWork() != null) {
                specification = specification.and(buildSpecification(criteria.getShiftWork(), Patient_.shiftWork));
            }
            if (criteria.getLevelOfStress() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getLevelOfStress(), Patient_.levelOfStress));
            }
            if (criteria.getPatientCondition() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getPatientCondition(), Patient_.patientCondition));
            }
            if (criteria.getCurrentMedication() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getCurrentMedication(), Patient_.currentMedication));
            }
            if (criteria.getChronologicalHistory() != null) {
                specification = specification.and(
                        buildStringSpecification(criteria.getChronologicalHistory(), Patient_.chronologicalHistory));
            }
            if (criteria.getSymptomaticChanges() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getSymptomaticChanges(), Patient_.symptomaticChanges));
            }
            if (criteria.getLevelOfDiscomfort() != null) {
                specification = specification
                        .and(buildRangeSpecification(criteria.getLevelOfDiscomfort(), Patient_.levelOfDiscomfort));
            }
            if (criteria.getFeesDiscount() != null) {
                specification = specification
                        .and(buildSpecification(criteria.getFeesDiscount(), Patient_.feesDiscount));
            }
            if (criteria.getFeesDiscountGivenBy() != null) {
                specification = specification
                        .and(buildStringSpecification(criteria.getFeesDiscountGivenBy(), Patient_.feesDiscountGivenBy));
            }
            if (criteria.getDiscountType() != null) {
                specification = specification
                        .and(buildSpecification(criteria.getDiscountType(), Patient_.discountType));
            }
            if (criteria.getDiscount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiscount(), Patient_.discount));
            }
            if (criteria.getAllowEmail() != null) {
                specification = specification.and(buildSpecification(criteria.getAllowEmail(), Patient_.allowEmail));
            }
            if (criteria.getAllowSMS() != null) {
                specification = specification.and(buildSpecification(criteria.getAllowSMS(), Patient_.allowSMS));
            }
            if (criteria.getAllowWhatsApp() != null) {
                specification = specification
                        .and(buildSpecification(criteria.getAllowWhatsApp(), Patient_.allowWhatsApp));
            }
            if (criteria.getAllowPromotions() != null) {
                specification = specification
                        .and(buildSpecification(criteria.getAllowPromotions(), Patient_.allowPromotions));
            }
            if (criteria.getiAgree() != null) {
                specification = specification.and(buildSpecification(criteria.getiAgree(), Patient_.iAgree));
            }
            if (criteria.getHealingLevel() != null) {
                specification = specification
                        .and(buildSpecification(criteria.getHealingLevel(), Patient_.healingLevel));
            }
            if (criteria.getAllowLogin() != null) {
                specification = specification.and(buildSpecification(criteria.getAllowLogin(), Patient_.allowLogin));
            }
            if (criteria.getPassword() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPassword(), Patient_.password));
            }
            if (criteria.getVoided() != null) {
                specification = specification.and(buildSpecification(criteria.getVoided(), Patient_.voided));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(), Patient_.userId));
            }
        }
        return specification;
    }
}
