package com.example.demo;

import com.example.demo.configuration.ApplicationConfig;
import com.example.demo.dao.PersonDAO;
import com.example.demo.entity.Person;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

public class UserDetailsServiceTest {

    @Test
    public void test_findByEmail() {
        // Arrange

        PersonDAO mockRepository =  Mockito.mock(PersonDAO.class) ;

        String email = "trao@kekw.hehe";

        Person expectedPerson = new Person(email,"password") ;




    }

    @Test
    public void testLoadUserByUsername_WhenUserDoesNotExist_ExpectUsernameNotFoundException() {
        // Arrange
        String email = "nonexistent@example.com";
        PersonDAO repository = Mockito.mock(PersonDAO.class);
        Mockito.when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setRepository(repository);
        UserDetailsService userDetailsService = applicationConfig.UserDetailsService();

        // Act
        Exception exception = null;
        try {
            userDetailsService.loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            exception = e;
        }

        // Assert
        assertNotNull(exception);
        assertEquals("User not found", exception.getMessage());
    }
}
