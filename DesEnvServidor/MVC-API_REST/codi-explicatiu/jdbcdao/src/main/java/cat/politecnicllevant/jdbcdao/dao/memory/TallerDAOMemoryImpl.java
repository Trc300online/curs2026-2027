package cat.politecnicllevant.jdbcdao.dao.memory;

import cat.politecnicllevant.jdbcdao.dao.TallerDAO;
import cat.politecnicllevant.jdbcdao.model.Taller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TallerDAOMemoryImpl implements TallerDAO {

    private final Map<String, Taller> tallers = new LinkedHashMap<>();

    public TallerDAOMemoryImpl() {
        afegeixTaller(new Taller("git", "Git i Github sense por", "Aina Ferrà", null, 5, null));
        afegeixTaller(new Taller("docker", "Docker per a prinicpiants", "Pau Mir", null, 5, null));

    }

    private void afegeixTaller(Taller taller) {
        tallers.put(taller.getId(), taller);
    }

    @Override
    public List<Taller> getAll() {
        return new ArrayList<>(tallers.values());
    }

    @Override
    public Taller getById(String id) {
        return tallers.get(id);
    }
}
