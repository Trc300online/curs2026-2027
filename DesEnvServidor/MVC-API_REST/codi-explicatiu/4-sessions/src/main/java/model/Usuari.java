package model;

import java.io.Serializable;

/**
 * L'usuari que guardam a la sessió. Un JavaBean, i sense la contrasenya: a la sessió només hi va el que cal.
 * Serializable: si un servidor guarda les sessions a disc o les comparteix amb un altre, els objectes s'han de poder desar.
 */
public class Usuari implements Serializable {

    private String nom;
    private String nomComplet;
    private boolean admin;

    public Usuari() {
    }

    public Usuari(String nom, String nomComplet, boolean admin) {
        this.nom = nom;
        this.nomComplet = nomComplet;
        this.admin = admin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
