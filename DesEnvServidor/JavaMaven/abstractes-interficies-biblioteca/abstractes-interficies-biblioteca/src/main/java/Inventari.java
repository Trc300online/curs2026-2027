import java.util.ArrayList;
import java.util.List;

public class Inventari {

    private List<Inventariable> items;

    public Inventari() {
        items = new ArrayList<>();
    }

    public void afegir(Inventariable item) {
        items.add(item);
    }

    public void mostrar() {
        for (Inventariable item : items) {
            System.out.println(
                    item.descripcio() + ": "
                            + String.format("%.1f", (double) item.getValorReposicio())
                            + " €"
                            + (item.esDeValor() ? " (de valor)" : "")
            );
        }
    }

    public int valorTotal() {
        int total = 0;

        for (Inventariable item : items) {
            total += item.getValorReposicio();
        }

        return total;
    }

    public int quantsDeValor() {
        int quantitat = 0;

        for (Inventariable item : items) {
            if (item.esDeValor()) {
                quantitat++;
            }
        }

        return quantitat;
    }
}