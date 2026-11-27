package cat.politecnicllevant.jpa.dao.jpa;

import cat.politecnicllevant.jpa.dao.TallerDao;
import cat.politecnicllevant.jpa.model.Taller;

import java.util.List;

/**
 * La mateixa interfície que TallerDaoJdbcImpl, sense cap SQL: el genera Hibernate.
 * Teniu obert docker compose logs -f tomcat: hibernate.show_sql hi escriu cada sentència.
 *
 * callInTransaction obre un EntityManager i una transacció, executa el lambda i ho tanca tot:
 * si el lambda acaba bé, fa el commit, i si llança una excepció, el rollback.
 *
 * No hi ha cap try-catch: els errors de JPA són PersistenceException, que ja és una RuntimeException.
 * Si n'hi ha cap, Tomcat mostra l'error 500, com el throw new RuntimeException(e) de TallerDaoJdbcImpl.
 */
public class TallerDaoJpaImpl implements TallerDao {

    @Override
    public List<Taller> getAll() {
        return Persistencia.fabrica().callInTransaction(em -> {
            // JPQL: parla de la classe Taller, no de la taula taller. Hibernate el tradueix al SQL de MySQL
            List<Taller> tallers = em.createQuery("SELECT t FROM Taller t", Taller.class).getResultList();

            // ── PROVA 3 ─ Descomentau aquesta línia i obriu /tallers: el primer taller surt en majúscules, i al log hi ha
            //    un update que no hem escrit. Dins la transacció l'objecte està «gestionat»: Hibernate guarda els canvis
            //    al commit. Feis F5: ja no hi ha update, perquè el nom ja era en majúscules i no ha canviat res.
            //    Tornau-la a comentar. El nom queda en majúscules a la base de dades: ara ja sabeu per què.
            // tallers.getFirst().setNom(tallers.getFirst().getNom().toUpperCase());

            return tallers;
        });
    }

    @Override
    public Taller getById(String id) {
        // Per clau primària no cal JPQL. Torna null si no hi és
        return Persistencia.fabrica().callInTransaction(em -> em.find(Taller.class, id));
    }

    @Override
    public boolean insert(Taller taller) {
        return Persistencia.fabrica().callInTransaction(em -> {
            // Abans, SQLIntegrityConstraintViolationException ens deia que el codi ja hi era. Ara ho miram abans
            if (em.find(Taller.class, taller.getId()) != null) {
                return false;
            }
            em.persist(taller);     // encara no és l'INSERT: Hibernate l'envia al commit, quan acaba el lambda

            // ── PROVA 4 ─ Descomentau aquest throw i creau un taller: 500, i el taller no hi és. Al log no hi ha cap
            //    insert: l'excepció arriba abans del commit, i callInTransaction fa el rollback. O tot, o res.
            // if (true) throw new IllegalStateException("Error provocat per la PROVA 4");

            return true;
        });
    }
}
