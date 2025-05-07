package org.nwf.wcms;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.nwf.wcms.Controller.ObservationController;
import org.nwf.wcms.Entity.Observation;
import org.nwf.wcms.Service.ObservationService;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObservationControllerTest {

    @Mock
    private ObservationService observationService;

    @InjectMocks
    private ObservationController observationController;

    @Test
    void testGetObservationSummary() {
        MockitoAnnotations.openMocks(this);

        // Mock data
        Map<String, Object> mockSummary = new HashMap<>();
        mockSummary.put("observationsPerSpecies", Map.of("Tiger", 2L));
        mockSummary.put("endangeredSpeciesSightings", List.of("Tiger"));
        mockSummary.put("detailedObservations", List.of(Map.of("id", 1, "type", "Sighting")));

        // Mock service behavior
        when(observationService.generateObservationSummary(
                any(LocalDateTime.class), any(LocalDateTime.class), anyBoolean()))
                .thenReturn(mockSummary);

        // Call the method under test
        ResponseEntity<Map<String, Object>> response = observationController.getObservationSummary(
                LocalDateTime.now().minusDays(5), LocalDateTime.now(), true);

        // Assertions
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("observationsPerSpecies"));
        assertTrue(response.getBody().containsKey("endangeredSpeciesSightings"));
        assertTrue(response.getBody().containsKey("detailedObservations"));

        // Verify service interaction
        verify(observationService, times(1)).generateObservationSummary(
                any(LocalDateTime.class), any(LocalDateTime.class), anyBoolean());
    }
}