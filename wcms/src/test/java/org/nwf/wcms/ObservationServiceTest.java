package org.nwf.wcms;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.nwf.wcms.Entity.Observation;
import org.nwf.wcms.Entity.Species;
import org.nwf.wcms.Repository.ObservationRepository;
import org.nwf.wcms.Service.ObservationService;

class ObservationServiceTest {

    @Mock
    private ObservationRepository observationRepository;

    @InjectMocks
    private ObservationService observationService;

    @Test
    void testGenerateObservationSummary() {
        MockitoAnnotations.openMocks(this);

        // Mock data
        Species species1 = new Species(1, "Elephant", false, null);
        Species species2 = new Species(2, "Tiger", true, null);

        Observation obs1 = new Observation(1, "Sighting", "Forest", LocalDateTime.now().minusDays(1), species1, null);
        Observation obs2 = new Observation(2, "Sighting", "Jungle", LocalDateTime.now().minusDays(2), species2, null);
        Observation obs3 = new Observation(3, "Sighting", "Forest", LocalDateTime.now().minusDays(3), species2, null);

        List<Observation> mockObservations = Arrays.asList(obs1, obs2, obs3);

        // Mock repository behavior
        when(observationRepository.findAll()).thenReturn(mockObservations);

        // Call the method under test
        Map<String, Object> summary = observationService.generateObservationSummary(
                LocalDateTime.now().minusDays(5), LocalDateTime.now(), true);

        // Assertions
        assertNotNull(summary);
        assertTrue(summary.containsKey("observationsPerSpecies"));
        assertTrue(summary.containsKey("endangeredSpeciesSightings"));
        assertTrue(summary.containsKey("detailedObservations"));

        // Verify observations per species
        Map<String, Long> observationsPerSpecies = (Map<String, Long>) summary.get("observationsPerSpecies");
        assertEquals(1, observationsPerSpecies.size());
        assertEquals(2L, observationsPerSpecies.get("Tiger"));

        // Verify endangered species sightings
        List<String> endangeredSpeciesSightings = (List<String>) summary.get("endangeredSpeciesSightings");
        assertEquals(1, endangeredSpeciesSightings.size());
        assertTrue(endangeredSpeciesSightings.contains("Tiger"));

        // Verify detailed observations
        List<Map<String, Object>> detailedObservations = (List<Map<String, Object>>) summary.get("detailedObservations");
        assertEquals(2, detailedObservations.size());

        // Verify repository interaction
        verify(observationRepository, times(1)).findAll();
    }
}