package dao.jpa;

import dao.DaoException;
import dao.TallerDao;
import jakarta.persistence.PersistenceException;
import model.Taller;

import java.util.List;

public class TallerDaoJpa implements TallerDao {

    @Override
    public List<Taller> tots() {
        try {
            // callInTransaction obre un EntityManager i una transacció, fa la feina, i ho tanca tot
            return Persistencia.fabrica().callInTransaction(em ->
                    em.createQuery("SELECT t FROM Taller t ORDER BY t.nom", Taller.class).getResultList());
        } catch (PersistenceException e) {
            throw new DaoException("No s'han pogut llegir els tallers", e);
        }
    }

    @Override
    public Taller cerca(String id) {
        try {
            // Per clau primària no cal ni JPQL. Torna null si no existeix
            return Persistencia.fabrica().callInTransaction(em -> em.find(Taller.class, id));
        } catch (PersistenceException e) {
            throw new DaoException("No s'ha pogut llegir el taller", e);
        }
    }
}
