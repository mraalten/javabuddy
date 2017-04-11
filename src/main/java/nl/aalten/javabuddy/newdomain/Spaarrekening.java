package nl.aalten.javabuddy.newdomain;

public class Spaarrekening extends Rekening {
    private static int SPAARSALDO = 0;
    private static int SPAARKREDIET = 0;

    public Spaarrekening(String rekeningNummer) {
        super(rekeningNummer, SPAARSALDO, SPAARKREDIET);
    }
}
