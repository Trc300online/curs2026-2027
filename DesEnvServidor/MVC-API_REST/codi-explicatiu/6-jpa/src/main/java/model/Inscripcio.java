package model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;

/** A Java, una inscripció té un Usuari i un Taller. A la taula, dues claus. JPA tradueix d'una cosa a l'altra. */
@Entity
@Table(name = "inscripcio")
public class Inscripcio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // el genera MySQL: AUTO_INCREMENT
    private int id;

    // Moltes inscripcions, un usuari. LAZY: no el llegeix fins que no el demana algú
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuari")        // la columna de la clau forana
    private Usuari usuari;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taller")
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
