import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Primera versió, feta pel becari.
 */
public class Reserva {
    public static int comptador = 0;

    public String codi;
    public Client client;
    public Habitacio habitacio;
    public LocalDate entrada;
    public LocalDate sortida;
    public int persones;
    public int nits;
    public double preu;
    public boolean cancelada;
    public ArrayList<String> observacions = new ArrayList<>();

    public Reserva(Client client, Habitacio habitacio, LocalDate entrada, LocalDate sortida, int persones) {
        comptador++;
        this.codi = "R" + comptador;
        this.client = client;
        this.habitacio = habitacio;
        this.entrada = entrada;
        this.sortida = sortida;
        this.persones = persones;
        // ChronoUnit.DAYS.between compta els dies que hi ha entre dues dates
        this.nits = (int) ChronoUnit.DAYS.between(entrada, sortida);
        this.preu = nits * habitacio.preuNit;
        if (habitacio.vistes) {
            this.preu = this.preu + nits * 15;
        }
    }
}
