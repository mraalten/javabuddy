package nl.aalten.javabuddy.newdomain;

import java.time.LocalDate;
import java.util.*;

public class Bank {
    private String naam;

    private Map<String, Persoon> personen = new HashMap<>();
    private Map<String, Rekening> rekeningen = new HashMap<>();

    public Bank(String naam) {
        this.naam = naam;
    }

    public void openAccount(String bsn, String naam, LocalDate geboorteDatum) {
        Persoon persoon = createNewPersoon(bsn, naam, geboorteDatum);
    }

    /* Richard : De naam van deze methode klopt niet; als de persoon al bestaat wordt er geen nieuwe aangemaakt.
    findOrCreatePersoon is denk ik een betere naam. Daarnaast begint de methode met een hoofdletter en dat is niet gebruikelijk */
    public Persoon createNewPersoon(String bsn, String naam, LocalDate geboorteDatum) {
        if (personen.get(bsn) != null) {
            throw new IllegalStateException("Klant bestaat al met bsn-nummer " + bsn);
        }
        System.out.println("Dit bsnnummer bestaat niet. Wilt u deze nu invoeren?");
        Persoon person = new Persoon(bsn, naam, geboorteDatum);
        personen.put(bsn, person);

        person.addRekening(createBetaalrekening());
        addSpaarRekeningToPersoon("12345", bank.bepaalMaxRekeningNummer(), spaarSaldo, spaarKrediet);

        return person;
    }


    public Rekening createBetaalrekening() {
        String rekeningNummer = "NLJAVA1" + bepaalMaxRekeningNummer();
        Rekening rekening = new Betaalrekening(rekeningNummer);
        rekeningen.put(rekeningNummer, rekening);
        return rekening;
    }

    public void addSpaarRekeningToPersoon(String bsn, String maxRekeningNummer, int saldo, int kredietLimiet) {
        Persoon existingPerson = personen.get(bsn);

        /* Richard : ik zou deze constante NLJAVA2 onderdeel maken van de bepaalMaxRekeningNummer methode */
        String rekeningNummer = "NLJAVA2" + maxRekeningNummer;

        Rekening rekening = new Spaarrekening(rekeningNummer, saldo, kredietLimiet);
        existingPerson.rekeningen.add(rekening);
        List<Rekening> spaarrekeningenVoorPersoon = new ArrayList<>();
        spaarrekeningenVoorPersoon.add(rekening);
        rekeningen.put(rekeningNummer, rekening);
    }

    public Persoon findExistingPersoon(String bsn) {
        Persoon existingPerson = personen.get(bsn);
        if (existingPerson == null) {
            System.out.println("Dit bsnnummer bestaat niet. Wilt u deze nu invoeren?");
        }
        return existingPerson;
    }

    public void deposit(String rekeningNummer, int teStortenBedrag) {
        Rekening existingRekening = rekeningen.get(rekeningNummer);
        if (existingRekening == null) {
            throw new IllegalStateException("Rekening bestaat niet");
        }
        existingRekening.deposit(teStortenBedrag);
    }

    public void withdraw(String rekeningNummer, int opTeNemenBedrag) {
        Rekening existingRekening = rekeningen.get(rekeningNummer);
        if (existingRekening == null) {
            throw new IllegalStateException("Rekening bestaat niet");
        }
        existingRekening.withdraw(opTeNemenBedrag);
    }

    public void transferMoney(String rekeningFrom, String rekeningTo, int overTeMakenBedrag) {
        Rekening rekeningteStortenBedrag = rekeningen.get(rekeningTo);
        Rekening rekeningopTeNemenBedrag = rekeningen.get(rekeningFrom);
        if (rekeningteStortenBedrag == null && rekeningopTeNemenBedrag == null) {
            throw new IllegalStateException("Rekening bestaat niet");
        } else {
            rekeningopTeNemenBedrag.withdraw(rekeningopTeNemenBedrag.getSaldo() - overTeMakenBedrag);
            rekeningteStortenBedrag.deposit(overTeMakenBedrag);
        }
    }

    private String bepaalMaxRekeningNummer() {
        int maxRekeningNummer = 0;

        /* Richard : ipv de entrySet te gebruiken voor deze loop kun je het ook als volgt doen:
        * for (Rekening rekening : rekeningen.values()) {. Is iets leesbaarder */

        for (Map.Entry<String, Rekening> entry : rekeningen.entrySet()) {
            Rekening rekening = entry.getValue();
            String rekeningNummer = rekening.getRekeningNummer().substring(9, 16);
            int intRekeningNummer = Integer.parseInt(rekeningNummer);
            if (maxRekeningNummer < intRekeningNummer) {
                maxRekeningNummer = intRekeningNummer;
            }
        }
        String rekeningVolgnummer = String.format("%09d", ++maxRekeningNummer);
        return rekeningVolgnummer;
    }
}
