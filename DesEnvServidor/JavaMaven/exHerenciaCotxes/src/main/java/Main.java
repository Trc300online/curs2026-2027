import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Vehicle> flota= new ArrayList<>();

        Cotxe c1 = new Cotxe("1245qwe", "Seat", "Ibiza", 35, 5, false);
        Cotxe c2 = new Cotxe("2356rty", "Tesla", "Model 3", 80, 5, true);
        flota.add(c1);
        flota.add(c2);
        Moto m1 = new Moto("4578uio", "Kawasaki", "Ninja", 50, 200);
        Moto m2 = new Moto("5689pas", "Honda", "SFX", 15, 49);
        flota.add(m1);
        flota.add(m2);
        Furgoneta f1 = new Furgoneta("1278dfg", "Ford", "Transit", 55, 350);
        Furgoneta f2 = new Furgoneta("2389hjk", "Fiat", "Ducato", 45, 290);
        flota.add(f1);
        flota.add(f2);

        double preuTotal = 0;

        System.out.println("           Vehicle         /       3 dies      /       7 dies");
        for (int x = 0; x < flota.size(); x++) {
            System.out.println(flota.get(x) + "     /      " + flota.get(x).preuLloguer(3) + "€" + "    /      " + flota.get(x).preuLloguer(7) + "€");

            preuTotal += flota.get(x).preuLloguer(7);
        }
        System.out.println();
        System.out.println("El preu de llogar tota la flota durant 7 dies es " + preuTotal);

        //Vehicle v = new Moto("a", "a", "a", 1, 1);
        //v.potConduirAmbCarnetB();
    }
}
