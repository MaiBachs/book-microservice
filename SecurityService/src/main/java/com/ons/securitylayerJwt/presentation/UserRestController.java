package com.ons.securitylayerJwt.presentation;

import com.ons.securitylayerJwt.businessLogic.UserService;
import com.ons.securitylayerJwt.dto.LoginDto;
import com.ons.securitylayerJwt.dto.RegisterDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/sercuriy-service/user")
@RequiredArgsConstructor
public class UserRestController {

	@Autowired
    private UserService iUserService ;
	
    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto)
    { return  iUserService.register(registerDto);}

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) {
    loginDto = iUserService.authenticate(loginDto);
    return ResponseEntity.ok(loginDto); }

}
