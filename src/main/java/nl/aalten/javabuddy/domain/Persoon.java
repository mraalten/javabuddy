package nl.aalten.javabuddy.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Persoon {
    private String bsnNummer;
    private String naam;
    private LocalDate geboortedatum;
    public List<Rekening> myRekeningen = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public Persoon(String bsnNummer, String naam, LocalDate geboortedatum) {
        this.bsnNummer = bsnNummer;
        this.naam = naam;
        this.geboortedatum = geboortedatum;
    }

    public String getNaam() {
        return naam;
    }

    public String getBsnNummer() {
        return bsnNummer;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void maakNieuweKlantAan(String maxVolgnummer) {
        Persoon klant = new Persoon("", "", LocalDate.of(1900, 1, 1));
        System.out.println("Wat is het bsn nummer de klant");
        this.bsnNummer = (input.nextLine());
        System.out.println("Wat is de naam van de klant");
        this.naam = (input.nextLine());
        System.out.println("Wat is de geboortejaar  van de klant bijv 1966");
        int klant_gebjaar = Integer.parseInt(input.nextLine());
        System.out.println("Wat is de geboortemaand  van de klant bijv 06");
        int klant_gebmaand = Integer.parseInt(input.nextLine());
        System.out.println("Wat is de geboortedag  van de klant bijv 14");
        int klant_gebdag = Integer.parseInt(input.nextLine());
        this.geboortedatum = LocalDate.of(klant_gebjaar, klant_gebmaand, klant_gebdag);

        Rekening betaalRekening = new Rekening(bsnNummer, "NLJAVA0" + maxVolgnummer, "betaal", 100, 1000);
        myRekeningen.add(betaalRekening);
        System.out.println("Wil de klant ook een spaarrekeing? j/n");
        String wileenspaarrekening = input.nextLine();
        if (wileenspaarrekening.equals("j")) {
            System.out.println("Er wordt een aangemaakt");
            Rekening spaarRekening = new Rekening(bsnNummer, "NLJAVA1" + maxVolgnummer, "spaar", 100, 0);
            myRekeningen.add(spaarRekening);
        } else {
            System.out.println("Er wordt geen aangemaakt");
        }
    }

    public void maakNieuweRekeningAan(String rekeningType, String maxVolgnummer) {
        Rekening spaarRekening = new Rekening(bsnNummer, "NLJAVA1" + maxVolgnummer, rekeningType, 100, 0);
        myRekeningen.add(spaarRekening);
    }
    private boolean wileenspaarrekening() {
        return input.nextLine()  == "j";
    }
}



