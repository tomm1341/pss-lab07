package it.unibo.bank.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assumptions.abort;

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
        Assertions.assertEquals(2, account.getTransactionsCount());
        account.deposit(aRossi.getUserID(), -40);
        Assertions.assertEquals(10360, account.getBalance());
    }

    @Test
    public void testDepositFromATM() {
        final double initialAmount = 0;
        BankAccount account = new SimpleBankAccount(aBianchi, initialAmount);
        Assertions.assertEquals(0, account.getTransactionsCount());
        account.depositFromATM(aBianchi.getUserID(), 10000);
        Assertions.assertEquals(9999, account.getBalance());
        account.depositFromATM(aBianchi.getUserID(), -11);
        System.out.println(account.getBalance());
        Assertions.assertEquals(9987, account.getBalance());
    }

    @Test
    public void testWithdraw() {
        final double initialAmount = 10000;
        BankAccount account = new SimpleBankAccount(aBianchi, initialAmount);
        account.withdraw(aBianchi.getUserID(), 5000);
        Assertions.assertEquals(5000, account.getBalance());
        account.withdraw(aBianchi.getUserID(), 10000);
        Assertions.assertEquals(-5000, account.getBalance());
        Assertions.assertEquals(2, account.getTransactionsCount());
    }

    @Test
    public void testWithdrawFromATM() {
        final double initialAmount = 10000;
        BankAccount account = new SimpleBankAccount(aBianchi, initialAmount);
        account.withdrawFromATM(aBianchi.getUserID(), 9999);
        Assertions.assertEquals(0, account.getBalance());
        Assertions.assertEquals(1, account.getTransactionsCount());
        account.withdrawFromATM(aBianchi.getUserID(), 10);
        Assertions.assertEquals(-11, account.getBalance());
    }

    @Test
    public void testChargeManagementFees() {
        final double initialAmount = 50;
        BankAccount account = new SimpleBankAccount(aRossi, initialAmount);
        account.chargeManagementFees(aRossi.getUserID());
        Assertions.assertEquals(45, account.getBalance());
        Assertions.assertEquals(0, account.getTransactionsCount());
    }
    
}
