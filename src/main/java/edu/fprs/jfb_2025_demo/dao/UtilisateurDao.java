package edu.fprs.jfb_2025_demo.dao;

import edu.fprs.jfb_2025_demo.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByEmail(String email);

//    public Produit findByNom(String name);
}
