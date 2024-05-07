package org.app.testing;

import org.app.valuables.Valuable;
import org.app.valuables.ValuableMultiton;
import org.app.valuables.WoodenCoin;
import org.app.rooms.Deposit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.app.valuables.Diamond;

public class DepositTest {

    @Test
    public void testAdd() throws InterruptedException {
        // Create an instance of Deposit
        Deposit deposit = new Deposit();

        // Add a valuable to the deposit
        Valuable valuable = Diamond.getInstance("Diamond", 100);
        deposit.add(valuable);

        // Check that the valuable was added
        assertFalse(deposit.isEmpty());
    }

    @Test
    public void testRemove() throws InterruptedException {
        // Create an instance of Deposit
        Deposit deposit = new Deposit();

        // Add a valuable to the deposit
        Valuable valuable = Diamond.getInstance("Diamond", 100);
        deposit.add(valuable);

        // Remove a valuable from the deposit
        Valuable removedValuable = deposit.remove();

        // Check that the valuable was removed
        assertEquals(valuable, removedValuable);
        assertTrue(deposit.isEmpty());
    }

    @Test
    public void testIsEmpty() throws InterruptedException {
        // Create an instance of Deposit
        Deposit deposit = new Deposit();

        // Check that the deposit is initially empty
        assertTrue(deposit.isEmpty());

        // Add a valuable to the deposit
        Valuable valuable = Diamond.getInstance("Diamond", 100);
        deposit.add(valuable);

        // Check that the deposit is not empty
        assertFalse(deposit.isEmpty());
    }

    @Test
    public void testAddWoodenCoin() throws InterruptedException {
        // Create an instance of Deposit
        Deposit deposit = new Deposit();

        // Add a WoodenCoin to the deposit
        Valuable woodenCoin = WoodenCoin.getInstance("Wooden Coin", 0.0);
        deposit.add(woodenCoin);

        // Check that the WoodenCoin was not added
        assertTrue(deposit.isEmpty());
    }
}