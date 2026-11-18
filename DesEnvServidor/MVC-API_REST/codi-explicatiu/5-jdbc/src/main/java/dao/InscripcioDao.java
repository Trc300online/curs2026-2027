package dao;

import model.Inscripcio;
import model.Taller;
import model.Usuari;

import java.util.List;

public interface InscripcioDao {

    List<Inscripcio> totes();

    List<Inscripcio> deUsuari(Usuari usuari);

    /** Guarda la inscripció i ocupa una plaça. Torna false si ja no en queda cap. */
    boolean inscriu(Usuari usuari, Taller taller);

    /** Esborra la inscripció, només si és d'aquest usuari, i allibera la plaça. Torna false si no n'hi ha cap. */
    boolean anulla(int id, Usuari usuari);
}
