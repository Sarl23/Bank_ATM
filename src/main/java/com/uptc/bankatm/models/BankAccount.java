/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.models;

import com.uptc.bankatm.exceptions.IncorrectKeyException;
import com.uptc.bankatm.exceptions.InsufficientBalanceException;
import com.uptc.bankatm.exceptions.InvalidAmountException;
import com.uptc.bankatm.exceptions.AccountLockedException;

/**
 *
 * @author sergiorojas
 */
public class BankAccount {

    private String accountNumber;
    private String passWord;
    private double balance;
    
    /** Maximum number of consecutive failed attempts before the account is locked. */
    public static final int MAX_FAILED_ATTEMPTS = 3;

    private int failedAttempts;
    private boolean locked;
    
    public BankAccount(String accountNumber, String passWord, double balance) {
        this.accountNumber = accountNumber;
        this.passWord = passWord;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    /**
     * Returns the number of consecutive failed password attempts.
     *
     * @return the number of failed attempts
     */
    public int getFailedAttempts() {
        return failedAttempts;
    }

    /**
     * Checks whether the account is locked.
     *
     * @return true if the account is locked, false otherwise
     */
    public boolean isLocked() {
        return locked;
    }
    @Override
    public String toString() {
        return "BankAccount{" + "accountNumber=" + accountNumber + ", passWord=" + passWord + ", balance=" + balance + '}';
    }
    
public void deposit(double amount) {
    if (amount <= 0) {
        throw new InvalidAmountException("El monto debe ser mayor que cero.");
    }
    balance += amount;
}

    /**
     * Withdraws money from the account. Validates the lock state, password,
     * amount and balance, in that order. Three consecutive wrong passwords
     * lock the account, and a correct password resets the failed attempts counter.
     *
     * @param amount the amount to withdraw
     * @param key the password typed by the user
     * @throws IncorrectKeyException if the password is wrong
     * @throws InsufficientBalanceException if the amount exceeds the balance
     * @throws AccountLockedException if the account is locked or becomes locked
     */
    public void withdraw(double amount, String key)
            throws IncorrectKeyException, InsufficientBalanceException, AccountLockedException {
        if (locked) {
            throw new AccountLockedException("La cuenta está bloqueada. No se permiten retiros.");
        }
        if (!passWord.equals(key)) {
            failedAttempts++;
            if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
                locked = true;
                throw new AccountLockedException(
                        "Cuenta bloqueada tras " + MAX_FAILED_ATTEMPTS + " intentos fallidos consecutivos.");
            }
            throw new IncorrectKeyException("La clave ingresada es incorrecta.");
        }
        failedAttempts = 0;
        if (amount <= 0) {
            throw new InvalidAmountException("El monto debe ser mayor que cero.");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Saldo insuficiente. Saldo disponible: " + balance);
        }
        balance -= amount;
    }
}
