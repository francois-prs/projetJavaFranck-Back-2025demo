package edu.fprs.jfb_2025_demo;

import edu.fprs.jfb_2025_demo.controller.ProduitController;
import edu.fprs.jfb_2025_demo.mock.MockProduitDao;
import edu.fprs.jfb_2025_demo.mock.MockSecuriteUtils;
import edu.fprs.jfb_2025_demo.model.Produit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProduitControllerTest {

    @Test
    void callGetWithExistingProduct_shouldSend200ok() {

        ProduitController produitController = new ProduitController(
                new MockProduitDao(), null
        );

        ResponseEntity<Produit> response = produitController.get(1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void callGetWithNoExistingProduct_shouldSend200ok() {

        ProduitController produitController = new ProduitController(
                new MockProduitDao(), new MockSecuriteUtils()
        );

        ResponseEntity<Produit> reponse = produitController.get(2);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, reponse.getStatusCode());
    }
}