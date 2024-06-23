package com.example.demo;

import com.example.demo.configuration.ApplicationConfig;
import com.example.demo.dao.PersonDAO;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationProvider;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class AuthenticationProviderTest {

    @Test
    public void testAuthenticationProviderCreation() {
        // Arrange
        ApplicationConfig applicationConfig = new ApplicationConfig();
        PersonDAO repository = mock(PersonDAO.class);
        applicationConfig.setRepository(repository);

        // Act
        AuthenticationProvider authenticationProvider = applicationConfig.authenticationProvider();

        // Assert
        assertNotNull(authenticationProvider);



    }

    @Test
    public void testAuthenticationProviderConfiguration() {
        // Arrange
        ApplicationConfig applicationConfig = new ApplicationConfig();
        PersonDAO repository = mock(PersonDAO.class);
        applicationConfig.setRepository(repository);

        // Act
        AuthenticationProvider authenticationProvider = applicationConfig.authenticationProvider();

    /*    // Ass
    ert
        assertNotNull(authenticationProvider.getUserDetailsService());
        assertNotNull(authenticationProvider.getPasswordEncoder());
    }


*/

}}


