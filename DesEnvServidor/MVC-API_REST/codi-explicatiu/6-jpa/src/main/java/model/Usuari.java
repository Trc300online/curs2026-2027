package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

/**
 * Una entitat: una classe que correspon a una taula. Continua sent un JavaBean, amb unes anotacions.
 * Serializable: si un servidor guarda les sessions a disc o les comparteix amb un altre, els objectes s'han de poder desar.
 */
@Entity
@Table(name = "usuari")     // sense això cercaria la taula «Usuari», i a Linux MySQL distingeix majúscules
public class Usuari implements Serializable {

    @Id                     // la clau primària
    private String nom;

    @Column(name = "nom_complet")   // cal dir-ho només quan la columna no es diu igual que el camp
    private String nomComplet;

    // Sense getter: només serveix per comprovar-la a la consulta del DAO, i a la sessió no hi arriba mai
    private String contrasenya;

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
