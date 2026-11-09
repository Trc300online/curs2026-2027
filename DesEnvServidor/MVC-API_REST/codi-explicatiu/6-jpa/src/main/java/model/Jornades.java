package model;

import dao.InscripcioDao;
import dao.TallerDao;
import dao.UsuariDao;
import dao.jpa.InscripcioDaoJpa;
import dao.jpa.TallerDaoJpa;
import dao.jpa.UsuariDaoJpa;

import java.util.List;

/**
 * Exactament la mateixa classe de la passa 5, llevat de les tres línies que creen els DAO.
 * Les regles segueixen aquí, i no saben si darrere hi ha JDBC o JPA.
 */
public final class Jornades {

    private static final Jornades INSTANCIA = new Jornades();

    // Les úniques línies que saben que hi ha JPA
    private final UsuariDao usuaris = new UsuariDaoJpa();
    private final TallerDao tallers = new TallerDaoJpa();
    private final InscripcioDao inscripcions = new InscripcioDaoJpa();

    private Jornades() {
    }

    public static Jornades instancia() {
        return INSTANCIA;
    }

    /** Torna l'usuari si el nom i la contrasenya són correctes, o null si no ho són. */
    public Usuari autentica(String nom, String contrasenya) {
        return usuaris.autentica(nom, contrasenya);
    }

    public List<Taller> tallers() {
        return tallers.tots();
    }

    public List<Inscripcio> inscripcions() {
        return inscripcions.totes();
    }

    public List<Inscripcio> inscripcionsDe(Usuari usuari) {
        return inscripcions.deUsuari(usuari);
    }

    /** Inscriu l'usuari al taller. Torna el motiu si no pot, o null si tot ha anat bé. */
    public String inscriu(Usuari usuari, String idTaller) {
        Taller taller = tallers.cerca(idTaller);
        if (taller == null) {
            return "Aquest taller no existeix.";
        }
        for (Inscripcio i : inscripcions.deUsuari(usuari)) {
            if (i.getTaller().getId().equals(taller.getId())) {
                return "Ja estau inscrit a «" + taller.getNom() + "».";
            }
        }
        if (!inscripcions.inscriu(usuari, taller)) {
            return "El taller «" + taller.getNom() + "» ja és ple.";
        }
        return null;
    }

    /** Anul·la la inscripció, només si és d'aquest usuari. Torna false si no existeix o és d'un altre. */
    public boolean anulla(int id, Usuari usuari) {
        return inscripcions.anulla(id, usuari);
    }
}
