package com.bluepearlbiotech.patient.web.rest;

import com.bluepearlbiotech.patient.PatientApp;
import com.bluepearlbiotech.patient.domain.Lookup;
import com.bluepearlbiotech.patient.repository.LookupRepository;
import com.bluepearlbiotech.patient.service.LookupService;
import com.bluepearlbiotech.patient.service.dto.LookupCriteria;
import com.bluepearlbiotech.patient.service.LookupQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LookupResource} REST controller.
 */
@SpringBootTest(classes = PatientApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LookupResourceIT {

    private static final String DEFAULT_LOOKUP = "AAAAAAAAAA";
    private static final String UPDATED_LOOKUP = "BBBBBBBBBB";

    private static final String DEFAULT_LOOKUP_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_LOOKUP_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOOKUP_ORDER = 1;
    private static final Integer UPDATED_LOOKUP_ORDER = 2;
    private static final Integer SMALLER_LOOKUP_ORDER = 1 - 1;

    private static final String DEFAULT_LOOKUP_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_LOOKUP_NOTES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VOIDED = false;
    private static final Boolean UPDATED_VOIDED = true;

    @Autowired
    private LookupRepository lookupRepository;

    @Autowired
    private LookupService lookupService;

    @Autowired
    private LookupQueryService lookupQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLookupMockMvc;

    private Lookup lookup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lookup createEntity(EntityManager em) {
        Lookup lookup = new Lookup()
            .lookup(DEFAULT_LOOKUP)
            .lookupType(DEFAULT_LOOKUP_TYPE)
            .lookupOrder(DEFAULT_LOOKUP_ORDER)
            .lookupNotes(DEFAULT_LOOKUP_NOTES)
            .voided(DEFAULT_VOIDED);
        return lookup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lookup createUpdatedEntity(EntityManager em) {
        Lookup lookup = new Lookup()
            .lookup(UPDATED_LOOKUP)
            .lookupType(UPDATED_LOOKUP_TYPE)
            .lookupOrder(UPDATED_LOOKUP_ORDER)
            .lookupNotes(UPDATED_LOOKUP_NOTES)
            .voided(UPDATED_VOIDED);
        return lookup;
    }

    @BeforeEach
    public void initTest() {
        lookup = createEntity(em);
    }

    @Test
    @Transactional
    public void createLookup() throws Exception {
        int databaseSizeBeforeCreate = lookupRepository.findAll().size();
        // Create the Lookup
        restLookupMockMvc.perform(post("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookup)))
            .andExpect(status().isCreated());

        // Validate the Lookup in the database
        List<Lookup> lookupList = lookupRepository.findAll();
        assertThat(lookupList).hasSize(databaseSizeBeforeCreate + 1);
        Lookup testLookup = lookupList.get(lookupList.size() - 1);
        assertThat(testLookup.getLookup()).isEqualTo(DEFAULT_LOOKUP);
        assertThat(testLookup.getLookupType()).isEqualTo(DEFAULT_LOOKUP_TYPE);
        assertThat(testLookup.getLookupOrder()).isEqualTo(DEFAULT_LOOKUP_ORDER);
        assertThat(testLookup.getLookupNotes()).isEqualTo(DEFAULT_LOOKUP_NOTES);
        assertThat(testLookup.isVoided()).isEqualTo(DEFAULT_VOIDED);
    }

    @Test
    @Transactional
    public void createLookupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lookupRepository.findAll().size();

        // Create the Lookup with an existing ID
        lookup.setId("00000000-0000-0000-0000-000000000001");

        // An entity with an existing ID cannot be created, so this API call must fail
        restLookupMockMvc.perform(post("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookup)))
            .andExpect(status().isBadRequest());

        // Validate the Lookup in the database
        List<Lookup> lookupList = lookupRepository.findAll();
        assertThat(lookupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLookupIsRequired() throws Exception {
        int databaseSizeBeforeTest = lookupRepository.findAll().size();
        // set the field null
        lookup.setLookup(null);

        // Create the Lookup, which fails.


        restLookupMockMvc.perform(post("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookup)))
            .andExpect(status().isBadRequest());

        List<Lookup> lookupList = lookupRepository.findAll();
        assertThat(lookupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLookupTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = lookupRepository.findAll().size();
        // set the field null
        lookup.setLookupType(null);

        // Create the Lookup, which fails.


        restLookupMockMvc.perform(post("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookup)))
            .andExpect(status().isBadRequest());

        List<Lookup> lookupList = lookupRepository.findAll();
        assertThat(lookupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLookups() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList
        restLookupMockMvc.perform(get("/api/lookups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lookup.getId().toString())))
            .andExpect(jsonPath("$.[*].lookup").value(hasItem(DEFAULT_LOOKUP)))
            .andExpect(jsonPath("$.[*].lookupType").value(hasItem(DEFAULT_LOOKUP_TYPE)))
            .andExpect(jsonPath("$.[*].lookupOrder").value(hasItem(DEFAULT_LOOKUP_ORDER)))
            .andExpect(jsonPath("$.[*].lookupNotes").value(hasItem(DEFAULT_LOOKUP_NOTES)))
            .andExpect(jsonPath("$.[*].voided").value(hasItem(DEFAULT_VOIDED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getLookup() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get the lookup
        restLookupMockMvc.perform(get("/api/lookups/{id}", lookup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lookup.getId().toString()))
            .andExpect(jsonPath("$.lookup").value(DEFAULT_LOOKUP))
            .andExpect(jsonPath("$.lookupType").value(DEFAULT_LOOKUP_TYPE))
            .andExpect(jsonPath("$.lookupOrder").value(DEFAULT_LOOKUP_ORDER))
            .andExpect(jsonPath("$.lookupNotes").value(DEFAULT_LOOKUP_NOTES))
            .andExpect(jsonPath("$.voided").value(DEFAULT_VOIDED.booleanValue()));
    }


    @Test
    @Transactional
    public void getLookupsByIdFiltering() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        UUID id = lookup.getId();

        defaultLookupShouldBeFound("id.equals=" + id);
        defaultLookupShouldNotBeFound("id.notEquals=" + id);

        defaultLookupShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultLookupShouldNotBeFound("id.greaterThan=" + id);

        defaultLookupShouldBeFound("id.lessThanOrEqual=" + id);
        defaultLookupShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllLookupsByLookupIsEqualToSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookup equals to DEFAULT_LOOKUP
        defaultLookupShouldBeFound("lookup.equals=" + DEFAULT_LOOKUP);

        // Get all the lookupList where lookup equals to UPDATED_LOOKUP
        defaultLookupShouldNotBeFound("lookup.equals=" + UPDATED_LOOKUP);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookup not equals to DEFAULT_LOOKUP
        defaultLookupShouldNotBeFound("lookup.notEquals=" + DEFAULT_LOOKUP);

        // Get all the lookupList where lookup not equals to UPDATED_LOOKUP
        defaultLookupShouldBeFound("lookup.notEquals=" + UPDATED_LOOKUP);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupIsInShouldWork() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookup in DEFAULT_LOOKUP or UPDATED_LOOKUP
        defaultLookupShouldBeFound("lookup.in=" + DEFAULT_LOOKUP + "," + UPDATED_LOOKUP);

        // Get all the lookupList where lookup equals to UPDATED_LOOKUP
        defaultLookupShouldNotBeFound("lookup.in=" + UPDATED_LOOKUP);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupIsNullOrNotNull() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookup is not null
        defaultLookupShouldBeFound("lookup.specified=true");

        // Get all the lookupList where lookup is null
        defaultLookupShouldNotBeFound("lookup.specified=false");
    }
                @Test
    @Transactional
    public void getAllLookupsByLookupContainsSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookup contains DEFAULT_LOOKUP
        defaultLookupShouldBeFound("lookup.contains=" + DEFAULT_LOOKUP);

        // Get all the lookupList where lookup contains UPDATED_LOOKUP
        defaultLookupShouldNotBeFound("lookup.contains=" + UPDATED_LOOKUP);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupNotContainsSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookup does not contain DEFAULT_LOOKUP
        defaultLookupShouldNotBeFound("lookup.doesNotContain=" + DEFAULT_LOOKUP);

        // Get all the lookupList where lookup does not contain UPDATED_LOOKUP
        defaultLookupShouldBeFound("lookup.doesNotContain=" + UPDATED_LOOKUP);
    }


    @Test
    @Transactional
    public void getAllLookupsByLookupTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupType equals to DEFAULT_LOOKUP_TYPE
        defaultLookupShouldBeFound("lookupType.equals=" + DEFAULT_LOOKUP_TYPE);

        // Get all the lookupList where lookupType equals to UPDATED_LOOKUP_TYPE
        defaultLookupShouldNotBeFound("lookupType.equals=" + UPDATED_LOOKUP_TYPE);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupType not equals to DEFAULT_LOOKUP_TYPE
        defaultLookupShouldNotBeFound("lookupType.notEquals=" + DEFAULT_LOOKUP_TYPE);

        // Get all the lookupList where lookupType not equals to UPDATED_LOOKUP_TYPE
        defaultLookupShouldBeFound("lookupType.notEquals=" + UPDATED_LOOKUP_TYPE);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupTypeIsInShouldWork() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupType in DEFAULT_LOOKUP_TYPE or UPDATED_LOOKUP_TYPE
        defaultLookupShouldBeFound("lookupType.in=" + DEFAULT_LOOKUP_TYPE + "," + UPDATED_LOOKUP_TYPE);

        // Get all the lookupList where lookupType equals to UPDATED_LOOKUP_TYPE
        defaultLookupShouldNotBeFound("lookupType.in=" + UPDATED_LOOKUP_TYPE);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupType is not null
        defaultLookupShouldBeFound("lookupType.specified=true");

        // Get all the lookupList where lookupType is null
        defaultLookupShouldNotBeFound("lookupType.specified=false");
    }
                @Test
    @Transactional
    public void getAllLookupsByLookupTypeContainsSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupType contains DEFAULT_LOOKUP_TYPE
        defaultLookupShouldBeFound("lookupType.contains=" + DEFAULT_LOOKUP_TYPE);

        // Get all the lookupList where lookupType contains UPDATED_LOOKUP_TYPE
        defaultLookupShouldNotBeFound("lookupType.contains=" + UPDATED_LOOKUP_TYPE);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupTypeNotContainsSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupType does not contain DEFAULT_LOOKUP_TYPE
        defaultLookupShouldNotBeFound("lookupType.doesNotContain=" + DEFAULT_LOOKUP_TYPE);

        // Get all the lookupList where lookupType does not contain UPDATED_LOOKUP_TYPE
        defaultLookupShouldBeFound("lookupType.doesNotContain=" + UPDATED_LOOKUP_TYPE);
    }


    @Test
    @Transactional
    public void getAllLookupsByLookupOrderIsEqualToSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupOrder equals to DEFAULT_LOOKUP_ORDER
        defaultLookupShouldBeFound("lookupOrder.equals=" + DEFAULT_LOOKUP_ORDER);

        // Get all the lookupList where lookupOrder equals to UPDATED_LOOKUP_ORDER
        defaultLookupShouldNotBeFound("lookupOrder.equals=" + UPDATED_LOOKUP_ORDER);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupOrderIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupOrder not equals to DEFAULT_LOOKUP_ORDER
        defaultLookupShouldNotBeFound("lookupOrder.notEquals=" + DEFAULT_LOOKUP_ORDER);

        // Get all the lookupList where lookupOrder not equals to UPDATED_LOOKUP_ORDER
        defaultLookupShouldBeFound("lookupOrder.notEquals=" + UPDATED_LOOKUP_ORDER);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupOrderIsInShouldWork() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupOrder in DEFAULT_LOOKUP_ORDER or UPDATED_LOOKUP_ORDER
        defaultLookupShouldBeFound("lookupOrder.in=" + DEFAULT_LOOKUP_ORDER + "," + UPDATED_LOOKUP_ORDER);

        // Get all the lookupList where lookupOrder equals to UPDATED_LOOKUP_ORDER
        defaultLookupShouldNotBeFound("lookupOrder.in=" + UPDATED_LOOKUP_ORDER);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupOrderIsNullOrNotNull() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupOrder is not null
        defaultLookupShouldBeFound("lookupOrder.specified=true");

        // Get all the lookupList where lookupOrder is null
        defaultLookupShouldNotBeFound("lookupOrder.specified=false");
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupOrderIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupOrder is greater than or equal to DEFAULT_LOOKUP_ORDER
        defaultLookupShouldBeFound("lookupOrder.greaterThanOrEqual=" + DEFAULT_LOOKUP_ORDER);

        // Get all the lookupList where lookupOrder is greater than or equal to UPDATED_LOOKUP_ORDER
        defaultLookupShouldNotBeFound("lookupOrder.greaterThanOrEqual=" + UPDATED_LOOKUP_ORDER);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupOrderIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupOrder is less than or equal to DEFAULT_LOOKUP_ORDER
        defaultLookupShouldBeFound("lookupOrder.lessThanOrEqual=" + DEFAULT_LOOKUP_ORDER);

        // Get all the lookupList where lookupOrder is less than or equal to SMALLER_LOOKUP_ORDER
        defaultLookupShouldNotBeFound("lookupOrder.lessThanOrEqual=" + SMALLER_LOOKUP_ORDER);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupOrderIsLessThanSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupOrder is less than DEFAULT_LOOKUP_ORDER
        defaultLookupShouldNotBeFound("lookupOrder.lessThan=" + DEFAULT_LOOKUP_ORDER);

        // Get all the lookupList where lookupOrder is less than UPDATED_LOOKUP_ORDER
        defaultLookupShouldBeFound("lookupOrder.lessThan=" + UPDATED_LOOKUP_ORDER);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupOrderIsGreaterThanSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupOrder is greater than DEFAULT_LOOKUP_ORDER
        defaultLookupShouldNotBeFound("lookupOrder.greaterThan=" + DEFAULT_LOOKUP_ORDER);

        // Get all the lookupList where lookupOrder is greater than SMALLER_LOOKUP_ORDER
        defaultLookupShouldBeFound("lookupOrder.greaterThan=" + SMALLER_LOOKUP_ORDER);
    }


    @Test
    @Transactional
    public void getAllLookupsByLookupNotesIsEqualToSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupNotes equals to DEFAULT_LOOKUP_NOTES
        defaultLookupShouldBeFound("lookupNotes.equals=" + DEFAULT_LOOKUP_NOTES);

        // Get all the lookupList where lookupNotes equals to UPDATED_LOOKUP_NOTES
        defaultLookupShouldNotBeFound("lookupNotes.equals=" + UPDATED_LOOKUP_NOTES);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupNotesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupNotes not equals to DEFAULT_LOOKUP_NOTES
        defaultLookupShouldNotBeFound("lookupNotes.notEquals=" + DEFAULT_LOOKUP_NOTES);

        // Get all the lookupList where lookupNotes not equals to UPDATED_LOOKUP_NOTES
        defaultLookupShouldBeFound("lookupNotes.notEquals=" + UPDATED_LOOKUP_NOTES);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupNotesIsInShouldWork() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupNotes in DEFAULT_LOOKUP_NOTES or UPDATED_LOOKUP_NOTES
        defaultLookupShouldBeFound("lookupNotes.in=" + DEFAULT_LOOKUP_NOTES + "," + UPDATED_LOOKUP_NOTES);

        // Get all the lookupList where lookupNotes equals to UPDATED_LOOKUP_NOTES
        defaultLookupShouldNotBeFound("lookupNotes.in=" + UPDATED_LOOKUP_NOTES);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupNotesIsNullOrNotNull() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupNotes is not null
        defaultLookupShouldBeFound("lookupNotes.specified=true");

        // Get all the lookupList where lookupNotes is null
        defaultLookupShouldNotBeFound("lookupNotes.specified=false");
    }
                @Test
    @Transactional
    public void getAllLookupsByLookupNotesContainsSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupNotes contains DEFAULT_LOOKUP_NOTES
        defaultLookupShouldBeFound("lookupNotes.contains=" + DEFAULT_LOOKUP_NOTES);

        // Get all the lookupList where lookupNotes contains UPDATED_LOOKUP_NOTES
        defaultLookupShouldNotBeFound("lookupNotes.contains=" + UPDATED_LOOKUP_NOTES);
    }

    @Test
    @Transactional
    public void getAllLookupsByLookupNotesNotContainsSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where lookupNotes does not contain DEFAULT_LOOKUP_NOTES
        defaultLookupShouldNotBeFound("lookupNotes.doesNotContain=" + DEFAULT_LOOKUP_NOTES);

        // Get all the lookupList where lookupNotes does not contain UPDATED_LOOKUP_NOTES
        defaultLookupShouldBeFound("lookupNotes.doesNotContain=" + UPDATED_LOOKUP_NOTES);
    }


    @Test
    @Transactional
    public void getAllLookupsByVoidedIsEqualToSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where voided equals to DEFAULT_VOIDED
        defaultLookupShouldBeFound("voided.equals=" + DEFAULT_VOIDED);

        // Get all the lookupList where voided equals to UPDATED_VOIDED
        defaultLookupShouldNotBeFound("voided.equals=" + UPDATED_VOIDED);
    }

    @Test
    @Transactional
    public void getAllLookupsByVoidedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where voided not equals to DEFAULT_VOIDED
        defaultLookupShouldNotBeFound("voided.notEquals=" + DEFAULT_VOIDED);

        // Get all the lookupList where voided not equals to UPDATED_VOIDED
        defaultLookupShouldBeFound("voided.notEquals=" + UPDATED_VOIDED);
    }

    @Test
    @Transactional
    public void getAllLookupsByVoidedIsInShouldWork() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where voided in DEFAULT_VOIDED or UPDATED_VOIDED
        defaultLookupShouldBeFound("voided.in=" + DEFAULT_VOIDED + "," + UPDATED_VOIDED);

        // Get all the lookupList where voided equals to UPDATED_VOIDED
        defaultLookupShouldNotBeFound("voided.in=" + UPDATED_VOIDED);
    }

    @Test
    @Transactional
    public void getAllLookupsByVoidedIsNullOrNotNull() throws Exception {
        // Initialize the database
        lookupRepository.saveAndFlush(lookup);

        // Get all the lookupList where voided is not null
        defaultLookupShouldBeFound("voided.specified=true");

        // Get all the lookupList where voided is null
        defaultLookupShouldNotBeFound("voided.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLookupShouldBeFound(String filter) throws Exception {
        restLookupMockMvc.perform(get("/api/lookups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lookup.getId().toString())))
            .andExpect(jsonPath("$.[*].lookup").value(hasItem(DEFAULT_LOOKUP)))
            .andExpect(jsonPath("$.[*].lookupType").value(hasItem(DEFAULT_LOOKUP_TYPE)))
            .andExpect(jsonPath("$.[*].lookupOrder").value(hasItem(DEFAULT_LOOKUP_ORDER)))
            .andExpect(jsonPath("$.[*].lookupNotes").value(hasItem(DEFAULT_LOOKUP_NOTES)))
            .andExpect(jsonPath("$.[*].voided").value(hasItem(DEFAULT_VOIDED.booleanValue())));

        // Check, that the count call also returns 1
        restLookupMockMvc.perform(get("/api/lookups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLookupShouldNotBeFound(String filter) throws Exception {
        restLookupMockMvc.perform(get("/api/lookups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLookupMockMvc.perform(get("/api/lookups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingLookup() throws Exception {
        // Get the lookup
        restLookupMockMvc.perform(get("/api/lookups/{id}", UUID.randomUUID()))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLookup() throws Exception {
        // Initialize the database
        lookupService.save(lookup);

        int databaseSizeBeforeUpdate = lookupRepository.findAll().size();

        // Update the lookup
        Lookup updatedLookup = lookupRepository.findById(lookup.getId()).get();
        // Disconnect from session so that the updates on updatedLookup are not directly saved in db
        em.detach(updatedLookup);
        updatedLookup
            .lookup(UPDATED_LOOKUP)
            .lookupType(UPDATED_LOOKUP_TYPE)
            .lookupOrder(UPDATED_LOOKUP_ORDER)
            .lookupNotes(UPDATED_LOOKUP_NOTES)
            .voided(UPDATED_VOIDED);

        restLookupMockMvc.perform(put("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLookup)))
            .andExpect(status().isOk());

        // Validate the Lookup in the database
        List<Lookup> lookupList = lookupRepository.findAll();
        assertThat(lookupList).hasSize(databaseSizeBeforeUpdate);
        Lookup testLookup = lookupList.get(lookupList.size() - 1);
        assertThat(testLookup.getLookup()).isEqualTo(UPDATED_LOOKUP);
        assertThat(testLookup.getLookupType()).isEqualTo(UPDATED_LOOKUP_TYPE);
        assertThat(testLookup.getLookupOrder()).isEqualTo(UPDATED_LOOKUP_ORDER);
        assertThat(testLookup.getLookupNotes()).isEqualTo(UPDATED_LOOKUP_NOTES);
        assertThat(testLookup.isVoided()).isEqualTo(UPDATED_VOIDED);
    }

    @Test
    @Transactional
    public void updateNonExistingLookup() throws Exception {
        int databaseSizeBeforeUpdate = lookupRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLookupMockMvc.perform(put("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookup)))
            .andExpect(status().isBadRequest());

        // Validate the Lookup in the database
        List<Lookup> lookupList = lookupRepository.findAll();
        assertThat(lookupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLookup() throws Exception {
        // Initialize the database
        lookupService.save(lookup);

        int databaseSizeBeforeDelete = lookupRepository.findAll().size();

        // Delete the lookup
        restLookupMockMvc.perform(delete("/api/lookups/{id}", lookup.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Lookup> lookupList = lookupRepository.findAll();
        assertThat(lookupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
