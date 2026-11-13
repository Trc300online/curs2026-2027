package cat.politecnicllevant.jdbcdao.dao;

import cat.politecnicllevant.jdbcdao.model.Taller;
import cat.politecnicllevant.jdbcdao.model.bancDeTallers;

import java.util.List;

public class TallerDAOMemoryImpl implements TallerDAO{

    bancDeTallers banc = bancDeTallers.instancia();

    @Override
    public List<Taller> getAll() {
        return banc.tallers();
    }

    @Override
    public Taller getById(String id) {
        return banc.taller(id);
    }
}
