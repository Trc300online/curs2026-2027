import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** El codi d'abans: guarda els refugis. Quan el Main funcioni amb Repositori, s'ha d'esborrar. */
public class RepositoriRefugis {
    private final List<Refugi> refugis = new ArrayList<>();

    public void guardar(Refugi refugi) {
        if (cercar(refugi.getId()) != null) {
            throw new IllegalArgumentException("Ja existeix un refugi amb l'id " + refugi.getId());
        }
        refugis.add(refugi);
    }

    /** Retorna el refugi amb aquest número, o null si no n'hi ha cap. */
    public Refugi cercar(Integer id) {
        for (Refugi refugi : refugis) {
            if (refugi.getId().equals(id)) {
                return refugi;
            }
        }
        return null;
    }

    public List<Refugi> tots() {
        return Collections.unmodifiableList(refugis);
    }

    public int quants() {
        return refugis.size();
    }
}
