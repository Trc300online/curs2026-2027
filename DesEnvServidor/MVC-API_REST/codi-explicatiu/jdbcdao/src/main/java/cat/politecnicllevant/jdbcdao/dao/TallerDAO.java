package cat.politecnicllevant.jdbcdao.dao;

import cat.politecnicllevant.jdbcdao.model.Taller;
import java.util.List;

public interface TallerDAO {
    List<Taller> getAll();
    Taller getById(String id);
}
