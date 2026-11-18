package model;

import java.io.Serializable;

/** Ara una inscripció és d'un usuari: el nom i el correu ja no els escriu ningú, surten de la sessió. */
public class Inscripcio implements Serializable {

    private int id;
    private Usuari usuari;
    private Taller taller;

    public Inscripcio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }
}
