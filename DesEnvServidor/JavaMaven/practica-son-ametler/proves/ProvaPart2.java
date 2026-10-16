import java.time.LocalDate;

/**
 * Prova de la part 2 (encapsulació). NO l'heu de modificar.
 *
 * Copiau-la a src/main/java quan comenceu la part 2. No compilarà fins que Habitacio i Reserva no tenguin
 * els mètodes que fa servir. Ha de continuar funcionant igual fins al final de la pràctica.
 *
 * Cada bloc try intenta una cosa que no hauria de ser possible. Si les classes estan ben encapsulades,
 * salta una excepció i s'escriu [OK]. Si no, s'escriu [ALERTA].
 */
public class ProvaPart2 {
    public static void main(String[] args) {
        Client aina = new Client("43123456A", "Aina Riera", "aina@exemple.cat");
        Client biel = new Client("41987654B", "Biel Mas", "biel@exemple.cat");
        Habitacio ametler = new Habitacio("H1", "Ametler", 2, 90, false);
        Habitacio garrover = new Habitacio("H2", "Garrover", 2, 90, true);

        System.out.println("── Habitacions ──");
        System.out.println(ametler + ": " + euros(ametler.preuNit()) + " per nit");
        System.out.println(garrover + ": " + euros(garrover.preuNit()) + " per nit");

        System.out.println();
        System.out.println("── Reserves ──");
        Reserva r1 = new Reserva(aina, garrover, LocalDate.of(2026, 7, 10), LocalDate.of(2026, 7, 13), 2);
        Reserva r2 = new Reserva(biel, ametler, LocalDate.of(2026, 8, 1), LocalDate.of(2026, 8, 8), 1);
        r1.afegirObservacio("Arribarem després de les 22 h");
        r2.cancelar();
        mostrar(r1);
        mostrar(r2);
        System.out.println("Observacions de " + r1.getCodi() + ": " + r1.getObservacions());

        System.out.println();
        System.out.println("── Canvi de preu ──");
        ametler.setPreuBase(95);
        System.out.println(ametler.getCodi() + " ara val " + euros(ametler.preuNit()) + " per nit");

        System.out.println();
        System.out.println("── Coses que no s'han de poder fer ──");
        try {
            new Habitacio("H9", "Murta", 6, 90, false);
            alerta("Una habitació de 6 places");
        } catch (RuntimeException e) {
            ok("Una habitació de 6 places", e);
        }
        try {
            garrover.setPreuBase(0);
            alerta("Un preu de 0 €");
        } catch (RuntimeException e) {
            ok("Un preu de 0 €", e);
        }
        try {
            new Reserva(aina, ametler, LocalDate.of(2026, 9, 10), LocalDate.of(2026, 9, 10), 2);
            alerta("Una reserva de 0 nits");
        } catch (RuntimeException e) {
            ok("Una reserva de 0 nits", e);
        }
        try {
            new Reserva(biel, garrover, LocalDate.of(2026, 9, 1), LocalDate.of(2026, 9, 3), 3);
            alerta("3 persones en una habitació de 2 places");
        } catch (RuntimeException e) {
            ok("3 persones en una habitació de 2 places", e);
        }
        try {
            new Reserva(null, garrover, LocalDate.of(2026, 9, 1), LocalDate.of(2026, 9, 3), 1);
            alerta("Una reserva sense client");
        } catch (RuntimeException e) {
            ok("Una reserva sense client", e);
        }
        try {
            r2.cancelar();
            alerta("Cancel·lar dues vegades");
        } catch (RuntimeException e) {
            ok("Cancel·lar dues vegades", e);
        }
        try {
            r1.afegirObservacio("");
            alerta("Una observació buida");
        } catch (RuntimeException e) {
            ok("Una observació buida", e);
        }
        try {
            r1.getObservacions().clear();
            alerta("Esborrar les observacions des de fora");
        } catch (RuntimeException e) {
            ok("Esborrar les observacions des de fora", e);
        }

        System.out.println();
        System.out.println("── Després dels errors ──");
        Reserva r3 = new Reserva(biel, garrover, LocalDate.of(2026, 10, 2), LocalDate.of(2026, 10, 4), 2);
        System.out.println("La reserva nova és la " + r3.getCodi());
    }

    private static void mostrar(Reserva r) {
        System.out.println(r + " · " + r.nits() + " nits · " + euros(r.preu()));
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
