public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        TargetaTransport t = new TargetaTransport("T-0001", "Aina Riera");

        // Recàrrega de 10 €
        //t.saldo = t.saldo + 10;
        t.recarregar(10);

        // Validam tres viatges
        for (int i = 1; i <= 3; i++) {
            String linea = String.valueOf(i);
            t.validarViatge(linea);
            /*if (t.saldo >= t.tarifa) {
                t.saldo = t.saldo - t.tarifa;
                t.viatges.add("Línia " + i);
            }*/
        }
        System.out.println(t.toString());
        //System.out.println(t.titular + " té " + t.saldo + " € i " + t.viatges.size() + " viatges");
        System.out.println(t.getTitular() + " té " + t.getSaldo() + " € i " + t.getViatges().size() + " viatges");

        // Coses que NO haurien de ser possibles:
        t.recarregar(-50);  //t.saldo = -50;        // saldo negatiu
        t.setTarifa(0);             //t.tarifa = 0;         // viatges gratis per sempre
        // No existeix un metode per modificar el numero    //t.numero = "T-9999";    // la targeta canvia de número
        t.setTitular("");           //t.titular = "";         // titular buit
        // No existeix un metode per modificar els viatges  //t.viatges.clear();      // esborram l'historial de viatges
        //System.out.println(t.titular + " té " + t.saldo + " € i " + t.viatges.size() + " viatges");
        System.out.println(t.getTitular() + " té " + t.getSaldo() + " € i " + t.getViatges().size() + " viatges");
    }
}
