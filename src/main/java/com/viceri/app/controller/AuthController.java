package com.viceri.app.controller;

import com.viceri.app.dto.AuthenticationRequest;
import com.viceri.app.dto.AuthenticationResponse;
import com.viceri.app.service.AuthenticateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticateService authenticationService;

    public AuthController(AuthenticateService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping()
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }
}