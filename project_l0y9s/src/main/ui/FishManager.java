package ui;

import model.Ship;
import model.Fish;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// REFERENCED FROM FITLIFEGYMKiosk

public class FishManager {

    private static final String JSON_STORE = "./data/ship.json";
    private final Scanner input;
    private boolean runProgram;
    private Ship ship;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: represents the operations and instructions tha that will e performed
    public FishManager(Ship ship) throws FileNotFoundException {
        this.ship = ship;
        input = new Scanner(System.in);
        runProgram = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        handleUserInput();
    }

    //EFFECTS: handles user input until user quits
    public void handleUserInput() {
        printInstructions();
        String str;

        while (runProgram) {
            str = getUserInputString();
            nextOptions(str);
        }
    }

    // EFFECTS : prints the instructions to use the program
    private void printInstructions() {
        System.out.println("\nYou can request the following information:\n");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Enter '" + 1 + "' for the list of caught fish in this ship");
        System.out.println("Enter '" + 2 + "' for the ship's information");
        System.out.println("Enter '" + 3 + "' to add a fish");
        System.out.println("Enter '" + 4 + "' to remove all fish");
        System.out.println("Enter '" + 5 + "' to quit the program");
        System.out.println("Enter '" + 6 + "' to save the ship to file");
        System.out.println("Enter '" + 7 + "' to load the ship from file");
        System.out.println("--------------------------------------------------------------");
    }

    //EFFECTS: prints the next menu options and information according to input str
    private void nextOptions(String str) {
        if (str.length() > 0) {
            switch (str) {
                case "1":
                    System.out.println(ship.getSpeciesCaughtFish());
                    printInstructions();
                    break;
                case "2":
                    System.out.println("Name:" + ship.getName() + ", total current weight:" + ship.getCurrentWeight());
                    printInstructions();
                    break;
                case "3":
                    extraOptions();
                    break;
                case "4":
                    warningOption();
                    break;
                default:
                    moreOptions(str);
            }
        }
    }

    //EFFECTS: prints the next menu options and information according to input str
    private void moreOptions(String str) {
        if (str.length() > 0) {
            switch (str) {
                case "5":
                    runProgram = false;
                    endProgram();
                    break;
                case "6":
                    saveShip();
                    break;
                case "7":
                    loadShip();
                    break;
            }
        }
    }

    // EFFECTS: prints extra menu option to add a fish by inputting species and weight of the fish
    public void extraOptions() {
        System.out.println("What is the type of the fish?");
        String type = getUserInputString();
        System.out.println("Please type the weight of the fish in kg");
        int angka = getUserInputInt();
        Fish yu = new Fish(type, angka);
        if (ship.addWeight(yu)) {
            ship.addAFish(yu);
            System.out.println("Fish has been added!");
            printInstructions();
        } else {
            System.out.println("Containers are already full! Go back to unload all fish!");
        }
    }

    // EFFECTS: prints out warning menu to make sure when clearing all fish
    public void warningOption() {
        System.out.println("Clear all fish?");
        System.out.println("Enter y for yes");
        System.out.println("Enter n for no");
        String str = getUserInputString();

        if (str.length() > 0) {
            switch (str) {
                case "y":
                    ship.removeAllFish();
                    ship.removeAllWeight();
                    System.out.println("ALL FISH HAS BEEN REMOVED!");
                    break;
                case "n":
                    System.out.println("FISH ARE NOT REMOVED");
                    break;
            }
        }
        printInstructions();
    }

    //EFFECTS: stops receiving user input
    public void endProgram() {
        System.out.println("Shutting down...");
        input.close();
    }

    // EFFECTS: gets the user's string input
    private String getUserInputString() {
        String str = "";
        if (input.hasNext()) {
            str = input.nextLine();
        }
        return str;
    }

    // EFFECTS: gets the user's integer input
    private int getUserInputInt() {
        int angka = 0;
        if (input.hasNextInt()) {
            angka = input.nextInt();
        }
        return angka;
    }

    // EFFECTS: saves the ship to file
    private void saveShip() {
        try {
            jsonWriter.open();
            jsonWriter.write(ship);
            jsonWriter.close();
            System.out.println("Saved " + ship.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads ship from file
    private void loadShip() {
        try {
            ship = jsonReader.read();
            System.out.println("Loaded " + ship.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

