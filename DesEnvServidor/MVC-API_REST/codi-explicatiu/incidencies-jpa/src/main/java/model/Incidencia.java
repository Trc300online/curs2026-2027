package model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;

/**
 * Un JavaBean: Serializable, constructor sense arguments, camps privats i getters i setters.
 * El nom dels getters és el que després usa la vista: ${i.aula} crida getAula().
 */
public class Incidencia implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String aula;
    private String descripcio;
    private String prioritat;
    @ColumnDefault("false")
    private boolean resolta;

    public Incidencia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getPrioritat() {
        return prioritat;
    }

    public void setPrioritat(String prioritat) {
        this.prioritat = prioritat;
    }

    // Un boolean usa «is»: ${i.resolta} crida isResolta()
    public boolean isResolta() {
        return resolta;
    }

    public void setResolta(boolean resolta) {
        this.resolta = resolta;
    }
}
