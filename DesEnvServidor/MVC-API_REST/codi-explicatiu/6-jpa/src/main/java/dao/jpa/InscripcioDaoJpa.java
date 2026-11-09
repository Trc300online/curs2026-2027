package dao.jpa;

import dao.DaoException;
import dao.InscripcioDao;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceException;
import model.Inscripcio;
import model.Taller;
import model.Usuari;

import java.util.List;

public class InscripcioDaoJpa implements InscripcioDao {

    // ── PROVA 2 ─ Llevau els dos JOIN FETCH, compilau i obriu la vostra llista: 500 a la línia de la JSP, i a docker compose logs
    //    «LazyInitializationException». La JSP demana el taller quan l'EntityManager ja és tancat. Si a Inscripcio llevau també els fetch = LAZY, funciona,
    //    però mirau docker compose logs: una consulta per a cada usuari i taller diferents. És el problema «N+1».
    private static final String SELECT = "SELECT i FROM Inscripcio i JOIN FETCH i.usuari JOIN FETCH i.taller ";

    @Override
    public List<Inscripcio> totes() {
        try {
            return Persistencia.fabrica().callInTransaction(em ->
                    em.createQuery(SELECT + "ORDER BY i.id", Inscripcio.class).getResultList());
        } catch (PersistenceException e) {
            throw new DaoException("No s'han pogut llegir les inscripcions", e);
        }
    }

    @Override
    public List<Inscripcio> deUsuari(Usuari usuari) {
        try {
            return Persistencia.fabrica().callInTransaction(em ->
                    em.createQuery(SELECT + "WHERE i.usuari.nom = :nom ORDER BY i.id", Inscripcio.class)
                            .setParameter("nom", usuari.getNom())
                            .getResultList());
        } catch (PersistenceException e) {
            throw new DaoException("No s'han pogut llegir les inscripcions", e);
        }
    }

    /**
     * La mateixa transacció de la passa 5. Si la funció acaba bé, callInTransaction fa el commit.
     * Si llança una excepció, fa el rollback. Ja no s'ha d'escriure.
     */
    @Override
    public boolean inscriu(Usuari usuari, Taller taller) {
        try {
            return Persistencia.fabrica().callInTransaction(em -> {
                // JPQL també pot actualitzar. La comprovació continua dins l'UPDATE, per les peticions simultànies
                int canviades = em.createQuery("UPDATE Taller t SET t.inscrits = t.inscrits + 1 "
                                + "WHERE t.id = :id AND t.inscrits < t.places")
                        .setParameter("id", taller.getId())
                        .executeUpdate();
                if (canviades == 0) {
                    return false;
                }

                // ── PROVA 3 ─ Descomentau aquest throw i compilau. Inscriviu-vos: 500, i a la base de dades no ha canviat res.
                // if (true) throw new PersistenceException("Error provocat per la PROVA 3");

                Inscripcio inscripcio = new Inscripcio();
                // getReference: només en necessitam la clau, i així no els ha de tornar a llegir
                inscripcio.setUsuari(em.getReference(Usuari.class, usuari.getNom()));
                inscripcio.setTaller(em.getReference(Taller.class, taller.getId()));
                em.persist(inscripcio);     // l'INSERT el genera Hibernate
                return true;
            });
        } catch (PersistenceException e) {
            throw new DaoException("No s'ha pogut fer la inscripció", e);
        }
    }

    @Override
    public boolean anulla(int id, Usuari usuari) {
        try {
            return Persistencia.fabrica().callInTransaction(em -> {
                Inscripcio inscripcio = em.createQuery(
                                "SELECT i FROM Inscripcio i WHERE i.id = :id AND i.usuari.nom = :nom", Inscripcio.class)
                        .setParameter("id", id)
                        .setParameter("nom", usuari.getNom())
                        .getSingleResultOrNull();
                if (inscripcio == null) {
                    return false;
                }

                // Bloqueja la fila fins al commit: si n'arriben dues alhora, MySQL les atén d'una en una
                Taller taller = em.find(Taller.class, inscripcio.getTaller().getId(), LockModeType.PESSIMISTIC_WRITE);

                // ── PROVA 5 ─ Aquí no hi ha cap UPDATE: l'objecte està «gestionat», i Hibernate guarda els canvis al commit.
                //    Mirau-lo a docker compose logs quan anul·lau una inscripció. Comentau aquesta línia: ja no hi és.
                taller.setInscrits(taller.getInscrits() - 1);

                em.remove(inscripcio);
                return true;
            });
        } catch (PersistenceException e) {
            throw new DaoException("No s'ha pogut anul·lar la inscripció", e);
        }
    }
}
