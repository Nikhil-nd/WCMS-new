package org.nwf.wcms.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




import org.springframework.beans.factory.annotation.Autowired;


@Configuration
@EnableWebSecurity
public class ConfigSecurity {
	
	
	@Autowired
	private JwtFilter jwtFilter;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(authz -> authz
	            .requestMatchers("/register", "/login").permitAll()
	            .anyRequest().authenticated()
	        )
	        .addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
    
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
    	
    	provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    	provider.setUserDetailsService(userDetailsService);
    	
    	return provider;
    	
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    	return configuration.getAuthenticationManager();
    	
    	
    }
}
