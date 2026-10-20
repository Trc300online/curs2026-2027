public interface Facturable {

    public static final int IVA_REDUIT = 10;
    public static final int IVA_GENERAL = 21;


    public String concepte();

    public double baseImposable();

    public int percentatgeIva();

    default double iva() {
        return baseImposable() * percentatgeIva() / 100.0;
    }

    default double total() {
        return baseImposable() + iva();
    }
}
