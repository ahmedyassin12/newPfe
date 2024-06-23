package com.example.demo.auth;

import com.example.demo.service.PersonService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
@Builder
public class AuthenticationController {

    private final AuthenticationService service;



    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register ( @RequestBody RegisterRequest request ){

       //

return ResponseEntity.ok(service.register(request)) ;


    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate
            ( @RequestBody AuthenticationRequest request ) throws BadRequestException {

        //
        return ResponseEntity.ok(service.authenticate(request)) ;


    }





}
