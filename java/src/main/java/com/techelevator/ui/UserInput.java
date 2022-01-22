package com.techelevator.ui;

import com.techelevator.application.VendingMachine;
import com.techelevator.products.Item;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Map;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * 
 * Dependencies: None
 */
public class UserInput {
    private static Scanner scanner = new Scanner(System.in);

    public static String purchaseMenu() {

        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("1) Feed Money");
        System.out.println("2) Select Product");
        System.out.println("3) Finish Transaction");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();

        return option;
    }

    public static String getHomeScreenOption() {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("1) Display Vending Machine Items");
        System.out.println("2) Purchase");
        System.out.println("3) Exit");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();

        if (option.equals("1")) {
            return "display";
        } else if (option.equals("2")) {
            return "purchase";
        } else if (option.equals("3")) {
            return "exit";
        } else if (option.equals("4")) {
            return "report";
        } else {
            return "";
        }

    }
    public static String moneyOption() {
        System.out.println("1) Feed $1");
        System.out.println("2) Feed $2");
        System.out.println("3) Feed $5");
        System.out.println("4) Feed $10");
        System.out.println("5) Stop feeding money, return to menu");

        String moneyOption = scanner.nextLine();
        String option = moneyOption.trim();

        while (!option.equals("1") && (!option.equals("2") && (!option.equals("3")) && (!option.equals("4")) && (!option.equals("5")))) {
            UserOutput.displayMessage("Input not recognized, please choose a valid option...");
            UserOutput.displayFeedMoneyMenu();
            option = scanner.nextLine();
        }
        return option;
    }

    public static String purchaseChoice(VendingMachine vendingMachine) {

        System.out.println(vendingMachine.displaySlots());
        System.out.println("Please enter the code of the item you would like to purchase: ");

        String purchaseChoice = scanner.nextLine().toUpperCase();
        String option = purchaseChoice.trim();

        while (!vendingMachine.getStock().containsKey(option)) {
            System.out.println(vendingMachine.displaySlots());
            UserOutput.displayMessage("Sorry, not a valid code. Please enter valid code:");
            option = scanner.nextLine().toUpperCase();
        }
        return option;
    }

}

