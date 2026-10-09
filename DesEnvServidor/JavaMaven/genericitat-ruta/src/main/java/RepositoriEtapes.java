import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** El codi d'abans: guarda les etapes. Quan el Main funcioni amb Repositori, s'ha d'esborrar. */
public class RepositoriEtapes {
    private final List<Etapa> etapes = new ArrayList<>();

    public void guardar(Etapa etapa) {
        if (cercar(etapa.getId()) != null) {
            throw new IllegalArgumentException("Ja existeix una etapa amb l'id " + etapa.getId());
        }
        etapes.add(etapa);
    }

    /** Retorna l'etapa amb aquest id, o null si no n'hi ha cap. */
    public Etapa cercar(String id) {
        for (Etapa etapa : etapes) {
            if (etapa.getId().equals(id)) {
                return etapa;
            }
        }
        return null;
    }

    public List<Etapa> tots() {
        return Collections.unmodifiableList(etapes);
    }

    public int quants() {
        return etapes.size();
    }
}
