package cat.politecnicllevant.jpa.dao.memory;

import cat.politecnicllevant.jpa.dao.TallerDao;
import cat.politecnicllevant.jpa.model.Taller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TallerDaoMemoryImpl implements TallerDao {

    private final Map<String, Taller> tallers = new LinkedHashMap<>();

    public TallerDaoMemoryImpl() {
        afegeixTaller(new Taller("git", "Git i GitHub sense por", "Aina Ferrà", LocalDate.of(2026, 11, 24), 20, new BigDecimal("0.00")));
        afegeixTaller(new Taller("docker", "Docker per a principiants", "Pau Mir", LocalDate.of(2026, 11, 24), 15, new BigDecimal("12.50")));
        afegeixTaller(new Taller("ia", "Programar amb assistents d'IA", "Marta Riera", LocalDate.of(2026, 11, 25), 30, new BigDecimal("15.00")));
        afegeixTaller(new Taller("xss", "Seguretat web: XSS & companyia", "Joan Serra", LocalDate.of(2026, 11, 26), 12, new BigDecimal("5.00")));
        afegeixTaller(new Taller("sql", "SQL i índexs per a desenvolupadors", "Biel Cànaves", LocalDate.of(2026, 11, 26), 18, new BigDecimal("8.00")));
        afegeixTaller(new Taller("rest", "Dissenyar una API REST", "Catalina Vidal", LocalDate.of(2026, 11, 27), 25, new BigDecimal("10.00")));
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

    @Override
    public boolean insert(Taller taller) {
        // Fa el paper de la clau primària: dos tallers no poden tenir el mateix codi
        if (tallers.containsKey(taller.getId())) {
            return false;
        }
        afegeixTaller(taller);
        return true;
    }
}
