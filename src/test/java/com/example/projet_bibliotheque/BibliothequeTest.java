package com.example.projet_bibliotheque;
import com.example.projet_bibliotheque.Model.Bibliotheque;
import com.example.projet_bibliotheque.Model.Livre;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class BibliothequeTest {
    @Test
    void loadXML() throws IOException, XMLStreamException{
        // Préparation des données de test
        String filepath = "src/main/resources/com/example/projet_bibliotheque/Biblio.xml";

        // Création de l'objet à tester
        Bibliotheque bibliotheque = new Bibliotheque();

        // Exécution de la méthode à tester
        bibliotheque.LoadXML(filepath);

        // Vérification des résultats
        List<Livre> livres = bibliotheque.livres;  // Accéder directement à la liste de livres

        assertEquals(1, livres.size());  // Vérifiez le nombre de livres ajoutés
        Livre livre = livres.get(0);
        assertEquals("Titre du livre", livre.getTitre());  // Vérifiez les valeurs des propriétés du livre
        assertEquals("Nom de l'auteur", livre.getNomAuteur());
        assertEquals("Prénom de l'auteur", livre.getPrenomAuteur());
        assertEquals("Description du livre", livre.getPresentation());
        assertEquals(2022, livre.getParution());
        assertEquals(3, livre.getColonne());
        assertEquals(2, livre.getRangee());
    }

    @Test
    void toXml() {
    }
}