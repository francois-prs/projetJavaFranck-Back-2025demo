package edu.fprs.jfb_2025_demo.dao;

import edu.fprs.jfb_2025_demo.model.Etat;
import edu.fprs.jfb_2025_demo.model.Etiquette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtiquetteDao extends JpaRepository<Etiquette, Integer> {

}
