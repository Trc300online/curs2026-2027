public class TargetaJove extends TargetaTransport{

    public TargetaJove(String numero, String titular) {
        super(numero, titular);
        this.tarifa = getTarifa()/2;
    }
}
