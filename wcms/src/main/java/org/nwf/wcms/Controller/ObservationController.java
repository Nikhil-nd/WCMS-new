package org.nwf.wcms.Controller;

import org.nwf.wcms.Entity.Observation;
import org.nwf.wcms.Service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {

    @Autowired
    private ObservationService observationService;

    // Create a new Observation
    @PostMapping("/post")
    public ResponseEntity<Observation> createObservation(@RequestBody Observation observation) {
        observationService.createObservation(observation);
        return ResponseEntity.ok(observation);
    }

    // Update an existing Observation
    @PutMapping("/update/{id}")
    public ResponseEntity<Observation> updateObservation(@PathVariable int id, @RequestBody Observation observation) {
        observationService.updateObservation(id, observation);
        return ResponseEntity.ok(observation);
    }

    // Delete an Observation
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteObservation(@PathVariable int id) {
        observationService.deleteObservation(id);
        return ResponseEntity.noContent().build();
    }
}
