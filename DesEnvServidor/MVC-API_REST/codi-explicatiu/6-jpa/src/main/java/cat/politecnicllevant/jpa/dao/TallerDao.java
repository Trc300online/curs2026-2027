package cat.politecnicllevant.jpa.dao;

import cat.politecnicllevant.jpa.model.Taller;

import java.util.List;

public interface TallerDao {
    List<Taller> getAll();
    Taller getById(String id);

    boolean insert(Taller taller);
}
