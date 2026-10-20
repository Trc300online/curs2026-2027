import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Primera versió, feta pel becari.
 */
public class Reserva implements Facturable{
    private static int comptador = 0;

    private final String codi;
    private final Client client;
    private final Allotjament habitacio;
    private final LocalDate entrada;
    private final LocalDate sortida;
    private final int persones;

    private boolean cancelada;

    private ArrayList<String> observacions = new ArrayList<>();

    public Reserva(Client client, Allotjament habitacio, LocalDate entrada, LocalDate sortida, int persones) {

        validarClient(client);
        validarHabitacio(habitacio);
        validarDates(entrada, sortida);
        validarPersones(persones, habitacio);

        comptador++;
        this.codi = String.format("R%03d", comptador);
        this.client = client;
        this.habitacio = habitacio;
        this.entrada = entrada;
        this.sortida = sortida;
        this.persones = persones;
    }


    private void validarClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("El client és obligatori");
        }
    }

    private void validarHabitacio(Allotjament habitacio) {
        if (habitacio == null) {
            throw new IllegalArgumentException("L'habitació és obligatòria");
        }
    }

    private void validarDates(LocalDate entrada, LocalDate sortida) {

        if (entrada == null || sortida == null) {
            throw new IllegalArgumentException("Les dates són obligatòries");
        }

        if (!sortida.isAfter(entrada)) {
            throw new IllegalArgumentException("La sortida ha de ser posterior a l'entrada");
        }
    }

    private void validarPersones(int persones, Allotjament habitacio) {

        if (persones < 1 || persones > habitacio.getPlaces()) {
            throw new IllegalArgumentException("El nombre de persones no és vàlid");
        }
    }

    private void validarObservacio(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("L'observació no pot ser buida");
        }
    }


    public String getCodi() {
        return codi;
    }

    public Client getClient() {
        return client;
    }

    public long nits() {
        return ChronoUnit.DAYS.between(entrada, sortida);
    }

    public double preu() {
        return habitacio.preuEstada((int) nits());
    }

    public boolean estaCancelada() {
        return cancelada;
    }

    public void cancelar() {
        if (!estaCancelada()) {
            cancelada = true;
        } else {
            throw new IllegalStateException("la reserva ja ha estat cancelada");
        }
    }

    public void afegirObservacions(String text) {
        validarObservacio(text);
        observacions.add(text);
    }

    public List<String> getObservacions() {
        return Collections.unmodifiableList(new ArrayList<>(observacions));
    }

    public String toString() {
        String msg = codi + " · " + client.getNom() + " · " + habitacio.getCodi() + " · " + entrada + " -> " + sortida;

        if (estaCancelada()) {
            msg += " · cancel·lada";
        }

        return msg;
    }

    @Override
    public String concepte() {
        return "Estada a " + habitacio.getNom() + " (" + nits() + " nits)";
    }

    @Override
    public double baseImposable() {
        return preu();
    }

    @Override
    public int percentatgeIva() {
        return Facturable.IVA_REDUIT;
    }
}
