package com.bluepearlbiotech.patient.repository;

import com.bluepearlbiotech.patient.domain.Location;

import org.springframework.data.jpa.repository.*;
import java.util.UUID;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Location entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocationRepository extends JpaRepository<Location, UUID>, JpaSpecificationExecutor<Location> {
}
