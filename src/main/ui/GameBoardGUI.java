package ui;

import model.RuleSet;
import model.Tile;
import model.TileList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Represents the minesweeper window
public class GameBoardGUI extends JFrame {
    private RuleSet ruleSet;
    private TileList tileList;
    private List<TileGUI> board;
    private JPanel tilePanel;
    private MenuGUI menuGUI;
    private boolean firstMove;
    private int selectedIndex;
    private int numUnrevealedTiles;
    private int numMines;
    private int numFlags;

    // EFFECTS: constructs a new GameBoardGUI using the given ruleset
    public GameBoardGUI(RuleSet ruleSet, MenuGUI menuGUI) {
        super("Minesweeper");
        addWindowListener(new GameBoardListener());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.ruleSet = ruleSet;
        this.numUnrevealedTiles = this.ruleSet.getNumCols() * this.ruleSet.getNumRows();
        this.firstMove = true;
        this.numFlags = 0;
        this.tileList = new TileList(ruleSet);
        this.board = new ArrayList<>();
        this.menuGUI = menuGUI;
        tilePanel = new JPanel();
        tilePanel.setLayout(new GridLayout(ruleSet.getNumRows(), ruleSet.getNumCols()));
        this.board.clear();
        for (int index = 0; index < this.ruleSet.getNumCols() * this.ruleSet.getNumRows(); index++) {
            Tile tile = new Tile(index);
            board.add(new TileGUI(tile, this));
        }
        for (TileGUI tile : board) {
            tilePanel.add(tile.getLabel());
        }
        add(tilePanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: updates the board; if it is the first move, generates the board
    private void updateBoard() {
        Random random = new Random();
        int seed = random.nextInt();
        if (firstMove) {
            generateBoard(seed);
            this.numMines = (int)(ruleSet.getNumRows() * ruleSet.getNumCols() * ruleSet.getMineProportion());
            firstMove = false;
        }
        this.board.clear();
        for (Tile tile : tileList.getTileList()) {
            seed = random.nextInt();
            tile.updateDisplayValue(seed);
            board.add(new TileGUI(tile, this));
        }
        tilePanel.removeAll();
        for (TileGUI tile : board) {
            tilePanel.add(tile.getLabel());
            tile.updateLabel();
        }
    }

    // MODIFIES: this
    // EFFECTS: generates the board
    private void generateBoard(int seed) {
        this.tileList.generateTiles(this.ruleSet, this.selectedIndex, seed);
        this.tileList.getTileList().get(selectedIndex).setState(1);
        this.tileList.setNeighbouringMinesAllTiles();
    }

    // MODIFIES: this
    // EFFECTS: processes user action
    public void processAction(String action, int index) {
        selectedIndex = index;
        if (action.equals("reveal")) {
            processReveal();
        } else if (action.equals("flag")) {
            processFlag();
        }
        updateBoard();
        checkGameIsOver();
    }

    // REQUIRES: numRows and numCols in ruleSet are both > 0,
    //           this.board.getTileList().size() = numRows * numCols
    // MODIFIES: Tile in tileList
    // EFFECTS: reveals the selected tile
    private void processReveal() {
        if (this.tileList.getTileList().get(selectedIndex).getState() == 0) {
            this.tileList.getTileList().get(selectedIndex).setState(1);
        }
        this.numUnrevealedTiles = 0;
        for (Tile t : this.tileList.getTileList()) {
            if (!(t.getState() == 1)) {
                this.numUnrevealedTiles++;
            }
        }
    }

    // REQUIRES: numRows and numCols in ruleSet are both > 0
    //           this.board.getTileList().size() = numRows * numCols
    // MODIFIES: Tile in tileList
    // EFFECTS: flags the selected tile
    private void processFlag() {
        if (this.tileList.getTileList().get(selectedIndex).getState() == 0) {
            if (this.ruleSet.isFlagLimit()) {
                if (numFlags != this.ruleSet.getMaxFlags()) {
                    this.tileList.getTileList().get(selectedIndex).setState(2);
                    this.numFlags++;
                }
            } else {
                this.tileList.getTileList().get(selectedIndex).setState(2);
            }
        } else if (this.tileList.getTileList().get(selectedIndex).getState() == 2) {
            this.tileList.getTileList().get(selectedIndex).setState(0);
            this.numFlags--;
        }
    }

    // REQUIRES: 0 <= this.index < this.board.getTileList().size()
    // MODIFIES: this
    // EFFECTS: checks if game is over and handles the game over sequence
    private void checkGameIsOver() {
        List<Tile> tileList = this.tileList.getTileList();
        if (tileList.get(selectedIndex).isMine() && (tileList.get(selectedIndex).getState() == 1)) {
            menuGUI.setTextDisplay("Hit a mine...");
            this.ruleSet.gameFinished(false);
            closeGameBoardGUI();
            this.menuGUI.returnToMenu();
        } else if (this.numUnrevealedTiles == this.numMines) {
            menuGUI.setTextDisplay("You win!");
            this.ruleSet.gameFinished(true);
            closeGameBoardGUI();
            this.menuGUI.returnToMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: closes the minesweeper window and returns to the main menu
    private void closeGameBoardGUI() {
        setVisible(false);
        dispose();
        this.menuGUI.returnToMenu();
    }

    // Represents a WindowListener for GameBoardGUI that handles closing the window
    private class GameBoardListener extends WindowAdapter {
        // EFFECTS: end the current game, treating it as a loss
        @Override
        public void windowClosing(WindowEvent e) {
            ruleSet.gameFinished(false);
            closeGameBoardGUI();
        }
    }
}