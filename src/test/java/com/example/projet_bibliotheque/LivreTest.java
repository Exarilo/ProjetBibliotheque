package com.example.projet_bibliotheque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivreTest {

    @Test
    void testGetTitre() {
        Livre livre = new Livre();
        livre.setTitre("Titre du livre");
        assertEquals("Titre du livre", livre.getTitre());
    }

    @Test
    void testSetTitre() {
        Livre livre = new Livre();
        livre.setTitre("Titre du livre");
        assertEquals("Titre du livre", livre.getTitre());
    }

    @Test
    void testGetNomAuteur() {
        Livre livre = new Livre();
        livre.setNomAuteur("Nom de l'auteur");
        assertEquals("Nom de l'auteur", livre.getNomAuteur());
    }

    @Test
    void testSetNomAuteur() {
        Livre livre = new Livre();
        livre.setNomAuteur("Nom de l'auteur");
        assertEquals("Nom de l'auteur", livre.getNomAuteur());
    }

    @Test
    void testGetPrenomAuteur() {
        Livre livre = new Livre();
        livre.setPrenomAuteur("Prénom de l'auteur");
        assertEquals("Prénom de l'auteur", livre.getPrenomAuteur());
    }

    @Test
    void testSetPrenomAuteur() {
        Livre livre = new Livre();
        livre.setPrenomAuteur("Prénom de l'auteur");
        assertEquals("Prénom de l'auteur", livre.getPrenomAuteur());
    }

    @Test
    void testGetPresentation() {
        Livre livre = new Livre();
        livre.setPresentation("Présentation du livre");
        assertEquals("Présentation du livre", livre.getPresentation());
    }

    @Test
    void testSetPresentation() {
        Livre livre = new Livre();
        livre.setPresentation("Présentation du livre");
        assertEquals("Présentation du livre", livre.getPresentation());
    }

    @Test
    void testGetParution() {
        Livre livre = new Livre();
        livre.setParution(2023);
        assertEquals(2023, livre.getParution());
    }

    @Test
    void testSetParution() {
        Livre livre = new Livre();
        livre.setParution(2023);
        assertEquals(2023, livre.getParution());
    }

    @Test
    void testGetColonne() {
        Livre livre = new Livre();
        livre.setColonne(5);
        assertEquals(5, livre.getColonne());
    }

    @Test
    void testSetColonne() {
        Livre livre = new Livre();
        livre.setColonne(5);
        assertEquals(5, livre.getColonne());
    }

    @Test
    void testGetRangee() {
        Livre livre = new Livre();
        livre.setRangee(2);
        assertEquals(2, livre.getRangee());
    }

    @Test
    void testSetRangee() {
        Livre livre = new Livre();
        livre.setRangee(2);
        assertEquals(2, livre.getRangee());
    }

    @Test
    void testGetAuteur() {
        Livre livre = new Livre();
        livre.setNomAuteur("Nom de l'auteur");
        livre.setPrenomAuteur("Prénom de l'auteur");
        assertEquals("Nom de l'auteur Prénom de l'auteur", livre.getAuteur());
    }

    @Test
    void testSetAuteur() {
        Livre livre = new Livre();
        Auteur auteur = new Auteur("NomAuteur","PrenomAuteur");
        livre.setAuteur(auteur);
        assertEquals("NomAuteur", livre.getNomAuteur());
        assertEquals("PrenomAuteur", livre.getPrenomAuteur());
    }
}
