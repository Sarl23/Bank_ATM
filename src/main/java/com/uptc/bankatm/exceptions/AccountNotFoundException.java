/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.exceptions;

/**
 *
 * @author AndresSandoval
 * 
 * Thrown when an account cannot be found in the bank.
 */
public class AccountNotFoundException extends Exception{
    
    /**
     * Creates the exception with a descriptive message.
     *
     * @param message the detail message
     */
    public AccountNotFoundException(String message) {
        super(message);
    }
}
