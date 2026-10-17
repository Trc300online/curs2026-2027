/**
 * Primera versió, feta pel becari. De moment, Son Ametler només lloga habitacions.
 */
public class Habitacio {
    private final String codi;
    private final String nom;
    private final int places;
    private double preuBase;
    private final boolean vistes;

    private static final double SUPLEMENT_VISTES = 15.00;

    public Habitacio(String codi, String nom, int places, double preuBase, boolean vistes) {
        validarCodi(codi);
        validarNom(nom);
        validarPlaces(places);
        validarPreuBase(preuBase);

        this.codi = codi;
        this.nom = nom;
        this.places = places;
        this.preuBase = preuBase;
        this.vistes = vistes;

    }


    private void validarCodi(String codi) {
        if (codi == null || codi.isBlank()) {
            throw new IllegalArgumentException("El codi és obligatori");
        }
    }

    private void validarNom(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("El nom és obligatori");
        }
    }

    private void validarPlaces(int places) {
        if (places < 1 || places > 4) {
            throw new IllegalArgumentException("Les places han d'estar entre 1 i 4");
        }
    }

    private void validarPreuBase(double preuBase) {
        if (preuBase <= 0) {
            throw new IllegalArgumentException("El preu base ha de ser superior a 0");
        }
    }



    public String getCodi() {
        return codi;
    }

    public String getNom() {
        return nom;
    }

    public void setPreuBase(double preuBase) {
        validarPreuBase(preuBase);
        this.preuBase = preuBase;
    }

    public double preuNit() {
        if (teVistes()) {
            return preuBase + SUPLEMENT_VISTES;
        }
        return preuBase;
    }

    public boolean teVistes() {
        return vistes;
    }

    public int getPlaces() {
        return places;
    }

    public String toString() {
        String msg = codi + " " + nom + " · " + places + " places";

        if (teVistes()) {
            msg += " · amb vistes";
        }

        return msg;
    }
}
