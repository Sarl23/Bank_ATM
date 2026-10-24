/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.logic;

import com.uptc.bankatm.exceptions.AccountNotFoundException;
import com.uptc.bankatm.models.BankAccount;
import java.util.ArrayList;
/**
 *
 * @author AndresSandoval
 * 
 */
public class Bank {
    
    private final ArrayList<BankAccount> accounts = new ArrayList<>();
    
    /**
     * Adds an account to the bank.
     *
     * @param account the account to add
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

    public BankAccount findAccount(String accountNumber)
        throws AccountNotFoundException {
     BankAccount account = searchAccount(accountNumber);
     if (account == null) {
        throw new AccountNotFoundException(
                "Cuenta no encontrada: " + accountNumber);
    }
    return account;
}
}
