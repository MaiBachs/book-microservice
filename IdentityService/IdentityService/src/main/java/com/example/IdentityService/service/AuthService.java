package com.example.IdentityService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.IdentityService.entity.UserCredential;
import com.example.IdentityService.repository.UserCredentialRepository;

@Service
public class AuthService {
    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
   
    public ResponseEntity<?> saveUser(UserCredential credential) {
    	Optional<UserCredential> ac = repository.findByName(credential.getName().trim());
    	if(ac.isPresent()) {
    		return new ResponseEntity<>("user already exist", HttpStatus.BAD_REQUEST);
    	}
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return new ResponseEntity<>("user added to the system", HttpStatus.OK);
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}