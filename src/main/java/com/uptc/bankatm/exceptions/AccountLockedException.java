package com.uptc.bankatm.exceptions;

/**
 * Checked exception thrown when a withdrawal is attempted on a locked
 * account, or when the account becomes locked after too many consecutive
 * failed password attempts.
 */
public class AccountLockedException extends Exception {

    /**
     * Creates the exception with a specific message.
     *
     * @param message the detail of the error
     */
    public AccountLockedException(String message) {
        super(message);
    }
}
