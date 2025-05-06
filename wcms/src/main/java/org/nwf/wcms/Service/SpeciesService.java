package org.nwf.wcms.Service;

import java.util.List;
import java.util.Optional;

import org.nwf.wcms.Entity.Species;
import org.nwf.wcms.Repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeciesService {
@Autowired
private SpeciesRepository repo;
public List<Species> getAllSpecies() {
    return repo.findAll();
}

public Optional<Species> getSpeciesById(int id) {
    return repo.findById(id);
}

public Species saveSpecies(Species species) {
    return repo.save(species);
}

public void deleteSpecies(int id) {
    repo.deleteById(id);
}
}
