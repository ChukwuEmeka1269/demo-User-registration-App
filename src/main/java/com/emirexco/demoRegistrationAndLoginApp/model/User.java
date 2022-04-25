package com.emirexco.demoRegistrationAndLoginApp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    @Email(regexp = ".+@.+\\..+")
    private String email;

    @NotEmpty(message = "First name field must not be empty or null")
    private String firstname;

    private String lastname;

    @Column(nullable = false, length = 65)
    private String password;

}
