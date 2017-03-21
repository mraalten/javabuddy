package nl.aalten.javabuddy.newdomain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;

public class BankTest {

    @Test
    public void it_should_return_an_existing_person() {
        Bank bank = new Bank("test");
        bank.addPersoon("12345", "test", LocalDate.now());

        Persoon persoon = bank.findOrCreatePersoon("12345", "test", LocalDate.now());
        assertThat(persoon).isNotNull();

    }

    @Test
    public void it_should_return_a_person_when_it_does_not_exist() {
        Bank bank = new Bank("test");
        String bsnNummer = "12345";
        Persoon persoon = bank.findOrCreatePersoon(bsnNummer, "test", LocalDate.now());

        assertThat(persoon).isNotNull();
        assertThat(persoon.getBsnNummer()).isEqualTo(bsnNummer);
    }

}
