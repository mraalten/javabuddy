package nl.aalten.javabuddy.newdomain;

import java.time.LocalDate;

public class Rekening {

    private String rekeningNummer;
    private int saldo;
    private int kredietLimiet;

    public Rekening(String rekeningNummer, int saldo, int kredietLimiet) {
        this.rekeningNummer = rekeningNummer;
        this.saldo = saldo;
        this.kredietLimiet = kredietLimiet;
    }

    public String getRekeningNummer() {
        return rekeningNummer;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getKredietLimiet() {
        return kredietLimiet;
    }
}
