package model;

import java.io.Serializable;

/** Una inscripció: un altre JavaBean. El controlador el crea buit i l'omple amb els paràmetres. */
public class Inscripcio implements Serializable {

    private int id;
    private String nom;
    private String correu;
    private Taller taller;

    public Inscripcio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }
}
