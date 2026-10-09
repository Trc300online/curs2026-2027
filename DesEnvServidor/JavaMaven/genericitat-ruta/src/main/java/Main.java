/**
 * NO l'heu de modificar.
 * No compilarà fins que no hàgiu creat la classe genèrica Repositori.
 */
public class Main {
    public static void main(String[] args) {
        Repositori<Etapa, String> etapes = new Repositori<>();
        etapes.guardar(new Etapa("E1", "Valldemossa → Deià", 11.2, 510));
        etapes.guardar(new Etapa("E2", "Deià → Port de Sóller", 10.3, 320));
        etapes.guardar(new Etapa("E3", "Port de Sóller → Tossals Verds", 18.6, 1110));
        etapes.guardar(new Etapa("E4", "Tossals Verds → Lluc", 14.2, 820));
        etapes.guardar(new Etapa("E5", "Lluc → Pollença", 17.8, 290));

        Repositori<Refugi, Integer> refugis = new Repositori<>();
        refugis.guardar(new Refugi(201, "Can Boi", 32));
        refugis.guardar(new Refugi(202, "Muleta", 40));
        refugis.guardar(new Refugi(203, "Tossals Verds", 44));
        refugis.guardar(new Refugi(204, "Son Amer", 52));
        refugis.guardar(new Refugi(205, "Pont Romà", 38));

        System.out.println("── Identificadors ──");
        System.out.println("Etapes: " + etapes.ids());
        System.out.println("Refugis: " + refugis.ids());

        System.out.println();
        System.out.println("── Cercar ──");
        Etapa etapa = etapes.cercar("E3");
        Refugi refugi = refugis.cercar(203);
        System.out.println(etapa);
        System.out.println(refugi);
        System.out.println("E9: " + (etapes.cercar("E9") == null ? "no existeix" : "existeix"));

        System.out.println();
        System.out.println("── Etapes de més de 15 km ──");
        for (Etapa e : etapes.tots()) {
            if (e.getKm() > 15) {
                System.out.println(e.getNom());
            }
        }

        System.out.println();
        System.out.println("── Places als refugis ──");
        int places = 0;
        for (Refugi r : refugis.tots()) {
            places += r.getPlaces();
        }
        System.out.println(refugis.quants() + " refugis, " + places + " places");

        System.out.println();
        System.out.println("── Duplicats ──");
        try {
            refugis.guardar(new Refugi(202, "Muleta (repetit)", 10));
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println(etapes.quants() + " etapes i " + refugis.quants() + " refugis");
    }
}
