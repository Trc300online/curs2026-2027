import java.time.LocalDate;
import java.util.List;

/**
 * Prova de la part 6 (genericitat). NO l'heu de modificar.
 *
 * Copiau-la a src/main/java quan comenceu la part 6. No compilarà fins que no hàgiu creat
 * Identificable, Registre i Informes.
 */
public class ProvaPart6 {
    public static void main(String[] args) {
        Client aina = new Client("43123456A", "Aina Riera", "aina@exemple.cat");
        Client biel = new Client("41987654B", "Biel Mas", "biel@exemple.cat");
        Client carme = new Client("78123456C", "Carme Vidal", "carme@exemple.cat");

        Registre<Allotjament> allotjaments = new Registre<>();
        allotjaments.afegir(new Habitacio("H2", "Garrover", 2, 90, true));
        allotjaments.afegir(new Suite("S1", "Olivera", 3, 100, true, 45));
        allotjaments.afegir(new Casa("C2", "Es Pou Vell", 6, true));

        Registre<Reserva> reserves = new Registre<>();
        reserves.afegir(new Reserva(aina, allotjaments.cercar("H2"), LocalDate.of(2026, 7, 10), LocalDate.of(2026, 7, 13), 2));
        reserves.afegir(new Reserva(biel, allotjaments.cercar("S1"), LocalDate.of(2026, 7, 20), LocalDate.of(2026, 7, 24), 3));
        reserves.afegir(new Reserva(carme, allotjaments.cercar("C2"), LocalDate.of(2026, 8, 3), LocalDate.of(2026, 8, 7), 5));

        List<Activitat> activitats = List.of(
                new Activitat("Excursió a cavall", 35, 4),
                new Activitat("Classe de cuina mallorquina", 45, 2));

        System.out.println("── Registres ──");
        System.out.println(allotjaments.quants() + " allotjaments i " + reserves.quants() + " reserves");
        for (Reserva r : reserves.tots()) {
            System.out.println(r);
        }

        System.out.println();
        System.out.println("── Cercar ──");
        Allotjament pou = allotjaments.cercar("C2");
        Reserva segona = reserves.cercar("R002");
        System.out.println(pou);
        System.out.println(segona + " · " + euros(segona.preu()));
        System.out.println("X9: " + (allotjaments.cercar("X9") == null ? "no existeix" : "existeix"));

        System.out.println();
        System.out.println("── Informes ──");
        System.out.println("Total de les reserves: " + euros(Informes.totalFacturat(reserves.tots())));
        System.out.println("Total de les activitats: " + euros(Informes.totalFacturat(activitats)));
        Reserva mesCara = Informes.mesCar(reserves.tots());
        Activitat activitatMesCara = Informes.mesCar(activitats);
        System.out.println("Reserva més cara: " + mesCara.getCodi() + " de " + mesCara.getClient().getNom());
        System.out.println("Activitat més cara: " + activitatMesCara.getNom());

        System.out.println();
        System.out.println("── Coses que no s'han de poder fer ──");
        try {
            allotjaments.afegir(new Habitacio("H2", "Garrover (repetida)", 2, 90, false));
            alerta("Dos allotjaments amb el codi H2");
        } catch (RuntimeException e) {
            ok("Dos allotjaments amb el codi H2", e);
        }
        try {
            reserves.tots().clear();
            alerta("Buidar un registre des de fora");
        } catch (RuntimeException e) {
            ok("Buidar un registre des de fora", e);
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
