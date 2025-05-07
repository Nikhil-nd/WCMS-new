package org.nwf.wcms.Service;

import org.nwf.wcms.Entity.UserRanger;
import org.nwf.wcms.Repository.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public class RegistrationLogin {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtToken jwtService;
	
	@Autowired
	private MyUserRepo repo;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

	public UserRanger register(UserRanger user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		return repo.saveAndFlush(user);
	}

	public String login(UserRanger user) {
	    try {
	        Authentication authenticator = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
	        );

	        return jwtService.generateJwtToken(user.getUsername()); // Will only reach here if authentication passes
	    } catch (AuthenticationException e) {
	        return "Fail"; // Wrong credentials, user not found, etc.
	    }
	}

}
