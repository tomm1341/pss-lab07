package it.unibo.bank.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;

public class TestSimpleBankAccount {
    private AccountHolder aRossi;
    private AccountHolder aBianchi;

    @BeforeEach
    public void setUp() {
        this.aRossi = new AccountHolder("Andrea", "Rossi", 1);
        this.aBianchi = new AccountHolder("Alex", "Bianchi", 2);
    }

    @Test
    public void testNewSimpleBankAccount() {
        BankAccount account = new SimpleBankAccount(aRossi, 0.0);
        Assertions.assertEquals(0.0, account.getBalance());
        Assertions.assertEquals(0, account.getTransactionsCount());
        Assertions.assertSame(this.aRossi, account.getAccountHolder());
    }    

    @Test
    public void testDeposit() {
        final double initialAmount = 0;
        BankAccount account = new SimpleBankAccount(aRossi, initialAmount);
        account.deposit(aRossi.getUserID(), 10000);
        Assertions.assertEquals(10000, account.getBalance());
        account.deposit(aRossi.getUserID(), 400);
        Assertions.assertEquals(10400, account.getBalance());
        account.deposit(aRossi.getUserID(), -40);
        Assertions.assertEquals(10400, account.getBalance());
    }

    @Test
    public void testDepositFromATM() {
        final double initialAmount = 0;
        BankAccount account = new SimpleBankAccount(aBianchi, initialAmount);
        account.depositFromATM(aBianchi.getUserID(), 10000);
        Assertions.assertEquals(9999, account.getBalance());
        account.depositFromATM(aBianchi.getUserID(), -141*-11);
        Assertions.assertEquals(11.549, account.getBalance());
    }

    @Test
    public void testWithdraw() {
        
    }
    
}
