package model;

/**
 * Un taller de les jornades. Tot el que té un getter és una «propietat» per a l'Expression Language:
 * ${taller.nom} crida getNom(), ${taller.ple} crida isPle() i ${taller.placesLliures} crida
 * getPlacesLliures(), encara que no existeixi cap camp amb aquest nom.
 * És gairebé un JavaBean, la forma de classe que veurem a la passa 3.
 */
public class Taller {

    private final String id;
    private final String nom;
    private final String ponent;
    private final int places;
    private final int inscrits;
    private final double preu;

    public Taller(String id, String nom, String ponent, int places, int inscrits, double preu) {
        this.id = id;
        this.nom = nom;
        this.ponent = ponent;
        this.places = places;
        this.inscrits = inscrits;
        this.preu = preu;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPonent() {
        return ponent;
    }

    public int getPlaces() {
        return places;
    }

    public int getInscrits() {
        return inscrits;
    }

    public double getPreu() {
        return preu;
    }

    public int getPlacesLliures() {
        return places - inscrits;
    }

    // Els booleans usen «is» en lloc de «get»: ${taller.ple}
    public boolean isPle() {
        return inscrits >= places;
    }
}
