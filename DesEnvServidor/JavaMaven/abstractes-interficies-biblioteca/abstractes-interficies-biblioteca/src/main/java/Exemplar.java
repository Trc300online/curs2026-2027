import java.time.LocalDate;

public abstract class Exemplar {

    private String codi;
    private String titol;

    public Exemplar(String codi, String titol) {
        this.codi = codi;
        this.titol = titol;
    }

    public String getCodi() {
        return codi;
    }

    public String getTitol() {
        return titol;
    }

    public abstract LocalDate dataRetorn(LocalDate dataPrestec);

    @Override
    public String toString() {
        return titol + " [" + codi + "]";
    }
}