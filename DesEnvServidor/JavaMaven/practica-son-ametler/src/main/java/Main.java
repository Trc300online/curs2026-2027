import java.time.LocalDate;

/**
 * El programa del becari. Compila i s'executa, però deixa fer coses que no s'haurien de poder fer.
 */
public class Main {
    public static void main(String[] args) {
        Client aina = new Client("43123456A", "Aina Riera", "aina@exemple.cat");
        Habitacio garrover = new Habitacio("H2", "Garrover", 2, 90, true);
        Reserva r = new Reserva(aina, garrover, LocalDate.of(2026, 7, 10), LocalDate.of(2026, 7, 13), 2);
        r.afegirObservacions("Arribarem després de les 22 h");
        System.out.println(r.getCodi() + ": " + r.getClient().getNom() + ", " + r.nits() + " nits, " + r.preu() + " €");

        // Coses que NO haurien de ser possibles:
        // r.sortida = LocalDate.of(2026, 7, 20);   // 1. allargam l'estada... però les nits i el preu no canvien
        // r.persones = 5;                          // 2. cinc persones en una habitació de dues places
        // r.codi = "R999";                         // 3. la reserva canvia de codi
        // Reserva.comptador = 0;                   // 4. la reserva següent tornarà a ser la R1
        // garrover.preuNit = -90;                  // 5. un preu negatiu
        // r.cancelada = true;                      // 6. cancel·lam la reserva...
        // r.cancelada = false;                     //    ...i la «descancel·lam»
        // r.observacions.clear();                  // 7. esborram les observacions des de fora
        System.out.println(r.getCodi() + ": " + r.getClient().getNom() + ", " + r.nits() + " nits, " + r.preu() + " €");
    }
}
/*
* 1- te un acces privat per tant no es pot modificar sense un getter el cual no esta creat i les nits i preu se calculen automaticamet
* 2- te un acces privat, a la creació de la reserva es comprova la capacitat de la habitació i les persones que es volen assignar
* 3- te un acces privat, el codi el genera el sistema automaticament
* 4- te un acces privat, i el sistema nomes incrementa el numero de la reserva si la reserva es valida
* 5- aquest atribut no te un acces directe, i el preu es calcula a preuNit(), donant una exception si el preu es menor o igual a 0
* 6- te un acces privat i no es pot accedir/modificar, nomes es pot cancelar amb el mètode cancelar() i no hi ha forma de revertirlo
* 7- te un acces privat, la llista de observacions que es retorna es una llista inmodificable
* */