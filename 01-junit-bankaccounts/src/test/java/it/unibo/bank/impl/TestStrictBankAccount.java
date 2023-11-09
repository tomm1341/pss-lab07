package it.unibo.bank.impl;

import static org.junit.jupiter.api.Assumptions.abort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;

public class TestStrictBankAccount {
    private AccountHolder aRossi;
    private AccountHolder aBianchi;

    @BeforeEach
    public void setUp() {
        this.aRossi = new AccountHolder("Andrea", "Rossi", 1);
        this.aBianchi = new AccountHolder("Alex", "Bianchi", 2);
    }

    @Test
    public void testWithdwaw() {
        final double initialAmount = 10000;
        BankAccount account = new StrictBankAccount(aBianchi, initialAmount);
        account.withdraw(aBianchi.getUserID(), 5000);
        Assertions.assertEquals(account.getBalance(), 5000);
        account.withdraw(aBianchi.getUserID(), 10000);
        Assertions.assertEquals(account.getBalance(), 5000);
        Assertions.assertEquals(account.getTransactionsCount(), 1);

    }

}
