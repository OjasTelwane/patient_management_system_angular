package com.bluepearlbiotech.patient.repository;

import com.bluepearlbiotech.patient.domain.Patient;

import org.springframework.data.jpa.repository.*;
import java.util.UUID;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Patient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID>, JpaSpecificationExecutor<Patient> {
}
