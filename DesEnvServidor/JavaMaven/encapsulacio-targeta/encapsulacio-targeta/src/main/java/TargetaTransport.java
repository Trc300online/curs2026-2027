import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Targeta de transport públic.
 * ATENCIÓ: aquesta versió està MAL encapsulada. És el punt de partida de l'activitat 3.
 */
public class TargetaTransport {
    private final String numero;
    private String titular;
    private long saldo;
    long tarifa;
    private List<String> viatges = new ArrayList<>();

    public TargetaTransport(String numero, String titular) {
        this.numero = numero;
        this.titular = titular;
        this.tarifa = 115;
        this.saldo = 0;
    }

    @Override
    public String toString() {
        return "Targeta " + numero + " (" + titular + "): " + (double) saldo/100 + "€, " + viatges.size() + " viatges.";
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return (double) saldo / 100;
    }

    public long getTarifa() {
        return tarifa;
    }

    public void setTarifa(long tarifa) {

        if (tarifa <= 0) {
            throw new IllegalArgumentException("la tarifa a de ser superior a 0");
        }
        this.tarifa = tarifa;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        if (esBuit(titular)) {
            throw new IllegalArgumentException("el nom del titular no pot ser buit");
        }
        this.titular = titular;
    }

    private boolean esBuit(String text) {
        return text.isBlank();
    }

    public List<String> getViatges() {
        return Collections.unmodifiableList(viatges);
    }

    public void recarregar(int quantitat) throws IllegalAccessException {
        if (quantitat > 50 || quantitat < 5) {
            throw new IllegalAccessException("quantitat a recargar invalida");
        }
        if (saldo >= 100) {
            throw new IllegalAccessException("saldo massa elevat per recarregar");
        }
        saldo += quantitat * 100;
    }

    public boolean validarViatge(String linea) {
        if (saldo < tarifa) {
            System.out.println("saldo insuficient");
            return false;
            //throw new IllegalArgumentException("saldo insuficient");
        }

        saldo -= tarifa;
        viatges.add(linea);
        return true;
    }
}
