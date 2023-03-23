package com.example.projet_bibliotheque;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bibliotheque")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bibliotheque {

    @XmlElement(name = "livre")
    private List<Livre> livres = new ArrayList<>();

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }
}



