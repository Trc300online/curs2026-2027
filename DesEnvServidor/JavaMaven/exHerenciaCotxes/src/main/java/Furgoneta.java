public class Furgoneta extends Vehicle{

    public static final double SUPLEMENT_NETEJA = 15;
    private int capacitatKg;

    public Furgoneta(String matricula, String marca, String model, double preuDia, int capacitatKg) {
        super(matricula, marca, model, preuDia);
        this.capacitatKg = capacitatKg;
    }

    @Override
    public double preuLloguer(int dies) {
        double preuFinal = getPreuDia() * dies;

        if (dies >= getDISCOUNT_THRESHOLD()) {
            preuFinal *= getDISCOUNT();
        }

        return preuFinal + SUPLEMENT_NETEJA;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + capacitatKg + " Kg de capacitat de carrega";
    }
}
