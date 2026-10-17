import java.time.LocalDate;
import java.util.List;

/**
 * Prova de la part 3 (herència). NO l'heu de modificar.
 *
 * Copiau-la a src/main/java quan comenceu la part 3. No compilarà fins que no hàgiu creat la classe Suite.
 */
public class ProvaPart3 {
    public static void main(String[] args) {
        Client biel = new Client("41987654B", "Biel Mas", "biel@exemple.cat");
        Suite olivera = new Suite("S1", "Olivera", 3, 100, true, 45);
        Suite llimonera = new Suite("S2", "Llimonera", 2, 100, false, 35);

        System.out.println("── Habitacions i suites ──");
        List<Habitacio> habitacions = List.of(
                new Habitacio("H1", "Ametler", 2, 90, false),
                new Habitacio("H2", "Garrover", 2, 90, true),
                olivera,
                llimonera);
        for (Habitacio h : habitacions) {
            System.out.println(h + ": " + euros(h.preuNit()) + " per nit");
        }

        System.out.println();
        System.out.println("── Una reserva a una suite ──");
        Reserva r = new Reserva(biel, olivera, LocalDate.of(2026, 7, 20), LocalDate.of(2026, 7, 24), 3);
        System.out.println(r + " · " + r.nits() + " nits · " + euros(r.preu()));

        System.out.println();
        System.out.println("── Una suite també és una habitació ──");
        llimonera.setPreuBase(120);
        System.out.println(llimonera.getCodi() + " amb el preu base a 120 €: " + euros(llimonera.preuNit()) + " per nit");

        System.out.println();
        System.out.println("── Coses que no s'han de poder fer ──");
        try {
            new Suite("S9", "Murta", 2, 100, false, 0);
            alerta("Una suite de 0 m²");
        } catch (RuntimeException e) {
            ok("Una suite de 0 m²", e);
        }
        try {
            new Suite("S9", "Murta", 5, 100, false, 50);
            alerta("Una suite de 5 places");
        } catch (RuntimeException e) {
            ok("Una suite de 5 places", e);
        }
        try {
            olivera.setPreuBase(-10);
            alerta("Una suite amb preu negatiu");
        } catch (RuntimeException e) {
            ok("Una suite amb preu negatiu", e);
        }
    }

    private static String euros(double quantitat) {
        return String.format("%.2f €", quantitat);
    }

    private static void ok(String prova, RuntimeException e) {
        System.out.println("[OK] " + prova + " → " + e.getClass().getSimpleName());
    }

    private static void alerta(String prova) {
        System.out.println("[ALERTA] " + prova + ": s'ha permès!");
    }
}
