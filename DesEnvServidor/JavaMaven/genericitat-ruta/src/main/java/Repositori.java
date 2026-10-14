import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repositori<T extends Identificable<ID>, ID> {
    private final List<T> elements = new ArrayList<>();

    public void guardar(T element) {
        if (cercar(element.getId()) != null) {
            throw new IllegalArgumentException("Ja existeix un element amb l'id " + element.getId());
        }
        elements.add(element);
    }

    public T cercar(ID id) {
        for (T element : elements) {
            if (element.getId().equals(id)) {
                return element;
            }
        }
        return null;
    }

    public List<T> tots() {
        return Collections.unmodifiableList(elements);
    }

    public int quants() {
        return elements.size();
    }

    public List<ID> ids() {
        List<ID> ids = new ArrayList<>();
        for (T element : elements) {
            ids.add(element.getId());
        }
        return ids;
    }
}
