package org.nwf.wcms.Repository;

import org.nwf.wcms.Entity.UserRanger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MyUserRepo extends JpaRepository<UserRanger, Integer> {

	public UserRanger findByUserName(String username);

}