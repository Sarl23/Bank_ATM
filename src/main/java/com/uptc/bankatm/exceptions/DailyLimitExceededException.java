/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.exceptions;
    
/**
 *  Exception that occurs when an attempt is made to withdraw an amount exceeding the daily limit
 * 
 * @author William
 * @author Karol
 */

public class DailyLimitExceededException extends Exception {
    
    /**
     * throw an exception when the amount to be withdrawn exceeds the limit
     * 
     * @param message error message
     */

    public DailyLimitExceededException(String message) {
        super(message);
    }
}
