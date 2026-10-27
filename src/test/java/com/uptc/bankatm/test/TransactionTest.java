package com.uptc.bankatm.test;



import static org.junit.jupiter.api.Assertions.*;
import com.uptc.bankatm.models.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Unit tests for {@link Transaction}.
 */
public class TransactionTest {

    @Test
    void constructorStoresAllValues() {
        Transaction t = new Transaction(Transaction.Type.DEPOSIT, 500, 1000);
        assertEquals(Transaction.Type.DEPOSIT, t.getType());
        assertEquals(500, t.getAmount());
        assertEquals(1000, t.getBalanceAfter());
        assertNotNull(t.getDateTime());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -250.5})
    void nonPositiveAmountIsRejected(double amount) {
        assertThrows(IllegalArgumentException.class,
                () -> new Transaction(Transaction.Type.WITHDRAWAL, amount, 100));
    }

    @Test
    void nullTypeIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new Transaction(null, 100, 100));
    }

    @Test
    void toStringMentionsTypeAndAmount() {
        Transaction t = new Transaction(Transaction.Type.WITHDRAWAL, 200, 300);
        String text = t.toString();
        assertTrue(text.contains("WITHDRAWAL"));
        assertTrue(text.contains("200.0"));
        assertTrue(text.contains("300.0"));
    }
}
