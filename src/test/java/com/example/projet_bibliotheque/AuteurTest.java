package com.example.projet_bibliotheque;

import com.example.projet_bibliotheque.Model.Auteur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuteurTest {

    @Test
    void getNom() {

        Auteur auteur = new Auteur("Aoudia","Redha");

        assertEquals("Aoudia",auteur.getNom());
    }

    @Test
    void setNom() {
        Auteur auteur = new Auteur("Aoudia","Redha");

        assertEquals("Aoudia",auteur.getNom());

        auteur.setNom("Madery");

        assertEquals("Madery",auteur.getNom());

    }

    @Test
    void getPrenom() {

        Auteur auteur = new Auteur("Aoudia","Redha");

        assertEquals("Redha",auteur.getPrenom());
    }

    @Test
    void setPrenom() {
        Auteur auteur = new Auteur("Aoudia","Redha");

        assertEquals("Redha",auteur.getPrenom());

        auteur.setPrenom("Romain");

        assertEquals("Romain",auteur.getPrenom());
    }
}