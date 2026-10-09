/** Una etapa de la Ruta de Pedra en Sec. L'identificador és un text: «E1», «E2»... */
public class Etapa implements Identificable<String> {
    private final String id;
    private final String nom;
    private final double km;
    private final int desnivell;

    public Etapa(String id, String nom, double km, int desnivell) {
        this.id = id;
        this.nom = nom;
        this.km = km;
        this.desnivell = desnivell;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getKm() {
        return km;
    }

    public int getDesnivell() {
        return desnivell;
    }

    @Override
    public String toString() {
        return id + " " + nom + " (" + km + " km, +" + desnivell + " m)";
    }
}
