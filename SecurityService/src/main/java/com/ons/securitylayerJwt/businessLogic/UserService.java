package com.ons.securitylayerJwt.businessLogic;

import com.ons.securitylayerJwt.dto.BearerToken;
import com.ons.securitylayerJwt.dto.LoginDto;
import com.ons.securitylayerJwt.dto.RegisterDto;
import com.ons.securitylayerJwt.models.Role;
import com.ons.securitylayerJwt.models.RoleName;
import com.ons.securitylayerJwt.models.User;
import com.ons.securitylayerJwt.persistence.IRoleRepository;
import com.ons.securitylayerJwt.persistence.IUserRepository;
import com.ons.securitylayerJwt.security.JwtUtilities;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService{
	
	@Autowired
    private AuthenticationManager authenticationManager ;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IRoleRepository iRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Autowired
    private JwtUtilities jwtUtilities ;

    public Role saveRole(Role role) {
        return iRoleRepository.save(role);
    }

    public User saverUser(User user) {
        return iUserRepository.save(user);
    }
    
    @Transactional
    public ResponseEntity<?> register(RegisterDto registerDto) {
        if(iUserRepository.existsByEmail(registerDto.getEmail()))
        { return  new ResponseEntity<>("email is already taken !", HttpStatus.BAD_REQUEST); }
        else
        { User user = new User();
            user.setEmail(registerDto.getEmail());
            user.setUserName(registerDto.getUserName());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            //By Default , he/she is a simple user
            Role role = iRoleRepository.findByRoleName(RoleName.USER);
            user.setRoles(Collections.singletonList(role));
            iUserRepository.save(user);
            String token = jwtUtilities.generateToken(registerDto.getEmail(),Collections.singletonList(role.getRoleName()));
            return new ResponseEntity<>(new BearerToken(token , "Bearer "),HttpStatus.OK);

        }
    }

    public LoginDto authenticate(LoginDto loginDto) {
    	UsernamePasswordAuthenticationToken acc = new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
        );
    	
        Authentication authentication= authenticationManager.authenticate(acc);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = iUserRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r-> rolesNames.add(r.getRoleName()));
        String token = jwtUtilities.generateToken(user.getUsername(),rolesNames);
        loginDto.setToken(token);
        loginDto.setUserId(user.getId());
        loginDto.setUserName(user.getUserName());
        return loginDto;
    }

}

