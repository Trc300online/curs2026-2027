import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Registre<T extends Identificable> {

    private final List<T> elements = new ArrayList<>();

    public void afegir(T element) {
        if (element == null) {
            throw new IllegalArgumentException("L'element no pot ser null");
        }

        if (cercar(element.getCodi()) != null) {
            throw new IllegalArgumentException("Ja existeix aquest codi");
        }

        elements.add(element);
    }

    public T cercar(String codi) {
        for (T element : elements) {
            if (element.getCodi().equals(codi)) {
                return element;
            }
        }

        return null;
    }

    public List<T> tots() {
        return Collections.unmodifiableList(new ArrayList<>(elements));
    }

    public int quants() {
        return elements.size();
    }
}
