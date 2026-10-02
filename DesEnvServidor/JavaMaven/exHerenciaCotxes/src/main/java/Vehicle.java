public class Vehicle {
    private String matricula;
    private String marca;
    private String model;
    private double preuDia;
    private final int DISCOUNT_THRESHOLD = 7;
    private final double DISCOUNT = 0.9;

    public Vehicle(String matricula, String marca, String model, double preuDia) {
        this.matricula = matricula;
        this.marca = marca;
        this.model = model;
        this.preuDia = preuDia;
    }

    public double preuLloguer(int dies) {
        double preu = preuDia * dies;

        if (dies >= DISCOUNT_THRESHOLD) {
            preu = preu * DISCOUNT;
        }

        return preu;
    }

    public String toString() {
        return getClass() + " " + marca + " " + model + " [" + matricula + "] " + preuDia + " €/dia";
    }
}
