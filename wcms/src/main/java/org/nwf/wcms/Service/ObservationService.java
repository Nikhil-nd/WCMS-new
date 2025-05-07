package org.nwf.wcms.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import org.nwf.wcms.Entity.Observation;
import org.nwf.wcms.Repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservationService {
@Autowired
private ObservationRepository repo;
public void createObservation(Observation observation) {
    repo.save(observation);
}

public Observation readObservation(int id) {
    return repo.findById(id).orElse(null);
}

public void updateObservation(int id, Observation updatedObservation) {
    if (repo.existsById(id)) {
        updatedObservation.setId(id);
        repo.save(updatedObservation);
    }
}

public void deleteObservation(int  id) {
    repo.deleteById(id);
}

public Map<String, Object> generateObservationSummary(LocalDateTime fromDate, LocalDateTime toDate, boolean onlyEndangered) {
    List<Observation> observations = repo.findAll();

    // Filter observations based on date range and endangered flag
    List<Observation> filteredObservations = observations.stream()
            .filter(o -> o.getTime().isAfter(fromDate) && o.getTime().isBefore(toDate))
            .filter(o -> !onlyEndangered || (o.getSpecies() != null && o.getSpecies().isEndangered()))
            .collect(Collectors.toList());

    // Count observations per species
    Map<String, Long> observationsPerSpecies = filteredObservations.stream()
            .filter(o -> Objects.nonNull(o.getSpecies()))
            .collect(Collectors.groupingBy(o -> o.getSpecies().getSpeciesName(), Collectors.counting()));

    // Check for endangered species sightings
    List<String> endangeredSpeciesSightings = filteredObservations.stream()
            .filter(o -> Objects.nonNull(o.getSpecies()) && o.getSpecies().isEndangered())
            .map(o -> o.getSpecies().getSpeciesName())
            .distinct()
            .collect(Collectors.toList());

    // Prepare detailed observation data
    List<Map<String, Object>> detailedObservations = filteredObservations.stream()
            .map(o -> {
                Map<String, Object> details = new HashMap<>();
                details.put("id", o.getId());
                details.put("type", o.getType());
                details.put("location", o.getLocation());
                details.put("time", o.getTime());
                details.put("species", o.getSpecies() != null ? o.getSpecies().getSpeciesName() : "Unknown");
                details.put("ranger", o.getRanger() != null ? o.getRanger().getName() : "Unknown");
                return details;
            })
            .collect(Collectors.toList());

    // Prepare the summary report
    Map<String, Object> summary = new HashMap<>();
    summary.put("observationsPerSpecies", observationsPerSpecies);
    summary.put("endangeredSpeciesSightings", endangeredSpeciesSightings);
    summary.put("detailedObservations", detailedObservations);

    return summary;
}
}
