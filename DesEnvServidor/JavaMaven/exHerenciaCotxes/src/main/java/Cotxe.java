public class Cotxe extends Vehicle{

    public static final double SUPLEMENT_AUTOMATIC_DIA = 5;
    private int places;
    private boolean automatic;

    public Cotxe(String matricula, String marca, String model, double preuDia, int places, boolean automatic) {
        super(matricula, marca, model, preuDia);
        this.places = places;
        this.automatic = automatic;
    }

    @Override
    public double preuLloguer(int dies) {
        double preuFinal = getPreuDia() * dies;

        if (dies >= getDISCOUNT_THRESHOLD()) {
            preuFinal *= getDISCOUNT();
        }

        if (automatic) {
            preuFinal = preuFinal + (SUPLEMENT_AUTOMATIC_DIA * dies);
        }

        return preuFinal;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + places + " places";
    }
}
