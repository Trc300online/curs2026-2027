public class Projector implements Inventariable {

    private String aula;
    private int valorReposicio;

    public Projector(String aula, int valorReposicio) {
        this.aula = aula;
        this.valorReposicio = valorReposicio;
    }

    public String getAula() {
        return aula;
    }

    @Override
    public int getValorReposicio() {
        return valorReposicio;
    }

    @Override
    public boolean esDeValor() {
        return valorReposicio >= 1000;
    }

    @Override
    public String descripcio() {
        return "Projector de l'aula " + aula;
    }
}