package com.example.demo.service;



import com.example.demo.auth.AuthenticationService;
import com.example.demo.auth.RegisterRequest;
import com.example.demo.dao.ManagerDAO;
import com.example.demo.entity.Manager;
import com.example.demo.entity.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static com.example.demo.entity.Role.MANAGER;


@Service
@Slf4j
@RequiredArgsConstructor
public class ManagerService {

    @Autowired
    private final PasswordEncoder passwordEncoder ;
    @Autowired
    private ManagerDAO managerRepository;

    @Autowired
    private AuthenticationService authenticationService ;

    public Iterable<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public void initManager() throws ParseException {

        String dateString = "05/12/2002";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = dateFormat.parse(dateString);

        var manager = new RegisterRequest().builder()
                .nom("ssss")
                .email("helahoula@os.com")
                .password(passwordEncoder.encode("kekw"))
                .phoneNumber("50289999")
                .username("hola")
                .role(MANAGER)
                .dateNaissance(date)
                .build();

        System.out.println("Manager token: "+ authenticationService.register(manager).getToken() );



    }

    public Manager getManagerById(Long id) {
        return managerRepository.findById(id).orElseThrow(() -> new RuntimeException("Manager not found for id: " + id));
    }

    public Optional<Manager> getManagerByEmail(String email) {
        return managerRepository.findManagerByEmail(email);

    }
    public Optional<Manager> getManagerBynom(String nom) {
        return managerRepository.findManagerBynom(nom);

    }
    public Optional<Manager> getManagerByUsername(String username) {
        return managerRepository.findManagerByUsername(username);

    }

    public Manager createNewManager(Manager manager) {


        manager.setPassword(passwordEncoder.encode(manager.getPassword()));

        managerRepository.save(manager);
        log.info("manager {} is saved", manager.getId());

        return manager;



    }

    public void rem_manager(Long id) {
        managerRepository.deleteById(id);
    }

    public Manager update_manager(Manager manager) {
        return managerRepository.save(manager);
    }

}
