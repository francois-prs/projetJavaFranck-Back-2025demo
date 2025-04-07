package edu.fprs.jfb_2025_demo.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Utilisateur {

//    public interface OnUpdate {
//    }
//
//    public interface OnCreate {
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotBlank //niveau Spring
    @Email//(groups = {OnUpdate.class, OnCreate.class})
    @Column(unique = true, nullable = false) //niveau base de donnée
    protected String email;

    @Column(updatable = false)
    @NotBlank//(groups = {OnCreate.class})
    protected String password;

    protected boolean admin;

}
