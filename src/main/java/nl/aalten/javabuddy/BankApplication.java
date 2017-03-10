package nl.aalten.javabuddy;

import nl.aalten.javabuddy.domain.Bank;
import nl.aalten.javabuddy.domain.Persoon;
import nl.aalten.javabuddy.domain.Rekening;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BankApplication {

    public static void main(String[] args) {

        Bank Bank = new Bank("Java");
        Bank.startBankApplicatieOp("Java");
    }
}

