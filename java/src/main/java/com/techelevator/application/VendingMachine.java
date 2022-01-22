package com.techelevator.application;

import com.techelevator.products.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {
    private BigDecimal moneyInMachine = new BigDecimal("0.00");
    private BigDecimal totalSales = new BigDecimal("0.00");
    private Map<String, Item> stock = new TreeMap<>();
    private List<String> log = new ArrayList<>();
    private static LocalDateTime currentDateTime = LocalDateTime.now();
    private Map<String, Integer> report = new TreeMap<>();


    public VendingMachine(String fileLocation) {
        generateMap(fileLocation);
    }

    public void run() {
        while (true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if (choice.equals("display")) {
                UserOutput.displayMessage(this.displaySlots());

            } else if (choice.equals("purchase")) {
                this.purchaseMenuCalculations(UserInput.purchaseMenu());

            } else if (choice.equals("exit")) {
                UserOutput.displayMessage("Goodbye!");
                break;

            } else if (choice.equals("report")) {
                DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM_dd_yyyy hh_mm a");
                String fileName = "Sales Report " + currentDateTime.format(targetFormat).toString() + ".txt";
                File outputFile = new File(fileName);
                try {
                    outputFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                logWriter(salesReport(),outputFile);

            } else {
                UserOutput.displayMessage("Unrecognized option, please try again.");
            }
        }
    }


    private void generateMap(String fileLocation) {
        File file = new File(fileLocation);

        try {
            Scanner scanner = new Scanner(file);
            String[] lineArr;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineArr = line.split("\\|");
                Item item = new Chips(lineArr[0], lineArr[1], new BigDecimal(lineArr[2]), lineArr[3], Integer.parseInt(lineArr[4]));

                if (lineArr[3].equals("Beverage")) {
                    item = new Beverage(lineArr[0], lineArr[1], new BigDecimal(lineArr[2]), lineArr[3], Integer.parseInt(lineArr[4]));
                }
                if (lineArr[3].equals("Chip")) {
                    item = new Chips(lineArr[0], lineArr[1], new BigDecimal(lineArr[2]), lineArr[3], Integer.parseInt(lineArr[4]));
                }
                if (lineArr[3].equals("Candy")) {
                    item = new Candy(lineArr[0], lineArr[1], new BigDecimal(lineArr[2]), lineArr[3], Integer.parseInt(lineArr[4]));
                }
                if (lineArr[3].equals("Gum")) {
                    item = new Gum(lineArr[0], lineArr[1], new BigDecimal(lineArr[2]), lineArr[3], Integer.parseInt(lineArr[4]));
                }

                stock.put(item.getCode(), item);

                report.put(item.getName(),0);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String displaySlots() {

        String allData = "";

        for (Map.Entry<String, Item> entry : this.stock.entrySet()) {
            String strName = entry.getValue().getName();
            String strCode = entry.getValue().getCode();
            BigDecimal strPrice = entry.getValue().getPrice();
            String strQuantity;
            if(entry.getValue().getQuantity() <= 0){
                strQuantity = "SOLD OUT";
            }else {
                strQuantity = Integer.toString(entry.getValue().getQuantity());
            }
            String strType = entry.getValue().getType();
            String entryData = strCode + " | " + strName + " | " + strPrice.toString() + " | " + strType + " | " + strQuantity + "\n";
            allData += entryData;
        }
        return allData;
    }

    public Map<String, Item> getStock() {
        return stock;
    }


    public String salesReport(){
      String allDataForReport = "";
       for (Map.Entry<String, Integer> entry : this.report.entrySet()) {
           String reportName = entry.getKey();
           int reportQuantity = entry.getValue();
           String reportLine = reportName + " | " + reportQuantity + "\n";
           allDataForReport += reportLine;
       }

         allDataForReport += "\n" + "TOTAL SALES = $" + totalSales.toString();
        return allDataForReport;
    }

    public BigDecimal getMoneyInMachine() {
        return moneyInMachine;
    }

    public void setMoneyInMachine(BigDecimal moneyInMachine) {
        this.moneyInMachine = moneyInMachine;
    }

    public String generateChange() {
        BigDecimal cash = moneyInMachine;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickel = new BigDecimal("0.05");
        //Greedy algorithm :)
        while (cash.compareTo(BigDecimal.ZERO) > 0) {
            if (cash.compareTo(quarter) >= 0) {
                cash = cash.subtract(quarter);
                quarters++;
            } else if (cash.compareTo(dime) >= 0) {
                cash = cash.subtract(dime);
                dimes++;
            } else {
                cash = cash.subtract(nickel);
                nickels++;
            }
        }

        return ("Change to return: " + quarters + " quarters " + dimes + " dimes " + nickels + " nickels ");
    }

    private void purchaseMenuCalculations(String option) {
        System.out.println("1) Feed Money");
        System.out.println("2) Select Product");
        System.out.println("3) Finish Transaction");

        System.out.println();
        System.out.println("Please select an option: ");
        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
        String newLogLine = "";

        boolean purchasing = true;
        File outputFile = new File("log.txt");

        while (purchasing) {
            newLogLine = currentDateTime.format(targetFormat).toString() + " ";
            if (option.equals("1")) {
                boolean feedingMoney = true;
                newLogLine += "FEED MONEY: ";
                while (feedingMoney) {
                    String moneyOption = UserInput.moneyOption();
                    if (moneyOption.equals("1")) {
                        moneyInMachine = moneyInMachine.add(BigDecimal.ONE);
                        newLogLine += " \\$1.00";
                        System.out.println("Added $1");
                        System.out.println("Total amount so far: $" + moneyInMachine.toString());
                        System.out.println();
                    } else if (moneyOption.equals("2")) {
                        moneyInMachine = moneyInMachine.add(BigDecimal.ONE).add(BigDecimal.ONE);
                        newLogLine += " \\$2.00";
                        System.out.println("Added $2");
                        System.out.println("Total amount so far: $" + moneyInMachine.toString());
                        System.out.println();
                    } else if (moneyOption.equals("3")) {
                        moneyInMachine = moneyInMachine.add(new BigDecimal("5.00"));
                        newLogLine += " \\$5.00";
                        System.out.println("Added $5");
                        System.out.println("Total amount so far: $" + moneyInMachine.toString());
                        System.out.println();
                    } else if (moneyOption.equals("4")) {
                        moneyInMachine = moneyInMachine.add(BigDecimal.TEN);
                        newLogLine += " \\$10.00";
                        System.out.println("Added $10");
                        System.out.println("Total amount so far: $" + moneyInMachine.toString());
                        System.out.println();
                    } else if (moneyOption.equals("5")) {
                        feedingMoney = false;
                    }
                }
                newLogLine = logWriter(newLogLine, outputFile);
                System.out.println("Current Money Provided: $" + moneyInMachine.toString());

            } else if (option.equals("2")) {
                String purchaseChoice = UserInput.purchaseChoice(this).trim();

                if (stock.containsKey(purchaseChoice)) {
                    Item item = stock.get(purchaseChoice);

                    if (item.getQuantity() > 0 && moneyInMachine.compareTo(item.getPrice()) >= 0) {
                        newLogLine += item.getName() + " " + item.getCode() + " " + "\\$" + getMoneyInMachine() + " " + "\\$" + (moneyInMachine.subtract(item.getPrice()));
                        item.decrementQuantity();
                        moneyInMachine = moneyInMachine.subtract(item.getPrice());
                        System.out.println("Bought " + item.getName() + " for " + item.getPrice());
                        System.out.println("Cash left: " + moneyInMachine);
                        System.out.println(item.message());
                        newLogLine = logWriter(newLogLine, outputFile);

                        totalSales = totalSales.add(item.getPrice());

                        report.put(item.getName(), report.get(item.getName()) + 1);

                    } else if (moneyInMachine.compareTo(item.getPrice()) < 0) {
                        System.out.println("Not enough funds to perform transaction...");
                    } else {
                        System.out.println("Item sold out. Sorry!");
                    }

                } else {
                    System.out.println("Product does not exist, please try again...");
                }
            } else if (option.equals("3")) {
                generateChange();
                newLogLine += "GIVE CHANGE: " + "\\$" + getMoneyInMachine() + " \\$" + "0.00" ;
                this.setMoneyInMachine(new BigDecimal("0.00"));
                newLogLine = logWriter(newLogLine, outputFile);
                purchasing = false;

            } else {
                UserOutput.displayMessage("Input not recognized, please choose an option...");
                ;
            }
            if (!option.equals("3")) {
                option = UserInput.purchaseMenu();
            } else {
                break;
            }

        }

    }

    public String logWriter(String newLogLine, File outputFile) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(outputFile, true))) {
            pw.println(newLogLine);
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

}


