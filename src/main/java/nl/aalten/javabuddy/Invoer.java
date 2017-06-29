package nl.aalten.javabuddy;

import nl.aalten.javabuddy.newdomain.Rekening;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoer {
    private String bsn;
    private String naam;
    private LocalDate geboortedatum;
    private String rekeningNummer;
    private int saldo;
    private int kredietLimiet;
    //

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public LocalDate getGeboorteDatum() {
        return geboortedatum;
    }

    public void setGeboorteDatum(int klant_gebjaar, int klant_gebmaand, int klant_gebdag) {
        this.geboortedatum = LocalDate.of(klant_gebjaar, klant_gebmaand, klant_gebdag);
    }

    public String getRekeningNummer() {
        return rekeningNummer;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getKredietLimiet() {
        return kredietLimiet;
    }
}
