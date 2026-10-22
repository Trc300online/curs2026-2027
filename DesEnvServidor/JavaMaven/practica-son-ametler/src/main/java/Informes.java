import java.util.List;

public class Informes {

    public static double totalFacturat(List<? extends Facturable> llista) {

        double suma = 0;

        for (Facturable linia : llista) {
            suma += linia.total();
        }

        return suma;
    }

    public static <T extends Facturable> T mesCar(List<T> llista) {
        if (llista.isEmpty()) {
            return null;
        }

        T mesCar = llista.get(0);

        for (T linia : llista) {
            if (linia.total() > mesCar.total()) {
                mesCar = linia;
            }
        }

        return mesCar;
    }
}
