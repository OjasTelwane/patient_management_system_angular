package com.bluepearlbiotech.patient.web.rest;

import com.bluepearlbiotech.patient.PatientApp;
import com.bluepearlbiotech.patient.domain.Location;
import com.bluepearlbiotech.patient.repository.LocationRepository;
import com.bluepearlbiotech.patient.service.LocationService;
import com.bluepearlbiotech.patient.service.dto.LocationCriteria;
import com.bluepearlbiotech.patient.service.LocationQueryService;

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
 * Integration tests for the {@link LocationResource} REST controller.
 */
@SpringBootTest(classes = PatientApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LocationResourceIT {

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_CON_STATE = "AAAAAAAAAA";
    private static final String UPDATED_CON_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_AREA = "AAAAAAAAAA";
    private static final String UPDATED_AREA = "BBBBBBBBBB";

    private static final String DEFAULT_PINCODE = "AAAAAAAAAA";
    private static final String UPDATED_PINCODE = "BBBBBBBBBB";

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationQueryService locationQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLocationMockMvc;

    private Location location;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Location createEntity(EntityManager em) {
        Location location = new Location()
            .country(DEFAULT_COUNTRY)
            .countryCode(DEFAULT_COUNTRY_CODE)
            .region(DEFAULT_REGION)
            .conState(DEFAULT_CON_STATE)
            .stateCode(DEFAULT_STATE_CODE)
            .district(DEFAULT_DISTRICT)
            .city(DEFAULT_CITY)
            .area(DEFAULT_AREA)
            .pincode(DEFAULT_PINCODE);
        return location;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Location createUpdatedEntity(EntityManager em) {
        Location location = new Location()
            .country(UPDATED_COUNTRY)
            .countryCode(UPDATED_COUNTRY_CODE)
            .region(UPDATED_REGION)
            .conState(UPDATED_CON_STATE)
            .stateCode(UPDATED_STATE_CODE)
            .district(UPDATED_DISTRICT)
            .city(UPDATED_CITY)
            .area(UPDATED_AREA)
            .pincode(UPDATED_PINCODE);
        return location;
    }

    @BeforeEach
    public void initTest() {
        location = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocation() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().size();
        // Create the Location
        restLocationMockMvc.perform(post("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(location)))
            .andExpect(status().isCreated());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate + 1);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testLocation.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testLocation.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testLocation.getConState()).isEqualTo(DEFAULT_CON_STATE);
        assertThat(testLocation.getStateCode()).isEqualTo(DEFAULT_STATE_CODE);
        assertThat(testLocation.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testLocation.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testLocation.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testLocation.getPincode()).isEqualTo(DEFAULT_PINCODE);
    }

    @Test
    @Transactional
    public void createLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().size();

        // Create the Location with an existing ID
        location.setId("00000000-0000-0000-0000-000000000001");

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocationMockMvc.perform(post("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(location)))
            .andExpect(status().isBadRequest());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLocations() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(location.getId().toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].conState").value(hasItem(DEFAULT_CON_STATE)))
            .andExpect(jsonPath("$.[*].stateCode").value(hasItem(DEFAULT_STATE_CODE)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE)));
    }
    
    @Test
    @Transactional
    public void getLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get the location
        restLocationMockMvc.perform(get("/api/locations/{id}", location.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(location.getId().toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION))
            .andExpect(jsonPath("$.conState").value(DEFAULT_CON_STATE))
            .andExpect(jsonPath("$.stateCode").value(DEFAULT_STATE_CODE))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE));
    }


    @Test
    @Transactional
    public void getLocationsByIdFiltering() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        UUID id = location.getId();

        defaultLocationShouldBeFound("id.equals=" + id);
        defaultLocationShouldNotBeFound("id.notEquals=" + id);

        defaultLocationShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultLocationShouldNotBeFound("id.greaterThan=" + id);

        defaultLocationShouldBeFound("id.lessThanOrEqual=" + id);
        defaultLocationShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllLocationsByCountryIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where country equals to DEFAULT_COUNTRY
        defaultLocationShouldBeFound("country.equals=" + DEFAULT_COUNTRY);

        // Get all the locationList where country equals to UPDATED_COUNTRY
        defaultLocationShouldNotBeFound("country.equals=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllLocationsByCountryIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where country not equals to DEFAULT_COUNTRY
        defaultLocationShouldNotBeFound("country.notEquals=" + DEFAULT_COUNTRY);

        // Get all the locationList where country not equals to UPDATED_COUNTRY
        defaultLocationShouldBeFound("country.notEquals=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllLocationsByCountryIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where country in DEFAULT_COUNTRY or UPDATED_COUNTRY
        defaultLocationShouldBeFound("country.in=" + DEFAULT_COUNTRY + "," + UPDATED_COUNTRY);

        // Get all the locationList where country equals to UPDATED_COUNTRY
        defaultLocationShouldNotBeFound("country.in=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllLocationsByCountryIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where country is not null
        defaultLocationShouldBeFound("country.specified=true");

        // Get all the locationList where country is null
        defaultLocationShouldNotBeFound("country.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByCountryContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where country contains DEFAULT_COUNTRY
        defaultLocationShouldBeFound("country.contains=" + DEFAULT_COUNTRY);

        // Get all the locationList where country contains UPDATED_COUNTRY
        defaultLocationShouldNotBeFound("country.contains=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllLocationsByCountryNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where country does not contain DEFAULT_COUNTRY
        defaultLocationShouldNotBeFound("country.doesNotContain=" + DEFAULT_COUNTRY);

        // Get all the locationList where country does not contain UPDATED_COUNTRY
        defaultLocationShouldBeFound("country.doesNotContain=" + UPDATED_COUNTRY);
    }


    @Test
    @Transactional
    public void getAllLocationsByCountryCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where countryCode equals to DEFAULT_COUNTRY_CODE
        defaultLocationShouldBeFound("countryCode.equals=" + DEFAULT_COUNTRY_CODE);

        // Get all the locationList where countryCode equals to UPDATED_COUNTRY_CODE
        defaultLocationShouldNotBeFound("countryCode.equals=" + UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByCountryCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where countryCode not equals to DEFAULT_COUNTRY_CODE
        defaultLocationShouldNotBeFound("countryCode.notEquals=" + DEFAULT_COUNTRY_CODE);

        // Get all the locationList where countryCode not equals to UPDATED_COUNTRY_CODE
        defaultLocationShouldBeFound("countryCode.notEquals=" + UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByCountryCodeIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where countryCode in DEFAULT_COUNTRY_CODE or UPDATED_COUNTRY_CODE
        defaultLocationShouldBeFound("countryCode.in=" + DEFAULT_COUNTRY_CODE + "," + UPDATED_COUNTRY_CODE);

        // Get all the locationList where countryCode equals to UPDATED_COUNTRY_CODE
        defaultLocationShouldNotBeFound("countryCode.in=" + UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByCountryCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where countryCode is not null
        defaultLocationShouldBeFound("countryCode.specified=true");

        // Get all the locationList where countryCode is null
        defaultLocationShouldNotBeFound("countryCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByCountryCodeContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where countryCode contains DEFAULT_COUNTRY_CODE
        defaultLocationShouldBeFound("countryCode.contains=" + DEFAULT_COUNTRY_CODE);

        // Get all the locationList where countryCode contains UPDATED_COUNTRY_CODE
        defaultLocationShouldNotBeFound("countryCode.contains=" + UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByCountryCodeNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where countryCode does not contain DEFAULT_COUNTRY_CODE
        defaultLocationShouldNotBeFound("countryCode.doesNotContain=" + DEFAULT_COUNTRY_CODE);

        // Get all the locationList where countryCode does not contain UPDATED_COUNTRY_CODE
        defaultLocationShouldBeFound("countryCode.doesNotContain=" + UPDATED_COUNTRY_CODE);
    }


    @Test
    @Transactional
    public void getAllLocationsByRegionIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where region equals to DEFAULT_REGION
        defaultLocationShouldBeFound("region.equals=" + DEFAULT_REGION);

        // Get all the locationList where region equals to UPDATED_REGION
        defaultLocationShouldNotBeFound("region.equals=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllLocationsByRegionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where region not equals to DEFAULT_REGION
        defaultLocationShouldNotBeFound("region.notEquals=" + DEFAULT_REGION);

        // Get all the locationList where region not equals to UPDATED_REGION
        defaultLocationShouldBeFound("region.notEquals=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllLocationsByRegionIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where region in DEFAULT_REGION or UPDATED_REGION
        defaultLocationShouldBeFound("region.in=" + DEFAULT_REGION + "," + UPDATED_REGION);

        // Get all the locationList where region equals to UPDATED_REGION
        defaultLocationShouldNotBeFound("region.in=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllLocationsByRegionIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where region is not null
        defaultLocationShouldBeFound("region.specified=true");

        // Get all the locationList where region is null
        defaultLocationShouldNotBeFound("region.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByRegionContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where region contains DEFAULT_REGION
        defaultLocationShouldBeFound("region.contains=" + DEFAULT_REGION);

        // Get all the locationList where region contains UPDATED_REGION
        defaultLocationShouldNotBeFound("region.contains=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllLocationsByRegionNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where region does not contain DEFAULT_REGION
        defaultLocationShouldNotBeFound("region.doesNotContain=" + DEFAULT_REGION);

        // Get all the locationList where region does not contain UPDATED_REGION
        defaultLocationShouldBeFound("region.doesNotContain=" + UPDATED_REGION);
    }


    @Test
    @Transactional
    public void getAllLocationsByConStateIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where conState equals to DEFAULT_CON_STATE
        defaultLocationShouldBeFound("conState.equals=" + DEFAULT_CON_STATE);

        // Get all the locationList where conState equals to UPDATED_CON_STATE
        defaultLocationShouldNotBeFound("conState.equals=" + UPDATED_CON_STATE);
    }

    @Test
    @Transactional
    public void getAllLocationsByConStateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where conState not equals to DEFAULT_CON_STATE
        defaultLocationShouldNotBeFound("conState.notEquals=" + DEFAULT_CON_STATE);

        // Get all the locationList where conState not equals to UPDATED_CON_STATE
        defaultLocationShouldBeFound("conState.notEquals=" + UPDATED_CON_STATE);
    }

    @Test
    @Transactional
    public void getAllLocationsByConStateIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where conState in DEFAULT_CON_STATE or UPDATED_CON_STATE
        defaultLocationShouldBeFound("conState.in=" + DEFAULT_CON_STATE + "," + UPDATED_CON_STATE);

        // Get all the locationList where conState equals to UPDATED_CON_STATE
        defaultLocationShouldNotBeFound("conState.in=" + UPDATED_CON_STATE);
    }

    @Test
    @Transactional
    public void getAllLocationsByConStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where conState is not null
        defaultLocationShouldBeFound("conState.specified=true");

        // Get all the locationList where conState is null
        defaultLocationShouldNotBeFound("conState.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByConStateContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where conState contains DEFAULT_CON_STATE
        defaultLocationShouldBeFound("conState.contains=" + DEFAULT_CON_STATE);

        // Get all the locationList where conState contains UPDATED_CON_STATE
        defaultLocationShouldNotBeFound("conState.contains=" + UPDATED_CON_STATE);
    }

    @Test
    @Transactional
    public void getAllLocationsByConStateNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where conState does not contain DEFAULT_CON_STATE
        defaultLocationShouldNotBeFound("conState.doesNotContain=" + DEFAULT_CON_STATE);

        // Get all the locationList where conState does not contain UPDATED_CON_STATE
        defaultLocationShouldBeFound("conState.doesNotContain=" + UPDATED_CON_STATE);
    }


    @Test
    @Transactional
    public void getAllLocationsByStateCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where stateCode equals to DEFAULT_STATE_CODE
        defaultLocationShouldBeFound("stateCode.equals=" + DEFAULT_STATE_CODE);

        // Get all the locationList where stateCode equals to UPDATED_STATE_CODE
        defaultLocationShouldNotBeFound("stateCode.equals=" + UPDATED_STATE_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByStateCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where stateCode not equals to DEFAULT_STATE_CODE
        defaultLocationShouldNotBeFound("stateCode.notEquals=" + DEFAULT_STATE_CODE);

        // Get all the locationList where stateCode not equals to UPDATED_STATE_CODE
        defaultLocationShouldBeFound("stateCode.notEquals=" + UPDATED_STATE_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByStateCodeIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where stateCode in DEFAULT_STATE_CODE or UPDATED_STATE_CODE
        defaultLocationShouldBeFound("stateCode.in=" + DEFAULT_STATE_CODE + "," + UPDATED_STATE_CODE);

        // Get all the locationList where stateCode equals to UPDATED_STATE_CODE
        defaultLocationShouldNotBeFound("stateCode.in=" + UPDATED_STATE_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByStateCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where stateCode is not null
        defaultLocationShouldBeFound("stateCode.specified=true");

        // Get all the locationList where stateCode is null
        defaultLocationShouldNotBeFound("stateCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByStateCodeContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where stateCode contains DEFAULT_STATE_CODE
        defaultLocationShouldBeFound("stateCode.contains=" + DEFAULT_STATE_CODE);

        // Get all the locationList where stateCode contains UPDATED_STATE_CODE
        defaultLocationShouldNotBeFound("stateCode.contains=" + UPDATED_STATE_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByStateCodeNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where stateCode does not contain DEFAULT_STATE_CODE
        defaultLocationShouldNotBeFound("stateCode.doesNotContain=" + DEFAULT_STATE_CODE);

        // Get all the locationList where stateCode does not contain UPDATED_STATE_CODE
        defaultLocationShouldBeFound("stateCode.doesNotContain=" + UPDATED_STATE_CODE);
    }


    @Test
    @Transactional
    public void getAllLocationsByDistrictIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where district equals to DEFAULT_DISTRICT
        defaultLocationShouldBeFound("district.equals=" + DEFAULT_DISTRICT);

        // Get all the locationList where district equals to UPDATED_DISTRICT
        defaultLocationShouldNotBeFound("district.equals=" + UPDATED_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllLocationsByDistrictIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where district not equals to DEFAULT_DISTRICT
        defaultLocationShouldNotBeFound("district.notEquals=" + DEFAULT_DISTRICT);

        // Get all the locationList where district not equals to UPDATED_DISTRICT
        defaultLocationShouldBeFound("district.notEquals=" + UPDATED_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllLocationsByDistrictIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where district in DEFAULT_DISTRICT or UPDATED_DISTRICT
        defaultLocationShouldBeFound("district.in=" + DEFAULT_DISTRICT + "," + UPDATED_DISTRICT);

        // Get all the locationList where district equals to UPDATED_DISTRICT
        defaultLocationShouldNotBeFound("district.in=" + UPDATED_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllLocationsByDistrictIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where district is not null
        defaultLocationShouldBeFound("district.specified=true");

        // Get all the locationList where district is null
        defaultLocationShouldNotBeFound("district.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByDistrictContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where district contains DEFAULT_DISTRICT
        defaultLocationShouldBeFound("district.contains=" + DEFAULT_DISTRICT);

        // Get all the locationList where district contains UPDATED_DISTRICT
        defaultLocationShouldNotBeFound("district.contains=" + UPDATED_DISTRICT);
    }

    @Test
    @Transactional
    public void getAllLocationsByDistrictNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where district does not contain DEFAULT_DISTRICT
        defaultLocationShouldNotBeFound("district.doesNotContain=" + DEFAULT_DISTRICT);

        // Get all the locationList where district does not contain UPDATED_DISTRICT
        defaultLocationShouldBeFound("district.doesNotContain=" + UPDATED_DISTRICT);
    }


    @Test
    @Transactional
    public void getAllLocationsByCityIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where city equals to DEFAULT_CITY
        defaultLocationShouldBeFound("city.equals=" + DEFAULT_CITY);

        // Get all the locationList where city equals to UPDATED_CITY
        defaultLocationShouldNotBeFound("city.equals=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllLocationsByCityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where city not equals to DEFAULT_CITY
        defaultLocationShouldNotBeFound("city.notEquals=" + DEFAULT_CITY);

        // Get all the locationList where city not equals to UPDATED_CITY
        defaultLocationShouldBeFound("city.notEquals=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllLocationsByCityIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where city in DEFAULT_CITY or UPDATED_CITY
        defaultLocationShouldBeFound("city.in=" + DEFAULT_CITY + "," + UPDATED_CITY);

        // Get all the locationList where city equals to UPDATED_CITY
        defaultLocationShouldNotBeFound("city.in=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllLocationsByCityIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where city is not null
        defaultLocationShouldBeFound("city.specified=true");

        // Get all the locationList where city is null
        defaultLocationShouldNotBeFound("city.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByCityContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where city contains DEFAULT_CITY
        defaultLocationShouldBeFound("city.contains=" + DEFAULT_CITY);

        // Get all the locationList where city contains UPDATED_CITY
        defaultLocationShouldNotBeFound("city.contains=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllLocationsByCityNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where city does not contain DEFAULT_CITY
        defaultLocationShouldNotBeFound("city.doesNotContain=" + DEFAULT_CITY);

        // Get all the locationList where city does not contain UPDATED_CITY
        defaultLocationShouldBeFound("city.doesNotContain=" + UPDATED_CITY);
    }


    @Test
    @Transactional
    public void getAllLocationsByAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where area equals to DEFAULT_AREA
        defaultLocationShouldBeFound("area.equals=" + DEFAULT_AREA);

        // Get all the locationList where area equals to UPDATED_AREA
        defaultLocationShouldNotBeFound("area.equals=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllLocationsByAreaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where area not equals to DEFAULT_AREA
        defaultLocationShouldNotBeFound("area.notEquals=" + DEFAULT_AREA);

        // Get all the locationList where area not equals to UPDATED_AREA
        defaultLocationShouldBeFound("area.notEquals=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllLocationsByAreaIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where area in DEFAULT_AREA or UPDATED_AREA
        defaultLocationShouldBeFound("area.in=" + DEFAULT_AREA + "," + UPDATED_AREA);

        // Get all the locationList where area equals to UPDATED_AREA
        defaultLocationShouldNotBeFound("area.in=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllLocationsByAreaIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where area is not null
        defaultLocationShouldBeFound("area.specified=true");

        // Get all the locationList where area is null
        defaultLocationShouldNotBeFound("area.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByAreaContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where area contains DEFAULT_AREA
        defaultLocationShouldBeFound("area.contains=" + DEFAULT_AREA);

        // Get all the locationList where area contains UPDATED_AREA
        defaultLocationShouldNotBeFound("area.contains=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllLocationsByAreaNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where area does not contain DEFAULT_AREA
        defaultLocationShouldNotBeFound("area.doesNotContain=" + DEFAULT_AREA);

        // Get all the locationList where area does not contain UPDATED_AREA
        defaultLocationShouldBeFound("area.doesNotContain=" + UPDATED_AREA);
    }


    @Test
    @Transactional
    public void getAllLocationsByPincodeIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where pincode equals to DEFAULT_PINCODE
        defaultLocationShouldBeFound("pincode.equals=" + DEFAULT_PINCODE);

        // Get all the locationList where pincode equals to UPDATED_PINCODE
        defaultLocationShouldNotBeFound("pincode.equals=" + UPDATED_PINCODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByPincodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where pincode not equals to DEFAULT_PINCODE
        defaultLocationShouldNotBeFound("pincode.notEquals=" + DEFAULT_PINCODE);

        // Get all the locationList where pincode not equals to UPDATED_PINCODE
        defaultLocationShouldBeFound("pincode.notEquals=" + UPDATED_PINCODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByPincodeIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where pincode in DEFAULT_PINCODE or UPDATED_PINCODE
        defaultLocationShouldBeFound("pincode.in=" + DEFAULT_PINCODE + "," + UPDATED_PINCODE);

        // Get all the locationList where pincode equals to UPDATED_PINCODE
        defaultLocationShouldNotBeFound("pincode.in=" + UPDATED_PINCODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByPincodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where pincode is not null
        defaultLocationShouldBeFound("pincode.specified=true");

        // Get all the locationList where pincode is null
        defaultLocationShouldNotBeFound("pincode.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByPincodeContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where pincode contains DEFAULT_PINCODE
        defaultLocationShouldBeFound("pincode.contains=" + DEFAULT_PINCODE);

        // Get all the locationList where pincode contains UPDATED_PINCODE
        defaultLocationShouldNotBeFound("pincode.contains=" + UPDATED_PINCODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByPincodeNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where pincode does not contain DEFAULT_PINCODE
        defaultLocationShouldNotBeFound("pincode.doesNotContain=" + DEFAULT_PINCODE);

        // Get all the locationList where pincode does not contain UPDATED_PINCODE
        defaultLocationShouldBeFound("pincode.doesNotContain=" + UPDATED_PINCODE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLocationShouldBeFound(String filter) throws Exception {
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(location.getId().toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].conState").value(hasItem(DEFAULT_CON_STATE)))
            .andExpect(jsonPath("$.[*].stateCode").value(hasItem(DEFAULT_STATE_CODE)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE)));

        // Check, that the count call also returns 1
        restLocationMockMvc.perform(get("/api/locations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLocationShouldNotBeFound(String filter) throws Exception {
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLocationMockMvc.perform(get("/api/locations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingLocation() throws Exception {
        // Get the location
        restLocationMockMvc.perform(get("/api/locations/{id}", UUID.randomUUID()))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocation() throws Exception {
        // Initialize the database
        locationService.save(location);

        int databaseSizeBeforeUpdate = locationRepository.findAll().size();

        // Update the location
        Location updatedLocation = locationRepository.findById(location.getId()).get();
        // Disconnect from session so that the updates on updatedLocation are not directly saved in db
        em.detach(updatedLocation);
        updatedLocation
            .country(UPDATED_COUNTRY)
            .countryCode(UPDATED_COUNTRY_CODE)
            .region(UPDATED_REGION)
            .conState(UPDATED_CON_STATE)
            .stateCode(UPDATED_STATE_CODE)
            .district(UPDATED_DISTRICT)
            .city(UPDATED_CITY)
            .area(UPDATED_AREA)
            .pincode(UPDATED_PINCODE);

        restLocationMockMvc.perform(put("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLocation)))
            .andExpect(status().isOk());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testLocation.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testLocation.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testLocation.getConState()).isEqualTo(UPDATED_CON_STATE);
        assertThat(testLocation.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
        assertThat(testLocation.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testLocation.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testLocation.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testLocation.getPincode()).isEqualTo(UPDATED_PINCODE);
    }

    @Test
    @Transactional
    public void updateNonExistingLocation() throws Exception {
        int databaseSizeBeforeUpdate = locationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocationMockMvc.perform(put("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(location)))
            .andExpect(status().isBadRequest());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLocation() throws Exception {
        // Initialize the database
        locationService.save(location);

        int databaseSizeBeforeDelete = locationRepository.findAll().size();

        // Delete the location
        restLocationMockMvc.perform(delete("/api/locations/{id}", location.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
