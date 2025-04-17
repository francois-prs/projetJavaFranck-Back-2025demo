package edu.fprs.jfb_2025_demo.model;


import com.fasterxml.jackson.annotation.JsonView;
import edu.fprs.jfb_2025_demo.view.AffichageCommande;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter //grace à Lambok qui va générer les getters et setters
@Setter
@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY = auto incrementation de mySQL
    protected Integer id; //Integer etant un objet il peut avoir une valeur nulle

    @Column(nullable = false)
    @NotBlank
    @JsonView(AffichageCommande.class)
    protected String nom;

    @Column(length = 10, nullable = false, unique = true)
    @Length(max = 10, min = 3)
    @NotBlank
    protected String code;

    @Column(columnDefinition = "TEXT")
    protected String description;

    @DecimalMin(value = "0.1")
    protected float prix;//float sur 4 octets, Double sur 8 octets

    @ManyToOne
    @JoinColumn(nullable = false)
    protected Etat etat;

    @ManyToMany
    @JoinTable(
            name = "etiquette_produit",
            joinColumns = @JoinColumn(name = "produit_id"),//pour renommer la colonne
            inverseJoinColumns = @JoinColumn(name = "etiquette_id") //et renommer l'autre colonne

    )
    protected List<Etiquette> etiquettes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    Utilisateur createur;
}
