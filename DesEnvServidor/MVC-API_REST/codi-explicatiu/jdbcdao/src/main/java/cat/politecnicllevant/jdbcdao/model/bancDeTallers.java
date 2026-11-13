package cat.politecnicllevant.jdbcdao.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class bancDeTallers {

    private static final bancDeTallers INSTANCIA = new bancDeTallers();

    private final Map<String, Taller> tallers = new LinkedHashMap<>();

    private bancDeTallers() {
        afegeixTaller(new Taller("git", "Git i Github sense por", "Aina Ferrà"));
        afegeixTaller(new Taller("docker", "Docker per a prinicpiants", "Pau Mir"));

    }

    public static bancDeTallers instancia() {
        return INSTANCIA;
    }

    private void afegeixTaller(Taller taller) {
        tallers.put(taller.getId(), taller);
    }

    public synchronized List<Taller> tallers() {
        return new ArrayList<>(tallers.values());
    }

    public synchronized Taller taller(String id) {
        return tallers.get(id);
    }

}
