/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.uptc.bankatm;

import com.uptc.bankatm.exceptions.IncorrectKeyException;
import com.uptc.bankatm.exceptions.InsufficientBalanceException;
import com.uptc.bankatm.exceptions.InvalidAmountException;
import com.uptc.bankatm.logic.ATM;
import com.uptc.bankatm.models.BankAccount;
import com.uptc.bankatm.exceptions.AccountLockedException;

/**
 *
 * @author sergiorojas
 */
public class BankATM {

    public static void main(String[] args) {
        BankAccount account = new BankAccount("001", "1234", 500);
        ATM atm = new ATM(account);

        System.out.println("Caso 3: monto negativo");
        tryWithdraw(atm, -50, "1234");
        
        System.out.println("Caso 5: bloqueo tras 3 claves incorrectas");
ATM lockAtm = new ATM(new BankAccount("002", "1234", 500));
tryWithdraw(lockAtm, 100, "0000");
tryWithdraw(lockAtm, 100, "0000");
tryWithdraw(lockAtm, 100, "0000");
tryWithdraw(lockAtm, 100, "1234");

System.out.println("Caso 6: una clave correcta reinicia el contador");
ATM resetAtm = new ATM(new BankAccount("003", "1234", 500));
tryWithdraw(resetAtm, 100, "0000");
tryWithdraw(resetAtm, 100, "0000");
tryWithdraw(resetAtm, 100, "1234");
tryWithdraw(resetAtm, 100, "0000");
tryWithdraw(resetAtm, 100, "0000");
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
        } catch (AccountLockedException e) {
            System.out.println("Error de bloqueo: " + e.getMessage());
        } finally {
            System.out.println("Intento procesado.");
        }
        }
    }
