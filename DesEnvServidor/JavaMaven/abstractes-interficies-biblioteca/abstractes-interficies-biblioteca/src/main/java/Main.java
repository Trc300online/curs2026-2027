import java.time.LocalDate;
import java.util.List;

/**
 * NO modifiqueu aquesta classe.
 * No compilarà fins que no hàgiu creat totes les classes que fa servir.
 */
public class Main {
    public static void main(String[] args) {
        LocalDate avui = LocalDate.of(2026, 10, 5);

        Portatil portatil = new Portatil("P-03", "Lenovo ThinkPad", 780);

        List<Exemplar> prestecs = List.of(
                new Llibre("L-001", "Mecanoscrit del segon origen", "Manuel de Pedrolo"),
                new Revista("R-014", "Descobrir", 312),
                portatil);

        System.out.println("── Préstecs del " + avui + " ──");
        for (Exemplar exemplar : prestecs) {
            System.out.println(exemplar + " → s'ha de tornar el " + exemplar.dataRetorn(avui));
        }

        Inventari inventari = new Inventari();
        inventari.afegir(portatil);
        inventari.afegir(new Projector("A12", 450));
        inventari.afegir(new Projector("B04", 1200));
        inventari.afegir(new Projector("C10-postCanvis", 800));

        System.out.println();
        System.out.println("── Inventari d'equips ──");
        inventari.mostrar();
        System.out.println("Valor total: " + inventari.valorTotal() + " €");
        System.out.println("Equips de valor: " + inventari.quantsDeValor());
    }
}
