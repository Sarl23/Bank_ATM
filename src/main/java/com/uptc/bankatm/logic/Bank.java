/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.logic;

import com.uptc.bankatm.exceptions.AccountNotFoundException;
import com.uptc.bankatm.exceptions.IncorrectKeyException;
import com.uptc.bankatm.exceptions.InsufficientBalanceException;
import com.uptc.bankatm.models.BankAccount;
import java.util.ArrayList;

/**
 *Manages a collection of bank accounts and the operations between them,
 *such as looking up an account or transferring money.
 * @author AndresSandoval
 *
 */
public class Bank {

    private final ArrayList<BankAccount> accounts = new ArrayList<>();

    /**
     * Adds an account to the bank.
     *
     * @param account the account to add
     * @throws IllegalArgumentException if the account is null or an account 
     * with the same number already exists
     */
    public void addAccount(BankAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula.");
        }
        if (searchAccount(account.getAccountNumber()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una cuenta con el número: "
                    + account.getAccountNumber());
        }
        accounts.add(account);
    }

    /**
     * Looks up an account by number without throwing.
     *
     * @param accountNumber the number to search for
     * @return the account, or null if it does not exist
     */
    private BankAccount searchAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Finds an account by its number.
     *
     * @param accountNumber the number of the account to find
     * @return the account with that number
     * @throws AccountNotFoundException if no account has that number
     */
    public BankAccount findAccount(String accountNumber)
            throws AccountNotFoundException {
        BankAccount account = searchAccount(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(
                    "Cuenta no encontrada: " + accountNumber);
        }
        return account;
    }
    /**
     * Transfers money from one account to another.
     * Both accounts are looked up before any money is withdrawn, so funds
     * are never lost when the destination account does not exist.
     * Validation of the key, the amount and the balance is delegated to
     * {@link BankAccount#withdraw(double, String)}.
     *
     * @param fromNumber the number of the source account
     * @param toNumber   the number of the destination account
     * @param amount     the amount to transfer
     * @param key        the key of the source account
     * @throws AccountNotFoundException     if either account does not exist
     * @throws IncorrectKeyException        if the key does not match the
     *                                      source account
     * @throws InsufficientBalanceException if the source account does not
     *                                      have enough balance
     */
    public void transfer(String fromNumber, String toNumber,
                         double amount, String key)
            throws AccountNotFoundException, IncorrectKeyException,
                   InsufficientBalanceException {
        BankAccount from = findAccount(fromNumber);
        BankAccount to = findAccount(toNumber);
        from.withdraw(amount, key);
        to.deposit(amount);
    }
}
