/** Un refugi de la ruta. L'identificador és el número de registre: 201, 202... */
public class Refugi implements Identificable<Integer> {
    private final int numero;
    private final String nom;
    private final int places;

    public Refugi(int numero, String nom, int places) {
        this.numero = numero;
        this.nom = nom;
        this.places = places;
    }

    @Override
    public Integer getId() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public int getPlaces() {
        return places;
    }

    @Override
    public String toString() {
        return numero + " Refugi de " + nom + " (" + places + " places)";
    }
}
