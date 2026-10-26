/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.logic;
import com.uptc.bankatm.exceptions.DailyLimitExceededException;
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
        throws InsufficientBalanceException, IncorrectKeyException, DailyLimitExceededException{
    account.withdraw(amount, key);
    System.out.println("Retiro exitoso. Nuevo saldo: " + account.getBalance());
    account.setDailyLimit(account.getDailyLimit()-amount);
}

public void deposit(double amount) {
    account.deposit(amount);
    System.out.println("Depósito exitoso. Nuevo saldo: " + account.getBalance());
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
