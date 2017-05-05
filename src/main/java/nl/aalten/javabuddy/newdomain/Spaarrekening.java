package nl.aalten.javabuddy.newdomain;

public class Spaarrekening extends Rekening {
    private static int SPAAR_SALDO = 100;
    private static int SPAAR_KREDIET = 250;

    public Spaarrekening(String rekeningNummer) {
        super(rekeningNummer, SPAAR_SALDO, SPAAR_KREDIET);
    }
}
