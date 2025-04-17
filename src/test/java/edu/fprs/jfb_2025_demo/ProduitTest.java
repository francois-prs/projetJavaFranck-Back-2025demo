package edu.fprs.jfb_2025_demo;

import edu.fprs.jfb_2025_demo.model.Produit;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProduitTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void createValidProduit_shouldNotThrowException() {

        Produit produitTest = new Produit();
        produitTest.setPrix(10);
        produitTest.setNom("Test");
        produitTest.setCode("1234");

        Set<ConstraintViolation<Produit>> violations = validator.validate(produitTest);

        //est-ce que c'est vide?
        Assertions.assertTrue(violations.isEmpty());
    }


    @Test
    void createProduitWithInvalidName_shouldNotBeValid() {
        Produit produitTest = new Produit();
        produitTest.setPrix(10);

        Set<ConstraintViolation<Produit>> violations = validator.validate(produitTest);

        assertEquals(1, violations.size());
    }

//    @Test
//    void createProduitWithoutName_shouldNotBeValid() {
//
//        Produit produitTest = new Produit();
//        produitTest.setPrix(10);
//        produitTest.setNom("");
//        produitTest.setCode("1234");
//
//        Set<ConstraintViolation<Produit>> violations = validator.validate(produitTest);
//
//        Assertions.assertEquals(1, violations.size());
//    }

    @Test
    void createProduitWithoutName_shouldNotBeValid() {

        Produit produitTest = new Produit();
//        produitTest.setPrix(10);
        Set<ConstraintViolation<Object>> violations = validator.validate(produitTest);

        boolean notBlankViolationExist = constraintExist(
                violations, "nom", "jakarta.validation.constraints.NotBlank");

        Assertions.assertTrue(notBlankViolationExist);

    }

    @Test
    void createProduitWithNegativePrice_shouldNotBeValid() {
        Produit produitTest = new Produit();
        produitTest.setNom("Test");
        produitTest.setPrix(-10);

        boolean decimalMinViolationExist = constraintExist(
                violations, "prix", "jakarta.validation.constraints");

        Assertions.assertTrue(decimalMinViolationExist);

    }

    private boolean constraintExist(Set<ConstraintViolation<Object>> violations, String fieldName, String constraintName) {
        return violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals(fieldName))
                .map(v -> v.getConstraintDescriptor().getAnnotation().annotationType().getName())
                .anyMatch(s -> s.equals("jakarta.validation.constraints." + constraintName));
    }
}
