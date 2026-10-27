/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.logic;
import com.uptc.bankatm.exceptions.AccountLockedException;
import com.uptc.bankatm.exceptions.IncorrectKeyException;
import com.uptc.bankatm.exceptions.InsufficientBalanceException;
import com.uptc.bankatm.models.BankAccount;

/**
 *
 * @author sergiorojas
 */
public class ATM {

    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }
    
    
public void withdraw(double amount, String key)
        throws InsufficientBalanceException, IncorrectKeyException {
        try {
            account.withdraw(amount, key);
            System.out.println("Retiro exitoso. Nuevo saldo: " + account.getBalance());
        } catch (AccountLockedException ex) {
            System.getLogger(ATM.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
}

public void deposit(double amount) {
    account.deposit(amount);
    System.out.println("Depósito exitoso. Nuevo saldo: " + account.getBalance());
}

}
