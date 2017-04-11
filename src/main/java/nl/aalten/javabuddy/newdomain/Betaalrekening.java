package nl.aalten.javabuddy.newdomain;

public class Betaalrekening extends Rekening {
    private static int DOORLOPEND_SALDO = 100;
    private static int DOORLOPEND_KREDIET = 500;

    public Betaalrekening(String rekeningNummer) {
        super(rekeningNummer, DOORLOPEND_SALDO, DOORLOPEND_KREDIET);
    }
}
