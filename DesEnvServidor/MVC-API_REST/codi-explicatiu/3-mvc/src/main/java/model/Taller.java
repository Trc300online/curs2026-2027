package model;

import java.io.Serializable;

/**
 * Un JavaBean: constructor sense arguments, camps privats, getters i setters, i Serializable.
 * Així qualsevol eina pot crear-lo buit i omplir-lo sense conèixer la classe: EL, JPA, Spring...
 */
public class Taller implements Serializable {

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

    // ── PROVA 3 ─ Canviau el nom d'aquest mètode per getLliures, compilau i obriu /tallers.
    //    Compila, perquè cap classe Java no el crida, però la vista sí: 500 que assenyala la línia de la JSP.
    //    La causa, al log de Tomcat: PropertyNotFoundException
    public int getPlacesLliures() {
        return places - inscrits;
    }

    public boolean isPle() {
        return inscrits >= places;
    }
}
