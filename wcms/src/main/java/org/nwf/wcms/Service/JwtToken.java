package org.nwf.wcms.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Service
public class JwtToken {
	
	 String secret = "";
	 public JwtToken() {
		// TODO Auto-generated constructor stub
		 try {
			KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacSHA256");
			
		SecretKey sKey=	keyGenerator.generateKey();
		secret=Base64.getEncoder().encodeToString(sKey.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	
	 public String generateJwtToken(String username) {
	        Map<String, Object> claims = new HashMap<>();
	      

	        return Jwts.builder()   
	                .claims(claims)  
	                .subject(username) 
	                .issuedAt(new Date())
	                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 
	                .signWith(getKey()) // new API for algo
	                .compact();
	    }

	 private SecretKey getKey() {
	        // Use a strong Base64-encoded secret key (32 bytes for HS256)
	       
	        byte[] keyBytes = Decoders.BASE64.decode(secret);
	        return Keys.hmacShaKeyFor(keyBytes);
	    }



	 public String extractUserName(String token) {
	        // extract the username from jwt token
	        return extractClaim(token, Claims::getSubject);
	    }

	    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimResolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parser()
	                .verifyWith(getKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }

	    public boolean validateToken(String token, UserDetails userDetails) {
	        final String userName = extractUserName(token);
	        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	    private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }
	    
	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

}