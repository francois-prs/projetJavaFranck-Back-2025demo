package edu.fprs.jfb_2025_demo.dao;

import edu.fprs.jfb_2025_demo.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeDao extends JpaRepository<Commande, Integer> {

}
