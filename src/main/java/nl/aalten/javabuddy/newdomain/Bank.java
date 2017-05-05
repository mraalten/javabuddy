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

    public Persoon fillInitialPersonAndAccountDbase(String bsn, String naam, LocalDate geboorteDatum) {
        Persoon person = new Persoon(bsn, naam, geboorteDatum);
        personen.put(bsn, person);
        person.addRekening(createRekening("betaal"));
        person.addRekening(createRekening("spaar"));
        return person;
    }

    public Rekening createRekening(String typeRekening) {
        String rekeningNummer = bepaalMaxRekeningNummer(typeRekening);
        if (typeRekening == "betaal") {
            Rekening rekening = new Betaalrekening(rekeningNummer);
            rekeningen.put(rekeningNummer, rekening);
            return rekening;
        } else {
            Rekening rekening = new Spaarrekening(rekeningNummer);
            rekeningen.put(rekeningNummer, rekening);
            return rekening;
        }
    }

    public Persoon createNewPersoon(String bsn, String naam, LocalDate geboorteDatum) {
        Persoon person = new Persoon(bsn, naam, geboorteDatum);
        personen.put(bsn, person);
        person.addRekening(createRekening("betaal"));
        person.addRekening(createRekening("spaar"));
        return person;
    }

    public Persoon findPersoon(String bsn) {
        Persoon person = new Persoon(bsn);
        if (personen.get(bsn) != null) {
            person = personen.get(bsn);
        } else {
            System.out.println("Dit bsnnummer bestaat niet. Wilt u deze nu invoeren?");
            person = createNewPersoon(bsn, "NewPersoon", LocalDate.of(1966, 12, 12));
        }
        return person;
    }

    public void deposit(String rekeningNummer, int teStortenBedrag) {
        Rekening existingRekening = rekeningen.get(rekeningNummer);
        if (existingRekening == null) {
            throw new IllegalStateException("Rekening bestaat niet");
        }
        existingRekening.deposit(teStortenBedrag);
        System.out.println("Nieuw saldo is:" + existingRekening.getSaldo());
    }

    public void withdraw(String rekeningNummer, int opTeNemenBedrag) {
        Rekening existingRekening = rekeningen.get(rekeningNummer);
        if (existingRekening == null) {
            //           throw new IllegalStateException("Rekening bestaat niet");
            System.out.println("Rekening bestaat niet");
        }
        existingRekening.withdraw(opTeNemenBedrag);
        System.out.println("Nieuw saldo is:" + existingRekening.getSaldo());
    }

    public void transferMoney(String rekeningFrom, String rekeningTo, int overTeMakenBedrag) {
        Rekening rekeningteStortenBedrag = rekeningen.get(rekeningTo);
        Rekening rekeningopTeNemenBedrag = rekeningen.get(rekeningFrom);
        if (rekeningteStortenBedrag == null && rekeningopTeNemenBedrag == null) {
            throw new IllegalStateException("Rekening bestaat niet");
        } else {
            rekeningopTeNemenBedrag.withdraw(rekeningopTeNemenBedrag.getSaldo() - overTeMakenBedrag);
            System.out.println("Nieuw saldo is:" + rekeningopTeNemenBedrag.getSaldo());
            rekeningteStortenBedrag.deposit(overTeMakenBedrag);
            System.out.println("Nieuw saldo is:" + rekeningteStortenBedrag.getSaldo());
        }
    }

    private String bepaalMaxRekeningNummer(String typeRekening) {
        int maxRekeningNummer = 0;
        String newRekeningNummer = "";

        for (Rekening rekening : rekeningen.values()) {
            String rekeningNummer = rekening.getRekeningNummer().substring(9, 16);
            int intRekeningNummer = Integer.parseInt(rekeningNummer);
            if (maxRekeningNummer < intRekeningNummer) {
                maxRekeningNummer = intRekeningNummer;
            }
        }
        String rekeningVolgnummer = String.format("%09d", ++maxRekeningNummer);
        if (typeRekening == "spaar") {
            newRekeningNummer = "NLJAVA2" + rekeningVolgnummer;
        } else {
            newRekeningNummer = "NLJAVA1" + rekeningVolgnummer;
        }
        return newRekeningNummer;
    }
}
