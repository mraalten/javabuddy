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
        Persoon persoon = findOrCreatePersoon(bsn, naam, geboorteDatum);
    }

    public Persoon findOrCreatePersoon(String bsn, String naam, LocalDate geboorteDatum) {
        Persoon existingPerson = personen.get(bsn);
        if (existingPerson == null) {
            System.out.println("Dit bsnnummer bestaat niet. Wilt u deze nu invoeren?");
            Persoon persoon = new Persoon(bsn, naam, geboorteDatum);
            personen.put(bsn, persoon);
        }
        return existingPerson;
    }

    public void deposit(String rekeningNummer, int teStortenBedrag) {
        Rekening existinRekening = rekeningen.get(rekeningNummer);
        if ( existinRekening == null ) {
            throw new IllegalStateException("Rekening bestaat niet");
        } else {
            existinRekening.setSaldo(existinRekening.getSaldo() + teStortenBedrag);
        }
    }

    public void withdraw(String rekeningNummer, int opTeNemenBedrag) {
        Rekening existingRekening = rekeningen.get(rekeningNummer);
        if ( existingRekening == null ) {
            throw new IllegalStateException("Rekening bestaat niet");
        }
        boolean kredietLimietOverschreden = existingRekening.getSaldo() - opTeNemenBedrag < -existingRekening.getKredietLimiet();
        if (kredietLimietOverschreden) {
            System.out.println("Dit is een te groot bedrag om op te nemen!");
        } else {
            existingRekening.setSaldo(existingRekening.getSaldo() - opTeNemenBedrag);
        }

    }

    public void transferMoney(String rekeningFrom, String rekeningTo, int overTeMakenBedrag) {
        Rekening rekeningteStortenBedrag = rekeningen.get(rekeningTo);
        Rekening rekeningopTeNemenBedrag = rekeningen.get(rekeningFrom);
        if ( rekeningteStortenBedrag == null && rekeningopTeNemenBedrag == null ) {
            throw new IllegalStateException("Rekening bestaat niet");
        } else {
            rekeningteStortenBedrag.setSaldo(rekeningteStortenBedrag.getSaldo() + overTeMakenBedrag);
            rekeningopTeNemenBedrag.setSaldo(rekeningopTeNemenBedrag.getSaldo() - overTeMakenBedrag);
        }

    }


    public void addRekeningToPersoon(String bsn, String maxRekeningNummer, int saldo, int kredietLimiet) {
        Persoon existingPerson = personen.get(bsn);
        String rekeningNummer = "NLJAVA1" + maxRekeningNummer;
        Rekening rekening = new Rekening(rekeningNummer, saldo, kredietLimiet);
        existingPerson.rekeningen.add(rekening);
        List<Rekening> rekeningenVoorPersoon = new ArrayList<>();
        rekeningenVoorPersoon.add(rekening);
        rekeningen.put(rekeningNummer, rekening);
    }

    public void addSpaarRekeningToPersoon(String bsn, String maxRekeningNummer, int saldo, int kredietLimiet) {
        Persoon existingPerson = personen.get(bsn);
        String rekeningNummer = "NLJAVA2" + maxRekeningNummer;
        Rekening rekening = new Rekening(rekeningNummer, saldo, kredietLimiet);
        existingPerson.rekeningen.add(rekening);
        List<Rekening> spaarrekeningenVoorPersoon = new ArrayList<>();
        spaarrekeningenVoorPersoon.add(rekening);
        rekeningen.put(rekeningNummer, rekening);
    }

    public String bepaalMaxRekeningNummer() {
        int maxRekeningNummer = 0;
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
