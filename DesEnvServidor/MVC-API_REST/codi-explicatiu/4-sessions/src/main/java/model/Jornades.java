package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * El model: les dades i les regles. Segueix sense saber res d'HTTP: rep un Usuari, no una sessió.
 * Les dades són en memòria, i es perden en recarregar l'aplicació. A la passa 5 aniran a una base de dades.
 */
public final class Jornades {

    private static final Jornades INSTANCIA = new Jornades();

    private final Map<String, Taller> tallers = new LinkedHashMap<>();
    private final Map<Integer, Inscripcio> inscripcions = new LinkedHashMap<>();
    private final Map<String, Usuari> usuaris = new LinkedHashMap<>();
    // Només per aprendre! Mai no es guarden les contrasenyes tal qual: es guarda el seu hash. Ho farem amb Spring Security.
    private final Map<String, String> contrasenyes = new LinkedHashMap<>();
    private int seguentId = 1;

    private Jornades() {
        afegeixTaller(new Taller("git", "Git i GitHub sense por", 20));
        afegeixTaller(new Taller("docker", "Docker per a principiants", 2));
        afegeixTaller(new Taller("ia", "Programar amb assistents d'IA", 30));
        afegeixUsuari(new Usuari("anna", "Anna Ferrà", false), "anna123");
        afegeixUsuari(new Usuari("marc", "Marc Riera", false), "marc123");
        afegeixUsuari(new Usuari("admin", "L'administradora", true), "admin123");
    }

    public static Jornades instancia() {
        return INSTANCIA;
    }

    private void afegeixTaller(Taller taller) {
        tallers.put(taller.getId(), taller);
    }

    private void afegeixUsuari(Usuari usuari, String contrasenya) {
        usuaris.put(usuari.getNom(), usuari);
        contrasenyes.put(usuari.getNom(), contrasenya);
    }

    /** Torna l'usuari si el nom i la contrasenya són correctes, o null si no ho són. */
    public synchronized Usuari autentica(String nom, String contrasenya) {
        if (contrasenya == null || !contrasenya.equals(contrasenyes.get(nom))) {
            return null;
        }
        return usuaris.get(nom);
    }

    public synchronized List<Taller> tallers() {
        return new ArrayList<>(tallers.values());
    }

    public synchronized List<Inscripcio> inscripcions() {
        return new ArrayList<>(inscripcions.values());
    }

    /**
     * Les inscripcions d'un usuari. Es compara pel nom, no amb ==: dos objectes
     * diferents poden representar el mateix usuari.
     */
    public synchronized List<Inscripcio> inscripcionsDe(Usuari usuari) {
        return inscripcions.values().stream()
                .filter(i -> i.getUsuari().getNom().equals(usuari.getNom()))
                .toList();
    }

    /** Inscriu l'usuari al taller. Torna el motiu si no pot, o null si tot ha anat bé. */
    public synchronized String inscriu(Usuari usuari, String idTaller) {
        Taller taller = tallers.get(idTaller);
        if (taller == null) {
            return "Aquest taller no existeix.";
        }
        if (taller.isPle()) {
            return "El taller «" + taller.getNom() + "» ja és ple.";
        }
        for (Inscripcio i : inscripcionsDe(usuari)) {
            if (i.getTaller() == taller) {
                return "Ja estau inscrit a «" + taller.getNom() + "».";
            }
        }
        Inscripcio inscripcio = new Inscripcio();
        inscripcio.setId(seguentId++);
        inscripcio.setUsuari(usuari);
        inscripcio.setTaller(taller);
        inscripcions.put(inscripcio.getId(), inscripcio);
        taller.setInscrits(taller.getInscrits() + 1);
        return null;
    }

    /** Anul·la la inscripció, només si és d'aquest usuari. Torna false si no existeix o és d'un altre. */
    public synchronized boolean anulla(int id, Usuari usuari) {
        Inscripcio inscripcio = inscripcions.get(id);
        if (inscripcio == null) {
            return false;
        }
        // ── PROVA 4 ─ Comentau aquest if i compilau. Entrau amb marc i inscriviu-vos. Sortiu, entrau amb anna, i a F12 canviau
        //    el valor del camp ocult «id» d'un botó d'Anul·lar pel de la inscripció d'en Marc. El filtre vos ha deixat passar:
        //    sap qui sou, però no de qui són les dades. Aquesta regla és del model.
        if (!inscripcio.getUsuari().getNom().equals(usuari.getNom())) {
            return false;
        }
        inscripcions.remove(id);
        Taller taller = inscripcio.getTaller();
        taller.setInscrits(taller.getInscrits() - 1);
        return true;
    }
}
