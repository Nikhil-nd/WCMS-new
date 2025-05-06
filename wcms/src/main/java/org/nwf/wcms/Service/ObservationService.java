package org.nwf.wcms.Service;

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
}
