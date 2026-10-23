/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.uptc.bankatm;

import com.uptc.bankatm.exceptions.IncorrectKeyException;
import com.uptc.bankatm.exceptions.InsufficientBalanceException;
import com.uptc.bankatm.exceptions.InvalidAmountException;
import com.uptc.bankatm.logic.ATM;
import com.uptc.bankatm.models.BankAccount;

/**
 *
 * @author sergiorojas
 */
public class BankATM {

    public static void main(String[] args) {
        BankAccount account = new BankAccount("001", "1234", 500);
        ATM atm = new ATM(account);

//        System.out.println("Caso 1: clave incorrecta");
//        tryWithdraw(atm, 100, "0000");
//
//        System.out.println("Caso 2: monto mayor al saldo");
//        tryWithdraw(atm, 1000, "1234");

        System.out.println("Caso 3: monto negativo");
        tryWithdraw(atm, -50, "1234");

//        System.out.println("Caso 4: retiro válido");
//        tryWithdraw(atm, 200, "1234");
    }

    private static void tryWithdraw(ATM atm, double amount, String key) {
        try {
            atm.withdraw(amount, key);
        } catch (InsufficientBalanceException e) {
            System.out.println("Error de saldo: " + e.getMessage());
        } catch (IncorrectKeyException e) {
            System.out.println("Error de clave: " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("Error de monto: " + e.getMessage());
        } finally {
            System.out.println("Intento procesado.");
        }
    }
}
