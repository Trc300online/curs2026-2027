package dao.jpa;

import dao.IncidenciaDao;
import model.Incidencia;

import java.util.List;

/**
 * Aquesta és la vostra classe. Teniu un exemple a codi-explicatiu/6-jpa: TallerDaoJpaImpl, al paquet
 * cat.politecnicllevant.jpa.dao.jpa. Com allà, sense cap try-catch.
 */
public class IncidenciaDaoJpa implements IncidenciaDao {

    @Override
    public List<Incidencia> totes() {
        throw new UnsupportedOperationException("Falta fer la passa 1");
    }

    @Override
    public void insereix(Incidencia incidencia) {
        throw new UnsupportedOperationException("Falta fer la passa 2");
    }

    @Override
    public boolean resol(int id) {
        throw new UnsupportedOperationException("Falta fer la passa 3");
    }
}
