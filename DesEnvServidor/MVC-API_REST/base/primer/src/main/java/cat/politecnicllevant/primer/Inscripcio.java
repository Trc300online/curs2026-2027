package cat.politecnicllevant.primer;

public class Inscripcio {

    private String nom;
    private String estudis;
    private int edat;

    public Inscripcio(String nom, int edat, String estudis) {
        this.estudis = estudis;
        this.nom = nom;
        this.edat = edat;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public String getEstudis() {
        return estudis;
    }

    public void setEstudis(String estudis) {
        this.estudis = estudis;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

