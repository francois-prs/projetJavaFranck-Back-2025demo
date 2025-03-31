package edu.fprs.jfb_2025_demo.dao;

import edu.fprs.jfb_2025_demo.model.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneCommandeDao extends JpaRepository<LigneCommande, Integer> {

}
