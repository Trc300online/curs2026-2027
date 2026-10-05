import java.time.LocalDate;

public class Llibre extends Exemplar {

    private static final int TEMPS_PRESTEC = 21;

    private String autor;

    public Llibre(String codi, String titol, String autor) {
        super(codi, titol);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public LocalDate dataRetorn(LocalDate dataPrestec) {
        return dataPrestec.plusDays(TEMPS_PRESTEC);
    }
}