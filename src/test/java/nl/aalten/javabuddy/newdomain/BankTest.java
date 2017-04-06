package nl.aalten.javabuddy.newdomain;

import nl.aalten.javabuddy.Invoer;
import nl.aalten.javabuddy.domain.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

//import org.junit.Test;

public class BankTest {

    @Test
    public void it_should_return_an_existing_person() {
        Bank bank = new Bank("test");
        Invoer invoer = new Invoer();
        invoer.setBsn("12345");
        invoer.setNaam("Anne");
        invoer.setGeboorteDatum(1965,12,12);
        bank.findOrCreatePersoon(invoer.getBsn(), invoer.getNaam(), invoer.getGeboorteDatum());
        Persoon vindPersoon = bank.findOrCreatePersoon("12345", "Anne", LocalDate.now());

        assertThat(vindPersoon).isNotNull();
    }

    @Test
    public void it_should_return_an_existing_rekening() {
        Bank bank = new Bank("test");
        bank.findOrCreatePersoon("12345", "test", LocalDate.now());
        Persoon persoon = bank.findOrCreatePersoon("12345", "test", LocalDate.now());
        bank.addRekeningToPersoon("12345", bank.bepaalMaxRekeningNummer(),100, 500);
        bank.addSpaarRekeningToPersoon("12345", bank.bepaalMaxRekeningNummer(), 100, 500);
        bank.transferMoney(persoon.rekeningen.get(0).getRekeningNummer(), persoon.rekeningen.get(1).getRekeningNummer(),100);

        assertThat(persoon.rekeningen).isNotNull();
    }

    @Test
    public void it_should_return_a_person_when_it_does_not_exist() {
        Bank bank = new Bank("test");
        Invoer invoer = new Invoer();
        invoer.setBsn("12345");
        invoer.setNaam("Anne");
        invoer.setGeboorteDatum(1965,12,12);
        Persoon persoon = bank.findOrCreatePersoon(invoer.getBsn(), invoer.getNaam(), invoer.getGeboorteDatum());

        assertThat(persoon).isNull();
 //       assertThat(personen.g).isEqualTo(invoer.getBsn());
    }

}
