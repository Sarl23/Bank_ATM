package com.uptc.bankatm.test;

import static org.junit.jupiter.api.Assertions.*;
import com.uptc.bankatm.logic.ATM;
import com.uptc.bankatm.exceptions.IncorrectKeyException;
import com.uptc.bankatm.exceptions.InsufficientBalanceException;
import com.uptc.bankatm.exceptions.InvalidAmountException;
import com.uptc.bankatm.models.BankAccount;
import com.uptc.bankatm.models.Transaction;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Unit tests for the transaction history feature of {@link ATM}.
 */
public class ATMHistoryTest {

    private BankAccount account;
    private ATM atm;

    @BeforeEach
    void setUp() {
        account = new BankAccount("001", "1234", 500);
        atm = new ATM(account);
    }

    @Test
    void historyStartsEmpty() {
        assertTrue(atm.getTransactions().isEmpty());
    }

    @Test
    void successfulDepositIsRecorded() {
        atm.deposit(100);
        List<Transaction> list = atm.getTransactions();
        assertEquals(1, list.size());
        assertEquals(Transaction.Type.DEPOSIT, list.get(0).getType());
        assertEquals(100, list.get(0).getAmount());
        assertEquals(600, list.get(0).getBalanceAfter());
    }

    @Test
    void successfulWithdrawalIsRecorded() throws IncorrectKeyException {
        atm.withdraw(200, "1234");
        List<Transaction> list = atm.getTransactions();
        assertEquals(1, list.size());
        assertEquals(Transaction.Type.WITHDRAWAL, list.get(0).getType());
        assertEquals(300, list.get(0).getBalanceAfter());
    }

    @Test
    void operationsAreKeptInOrder() throws IncorrectKeyException {
        atm.deposit(100);
        atm.withdraw(50, "1234");
        List<Transaction> list = atm.getTransactions();
        assertEquals(2, list.size());
        assertEquals(Transaction.Type.DEPOSIT, list.get(0).getType());
        assertEquals(Transaction.Type.WITHDRAWAL, list.get(1).getType());
    }

    @Test
    void wrongKeyIsNotRecorded() {
        assertThrows(IncorrectKeyException.class, () -> atm.withdraw(100, "0000"));
        assertTrue(atm.getTransactions().isEmpty());
    }

    @Test
    void insufficientBalanceIsNotRecorded() {
        assertThrows(InsufficientBalanceException.class, () -> atm.withdraw(1000, "1234"));
        assertTrue(atm.getTransactions().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -300})
    void invalidDepositIsNotRecorded(double amount) {
        assertThrows(InvalidAmountException.class, () -> atm.deposit(amount));
        assertTrue(atm.getTransactions().isEmpty());
    }

    @Test
    void getTransactionsReturnsACopy() {
        atm.deposit(100);
        List<Transaction> copy = atm.getTransactions();
        copy.clear();
        assertEquals(1, atm.getTransactions().size());
    }
}
