public abstract class Allotjament {

    private final String codi;
    private final String nom;
    private final int places;


    protected Allotjament(String codi, String nom, int places) {
        validarCodi(codi);
        validarNom(nom);

        if (places < 1) {
            throw new IllegalArgumentException("les places han de ser com a minim 1");
        }

        this.codi = codi;
        this.nom = nom;
        this.places = places;
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



    public String getCodi() {
        return codi;
    }

    public String getNom() {
        return nom;
    }

    public int getPlaces() {
        return places;
    }


    public abstract double preuNit();

    public double preuEstada(int nits) {
        if (nits < 1) {
            throw new IllegalArgumentException("les nits han de ser com a minim 1");
        }

        return nits * preuNit();
    }
}
