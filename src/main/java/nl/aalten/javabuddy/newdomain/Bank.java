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
        Persoon persoon = CreateNewPersoon(bsn, naam, geboorteDatum);
    }

    /* Richard : De naam van deze methode klopt niet; als de persoon al bestaat wordt er geen nieuwe aangemaakt.
    findOrCreatePersoon is denk ik een betere naam. Daarnaast begint de methode met een hoofdletter en dat is niet gebruikelijk */
    public Persoon CreateNewPersoon(String bsn, String naam, LocalDate geboorteDatum) {
        Persoon existingPerson = personen.get(bsn);
        if (existingPerson == null) {
            System.out.println("Dit bsnnummer bestaat niet. Wilt u deze nu invoeren?");
            existingPerson = new Persoon(bsn, naam, geboorteDatum);
            personen.put(bsn, existingPerson);
        }
        return existingPerson;
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
        } else {
            /* Richard : ipv rechtstreeks het saldo te zetten op een rekening is het beter om dit over te laten aan de
            * rekening-class zelf. Daar kun je dan ook een controle uitvoeren of er bv. wel voldoende saldo aanwezig is */
            existingRekening.setSaldo(existingRekening.getSaldo() + teStortenBedrag);
        }
    }

    public void withdraw(String rekeningNummer, int opTeNemenBedrag) {
        Rekening existingRekening = rekeningen.get(rekeningNummer);
        if (existingRekening == null) {
            throw new IllegalStateException("Rekening bestaat niet");
        }
        /* Richard : ook hieronder: ipv aan de rekening het saldo en de kredietlimiet te vragen is het beter dit aan de
         Rekening-class over te laten. Maakt het unit-testen ook makkelijker */

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
        if (rekeningteStortenBedrag == null && rekeningopTeNemenBedrag == null) {
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

        /* Richard : de addSpaarrekeningTopersoon-methode zou ik, als je hem al wilt gebruiken, private maken en
        * aanroepen vanuit de openAccount-methode. Misschien zelfs beter om een addRekening-methode te maken op Persoon :
        * persoon.addRekening(nieuweRekening) */

    public void addSpaarRekeningToPersoon(String bsn, String maxRekeningNummer, int saldo, int kredietLimiet) {
        Persoon existingPerson = personen.get(bsn);

        /* Richard : ik zou deze constante NLJAVA2 onderdeel maken van de bepaalMaxRekeningNummer methode */
        String rekeningNummer = "NLJAVA2" + maxRekeningNummer;

        Rekening rekening = new Rekening(rekeningNummer, saldo, kredietLimiet);
        existingPerson.rekeningen.add(rekening);
        List<Rekening> spaarrekeningenVoorPersoon = new ArrayList<>();
        spaarrekeningenVoorPersoon.add(rekening);
        rekeningen.put(rekeningNummer, rekening);
    }

    public String bepaalMaxRekeningNummer() {
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
