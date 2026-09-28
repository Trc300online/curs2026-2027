public class Main {
    public static void main(String[] args) {
        TargetaTransport t = new TargetaTransport("T-0001", "Aina Riera");

        // Recàrrega de 10 €
        t.saldo = t.saldo + 10;

        // Validam tres viatges
        for (int i = 1; i <= 3; i++) {
            if (t.saldo >= t.tarifa) {
                t.saldo = t.saldo - t.tarifa;
                t.viatges.add("Línia " + i);
            }
        }
        System.out.println(t.titular + " té " + t.saldo + " € i " + t.viatges.size() + " viatges");

        // Coses que NO haurien de ser possibles:
        t.saldo = -50;          // saldo negatiu
        t.tarifa = 0;           // viatges gratis per sempre
        t.numero = "T-9999";    // la targeta canvia de número
        t.titular = "";         // titular buit
        t.viatges.clear();      // esborram l'historial de viatges
        System.out.println(t.titular + " té " + t.saldo + " € i " + t.viatges.size() + " viatges");
    }
}
