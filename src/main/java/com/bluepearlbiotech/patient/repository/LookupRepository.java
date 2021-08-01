package com.bluepearlbiotech.patient.repository;

import com.bluepearlbiotech.patient.domain.Lookup;

import org.springframework.data.jpa.repository.*;
import java.util.UUID;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Lookup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LookupRepository extends JpaRepository<Lookup, UUID>, JpaSpecificationExecutor<Lookup> {
}
