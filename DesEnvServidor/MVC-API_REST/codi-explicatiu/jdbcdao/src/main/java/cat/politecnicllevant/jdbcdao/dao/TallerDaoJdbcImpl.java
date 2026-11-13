package cat.politecnicllevant.jdbcdao.dao;

import cat.politecnicllevant.jdbcdao.model.Taller;

import java.util.List;

public class TallerDaoJdbcImpl implements TallerDAO{
    @Override
    public List<Taller> getAll() {
        return List.of();
    }

    @Override
    public Taller getById(String id) {
        return null;
    }
}
