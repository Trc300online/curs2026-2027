package cat.politecnicllevant.jpa.model;

import cat.politecnicllevant.jpa.dao.TallerDao;
import cat.politecnicllevant.jpa.dao.jdbc.TallerDaoJdbcImpl;
import cat.politecnicllevant.jpa.dao.jpa.TallerDaoJpaImpl;
import cat.politecnicllevant.jpa.dao.memory.TallerDaoMemoryImpl;

import java.util.List;

public final class TallerModel {
    private static final TallerModel INSTANCIA = new TallerModel();

    public static TallerModel instancia() {
        return INSTANCIA;
    }

    // L'única línia del model que canvia respecte de tallers-sessions: ara els tallers els guarda JPA.
    // ── PROVA 2 ─ Canviau-la per new TallerDaoJdbcImpl() i després per new TallerDaoMemoryImpl(). Obriu /tallers
    //    cada vegada: la mateixa aplicació, i cap Servlet ni cap JSP no se n'assabenta. Això és el patró DAO.
    //    Tornau-la a deixar com era.
    private TallerDao tallerDao = new TallerDaoJpaImpl();

    public List<Taller> tallers(){
        return tallerDao.getAll();
    }

    /** El taller amb aquest codi, o null si no n'hi ha cap. */
    public Taller taller(String id) {
        // Sense codi (/taller, sense ?id=) no hi ha cap taller. Ho miram aquí perquè tots els DAO responguin igual.
        // Amb JPA és imprescindible: em.find amb un id null llança IllegalArgumentException, i seria un 500
        if (id == null || id.isEmpty()) {
            return null;
        }
        return tallerDao.getById(id);
    }

    /**
     * Crea un taller nou. Primer les regles del taller tot sol (valida), i després la que depèn de les
     * dades guardades: el codi no es pot repetir. Si tot és correcte, el guarda amb el DAO.
     * Torna la llista d'errors: buida si s'ha guardat.
     */
    public List<String> afegeix(Taller taller) {
        List<String> errors = taller.valida();

        // Només intentam guardar si és vàlid. Si el DAO torna false, el codi ja existia
        if (errors.isEmpty() && !tallerDao.insert(taller)) {
            errors.add("Ja hi ha un taller amb el codi «" + taller.getId() + "».");
        }
        return errors;
    }
}
