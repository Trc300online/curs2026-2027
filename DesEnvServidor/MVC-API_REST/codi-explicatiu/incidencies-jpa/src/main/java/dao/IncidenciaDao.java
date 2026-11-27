package dao;

import model.Incidencia;

import java.util.List;

/** Què es pot fer amb les incidències guardades. La interfície no diu com. */
public interface IncidenciaDao {

    /** Totes les incidències, ordenades per id. */
    List<Incidencia> totes();

    /** Guarda la incidència, i li posa l'id que ha generat la base de dades. */
    void insereix(Incidencia incidencia);

    /** Marca la incidència com a resolta. Torna false si no existeix. */
    boolean resol(int id);
}
