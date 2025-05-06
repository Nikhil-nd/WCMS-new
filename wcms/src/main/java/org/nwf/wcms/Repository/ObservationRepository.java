package org.nwf.wcms.Repository;

import org.nwf.wcms.Entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Integer> {

}
