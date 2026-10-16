/**
 * Primera versió, feta pel becari. De moment, Son Ametler només lloga habitacions.
 */
public class Habitacio {
    private final String codi;
    private final String nom;
    private int places;
    private double preuBase;
    private double preuNit;
    private final boolean vistes;
    private final double SUPLEMENT_VISITES = 15.00;

    public Habitacio(String codi, String nom, int places, double preuBase, boolean vistes) {
        this.codi = codi;
        this.nom = nom;
        if (places < 5  && places > 0) {
            this.places = places;
        }
        this.preuBase = preuBase;
        this.vistes = vistes;
    }

    public void setPreuBase(double preuBase) {
        if (preuBase > 0) {
            this.preuBase = preuBase;
        }
    }

    public void setPreuNit(double preuNit) {
        if (teVisites()) {
            this.preuNit = preuNit + SUPLEMENT_VISITES;
        }
    }

    public boolean teVisites() {
        return vistes;
    }

    public int getPlaces() {
        return places;
    }

    public String toString() {
        String msg = codi + " " + nom + " · " + places + " places";

        if (teVisites()) {
            msg += " · amb visites";
        }

        return msg;
    }
}
