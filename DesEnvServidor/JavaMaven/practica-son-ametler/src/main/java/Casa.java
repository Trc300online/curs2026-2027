public class Casa extends Allotjament{

    private static final double PREU_PLAÇA_NIT = 30;
    private static final double SUPLEMENT_PISCINA = 40;
    private static final double NETEJA = 60;
    private final boolean piscina;

    public Casa(String codi, String nom, int places, boolean piscina) {
        super(codi, nom, places);

        if (places > 6) {
            throw new IllegalArgumentException("Les cases poden tenir un màxim de 6 places");
        }

        this.piscina = piscina;
    }

    @Override
    public double preuNit() {
        double preu = getPlaces() * PREU_PLAÇA_NIT;

        if (piscina) {
            preu += SUPLEMENT_PISCINA;
        }

        return preu;
    }

    @Override
    public double preuEstada(int nits) {
        return super.preuEstada(nits) + NETEJA;
    }

    public String toString() {
        String msg = getCodi() + " " + getNom() + " · " + getPlaces() + " places";

        if (piscina) {
            msg += " · piscina privada";
        }

        return msg;
    }
}
