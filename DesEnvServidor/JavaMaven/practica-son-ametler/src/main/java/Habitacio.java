/**
 * Primera versió, feta pel becari. De moment, Son Ametler només lloga habitacions.
 */
public class Habitacio extends Allotjament{

    private double preuBase;
    private final boolean vistes;

    private static final double SUPLEMENT_VISTES = 15.00;

    public Habitacio(String codi, String nom, int places, double preuBase, boolean vistes) {
        super(codi, nom, places);

        validarPlaces(places);
        validarPreuBase(preuBase);

        this.preuBase = preuBase;
        this.vistes = vistes;

    }



    private void validarPlaces(int places) {
        if (places > 4) {
            throw new IllegalArgumentException("Les habitacions han tenen un maxim de 4 places");
        }
    }

    private void validarPreuBase(double preuBase) {
        if (preuBase <= 0) {
            throw new IllegalArgumentException("El preu base ha de ser superior a 0");
        }
    }




    public void setPreuBase(double preuBase) {
        validarPreuBase(preuBase);
        this.preuBase = preuBase;
    }

    @Override
    public double preuNit() {
        if (teVistes()) {
            return preuBase + SUPLEMENT_VISTES;
        }
        return preuBase;
    }

    public boolean teVistes() {
        return vistes;
    }


    public String toString() {
        String msg = getCodi() + " " + getNom() + " · " + getPlaces() + " places";

        if (teVistes()) {
            msg += " · amb vistes";
        }

        return msg;
    }
}
