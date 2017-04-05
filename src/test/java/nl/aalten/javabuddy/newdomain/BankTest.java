package nl.aalten.javabuddy.newdomain;

import nl.aalten.javabuddy.Invoer;
import nl.aalten.javabuddy.domain.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

//import org.junit.Test;

public class BankTest {

    @Test
    public void it_should_return_an_existing_person() {
        Bank bank = new Bank("test");
        bank.findOrCreatePersoon("12345", "test", LocalDate.now());
        Persoon persoon = bank.findOrCreatePersoon("12345", "test", LocalDate.now());

        assertThat(persoon).isNotNull();
    }

    @Test
    public void it_should_return_an_existing_rekening() {
        Bank bank = new Bank("test");
        bank.findOrCreatePersoon("12345", "test", LocalDate.now());
//        Invoer invoer = new Invoer();
        Persoon persoon = bank.findOrCreatePersoon("12345", "test", LocalDate.now());
        bank.addRekeningToPersoon("12345", "",0, 0);
        bank.addSpaarRekeningToPersoon("12345", "", 0, 0);

        assertThat(persoon.rekeningen).isNotNull();
    }

    @Test
    public void it_should_return_a_person_when_it_does_not_exist() {
        Bank bank = new Bank("test");

        String bsnNummer = "97977";
        Persoon persoon = bank.findOrCreatePersoon(bsnNummer, "test", LocalDate.now());

        assertThat(persoon).isNotNull();
  //      assertThat(persoon.getBsnNummer()).isEqualTo(bsnNummer);
    }

}
