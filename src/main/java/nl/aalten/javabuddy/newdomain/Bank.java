package nl.aalten.javabuddy.newdomain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private String naam;

    private Map<String, Persoon> personen = new HashMap<>();
    private Map<String, Rekening> rekeningen = new HashMap<>();

    public Bank(String naam) {
        this.naam = naam;
    }

    public void openAccount(String bsn, String naam, LocalDate geboorteDatum) {
         Persoon persoon = findOrCreatePersoon(bsn, naam, geboorteDatum);
    }

    public Persoon findOrCreatePersoon(String bsn, String naam, LocalDate geboorteDatum) {
        Persoon existingPerson = personen.get(bsn);
        if (existingPerson == null) {
            existingPerson = new Persoon(bsn, naam, geboorteDatum);
            personen.put(bsn, existingPerson);
        }
        return existingPerson;
    }

    public void deposit(String rekeningNummer, int teStortenBedrag) {
    }

    public void withdraw(String rekeningNummer, int opTeNemenBedrag) {
    }

    public void transferMoney(String rekeningFrom, String rekeningTo, int overTeMakenBedrag) {
    }

    public void addPersoon(String bsn, String naam, LocalDate geboorteDatum) {
        Persoon existingPerson = personen.get(bsn);
        if (existingPerson == null) {
            Persoon persoon = new Persoon(bsn, naam, geboorteDatum);
            personen.put(bsn, persoon);
        }
    }
}
