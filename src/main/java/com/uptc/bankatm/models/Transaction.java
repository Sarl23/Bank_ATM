package com.uptc.bankatm.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Immutable record of a successful ATM operation (deposit or withdrawal).
 * Failed operations are never represented by this class.
 */
public class Transaction {

    /**
     * The kind of operation that was performed.
     */
    public enum Type {
        /** Money added to the account. */
        DEPOSIT,
        /** Money taken out of the account. */
        WITHDRAWAL
    }

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Type type;
    private final double amount;
    private final LocalDateTime dateTime;
    private final double balanceAfter;

    /**
     * Creates a transaction stamped with the current date and time.
     *
     * @param type         the type of operation
     * @param amount       the amount moved, must be greater than zero
     * @param balanceAfter the account balance right after the operation
     * @throws IllegalArgumentException if the type is null or the amount is not positive
     */
    public Transaction(Type type, double amount, double balanceAfter) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.dateTime = LocalDateTime.now();
    }

    /**
     * Gets the type of operation.
     *
     * @return the operation type
     */
    public Type getType() {
        return type;
    }

    /**
     * Gets the amount moved by the operation.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the moment the operation happened.
     *
     * @return the date and time of the operation
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Gets the account balance right after the operation.
     *
     * @return the resulting balance
     */
    public double getBalanceAfter() {
        return balanceAfter;
    }

    /**
     * Returns a one-line description of this transaction.
     *
     * @return a string with date and time, type, amount and resulting balance
     */
    @Override
    public String toString() {
        return dateTime.format(FORMAT) + " | " + type + " | amount: " + amount
                + " | balance: " + balanceAfter;
    }
}
