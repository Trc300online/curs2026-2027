import java.time.LocalDate;

/**
 * Prova de la part 5 (interfícies). NO l'heu de modificar.
 *
 * Copiau-la a src/main/java quan comenceu la part 5. No compilarà fins que no hàgiu creat
 * Facturable, Activitat i Factura.
 */
public class ProvaPart5 {
    public static void main(String[] args) {
        Client carme = new Client("78123456C", "Carme Vidal", "carme@exemple.cat");
        Casa pou = new Casa("C2", "Es Pou Vell", 6, true);
        Reserva estada = new Reserva(carme, pou, LocalDate.of(2026, 8, 3), LocalDate.of(2026, 8, 7), 5);

        Factura factura = new Factura("F-0815", carme);
        factura.afegir(estada);
        factura.afegir(new Activitat("Excursió a cavall", 35, 4));
        factura.afegir(new Activitat("Classe de cuina mallorquina", 45, 2));

        System.out.println("── Factura " + factura.getNumero() + " de " + factura.getClient().getNom() + " ──");
        for (Facturable linia : factura.getLinies()) {
            System.out.println(linia.concepte() + ": " + euros(linia.baseImposable()) + " + IVA "
                    + linia.percentatgeIva() + " % = " + euros(linia.total()));
        }
        System.out.println("Total: " + euros(factura.total()));
        System.out.println("IVA reduït: " + Facturable.IVA_REDUIT + " % · IVA general: " + Facturable.IVA_GENERAL + " %");

        System.out.println();
        System.out.println("── Coses que no s'han de poder fer ──");
        try {
            new Activitat("Tast d'oli", 20, 0);
            alerta("Una activitat sense persones");
        } catch (RuntimeException e) {
            ok("Una activitat sense persones", e);
        }
        try {
            new Activitat("Tast d'oli", -20, 2);
            alerta("Una activitat amb preu negatiu");
        } catch (RuntimeException e) {
            ok("Una activitat amb preu negatiu", e);
        }
        try {
            factura.afegir(null);
            alerta("Afegir null a la factura");
        } catch (RuntimeException e) {
            ok("Afegir null a la factura", e);
        }
        try {
            factura.getLinies().clear();
            alerta("Esborrar les línies de la factura des de fora");
        } catch (RuntimeException e) {
            ok("Esborrar les línies de la factura des de fora", e);
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
