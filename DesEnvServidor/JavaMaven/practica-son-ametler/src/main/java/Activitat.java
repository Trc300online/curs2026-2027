public class Activitat implements Facturable{

    private final String nom;
    private final double preuPersona;
    private final int persones;

    public Activitat(String nom, double preuPersona, int persones) {
        validarNom(nom);
        validarPreuPersona(preuPersona);
        validarPresones(persones);

        this.nom = nom;
        this.preuPersona = preuPersona;
        this.persones = persones;
    }

    private void validarNom(String nom) {
        if (nom.isEmpty() || nom == "") {
            throw new IllegalArgumentException("El nom de la activitat no pot ser buit");
        }
    }

    private void validarPreuPersona(double preuPersona) {
        if (preuPersona <= 0) {
            throw new IllegalArgumentException("El preu per persona ha de ser superior a 0");
        }
    }

    private void validarPresones(int persones) {
        if (persones <= 0) {
            throw new IllegalArgumentException("Hi ha de haver com a minim 1 persona");
        }
    }

    @Override
    public String concepte() {
        return nom + " (" + persones + " pers,)";
    }

    @Override
    public double baseImposable() {
        return preuPersona * persones;
    }

    @Override
    public int percentatgeIva() {
        return Facturable.IVA_GENERAL;
    }

    public String getNom() {
        return nom;
    }
}
