package edu.fprs.jfb_2025_demo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter //grace à Lambok qui va générer les getters et setters
@Setter
@Entity
public class Etat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY = auto incrementation de mySQL
    protected Integer id; //Integer etant un objet il peut avoir une valeur nulle

    @Column(nullable = false)
    protected String nom;


}
