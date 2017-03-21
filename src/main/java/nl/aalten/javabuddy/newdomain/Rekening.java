package nl.aalten.javabuddy.newdomain;

public class Rekening {

    private String rekeningNummer;
    private int saldo;
    private int kredietLimiet;

    public Rekening(String rekeningNummer, int saldo, int kredietLimiet) {
        this.rekeningNummer = rekeningNummer;
        this.saldo = saldo;
        this.kredietLimiet = kredietLimiet;
    }

}
