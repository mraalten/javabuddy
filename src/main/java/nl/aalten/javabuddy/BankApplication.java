package nl.aalten.javabuddy;

import java.time.LocalDate;
import java.util.Scanner;

import nl.aalten.javabuddy.newdomain.Bank;

public class BankApplication {
    private static Bank bank = new Bank("Java");

    public static void main(String[] args) {
        Invoer invoer = showKeuzes();
        bank.addPersoon(invoer.getBsn(), "Richard", LocalDate.now());
    }

    private static Invoer showKeuzes() {
        Scanner input = new Scanner(System.in);
        System.out.println("1 Een nieuwe bank toevoegen: ");
        System.out.println("2 Een nieuwe klant toevoegen ");
        System.out.println("3 Een bestaande klant opvragen ");
        System.out.println("4 Geld storten/opnemen op een bepaalde rekening ");
        System.out.println("5 Geld overmaken naar een andere rekening ");
        System.out.println("6 Nog een rekening openen van een bestaande klant ");
        String input_keuze = input.nextLine();
        System.out.println("U heeft de volgende keuze gemaakt: " + input_keuze);

        Invoer invoer = new Invoer();
        System.out.println("Wat is het bsn nummer de klant");
        String bsnNummer = (input.nextLine());
        invoer.setBsn(bsnNummer);

        return invoer;
    }
}

