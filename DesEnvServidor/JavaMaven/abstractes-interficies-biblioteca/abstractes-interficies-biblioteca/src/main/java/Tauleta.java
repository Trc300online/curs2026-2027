import java.time.LocalDate;

public class Tauleta extends Exemplar implements Inventariable {

    private static final int TEMPS_PRESTEC = 3;

    private int valorReposicio;

    public Tauleta(String codi, String titol, int valorReposicio) {
        super(codi, titol);
        this.valorReposicio = valorReposicio;
    }

    @Override
    public LocalDate dataRetorn(LocalDate dataPrestec) {
        return dataPrestec.plusDays(TEMPS_PRESTEC);
    }

    @Override
    public int getValorReposicio() {
        return valorReposicio;
    }

    @Override
    public boolean esDeValor() {
        return valorReposicio >= LLINDAR_VALOR;
    }

    @Override
    public String descripcio() {
        return toString();
    }
}