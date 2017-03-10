package nl.aalten.javabuddy.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Bank {

    private String naamBank;
    private String klantBsnOpvragen;
    private int klantArrayNummer;

    public List<Persoon> myPersons = new ArrayList<>();

    public Bank(String naam) {
        this.naamBank = naam;
    }

    public void startBankApplicatieOp(String naamBank) {
        Scanner input = new Scanner(System.in);
        System.out.println("Goedendag medewerker. Wat voor actie wilt u uitvoeren? Kies een nummer: ");
        boolean nognietklaar = true;
        while (nognietklaar) {
            System.out.println("1 Een nieuwe bank toevoegen: ");
            System.out.println("2 Een nieuwe klant toevoegen ");
            System.out.println("3 Een bestaande klant opvragen ");
            System.out.println("4 Geld storten/opnemen op een bepaalde rekening ");
            System.out.println("5 Geld overmaken naar een andere rekening ");
            System.out.println("6 Nog een rekening openen van een bestaande klant ");
            String input_keuze = input.nextLine();
            System.out.println("U heeft de volgende keuze gemaakt: " + input_keuze);
            switch (input_keuze) {
                case "1":
                    System.out.println("U wilt een nieuwe bank toevoegen");
                    System.out.println("Geef de vier lettrige bankcode bijv JAVA");
                    String bankNaamKeuze = input.nextLine();
                    Bank bank = new Bank(bankNaamKeuze);
                    break;
                case "2":
                    Persoon klant = new Persoon("", "", LocalDate.of(1900, 1, 1));
                    String laatsteRekeningnummer = bepaalLaatsteRekeningnummer();
//                    myPersons.get(klantArrayNummer).maakNieuweKlantAan(volgNummer);
                    klant.maakNieuweKlantAan(laatsteRekeningnummer);
                    myPersons.add(klant);
                    break;
                case "3":
                    System.out.println("U wilt een bestaande klant opvragen");
                    System.out.println("Wat is de bsn van de klant");
                    klantBsnOpvragen = input.nextLine();
                    klantArrayNummer = zoekKlant(klantBsnOpvragen);
                    System.out.println("Gevonden klant is: " + myPersons.get(klantArrayNummer).getNaam());
                    zoekRekening(klantArrayNummer);
                    break;
                case "4":
                    System.out.println("Geef Bsn nummer van de klant naar wie je geld wilt storten");
                    System.out.println("Wat is de bsn van de klant");
                    klantBsnOpvragen = input.nextLine();
                    klantArrayNummer = zoekKlant(klantBsnOpvragen);
                    System.out.println("Gevonden klant is: " + myPersons.get(klantArrayNummer).getNaam());
                    zoekRekening(klantArrayNummer);
                    System.out.println("Op welke rekening wilt u geld overmaken/storten 0 betaal of 1 spaar");
                    int rekeningOvermakenGeld = Integer.parseInt(input.nextLine());
                    System.out.println("Hoeveel geld moet er gestort/opnemen worden?");
                    int teOvermakenGeld = Integer.parseInt(input.nextLine());
                    System.out.println("Wilt u 1 opnemen of 2 storten?");
                    int overmakenOfStorten = Integer.parseInt(input.nextLine());
                    if (overmakenOfStorten == 1) {
                        withdraw(klantArrayNummer, rekeningOvermakenGeld, teOvermakenGeld);
                    } else if (overmakenOfStorten == 2) {
                        deposit(klantArrayNummer, rekeningOvermakenGeld, teOvermakenGeld);
                    } else {
                        throw new IllegalStateException("Verkeerde keuze moet 1 of 2 zijn");
                    }
                    break;
                case "5":
                    System.out.println("Geef Bsn nummer van de klant die geld wil overmaken");
                    String klant1Overmaken = input.nextLine();
                    int klant1OvermakenArrayNummer = zoekKlant(klant1Overmaken);
                    zoekRekening(klant1OvermakenArrayNummer);
                    System.out.println("Van welke rekening wilt u geld afhalen 0 betaal of 1 spaar");
                    int rekeningAfhalenGeld = Integer.parseInt(input.nextLine());
                    System.out.println("Geef Bsn nummer van de klant naar wie we geld wil overmaken");
                    String klant2Overmaken = input.nextLine();
                    int klant2OvermakenArrayNummer = zoekKlant(klant2Overmaken);
                    System.out.println("Gevonden klant is: " + myPersons.get(klant2OvermakenArrayNummer).getNaam());
                    zoekRekening(klant2OvermakenArrayNummer);
                    System.out.println("Op welke rekening wilt u geld zetten 0 betaal of 1 spaar");
                    int rekeningOpzettenGeld = Integer.parseInt(input.nextLine());
                    System.out.println("Hoeveel geld moet er overgemaakt worden?");
                    int OverTeMakenGeld = Integer.parseInt(input.nextLine());
                    transfer(klant1OvermakenArrayNummer, rekeningAfhalenGeld, klant2OvermakenArrayNummer, rekeningOpzettenGeld, OverTeMakenGeld);
                    break;
                case "6":
                    System.out.println("U wilt een extra rekening van een bestaande klant openenen");
                    System.out.println("Wat is de bsn van de klant");
                    klantBsnOpvragen = input.nextLine();
                    klantArrayNummer = zoekKlant(klantBsnOpvragen);
                    System.out.println("Gevonden klant is: " + myPersons.get(klantArrayNummer).getNaam());
                    zoekRekening(klantArrayNummer);
                    System.out.println("Wilt u een extra betaal of spaar rekening. Kies resp 0 of 1");
                    int keuzeRekening = Integer.parseInt(input.nextLine());
                    String maxRekeningNummer = bepaalLaatsteRekeningnummer();
                    if (keuzeRekening == 0) {
                        myPersons.get(klantArrayNummer).maakNieuweRekeningAan("betaal", maxRekeningNummer);
                    } else if (keuzeRekening == 1) {
                        myPersons.get(klantArrayNummer).maakNieuweRekeningAan("spaar", maxRekeningNummer);
                    } else {
                        throw new IllegalStateException ("Verkeerde rekening keuze moet 0 of 1 zijn");
                    }
                    break;
                default:
                    System.out.println("U heeft een ongeldige keuze gemaakt");

            }
            System.out.println("Wilt u nog een actie uitvoeren j/n");
            String zijnWeNogNietKlaar = input.nextLine();
            if (zijnWeNogNietKlaar.equals("j")) {
                nognietklaar = true;
            } else {
                break;
            }
        }

    }

    private void deposit(int arrayOpvraagKlantNummer, int arrayOpvraagRekeningNummer, int bedrag) {
        myPersons.get(arrayOpvraagKlantNummer).myRekeningen.get(arrayOpvraagRekeningNummer).stortGeld(bedrag);
    }

    private void withdraw(int arrayOpvraagKlantNummer, int arrayOpvraagRekeningNummer, int bedrag) {
        myPersons.get(arrayOpvraagKlantNummer).myRekeningen.get(arrayOpvraagRekeningNummer).geldOpnemen(bedrag);
    }

    private void transfer(int arrayOpvraagKlantNummerOpnemen, int arrayOpvraagRekeningNummerOpnemen, int arrayOpvraagKlantNummerStorten, int arrayOpvraagRekeningNummerStorten, int bedrag) {
        myPersons.get(arrayOpvraagKlantNummerOpnemen).myRekeningen.get(arrayOpvraagRekeningNummerOpnemen).geldOpnemen(bedrag);
        myPersons.get(arrayOpvraagKlantNummerStorten).myRekeningen.get(arrayOpvraagRekeningNummerStorten).stortGeld(bedrag);
    }

    private String bepaalLaatsteRekeningnummer() {
        int MaxRekeningNummer = 0;
        for (int i = 0; i < myPersons.size(); i++) {
            for (int j = 0; j < myPersons.get(i).myRekeningen.size(); j++) {
                String subStringRekeningnummer = myPersons.get(i).myRekeningen.get(j).getRekeningNummer().substring(9, 16);
                int subIntRekeningnummer = Integer.parseInt(subStringRekeningnummer);
                if (MaxRekeningNummer < subIntRekeningnummer) {
                    MaxRekeningNummer = subIntRekeningnummer;
                }
            }
        }
        String doorGeefVolgnummer = String.format("%09d", ++MaxRekeningNummer);
        return doorGeefVolgnummer;
    }


    private int zoekKlant(String klantBsnOpvragen) {
        int klantNummer = 0;
        boolean bsnNogNietGevonden = false;
        for (int i = 0; i < myPersons.size(); i++) {
            String zoekBsn = myPersons.get(i).getBsnNummer();
            if (zoekBsn.equals(klantBsnOpvragen)) {
                System.out.println("Gevonden bsnnmummer  " + zoekBsn);
                klantNummer = i;
                break;
            } else {
                bsnNogNietGevonden = true;
            }
        }
        if (bsnNogNietGevonden) {
            System.out.println("U heeft een niet bestaand bsn nummer gegeven" + klantBsnOpvragen);
        }
        return klantNummer;
    }

    private void zoekRekening(int klantNummer) {
        for (int j = 0; j < myPersons.get(klantNummer).myRekeningen.size(); j++) {
            System.out.println("Gevonden reknummer: rekening" + j + " " + myPersons.get(klantNummer).myRekeningen.get(j).getRekeningNummer());
            System.out.println("Dit is een " + myPersons.get(klantNummer).myRekeningen.get(j).getRekening_type() + "met een saldo van " + myPersons.get(klantNummer).myRekeningen.get(j).getSaldo_type());
        }
    }
}
