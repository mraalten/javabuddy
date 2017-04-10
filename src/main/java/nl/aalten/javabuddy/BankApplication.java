package nl.aalten.javabuddy;

import java.time.LocalDate;
import java.util.Scanner;

import nl.aalten.javabuddy.newdomain.Bank;
import nl.aalten.javabuddy.newdomain.Persoon;

public class BankApplication {
    public static boolean nogNietEindeProgramma = true;
    private static Bank bank = new Bank("Java");

    public static void main(String[] args) {
        while (nogNietEindeProgramma) {
            Invoer invoer = showKeuzes();
        }
    }

    private static Invoer showKeuzes() {
        int doorlopendeSaldo = 100;
        int spaarSaldo = 0;
        int doorlopendKrediet = 500;
        int spaarKrediet = 0;

        Scanner input = new Scanner(System.in);
        System.out.println("1 Een nieuwe klant toevoegen ");
        System.out.println("2 Een bestaande klant opvragen ");
        System.out.println("3 Geld storten/opnemen op een bepaalde rekening ");
        System.out.println("4 Geld overmaken naar een andere rekening ");
        System.out.println("5 Nog een rekening openen van een bestaande klant ");
        System.out.println("9 Einde ");

        boolean DoeWAtinvoer = bank.toString().isEmpty();

        String input_keuze = input.nextLine();
        System.out.println("U heeft de volgende keuze gemaakt: " + input_keuze);
        Invoer invoer = new Invoer();
        switch (input_keuze) {
            case "1":
                    /* Richard : Ipv dat je vanuit de BankApplication alle stappen doet (dus createNewPersoon, addRekeningToPersoon etc), is de Bank verantwoordelijk voor alles wat nodig is
                     * om een nieuwe account te openen.
                     * Ik verwacht dus eigenlijk een openAccount methode op Bank die alles doet:
                     * bank.openAccount("12345", "Anne", LocalDate.of(1966, 12, 12))
                     * De openAccount zou dan een rekeningNummer kunnen teruggeven waarop je dan geld kunt storten */

                bank.CreateNewPersoon("12345", "Anne", LocalDate.of(1966, 12, 12));

                /* Richard : ook de bank.bepaalMaxRekeningNummer()-aanroep kun je "verstoppen" binnen Bank-class */
                bank.addRekeningToPersoon("12345", bank.bepaalMaxRekeningNummer(), doorlopendeSaldo, doorlopendKrediet);
                bank.addSpaarRekeningToPersoon("12345", bank.bepaalMaxRekeningNummer(), spaarSaldo, spaarKrediet);
                bank.CreateNewPersoon("98765", "Piet", LocalDate.of(1956, 11, 11));
                bank.addRekeningToPersoon("98765", bank.bepaalMaxRekeningNummer(), doorlopendeSaldo, doorlopendKrediet);
                bank.addSpaarRekeningToPersoon("98765", bank.bepaalMaxRekeningNummer(), spaarSaldo, spaarKrediet);
                break;
            case "2":
                System.out.println("Wat is het bsn nummer de klant");
                String bsnNummerGezocht = (input.nextLine());
                invoer.setBsn(bsnNummerGezocht);
                Persoon persoon = bank.findExistingPersoon(bsnNummerGezocht);
                System.out.println("Dit is de opgevraagde klant " + persoon.getNaam() + " " + persoon.getGeboorteDatum());
                for (int i = 0; i < persoon.rekeningen.size(); i++) {
                    System.out.println("Rekeningnummer" + i + " is: " + persoon.rekeningen.get(i).getRekeningNummer() + " saldo " + persoon.rekeningen.get(i).getSaldo());
                }
                break;
            case "3":
                bank.deposit("NLJAVA1000000003", 200);
                bank.withdraw("NLJAVA1000000003", 100);
                break;
            case "4":
                bank.transferMoney("NLJAVA1000000003", "NLJAVA1000000001", 25);
                break;
            case "5":
                System.out.println("Wat is het bsn nummer de klant");
                bsnNummerGezocht = (input.nextLine());
                invoer.setBsn(bsnNummerGezocht);
                Persoon persoonGezocht = bank.findExistingPersoon(bsnNummerGezocht);
                bank.addRekeningToPersoon(persoonGezocht.getBsnNummer(), bank.bepaalMaxRekeningNummer(), doorlopendeSaldo, doorlopendKrediet);
                break;
            case "9":
                nogNietEindeProgramma = false;
                System.out.println("Prograama stopt");

                break;
            default:
                System.out.println("U heeft een ongeldige keuze gemaakt");
        }
        return invoer;
    }
}

