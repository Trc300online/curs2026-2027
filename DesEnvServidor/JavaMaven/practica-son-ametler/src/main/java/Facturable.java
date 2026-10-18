public interface Facturable {

    public static final int IVA_REDUIT = 10;
    public static final int IVA_GENERAL = 21;


    public String concepte();

    public double baseImposable();

    public int precentatgeIva();

    public double iva();

    public double total();
}
