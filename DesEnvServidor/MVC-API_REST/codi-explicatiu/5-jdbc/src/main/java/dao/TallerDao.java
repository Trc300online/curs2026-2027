package dao;

import model.Taller;

import java.util.List;

public interface TallerDao {

    List<Taller> tots();

    /** Torna el taller amb aquest id, o null si no existeix. */
    Taller cerca(String id);
}
