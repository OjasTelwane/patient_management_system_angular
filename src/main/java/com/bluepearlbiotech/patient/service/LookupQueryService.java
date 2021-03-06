package com.bluepearlbiotech.patient.service;

import com.bluepearlbiotech.patient.domain.*; // for static metamodels
import com.bluepearlbiotech.patient.domain.Lookup;
import com.bluepearlbiotech.patient.repository.LookupRepository;
import com.bluepearlbiotech.patient.service.dto.LookupCriteria;
import io.github.jhipster.service.QueryService;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link Lookup} entities in the database.
 * The main input is a {@link LookupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Lookup} or a {@link Page} of {@link Lookup} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LookupQueryService extends QueryService<Lookup> {
  private final Logger log = LoggerFactory.getLogger(LookupQueryService.class);

  private final LookupRepository lookupRepository;

  public LookupQueryService(LookupRepository lookupRepository) {
    this.lookupRepository = lookupRepository;
  }

  /**
   * Return a {@link List} of {@link Lookup} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<Lookup> findByCriteria(LookupCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<Lookup> specification = createSpecification(criteria);
    return lookupRepository.findAll(specification);
  }

  /**
   * Return a {@link Page} of {@link Lookup} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<Lookup> findByCriteria(LookupCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<Lookup> specification = createSpecification(criteria);
    return lookupRepository.findAll(specification, page);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(LookupCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<Lookup> specification = createSpecification(criteria);
    return lookupRepository.count(specification);
  }

  /**
   * Function to convert {@link LookupCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<Lookup> createSpecification(LookupCriteria criteria) {
    Specification<Lookup> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildSpecification(criteria.getId(), Lookup_.id));
      }
      if (criteria.getLookup() != null) {
        specification = specification.and(buildStringSpecification(criteria.getLookup(), Lookup_.lookup));
      }
      if (criteria.getLookupType() != null) {
        specification = specification.and(buildStringSpecification(criteria.getLookupType(), Lookup_.lookupType));
      }
      if (criteria.getLookupOrder() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getLookupOrder(), Lookup_.lookupOrder));
      }
      if (criteria.getLookupNotes() != null) {
        specification = specification.and(buildStringSpecification(criteria.getLookupNotes(), Lookup_.lookupNotes));
      }
      if (criteria.getVoided() != null) {
        specification = specification.and(buildSpecification(criteria.getVoided(), Lookup_.voided));
      }
    }
    return specification;
  }
}
