package org.nwf.wcms.Service;

import java.util.List;

import org.nwf.wcms.Entity.Observation;
import org.nwf.wcms.Entity.Ranger;
import org.nwf.wcms.Repository.RangerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RangerService {

    @Autowired
    private RangerRepository rangerRepository;

    public Ranger insertRanger(Ranger ranger){
        return rangerRepository.save(ranger);
    }
    public void deleteRanger(int id){
         rangerRepository.deleteById(id);
    }
    public void updateRanger(Ranger ranger) {
        if (rangerRepository.existsById(ranger.getId())) {
            rangerRepository.save(ranger);
        } else {
            throw new IllegalArgumentException("Ranger with ID " + ranger.getId() + " does not exist.");
        }
    }
    public List<Observation> observations(int id){
        return rangerRepository.findById(id)
                .map(Ranger::getObservations)
                .orElseThrow(() -> new IllegalArgumentException("Ranger with ID " + id + " does not exist."));
    }
    
    
}
