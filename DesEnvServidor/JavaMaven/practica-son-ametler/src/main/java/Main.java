import java.time.LocalDate;

/**
 * El programa del becari. Compila i s'executa, però deixa fer coses que no s'haurien de poder fer.
 */
public class Main {
    public static void main(String[] args) {
        Client aina = new Client("43123456A", "Aina Riera", "aina@exemple.cat");
        Habitacio garrover = new Habitacio("H2", "Garrover", 2, 90, true);
        Reserva r = new Reserva(aina, garrover, LocalDate.of(2026, 7, 10), LocalDate.of(2026, 7, 13), 2);
        r.observacions.add("Arribarem després de les 22 h");
        System.out.println(r.codi + ": " + r.client.getNom() + ", " + r.nits + " nits, " + r.preu + " €");

        // Coses que NO haurien de ser possibles:
        r.sortida = LocalDate.of(2026, 7, 20);   // 1. allargam l'estada... però les nits i el preu no canvien
        r.persones = 5;                          // 2. cinc persones en una habitació de dues places
        r.codi = "R999";                         // 3. la reserva canvia de codi
        Reserva.comptador = 0;                   // 4. la reserva següent tornarà a ser la R1
        garrover.preuNit = -90;                  // 5. un preu negatiu
        r.cancelada = true;                      // 6. cancel·lam la reserva...
        r.cancelada = false;                     //    ...i la «descancel·lam»
        r.observacions.clear();                  // 7. esborram les observacions des de fora
        System.out.println(r.codi + ": " + r.client.getNom() + ", " + r.nits + " nits, " + r.preu + " €");
    }
}
