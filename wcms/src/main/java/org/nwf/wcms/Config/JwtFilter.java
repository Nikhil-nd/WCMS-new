package org.nwf.wcms.Config;

import java.io.IOException;

import org.nwf.wcms.Service.JwtToken;
import org.nwf.wcms.Service.MyRangerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtToken jwtToken;
    
    @Autowired
    ApplicationContext context;

   

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            userName = jwtToken.extractUserName(token);
        }
        
      if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
    	  
    	  UserDetails userDetails=context.getBean(MyRangerDetails.class).loadUserByUsername(userName);
    	  
    	  if(jwtToken.validateToken(token,userDetails)) {
    		  
    		  UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    		  
    		  authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    		  SecurityContextHolder.getContext().setAuthentication(authToken);
    	  }
      }
      
      filterChain.doFilter(request, response);
    
    }}
