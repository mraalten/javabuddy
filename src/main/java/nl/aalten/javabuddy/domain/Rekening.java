package nl.aalten.javabuddy.domain;

import java.time.LocalDate;

/**
 * Created by HoltropA on 22-2-2017.
 */
public class Rekening {

    private String rekeningNummer;
    private int saldo;
    private int kredietLimiet;

    public Rekening(String rekeningNummer, int saldo, int kredietLimiet) {
        this.rekeningNummer = rekeningNummer;
        this.saldo = saldo;
        this.kredietLimiet = kredietLimiet;
    }

    private void transfer(String rekeningFrom, String rekeingTo, int bedrag) {
    }

    private void stortGeld(int bedrag) {
    }

    private void geldOpnemen(int bedrag) {
    }

}
