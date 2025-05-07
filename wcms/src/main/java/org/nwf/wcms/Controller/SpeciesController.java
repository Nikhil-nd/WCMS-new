package org.nwf.wcms.Controller;

import java.util.List;

import org.nwf.wcms.Entity.Species;
import org.nwf.wcms.Service.SpeciesService;
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
@RequestMapping("/api/species")
public class SpeciesController {

    @Autowired
    private SpeciesService speciesService;

    // Get all Species
    @GetMapping
    public List<Species> getAllSpecies() {
        return speciesService.getAllSpecies();
    }

    // Get Species by ID
    @GetMapping("/{id}")
    public ResponseEntity<Species> getSpeciesById(@PathVariable int id) {
        return speciesService.getSpeciesById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new Species
    @PostMapping
    public Species createSpecies(@RequestBody Species species) {
        return speciesService.saveSpecies(species);
    }

    // Update an existing Species
    @PutMapping("/{id}")
    public ResponseEntity<Species> updateSpecies(@PathVariable int id, @RequestBody Species species) {
        if (speciesService.getSpeciesById(id).isPresent()) {
            species.setId(id);
            return ResponseEntity.ok(speciesService.saveSpecies(species));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Species
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecies(@PathVariable int id) {
        speciesService.deleteSpecies(id);
        return ResponseEntity.noContent().build();
    }
}
