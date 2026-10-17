public class Suite extends Habitacio{

    private int metres2;
    private static final double AUGMENT_PREU_BASE = 1.25;

    public Suite(String codi, String nom, int places, double preuBase, boolean vistes, int metres2) {
        super(codi, nom, places, preuBase, vistes);

        if (metres2 <= 0) {
            throw new IllegalArgumentException("La suite ha de tenir una superficia major a 0 m2");
        }

        this.metres2 = metres2;
    }

    @Override
    public double preuNit() {
        return super.preuNit() * AUGMENT_PREU_BASE;
    }

    public String toString() {
        String msg = getCodi() + " " + getNom() + " · " + getPlaces() + " places · ";

        if (teVistes()) {
            msg += "amb vistes · ";
        }

        msg += "suite de " + metres2 + " m2" ;

        return msg;
    }


}
