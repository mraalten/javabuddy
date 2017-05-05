package nl.aalten.javabuddy.newdomain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;

import nl.aalten.javabuddy.Invoer;
import org.junit.Test;

public class BankTest {

    @Test
    public void it_should_return_an_existing_person() {
        Bank bank = new Bank("test");
        Invoer invoer = new Invoer();
        invoer.setBsn("12345");
        invoer.setNaam("Anne");
        invoer.setGeboorteDatum(1965, 12, 12);
        Persoon vindPersoon = bank.findPersoon("12345");

        assertThat(vindPersoon).isNotNull();
    }

    @Test
    public void it_should_return_an_existing_rekening() {
        Bank bank = new Bank("test");
        Persoon persoon = bank.createNewPersoon("12345", "Anne", LocalDate.of(1966, 12, 12));
        int SaldoVoorTranser=persoon.getRekeningen().get(0).getSaldo();
        persoon.addRekening(bank.createRekening("betaal"));
        persoon.addRekening(bank.createRekening("spaar"));
        bank.transferMoney(persoon.getRekeningen().get(0).getRekeningNummer(), persoon.getRekeningen().get(1).getRekeningNummer(), 50);
        assertThat(persoon.getRekeningen()).isNotNull();
        assertNotEquals(SaldoVoorTranser, persoon.getRekeningen().get(0).getSaldo());
    }

    @Test
    public void it_should_return_a_person_when_it_does_not_exist() {
        Bank bank = new Bank("test");
        Invoer invoer = new Invoer();
        invoer.setBsn("12345");
        invoer.setNaam("Anne");
        invoer.setGeboorteDatum(1965, 12, 12);
        Persoon persoon = bank.findPersoon(invoer.getBsn());

        assertThat(persoon).isNotNull();
    }

    @Test
    public void it_should_return_the_same_saldo_kredietoverschreven() {
        Bank bank = new Bank("test");
        Persoon persoon = bank.findPersoon("12345");
        int saldoVoorOpname = persoon.getRekeningen().get(0).getSaldo();
        bank.withdraw(persoon.getRekeningen().get(0).getRekeningNummer(), 1000);

        assertEquals("Onvoldoende saldo", saldoVoorOpname, persoon.getRekeningen().get(0).getSaldo());
    }

    @Test
    public void it_should_return_a_different_saldo_krediet_not_overschreven() {
        Bank bank = new Bank("test");
        Persoon persoon = bank.findPersoon("12345");
        int saldoVoorOpname = persoon.getRekeningen().get(0).getSaldo();
        bank.withdraw(persoon.getRekeningen().get(0).getRekeningNummer(), 50);

        assertNotEquals(saldoVoorOpname, persoon.getRekeningen().get(0).getSaldo());
    }
}
