package edu.fprs.jfb_2025_demo.controller;

import edu.fprs.jfb_2025_demo.dao.ProduitDao;
import edu.fprs.jfb_2025_demo.model.Etat;
import edu.fprs.jfb_2025_demo.model.Produit;
import edu.fprs.jfb_2025_demo.security.IsRedacteur;
import edu.fprs.jfb_2025_demo.security.IsUtilisateur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
public class ProduitController {

//    @Autowired
//    protected ProduitDao produitDao;

    protected ProduitDao produitDao;

    @Autowired
    public ProduitController(ProduitDao produitDao) {
        this.produitDao = produitDao;
    }


    @GetMapping("/produit/{id}")
    public ResponseEntity<Produit> get(@PathVariable int id) {

        Optional<Produit> optionalProduit = produitDao.findById(id);

        if (optionalProduit.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalProduit.get(), HttpStatus.OK);

    }

    @GetMapping("/produits")
    @IsUtilisateur
    public List<Produit> getAll() {

        return produitDao.findAll();
    }

    @PostMapping("/produit")
    public ResponseEntity<Produit> save(@RequestBody Produit produit) {

        //si le produit recu n'a pas d'etat alors on indiquide qu'il neuf par defaut
        if (produit.getEtat() == null) {

            Etat etatNeuf = new Etat();
            etatNeuf.setId(1);
            produit.setEtat(etatNeuf);
        }

        produit.setId(null);
        produitDao.save(produit);
        return new ResponseEntity<>(produit, HttpStatus.CREATED);
    }

    @DeleteMapping("/produit/{id}")
    @IsRedacteur
    public ResponseEntity<Produit> delete(@PathVariable int id) {

        Optional<Produit> optionalProduit = produitDao.findById(id);

        if (optionalProduit.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        produitDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/produit/{id}")
    public ResponseEntity<Produit> update(
            @PathVariable int id,
            @RequestBody @Valid Produit produit) {

        Optional<Produit> optionalProduit = produitDao.findById(id);

        if (optionalProduit.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        produit.setId(id);

        produitDao.save(produit);

        return new ResponseEntity<>(produit, HttpStatus.NO_CONTENT);

    }
}

