package model;

import dao.InscripcioDao;
import dao.TallerDao;
import dao.UsuariDao;
import dao.jdbc.InscripcioDaoJdbc;
import dao.jdbc.TallerDaoJdbc;
import dao.jdbc.UsuariDaoJdbc;

import java.util.List;

/**
 * Els mateixos mètodes que a la passa 4: per això cap Servlet no ha canviat.
 * Abans guardava les dades en mapes. Ara les regles segueixen aquí, i les dades les guarda algú altre: els DAO.
 * No hi ha cap synchronized: aquesta classe ja no guarda res, i de les escriptures simultànies se n'ocupa MySQL.
 */
public final class Jornades {

    private static final Jornades INSTANCIA = new Jornades();

    // Les úniques línies que saben que hi ha JDBC. A la passa 6 canviarem només això
    private final UsuariDao usuaris = new UsuariDaoJdbc();
    private final TallerDao tallers = new TallerDaoJdbc();
    private final InscripcioDao inscripcions = new InscripcioDaoJdbc();

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
