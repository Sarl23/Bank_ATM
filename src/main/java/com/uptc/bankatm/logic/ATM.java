/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.logic;
import com.uptc.bankatm.exceptions.DailyLimitExceededException;
import com.uptc.bankatm.exceptions.IncorrectKeyException;
import com.uptc.bankatm.exceptions.InsufficientBalanceException;
import com.uptc.bankatm.models.BankAccount;
import com.uptc.bankatm.models.Transaction;
import java.util.ArrayList;

/**
 *
 * @author sergiorojas
 */
public class ATM {

    private BankAccount account;
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public ATM(BankAccount account) {
        this.account = account;
    }
    
    
public void withdraw(double amount, String key)
        throws InsufficientBalanceException, IncorrectKeyException, DailyLimitExceededException{
    account.withdraw(amount, key);
    transactions.add(new Transaction(Transaction.Type.WITHDRAWAL, amount, account.getBalance()));
    System.out.println("Retiro exitoso. Nuevo saldo: " + account.getBalance());
    account.setDailyLimit(account.getDailyLimit()-amount);
}

public void deposit(double amount) {
    account.deposit(amount);
    transactions.add(new Transaction(Transaction.Type.DEPOSIT, amount, account.getBalance()));
    System.out.println("Depósito exitoso. Nuevo saldo: " + account.getBalance());
}

/**
 * Prints every successful transaction, oldest first.
 * Prints a notice if nothing has been recorded yet.
 */
public void printStatement() {
    if (transactions.isEmpty()) {
        System.out.println("No hay movimientos registrados.");
        return;
    }
    for (Transaction t : transactions) {
        System.out.println(t);
    }
}

/**
 * Returns a copy of the transaction history.
 * Changes made to the returned list do not affect the ATM's own history.
 *
 * @return a new list with all recorded transactions
 */
public ArrayList<Transaction> getTransactions() {
    return new ArrayList<>(transactions);
}
/**
 * Method for resetting the daily limit
 * 
 * @author karol
 * @author william
 * 
 */
public void resetDailyWithdraw(){
    account.setDailyLimit(1000000);
    System.out.println("Limite diario restablecido con exito");
}

}
