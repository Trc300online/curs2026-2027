import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Factura {

    private String numero;
    private Client client;
    private List<Facturable> facturableList = new ArrayList<>();

    public Factura (String numero, Client client) {
        validarClient(client);
        validarNumero(numero);

        this.client = client;
        this.numero = numero;
    }

    private void validarClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("El client es obligatori");
        }
    }

    private void validarNumero(String numero) {
        if (numero == "" || numero.isEmpty()) {
            throw new IllegalArgumentException("El numero de factura no pot ser buit");
        }
    }

    public void afegir(Facturable linea) {
        if (linea == null) {
            throw new IllegalArgumentException("la linea no pot ser nul·la");
        }
        facturableList.add(linea);
    }

    public List<Facturable> getLinies() {
        return Collections.unmodifiableList(new ArrayList<>(facturableList));
    }

    public double total() {
        double suma = 0;

        for (Facturable linea : facturableList) {
            suma += linea.total();
        }

        return suma;
    }

    public String getNumero() {
        return numero;
    }

    public Client getClient() {
        return client;
    }
}
