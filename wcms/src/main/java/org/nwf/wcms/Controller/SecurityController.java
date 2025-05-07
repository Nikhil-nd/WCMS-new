package org.nwf.wcms.Controller;

import org.nwf.wcms.Entity.UserRanger;
import org.nwf.wcms.Service.RegistrationLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class SecurityController {

	@Autowired
	private RegistrationLogin service;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserRanger user) {
		
		
		service.register(user);
		return ResponseEntity.ok("register successfully");
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserRanger user){
		
		return ResponseEntity.ok(service.login(user));
		
	}
}