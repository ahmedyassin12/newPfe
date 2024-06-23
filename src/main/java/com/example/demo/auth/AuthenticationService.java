package com.example.demo.auth;

import com.example.demo.configuration.JwtService;
import com.example.demo.dao.PersonDAO;
import com.example.demo.dao.TokenDAO;
import com.example.demo.entity.Formateur;
import com.example.demo.entity.Manager;
import com.example.demo.entity.Student;
import com.example.demo.entity.Person;
import com.example.demo.token.Token;
import com.example.demo.token.TokenType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final PersonDAO repository ;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenDAO tokenDAO ;
    public AuthenticationResponse register(RegisterRequest request) {
        Person user;
        if (request.getRole().name().equals("STUDENT")) {
            user = Student.builder()
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phoneNumber(request.getPhoneNumber())
                    .dateNaissance(request.getDateNaissance())
                    .age(request.age)
                    .role(request.getRole())
                    .build();
            } else if (request.getRole().name().equals("FORMATEUR")) {
            user = Formateur.builder()
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .phoneNumber(request.getPhoneNumber())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .dateNaissance(request.getDateNaissance())
                    .availability(request.getAvailability())
                    .Skills(request.getSkills())
                    .role(request.getRole())

                    .build();
        } else if (request.getRole().name().equals("MANAGER")) {

            user = Manager.builder()
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phoneNumber(request.getPhoneNumber())
                    .dateNaissance(request.getDateNaissance())
                    .role(request.getRole())
                    .build();
        } else {
            throw new IllegalArgumentException("Invalid user type");
        }
       var savedUser= repository.save(user) ;

var jwtToken=jwtService.generateToken(user) ;


        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()

        .token(jwtToken)
        .build();

    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) throws BadRequestException {

        System.out.println("username = "+request.getUsername() +" pass = "+request.getPassword());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );


        } catch (BadCredentialsException e) {
            System.out.println("username = "+request.getUsername() +" pass = "+request.getPassword());

                throw new BadRequestException("Invalid username or password");
        }


        var user = repository.findByUsername(request.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username : " + request.getUsername())
        );

        var jwtToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);

        saveUserToken(user,jwtToken);
        
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    private void saveUserToken(Person user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build() ;
        tokenDAO.save(token) ;
    }

    private void revokeAllUserTokens(Person user){


        var validUserTokens = tokenDAO.findAllValidTokenByUser(user.getId()) ;
        if(validUserTokens.isEmpty()) return;

        validUserTokens.forEach(t->{

            t.setExpired(true);

            t.setRevoked(true);


        });

        tokenDAO.saveAll(validUserTokens);

    }

}