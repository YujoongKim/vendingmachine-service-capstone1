package com.techelevator.ui;

import java.math.BigDecimal;
import java.util.Map;


/**
 * Responsibilities: This class should handle formatting and displaying ALL
 * messages to the user
 * 
 * Dependencies: None
 */
public class UserOutput
{

    public static void displayMessage(String message)
    {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static void displayHomeScreen()
    {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Home");
        System.out.println("***************************************************");
        System.out.println();
    }

    public static void displayFeedMoneyMenu(){
        System.out.println("1) Feed $1");
        System.out.println("2) Feed $2");
        System.out.println("3) Feed $5");
        System.out.println("4) Feed $10");
        System.out.println("5) Stop feeding money, return to menu");
    }

}
