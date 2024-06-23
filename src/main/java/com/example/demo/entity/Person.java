package com.example.demo.entity;

import com.example.demo.token.Token;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "User_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Person implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id ;


    @Column(name="email",nullable = false)
    private String email;

    @Column(name="username",nullable = false,unique = true)
    private String username ;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "dateNaissance")
    private Date dateNaissance;



    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user" ,cascade=CascadeType.ALL)
    private List<Token> token ;


  /*  @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }
*/
    @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {


      return role.getAuthorities() ;


  }


    public Person(String username, String password) {
        this.username=username;
        this.password=password;

    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

