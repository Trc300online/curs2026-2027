import java.time.LocalDate;
import java.util.List;

/**
 * Prova de la part 4 (classes abstractes). NO l'heu de modificar.
 *
 * Copiau-la a src/main/java quan comenceu la part 4. No compilarà fins que no hàgiu creat
 * Allotjament i Casa.
 */
public class ProvaPart4 {
    public static void main(String[] args) {
        Client aina = new Client("43123456A", "Aina Riera", "aina@exemple.cat");
        Client carme = new Client("78123456C", "Carme Vidal", "carme@exemple.cat");
        Casa pou = new Casa("C2", "Es Pou Vell", 6, true);
        List<Allotjament> allotjaments = List.of(
                new Habitacio("H1", "Ametler", 2, 90, false),
                new Suite("S1", "Olivera", 3, 100, true, 45),
                new Casa("C1", "Sa Caseta", 4, false),
                pou);

        System.out.println("── Tots els allotjaments ──");
        for (Allotjament a : allotjaments) {
            System.out.println(a + ": " + euros(a.preuNit()) + " per nit");
        }

        System.out.println();
        System.out.println("── Una setmana a cada allotjament ──");
        for (Allotjament a : allotjaments) {
            System.out.println(a.getCodi() + " " + a.getNom() + ": " + euros(a.preuEstada(7)));
        }

        System.out.println();
        System.out.println("── Una reserva a una casa ──");
        Reserva r = new Reserva(carme, pou, LocalDate.of(2026, 8, 3), LocalDate.of(2026, 8, 7), 5);
        System.out.println(r + " · " + r.nits() + " nits · " + euros(r.preu()));

        System.out.println();
        System.out.println("── Coses que no s'han de poder fer ──");
        try {
            new Reserva(aina, pou, LocalDate.of(2026, 9, 1), LocalDate.of(2026, 9, 5), 7);
            alerta("7 persones en una casa de 6 places");
        } catch (RuntimeException e) {
            ok("7 persones en una casa de 6 places", e);
        }
        try {
            new Casa("C9", "Sa Sínia", 0, false);
            alerta("Una casa sense places");
        } catch (RuntimeException e) {
            ok("Una casa sense places", e);
        }
        try {
            new Casa("C9", "", 4, false);
            alerta("Una casa sense nom");
        } catch (RuntimeException e) {
            ok("Una casa sense nom", e);
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
