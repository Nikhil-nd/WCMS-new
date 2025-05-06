package org.nwf.wcms.Repository;

import org.nwf.wcms.Entity.Ranger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RangerRepository extends JpaRepository<Ranger, Integer> {

}
