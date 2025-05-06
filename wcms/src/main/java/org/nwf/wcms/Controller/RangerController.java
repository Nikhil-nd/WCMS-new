package org.nwf.wcms.Controller;

import org.nwf.wcms.Entity.Ranger;
import org.nwf.wcms.Service.RangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rangers")
public class RangerController {

    @Autowired
    private RangerService rangerService;

    // Insert a new Ranger
    @PostMapping("/post")
    public ResponseEntity<Ranger> insertRanger(@RequestBody Ranger ranger) {
        Ranger createdRanger = rangerService.insertRanger(ranger);
        return ResponseEntity.ok(createdRanger);
    }

    // Delete a Ranger by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRanger(@PathVariable int id) {
        rangerService.deleteRanger(id);
        return ResponseEntity.noContent().build();
    }

    // Update an existing Ranger
    @PutMapping("/put")
    public ResponseEntity<Ranger> updateRanger(@RequestBody Ranger ranger) {
        try {
            rangerService.updateRanger(ranger);
            return ResponseEntity.ok(ranger);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?>getObservation(@PathVariable ("id") int id ){
        return ResponseEntity.ok(rangerService.observations(id));
    }
}