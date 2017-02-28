package nl.aalten.javabuddy;

import nl.aalten.javabuddy.domain.Bank;
import nl.aalten.javabuddy.domain.Persoon;
import nl.aalten.javabuddy.domain.Rekening;

import java.time.LocalDate;
import java.util.Scanner;

public class BankApplication {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Goedendag medewerker. Wat voor actie wilt u uitvoeren? Kies een nummer: ");
        boolean nognietklaar = true;
        while (nognietklaar) {
            System.out.println("1 Een nieuwe bank toevoegen: ");
            System.out.println("2 Een nieuwe klant toevoegen ");
            System.out.println("3 Een bestaande klant opvragen ");
            String input_keuze = input.nextLine();

            System.out.println("U heeft de volgende keuze gemaakt: " + input_keuze);

            switch (input_keuze) {
                case "1":
                    System.out.println("U wilt een nieuwe bank toevoegen");
                    Bank bank = new Bank("ABNO");
                    break;
                case "2":
                    System.out.println("U wilt een nieuwe klant toevoegen");
                    System.out.println("Wat is het bsn nummer de klant");
                    String klant_bsn = input.nextLine();
                    System.out.println("Wat is de naam van de klant");
                    String klant_naam = input.nextLine();
                    System.out.println("Wat is de geboortejaar  van de klant bijv 1966");
                    int klant_gebjaar = Integer.parseInt(input.nextLine());
                    System.out.println("Wat is de geboortemaand  van de klant bijv 06");
                    int klant_gebmaand = Integer.parseInt(input.nextLine());
                    System.out.println("Wat is de geboortedag  van de klant bijv 14");
                    int klant_gebdag = Integer.parseInt(input.nextLine());
                    Persoon Klant1 = new Persoon("klant_bsn", "klant_naam", LocalDate.of(klant_gebjaar, klant_gebmaand, klant_gebdag));
                    //   Persoon Anne = new Persoon("12345", "Anne", LocalDate.of(1966, 06, 19));
                    Rekening rek1 = new Rekening("BABO09NL0123456789", 500, 1000);
                    break;
                case "3":
                    System.out.println("U wilt een bestaande klant opvragen");
                    System.out.println("Wat is de naam van de klant");
                    String klant_opvnaam = input.nextLine();
                    break;
                default:
                    System.out.println("U heeft een ongeldige keuze gemaakt");
            }
            System.out.println("Wilt u nog een actie uitvoeren j/n");
            String zijnwenognietklaar = input.nextLine();
            if (zijnwenognietklaar == "j") {
                nognietklaar = false;
            }
        }
    }
}
