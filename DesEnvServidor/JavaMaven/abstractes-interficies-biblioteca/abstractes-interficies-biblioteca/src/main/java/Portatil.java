import java.time.LocalDate;

public class Portatil extends Exemplar implements Inventariable {

    private static final int TEMPS_PRESTEC = 1;

    private int valorReposicio;

    public Portatil(String codi, String titol, int valorReposicio) {
        super(codi, titol);
        this.valorReposicio = valorReposicio;
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
    public LocalDate dataRetorn(LocalDate dataPrestec) {
        return dataPrestec.plusDays(TEMPS_PRESTEC);
    }

    @Override
    public String descripcio() {
        return toString();
    }
}