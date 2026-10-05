import java.time.LocalDate;

public class Revista extends Exemplar {

    private static final int TEMPS_PRESTEC = 7;

    private int numero;

    public Revista(String codi, String titol, int numero) {
        super(codi, titol);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public LocalDate dataRetorn(LocalDate dataPrestec) {
        return dataPrestec.plusDays(TEMPS_PRESTEC);
    }
}