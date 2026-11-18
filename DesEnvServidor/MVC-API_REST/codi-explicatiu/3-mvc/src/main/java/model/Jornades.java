package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * El model: les dades i les regles de l'aplicació. No sap res d'HTTP ni d'HTML: no hi ha cap request ni cap JSP.
 *
 * Els Servlets atenen moltes peticions alhora i tots usen aquest mateix objecte (un Singleton), per això
 * els mètodes són synchronized. Les dades són en memòria: es perden en recarregar l'aplicació.
 * A la passa 5 aniran a una base de dades.
 */
public final class Jornades {

    private static final Jornades INSTANCIA = new Jornades();

    private final Map<String, Taller> tallers = new LinkedHashMap<>();
    private final Map<Integer, Inscripcio> inscripcions = new LinkedHashMap<>();
    private int seguentId = 1;

    private Jornades() {
        afegeixTaller(new Taller("git", "Git i GitHub sense por", 20));
        afegeixTaller(new Taller("docker", "Docker per a principiants", 2));
        afegeixTaller(new Taller("ia", "Programar amb assistents d'IA", 30));
    }

    public static Jornades instancia() {
        return INSTANCIA;
    }

    private void afegeixTaller(Taller taller) {
        tallers.put(taller.getId(), taller);
    }

    public synchronized List<Taller> tallers() {
        return new ArrayList<>(tallers.values());
    }

    public synchronized Taller taller(String id) {
        return tallers.get(id);
    }

    public synchronized List<Inscripcio> inscripcions() {
        return new ArrayList<>(inscripcions.values());
    }

    public synchronized Inscripcio inscripcio(int id) {
        return inscripcions.get(id);
    }

    /**
     * La regla de negoci sencera: valida i, si tot és correcte, guarda la inscripció i li posa l'id.
     * Torna la llista d'errors, que és buida si tot ha anat bé.
     */
    public synchronized List<String> inscriu(Inscripcio inscripcio) {
        List<String> errors = new ArrayList<>();
        if (inscripcio.getNom() == null || inscripcio.getNom().isBlank()) {
            errors.add("Falta el nom.");
        }
        if (inscripcio.getCorreu() == null || !inscripcio.getCorreu().contains("@")) {
            errors.add("El correu no és vàlid.");
        }
        Taller taller = inscripcio.getTaller();
        if (taller == null) {
            errors.add("Heu de triar un taller.");
        } else if (taller.isPle()) {
            errors.add("El taller «" + taller.getNom() + "» ja és ple.");
        }
        if (errors.isEmpty()) {
            inscripcio.setId(seguentId++);
            inscripcions.put(inscripcio.getId(), inscripcio);
            taller.setInscrits(taller.getInscrits() + 1);
        }
        return errors;
    }

    /** Anul·la la inscripció i allibera la plaça. Torna false si no existia. */
    public synchronized boolean anulla(int id) {
        Inscripcio inscripcio = inscripcions.remove(id);
        if (inscripcio == null) {
            return false;
        }
        Taller taller = inscripcio.getTaller();
        taller.setInscrits(taller.getInscrits() - 1);
        return true;
    }
}
