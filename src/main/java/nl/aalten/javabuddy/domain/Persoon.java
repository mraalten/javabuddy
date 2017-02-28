package nl.aalten.javabuddy.domain;

import java.time.LocalDate;

/**
 * Created by HoltropA on 22-2-2017.
 */
public class Persoon {

    public String bsnNummer;
    private String naam;
    private LocalDate geboortedatum;

    public Persoon(String bsnNummer, String naam, LocalDate geboortedatum) {
        this.bsnNummer = bsnNummer;
        this.naam = naam;
        this.geboortedatum = geboortedatum;
    }
}


