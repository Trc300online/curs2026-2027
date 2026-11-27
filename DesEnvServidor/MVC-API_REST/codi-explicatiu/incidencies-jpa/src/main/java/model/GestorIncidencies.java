package model;

import dao.IncidenciaDao;
import dao.jpa.IncidenciaDaoJpa;

import java.util.ArrayList;
import java.util.List;

/**
 * El model. La mateixa classe que amb JDBC, llevat de la línia que crea el DAO.
 * Les regles segueixen aquí, i no saben si darrere hi ha JDBC o JPA.
 */
public final class GestorIncidencies {

    private static final List<String> PRIORITATS = List.of("alta", "mitjana", "baixa");
    private static final GestorIncidencies INSTANCIA = new GestorIncidencies();

    private final IncidenciaDao incidencies = new IncidenciaDaoJpa();

    private GestorIncidencies() {
    }

    public static GestorIncidencies instancia() {
        return INSTANCIA;
    }

    /** Els valors vàlids de la prioritat, per pintar el desplegable del formulari. */
    public List<String> prioritats() {
        return PRIORITATS;
    }

    public List<Incidencia> totes() {
        return incidencies.totes();
    }

    /**
     * Valida la incidència i, si és correcta, la guarda.
     * Torna la llista d'errors, que és buida si tot ha anat bé.
     */
    public List<String> crea(Incidencia incidencia) {
        List<String> errors = new ArrayList<>();
        if (incidencia.getAula() == null || incidencia.getAula().isBlank()) {
            errors.add("Falta l'aula.");
        }
        if (incidencia.getDescripcio() == null || incidencia.getDescripcio().isBlank()) {
            errors.add("Falta la descripció.");
        }
        if (!PRIORITATS.contains(incidencia.getPrioritat())) {
            errors.add("La prioritat no és vàlida.");
        }
        if (errors.isEmpty()) {
            incidencies.insereix(incidencia);
        }
        return errors;
    }

    /** Marca la incidència com a resolta. Torna false si no existeix. */
    public boolean resol(int id) {
        return incidencies.resol(id);
    }
}
