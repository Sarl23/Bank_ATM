package com.uptc.bankatm.view;

import com.uptc.bankatm.logic.ATM;
import com.uptc.bankatm.models.BankAccount;
import javax.swing.JOptionPane;

/**
 * Interactive menu of the ATM built with JOptionPane windows.
 * The menu is shown again and again until the user chooses to exit.
 */
public class ATMView {
    private ATM atm;
    private BankAccount account;
    /**
     * Creates the view.
     *
     * @param atm the ATM used to deposit and withdraw money
     * @param account the account used to read the balance
     */
    public ATMView(ATM atm, BankAccount account) {
        this.atm = atm;
        this.account = account;
    }
    /**
     * Shows the menu until the user chooses option 4 or cancels the window.
     * Every exception is caught and its message is shown to the user.
     */
    public void showMenu() {
        int opcion = 0;
        while (opcion != 4) {
            try {
                String texto = JOptionPane.showInputDialog(
                    "CAJERO AUTOMATICO\n"
                    + "1. Depositar\n"
                    + "2. Retirar\n"
                    + "3. Consultar saldo\n"
                    + "4. Salir"
                );
                if (texto == null) {
                    opcion = 4;
                } else {
                    opcion = Integer.parseInt(texto);
                }
                switch (opcion) {
                    case 1:
                        double deposito = Double.parseDouble(
                            JOptionPane.showInputDialog("Valor a depositar:")
                        );
                        atm.deposit(deposito);
                        JOptionPane.showMessageDialog(null,"Saldo: " + account.getBalance());
                        break;
                    case 2:
                        double retiro = Double.parseDouble(
                            JOptionPane.showInputDialog("Valor a retirar:")
                        );
                        String clave = JOptionPane.showInputDialog("Ingrese su clave:");
                        atm.withdraw(retiro, clave);
                        JOptionPane.showMessageDialog(null,"Saldo: " + account.getBalance());
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null,"Saldo actual: " + account.getBalance());
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null,"Gracias por utilizarme ");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null,
                            "Opcion no valida.");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe escribir un numero valido.");
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Operacion cancelada.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
}
