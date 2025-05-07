package org.nwf.wcms.Service;



import org.nwf.wcms.Entity.UserPrinicipal;
import org.nwf.wcms.Entity.UserRanger;
import org.nwf.wcms.Repository.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class MyRangerDetails implements UserDetailsService {

	@Autowired
	private MyUserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
	UserRanger user=repo.findByUserName(username);
	if(user==null) {
		throw new UsernameNotFoundException("Ranger not found");
	}
	
		return new UserPrinicipal(user);
	}

}
