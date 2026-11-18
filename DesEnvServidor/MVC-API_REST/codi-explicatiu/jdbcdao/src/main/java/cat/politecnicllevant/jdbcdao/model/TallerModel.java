package cat.politecnicllevant.jdbcdao.model;

import cat.politecnicllevant.jdbcdao.dao.TallerDAO;
import cat.politecnicllevant.jdbcdao.dao.jdbc.TallerDaoJdbcImpl;
import cat.politecnicllevant.jdbcdao.dao.memory.TallerDAOMemoryImpl;

import java.util.List;

public class TallerModel {

    private static final TallerModel INSTANCIA = new TallerModel();

    public static TallerModel instancia() {
        return INSTANCIA;
    }

    private TallerDAO tallerDAO = new TallerDaoJdbcImpl();

    public List<Taller> tallers() {
        return tallerDAO.getAll();
    }

    public Taller taller(String id) {
        return tallerDAO.getById(id);
    }
}
