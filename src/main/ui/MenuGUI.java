package ui;

import model.Event;
import model.EventLog;
import model.RuleSet;
import model.RuleSetList;
import persistence.DataReader;
import persistence.DataWriter;
import ui.buttons.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the main menu window
public class MenuGUI extends JFrame {
    private RuleSetList savedRuleSets;
    private DataWriter dataWriter;
    private DataReader dataReader;
    private static final String DATA_STORAGE = "./data/savefile.json";
    private GameBoardGUI game;

    // JFrame Components
    private JList ruleSetList;
    private DefaultListModel ruleSetListModel;
    private JTextField textField;
    private JLabel textDisplay;
    private AddButton addButton;
    private RemoveButton removeButton;
    private EditButton editButton;
    private StatsButton statsButton;
    private SaveButton saveButton;
    private LoadButton loadButton;
    private PlayButton playButton;
    private EnterButton enterButton;

    // These fields facilitate editing the selected ruleset
    private String input;
    private String editName;
    private int editNumRows;
    private int editNumCols;
    private double editMineProportion;
    private boolean editFlagLimit;
    private int editMaxFlags;
    private double editRangeChance;
    private double editQuestionMarkChance;
    private int state;

    // EFFECTS: constructs a new MenuGUI
    public MenuGUI() {
        super("Menu");
        state = 0;
        dataWriter = new DataWriter(DATA_STORAGE);
        dataReader = new DataReader(DATA_STORAGE);
        savedRuleSets = new RuleSetList();
        addWindowListener(new MenuListener());
        setupButtonPanel();
        ruleSetListModel = new DefaultListModel();
        updateRuleSetListModel();
        ruleSetList = new JList(ruleSetListModel);
        ruleSetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ruleSetList.setSelectedIndex(savedRuleSets.getSelectedRuleSetIndex());
        ruleSetList.addListSelectionListener(new RuleSetListPanelListener());
        JScrollPane listPanel = new JScrollPane(ruleSetList);
        add(listPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up the button panel for a MenuGUI
    private void setupButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JPanel firstRow = new JPanel();
        JPanel secondRow = new JPanel();
        JPanel thirdRow = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        firstRow.setLayout(new GridLayout(1, 0));
        secondRow.setLayout(new GridLayout(1, 0));
        thirdRow.setLayout(new BoxLayout(thirdRow, BoxLayout.LINE_AXIS));
        setupButtonPanelComponents(firstRow, secondRow, thirdRow);
        buttonPanel.add(firstRow);
        buttonPanel.add(secondRow);
        buttonPanel.add(thirdRow);
        buttonPanel.add(textDisplay);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: sets up the individual components of the button panel (for a MenuGUI)
    private void setupButtonPanelComponents(JPanel firstRow, JPanel secondRow, JPanel thirdRow) {
        addButton = new AddButton(firstRow, this);
        removeButton = new RemoveButton(firstRow, this);
        editButton = new EditButton(firstRow, this);
        statsButton = new StatsButton(firstRow, this);
        saveButton = new SaveButton(secondRow, this);
        loadButton = new LoadButton(secondRow, this);
        playButton = new PlayButton(secondRow, this);
        textField = new JTextField();
        thirdRow.add(textField);
        textDisplay = new JLabel();
        enterButton = new EnterButton(thirdRow, this);
        enterButton.disable();
        textField.setEnabled(false);
    }

    // EFFECTS: converts ruleset to a string
    private String ruleSetToString(RuleSet ruleSet) {
        return ruleSet.getName() + ", "
                + ruleSet.getNumRows() + ", "
                + ruleSet.getNumCols() + ", "
                + ruleSet.getMineProportion() + ", "
                + ruleSet.isFlagLimit() + ", "
                + ruleSet.getMaxFlags() + ", "
                + ruleSet.getRangeChance() + ", "
                + ruleSet.getQuestionMarkChance();
    }

    // MODIFIES: this
    // EFFECTS: processes the state of the MenuGUI.
    //          - State 0 - the default state
    //          - State 1 - the state where the user must edit the name of the selected ruleset
    //          - State 2 - the state where the user must edit the number of rows in the selected ruleset
    //          - State 3 - the state where the user must edit the number of columns in the selected ruleset
    //          - State 4 - the state where the user must edit the proportion of mines in the selected ruleset
    //          - State 5 - the state where the user must edit the flag limit of the selected ruleset
    //          - State 6 - the state where the user must edit the maximum number of flags in the selected ruleset
    //          - State 7 - the state where the user must edit the range chance in the selected ruleset
    //          - State 8 - the state where the user must edit the question mark chance in the selected ruleset
    private void processState() {
        if (state == 1) {
            processState1();
        } else if (state == 2) {
            processState2();
        } else if (state == 3) {
            processState3();
        } else if (state == 4) {
            processState4();
        } else if (state == 5) {
            processState5();
        } else if (state == 6) {
            processState6();
        } else if (state == 7) {
            processState7();
        } else if (state == 8) {
            processState8();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes state 1
    private void processState1() {
        editName = input;
        textDisplay.setText("Enter the new number of rows:");
    }

    // MODIFIES: this
    // EFFECTS: processes state 2
    private void processState2() {
        editNumRows = Integer.parseInt(input);
        textDisplay.setText("Enter the new number of columns:");
    }

    // MODIFIES: this
    // EFFECTS: processes state 3
    private void processState3() {
        editNumCols = Integer.parseInt(input);
        textDisplay.setText("Enter the new proportion of tiles that are mines:");
    }

    // MODIFIES: this
    // EFFECTS: processes state 4
    private void processState4() {
        editMineProportion = Double.parseDouble(input);
        textDisplay.setText("Enter whether or not there is a limit on the number of placeable flags:");
    }

    // MODIFIES: this
    // EFFECTS: processes state 5
    private void processState5() {
        editFlagLimit = Boolean.parseBoolean(input);
        textDisplay.setText("Enter the new maximum number of placeable flags:");
    }

    // MODIFIES: this
    // EFFECTS: processes state 6
    private void processState6() {
        editMaxFlags = Integer.parseInt(input);
        textDisplay.setText("Enter the new chance for a tile to display a range:");
    }

    // MODIFIES: this
    // EFFECTS: processes state 7
    private void processState7() {
        editRangeChance = Double.parseDouble(input);
        textDisplay.setText("Enter the new chance for a tile to display a question mark:");
    }

    // MODIFIES: this
    // EFFECTS: processes state 8
    private void processState8() {
        editQuestionMarkChance = Double.parseDouble(input);
        this.savedRuleSets.editExistingRuleSet(editName, editNumRows, editNumCols, editMineProportion,
                editFlagLimit, editMaxFlags, editRangeChance, editQuestionMarkChance);
        textDisplay.setText("Ruleset editing complete");
        addButton.enable();
        removeButton.enable();
        editButton.enable();
        statsButton.enable();
        playButton.enable();
        saveButton.enable();
        loadButton.enable();
        enterButton.disable();
    }

    // MODIFIES: this
    // EFFECTS: processes user action
    public void processAction(String action) {
        int selectedIndex = ruleSetList.getSelectedIndex();
        if (action.equals("add")) {
            processAdd();
            selectedIndex = savedRuleSets.getRuleSetList().size() - 1;
        } else if (action.equals("remove")) {
            processRemove();
            selectedIndex = 0;
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
            selectedIndex = savedRuleSets.getSelectedRuleSetIndex();
        } else if (action.equals("enter")) {
            processEnter();
        }
        updateRuleSetListModel();
        ruleSetList.setSelectedIndex(selectedIndex);
    }

    // MODIFIES: this
    // EFFECTS: updates the model for the ruleset list
    private void updateRuleSetListModel() {
        ruleSetListModel.removeAllElements();
        for (RuleSet ruleSet : savedRuleSets.getRuleSetList()) {
            ruleSetListModel.addElement(ruleSetToString(ruleSet));
        }
    }

    // MODIFIES: this
    // EFFECTS: add a default ruleset and set it as the currently selected ruleset
    private void processAdd() {
        this.savedRuleSets.addRuleSet();
        ruleSetList.setSelectedIndex(savedRuleSets.getSelectedRuleSetIndex());
        textDisplay.setText("New default ruleset added");
    }

    // MODIFIES: this
    // EFFECTS: removes the currently selected ruleset from the list
    //          unless it is the last ruleset in the list;
    //          sets the first ruleset as the currently selected ruleset
    private void processRemove() {
        int index = ruleSetList.getSelectedIndex();
        if (index != -1) {
            int sizeBefore = this.savedRuleSets.getRuleSetList().size();
            this.savedRuleSets.removeRuleSet();
            ruleSetList.setSelectedIndex(savedRuleSets.getSelectedRuleSetIndex());
            int sizeAfter = this.savedRuleSets.getRuleSetList().size();
            if (sizeBefore == sizeAfter) {
                textDisplay.setText("Last ruleset cannot be removed");
            } else {
                textDisplay.setText("Ruleset removed");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: edits the currently selected ruleset
    private void processEdit() {
        advanceState();
        addButton.disable();
        removeButton.disable();
        editButton.disable();
        statsButton.disable();
        playButton.disable();
        saveButton.disable();
        loadButton.disable();
        enterButton.enable();
        textField.setEnabled(true);
        textDisplay.setText("Enter the new name");
    }

    // EFFECTS: displays the statistics for the currently selected ruleset
    private void processStats() {
        textDisplay.setText(this.savedRuleSets.getSelectedRuleSetStats());
    }

    // MODIFIES: this
    // EFFECTS: initializes the minesweeper board for the currently selected ruleset
    private void processPlay() {
        textDisplay.setText("Playing minesweeper using \""
                + savedRuleSets.getCurrentlySelectedRuleSet().getName() + "\"");
        addButton.disable();
        removeButton.disable();
        editButton.disable();
        statsButton.disable();
        playButton.disable();
        saveButton.disable();
        loadButton.disable();
        enterButton.disable();
        textField.setEnabled(false);
        ruleSetList.setEnabled(false);
        game = new GameBoardGUI(this.savedRuleSets.getCurrentlySelectedRuleSet(), this);
    }

    // MODIFIES: this
    // EFFECTS: saves the list of rulesets to a file
    private void processSave() {
        try {
            dataWriter.write(savedRuleSets);
            textDisplay.setText("Data saved to " + DATA_STORAGE + "\n");
        } catch (FileNotFoundException e) {
            textDisplay.setText("Save failed\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads a list of rulesets from a file
    private void processLoad() {
        try {
            savedRuleSets = dataReader.read();
            textDisplay.setText("Data loaded from " + DATA_STORAGE + "\n");
        } catch (IOException e) {
            textDisplay.setText("Load failed\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes the input entered, effect depdends on the current state of the MenuGUI
    private void processEnter() {
        setInput(getTextField());
        processState();
        advanceState();
        resetTextField();
    }

    // MODIFIES: this
    // EFFECTS: advances the state of the program
    private void advanceState() {
        if (state == 8) {
            state = 0;
        } else {
            state++;
        }
    }

    // MODIFIES: this
    // EFFECTS: resets textField
    public void resetTextField() {
        textField.setText("");
    }

    // EFFECTS: returns the string in textField
    public String getTextField() {
        return textField.getText();
    }

    public void setInput(String string) {
        input = string;
    }

    // EFFECTS: sets the text of textDisplay to the given string
    public void setTextDisplay(String string) {
        textDisplay.setText(string);
    }

    // MODIFIES: this
    // EFFECTS: appropriately enables/disables the JFrame components
    //          of MenuGUI when a game of minesweeper ends
    public void returnToMenu() {
        addButton.enable();
        removeButton.enable();
        editButton.enable();
        statsButton.enable();
        playButton.enable();
        saveButton.enable();
        loadButton.enable();
        enterButton.disable();
        textField.setEnabled(false);
        ruleSetList.setEnabled(true);
    }

    // Represents a ListSelectionListener for ruleSetList
    private class RuleSetListPanelListener implements ListSelectionListener {
        // EFFECTS: sets the currently selected ruleset to the one that was selected in the JList
        @Override
        public void valueChanged(ListSelectionEvent e) {
            savedRuleSets.setSelectedRuleSetIndex(ruleSetList.getSelectedIndex());
        }
    }

    // Represents a WindowListener for GameBoardGUI that handles closing the window
    private class MenuListener extends WindowAdapter {
        // EFFECTS: prints all events to the console then quits the application
        @Override
        public void windowClosing(WindowEvent e) {
            EventLog eventLog = EventLog.getInstance();
            for (Event event : eventLog) {
                System.out.println(event.toString());
            }
            System.exit(0);
        }
    }
}