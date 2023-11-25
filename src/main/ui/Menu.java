package ui;

import model.RuleSet;
import model.RuleSetList;
import persistence.DataReader;
import persistence.DataWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Displays the main menu
// Code based off of the TellerApp class from the Teller application
public class Menu {
    private RuleSetList savedRuleSets;
    private Scanner input;
    private DataWriter dataWriter;
    private DataReader dataReader;
    private static final String DATA_STORAGE = "./data/savefile.json";

    // EFFECTS: initializes the list of rulesets then runs the menu
    public Menu() {
        dataWriter = new DataWriter(DATA_STORAGE);
        dataReader = new DataReader(DATA_STORAGE);
        savedRuleSets = new RuleSetList();
        input = new Scanner(System.in);
        runMenu();
    }

    // MODIFIES: this
    // EFFECTS: process user input
    public void runMenu() {
        boolean menuIsActive = true;
        String action;
        while (menuIsActive) {
            displaySavedRuleSets();
            displaySelectedRuleSet();
            displayMenu();
            action = input.next();
            action = action.toLowerCase();
            if (action.equals("quit")) {
                menuIsActive = false;
            } else {
                processAction(action);
            }
        }
    }

    // EFFECTS: displays the possible actions the user can perform
    private void displayMenu() {
        System.out.println("\n Choose an option:");
        System.out.println("\"add\" - add a new default ruleset");
        System.out.println("\"remove\" - remove the currently selected ruleset");
        System.out.println("\"select\" - select a ruleset from the saved rulesets");
        System.out.println("\"edit\" - edit the currently selected ruleset");
        System.out.println("\"stats\" - view statistics about the currently selected ruleset");
        System.out.println("\"play\" - play a game using the currently selected ruleset");
        System.out.println("\"save\" - save the list of rulesets to a file");
        System.out.println("\"load\" - load a list of rulesets from a file");
        System.out.println("\"quit\" - exit the application");
    }

    // EFFECTS: display the selected ruleset
    private void displaySelectedRuleSet() {
        RuleSet selected = savedRuleSets.getCurrentlySelectedRuleSet();
        System.out.println("Selected ruleset:");
        printRuleSet(selected);
        System.out.print("\n");
    }

    // EFFECTS: display the list of saved rulesets
    private void displaySavedRuleSets() {
        List<RuleSet> savedRuleSets = this.savedRuleSets.getRuleSetList();
        System.out.println("List of saved rulesets:");
        for (RuleSet ruleSet : savedRuleSets) {
            printRuleSet(ruleSet);
        }
        System.out.print("\n");
    }

    // EFFECTS: prints ruleset to console
    private void printRuleSet(RuleSet ruleSet) {
        System.out.print(ruleSet.getName());
        System.out.print(", ");
        System.out.print(ruleSet.getNumRows());
        System.out.print(", ");
        System.out.print(ruleSet.getNumCols());
        System.out.print(", ");
        System.out.print(ruleSet.getMineProportion());
        System.out.print(", ");
        System.out.print(ruleSet.isFlagLimit());
        System.out.print(", ");
        System.out.print(ruleSet.getMaxFlags());
        System.out.print(", ");
        System.out.print(ruleSet.getRangeChance());
        System.out.print(", ");
        System.out.println(ruleSet.getQuestionMarkChance());
    }

    // MODIFIES: this
    // EFFECTS: processes user action
    private void processAction(String action) {
        if (action.equals("add")) {
            processAdd();
        } else if (action.equals("remove")) {
            processRemove();
        } else if (action.equals("select")) {
            processSelect();
        } else if (action.equals("edit")) {
            processEdit();
        } else if (action.equals("stats")) {
            processStats();
        } else if (action.equals("play")) {
            processPlay();
        } else if (action.equals("save")) {
            processSave();
        } else if (action.equals("load")) {
            processLoad();
        } else {
            System.out.println("Invalid action - please select a valid action\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: add a default ruleset
    private void processAdd() {
        this.savedRuleSets.addRuleSet();
        System.out.println("New default ruleset added\n");
    }

    // MODIFIES: this
    // EFFECTS: removes the currently selected ruleset from the list
    //          unless it is the last ruleset in the list;
    //          sets the first ruleset as the currently selected ruleset
    private void processRemove() {
        int sizeBefore = this.savedRuleSets.getRuleSetList().size();
        this.savedRuleSets.removeRuleSet();
        int sizeAfter = this.savedRuleSets.getRuleSetList().size();
        if (sizeBefore == sizeAfter) {
            System.out.println("Last ruleset cannot be removed\n");
        } else {
            System.out.println("Ruleset removed\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the currently selected ruleset
    private void processSelect() {
        int size = this.savedRuleSets.getRuleSetList().size();
        System.out.println("Input the value of the desired ruleset's order in the list");
        int select = input.nextInt();
        if (select > size || select <= 0) {
            System.out.println("Cannot select invalid ruleset\n");
        } else {
            this.savedRuleSets.setSelectedRuleSetIndex(select - 1);
            String name = this.savedRuleSets.getCurrentlySelectedRuleSet().getName();
            System.out.println("Selected ruleset " + select + " - \"" + name + "\"\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: edits the currently selected ruleset
    private void processEdit() {
        System.out.println("Input the new name");
        String name = input.next();
        System.out.println("Input the new number of rows:");
        int rows = input.nextInt();
        System.out.println("Input the new number of columns:");
        int cols = input.nextInt();
        System.out.println("Input the new proportion of tiles that are mines:");
        double mineProp = input.nextDouble();
        System.out.println("Input whether or not there is a limit on the number of placeable flags:");
        boolean flagLimit = input.nextBoolean();
        System.out.println("Input the new maximum number of placeable flags:");
        int maxFlags = input.nextInt();
        System.out.println("Input the new chance for a tile to display a range:");
        double range = input.nextDouble();
        System.out.println("Input the new chance for a tile to display a question mark:");
        double questionMark = input.nextDouble();
        this.savedRuleSets.editExistingRuleSet(name, rows, cols, mineProp, flagLimit, maxFlags, range, questionMark);
    }

    // EFFECTS: displays the statistics for the currently selected ruleset
    private void processStats() {
        String name = this.savedRuleSets.getCurrentlySelectedRuleSet().getName();
        int gamesPlayed = this.savedRuleSets.getCurrentlySelectedRuleSet().getGamesPlayed();
        int gamesWon = this.savedRuleSets.getCurrentlySelectedRuleSet().getGamesWon();
        double winPercent = this.savedRuleSets.getCurrentlySelectedRuleSet().getWinPercent() * 100;
        System.out.println("Statistics for ruleset \"" + name + "\":");
        System.out.println("Games played: " + gamesPlayed);
        System.out.println("Games won: " + gamesWon);
        System.out.println("Win Percentage " + winPercent + "%\n");
    }

    // EFFECTS: initializes the minesweeper board for the currently selected ruleset
    private void processPlay() {
        new GameBoard(this.savedRuleSets.getCurrentlySelectedRuleSet());
    }

    // MODIFIES: this
    // EFFECTS: saves the list of rulesets to a file
    private void processSave() {
        try {
            dataWriter.write(savedRuleSets);
            System.out.println("Data saved to " + DATA_STORAGE + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Save failed\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads a list of rulesets from a file
    private void processLoad() {
        try {
            savedRuleSets = dataReader.read();
            System.out.println("Data loaded from " + DATA_STORAGE + "\n");
        } catch (IOException e) {
            System.out.println("Load failed\n");
        }
    }
}
