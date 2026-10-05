import java.util.Date;

public class Llibre extends Exemplar{

    private final int TEMPS_PRESTEC  = 21;
    private String autor;
    private Date dataRetorn;
    private Date dataPrestec;

    public Llibre(String codi, String titol, String autor) {
        super(codi, titol);
        this.autor = autor;
    }
}
