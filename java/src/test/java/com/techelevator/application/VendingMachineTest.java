package com.techelevator.application;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendingMachineTest {
    private VendingMachine vendingMachine;

    @Before
    public void setup() {
        vendingMachine = new VendingMachine("vendingmachine.csv");
    }
    @Test
    public void generateChangeTest0() {

    String testExpected = ("Change to return: " + 0 + " quarters " + 0 + " dimes " + 0 + " nickels ");

    String testStr1 = vendingMachine.generateChange();

    assertEquals(testExpected, testStr1);

    }
    @Test
    public void generateChangeTestingQuarters() {
        vendingMachine.setMoneyInMachine(BigDecimal.ONE);
        String testExpected = ("Change to return: " + 4 + " quarters " + 0 + " dimes " + 0 + " nickels ");

        String testStr1 = vendingMachine.generateChange();

        assertEquals(testExpected, testStr1);
    }
    @Test
    public void generateChangeTestingDimes() {
        BigDecimal twenty = new BigDecimal("0.20");
        vendingMachine.setMoneyInMachine(BigDecimal.ONE.add(twenty));
        String testExpected = ("Change to return: " + 4 + " quarters " + 2 + " dimes " + 0 + " nickels ");

        String testStr1 = vendingMachine.generateChange();

        assertEquals(testExpected, testStr1);
    }
    @Test
    public void generateChangeTestingNickel() {
        BigDecimal thirty = new BigDecimal("0.30");
        vendingMachine.setMoneyInMachine(BigDecimal.ONE.add(thirty));
        String testExpected = ("Change to return: " + 5 + " quarters " + 0 + " dimes " + 1 + " nickels ");

        String testStr1 = vendingMachine.generateChange();

        assertEquals(testExpected, testStr1);
    }
    @Test
    public void generateChangeTestingOnlyDime() {
        BigDecimal ten = new BigDecimal("0.10");
        vendingMachine.setMoneyInMachine(ten);
        String testExpected = ("Change to return: " + 0 + " quarters " + 1 + " dimes " + 0 + " nickels ");

        String testStr1 = vendingMachine.generateChange();

        assertEquals(testExpected, testStr1);
    }
    @Test
    public void generateChangeTestingOnlyNickel() {
        BigDecimal five = new BigDecimal("0.05");
        vendingMachine.setMoneyInMachine(five);
        String testExpected = ("Change to return: " + 0 + " quarters " + 0 + " dimes " + 1 + " nickels ");

        String testStr1 = vendingMachine.generateChange();

        assertEquals(testExpected, testStr1);
    }

    @Test
    public void displaySlotsEmpty() {
        VendingMachine emptyVendingMachine = new VendingMachine("empty.txt");

        String expected = "";

        String actual = emptyVendingMachine.displaySlots();

        assertEquals(expected, actual);
    }
    @Test
    public void displaySlotsTestSoldOut() {
        VendingMachine vendingMachine  =  new VendingMachine("testsoldout.txt");
        String expected = "A1 | Potato Crisps | 3.05 | Chip | 5\nA2 | Stackers | 1.45 | Chip | SOLD OUT\n";
        String actual = vendingMachine.displaySlots();
        assertEquals(expected,actual);
    }
    @Test
    public void displaySlotsTestWonkaBar() {
        VendingMachine vendingMachine  =  new VendingMachine("testWonkaBar.txt");
        String expected = "B3 | Wonka Bar | 1.50 | Candy | 4\n";
                //B3|Wonka Bar|1.50|Candy|4
        String actual = vendingMachine.displaySlots();
        assertEquals(expected,actual);
    }

}