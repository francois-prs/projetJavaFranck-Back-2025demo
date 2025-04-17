package edu.fprs.jfb_2025_demo.controller;

import edu.fprs.jfb_2025_demo.dao.ProduitDao;
import edu.fprs.jfb_2025_demo.model.Etat;
import edu.fprs.jfb_2025_demo.model.Produit;
import edu.fprs.jfb_2025_demo.security.AppUserDetails;
import edu.fprs.jfb_2025_demo.security.IsRedacteur;
import edu.fprs.jfb_2025_demo.security.IsUtilisateur;
import edu.fprs.jfb_2025_demo.security.SecuriteUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
public class ProduitController {

    protected ProduitDao produitDao;
    protected SecuriteUtils securiteUtils;

    @Autowired
    public ProduitController(ProduitDao produitDao, SecuriteUtils securiteUtils) {
        this.produitDao = produitDao;
        this.securiteUtils = securiteUtils;
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
    @IsUtilisateur
    public ResponseEntity<Produit> save(@RequestBody @Valid Produit produit,
                                        @AuthenticationPrincipal AppUserDetails userDetails) {

        produit.setCreateur(userDetails.getUtilisateur());

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
    @IsUtilisateur
    public ResponseEntity<Produit> delete(
            @PathVariable int id,
            @AuthenticationPrincipal AppUserDetails userDetails) {

        Optional<Produit> optionalProduit = produitDao.findById(id);

        if (optionalProduit.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //récupérer le role de la personne
        String role = securiteUtils.getRole(userDetails);

        //si l'id createur du produit est different de l'id de la personnne connectee
        if (!role.equals("ROLE_ADMINISTRATEUR") ||
                optionalProduit.get().getCreateur().getId() != userDetails.getUtilisateur().getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        produitDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/produit/{id}")
    @IsUtilisateur
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

