package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

/**
 * Un JavaBean: constructor sense arguments, camps privats, getters i setters, i Serializable.
 * Així qualsevol eina pot crear-lo buit i omplir-lo sense conèixer la classe: EL, JPA, Spring...
 */
@Entity
@Table(name = "taller")
public class Taller implements Serializable {

    // ── PROVA 1 ─ Posau @Column(name = "titol") damunt el camp nom, compilau i obriu /tallers: 500, «missing column [titol]».
    //    A persistence.xml hem demanat «validate»: Hibernate compara les entitats amb les taules quan arrenca.
    @Id
    private String id;
    private String nom;
    private int places;
    private int inscrits;

    public Taller() {
    }

    public Taller(String id, String nom, int places) {
        this.id = id;
        this.nom = nom;
        this.places = places;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getInscrits() {
        return inscrits;
    }

    public void setInscrits(int inscrits) {
        this.inscrits = inscrits;
    }

    // Aquests dos no tenen camp: JPA mira els camps, i per tant no cerca cap columna
    public int getPlacesLliures() {
        return places - inscrits;
    }

    public boolean isPle() {
        return inscrits >= places;
    }
}
