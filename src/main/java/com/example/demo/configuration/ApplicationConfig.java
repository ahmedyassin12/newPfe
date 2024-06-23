package com.example.demo.configuration;

import com.example.demo.dao.PersonDAO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Configuration
public class ApplicationConfig {
    @Autowired
    private PersonDAO repository ;
   @Bean
    public UserDetailsService UserDetailsService(){

        return username -> repository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException ("User not found") ) ;

    }
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

        @Bean
        public AuthenticationProvider authenticationProvider(){

            DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
            authProvider.setUserDetailsService( UserDetailsService() );
            authProvider.setPasswordEncoder(passwordEncoder());
            return authProvider ;

        }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {


        return new BCryptPasswordEncoder() ;
    }



}
