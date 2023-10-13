package ui;

import model.RuleSet;
import model.Tile;
import model.TileList;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Displays the game board
// Code based off of the TellerApp class from the Teller application
public class GameBoard {
    private TileList board;
    private RuleSet ruleSet;
    private Scanner input;
    private int index;
    private int coordX;
    private int coordY;
    private int numUnrevealedTiles;
    private int numMines;
    private int numFlags;
    private boolean gameIsActive;

    // EFFECTS: initializes an empty board and runs the process of revealing the first tile
    public GameBoard(RuleSet ruleSet) {
        System.out.println("Initializing game...\n");
        this.ruleSet = ruleSet;
        this.gameIsActive = true;
        this.numUnrevealedTiles = this.ruleSet.getNumCols() * this.ruleSet.getNumRows();
        this.numFlags = 0;
        this.board = new TileList(ruleSet);
        this.input = new Scanner(System.in);
        displayBoard();
        firstTile();
    }

    // EFFECTS: reveals the first tile then runs the board
    private void firstTile() {
        processReveal();
        runBoard();
    }

    // REQUIRES: 0 <= this.index < this.board.getTileList().size()
    // MODIFIES: this
    // EFFECTS: properly sets up the board, then processes user input
    private void runBoard() {
        Random random = new Random();
        int seed = random.nextInt();
        String action;
        this.board.generateTiles(this.ruleSet, this.index, seed);
        this.board.getTileList().get(index).setState(1);
        this.board.setNeighbouringMinesAllTiles();
        for (Tile t : this.board.getTileList()) {
            seed = random.nextInt();
            t.updateDisplayValue(seed);
        }
        this.numMines = (int)(this.ruleSet.getNumRows() * this.ruleSet.getNumCols() * this.ruleSet.getMineProportion());
        while (gameIsActive) {
            displayBoard();
            displayActions();
            action = input.next();
            action = action.toLowerCase();
            processAction(action);
            checkGameIsOver();
        }
    }

    // REQUIRES: numRows and numCols in ruleSet are both > 0,
    //           this.board.getTileList().size() = numRows * numCols
    // EFFECTS: displays the board
    private void displayBoard() {
        int position = 0;
        for (int i = 1; i <= (this.ruleSet.getNumRows() * this.ruleSet.getNumCols()); i++) {
            if (this.board.getTileList().get(position).getState() == 0) {
                System.out.print("|_|");
            } else if (this.board.getTileList().get(position).getState() == 2) {
                System.out.print(" X ");
            } else if (this.board.getTileList().get(position).getDisplayValue().equals("mine")) {
                System.out.print(this.board.getTileList().get(position).getDisplayValue());
            } else if (this.board.getTileList().get(position).isRange()
                    && !this.board.getTileList().get(position).isQuestionMark()) {
                System.out.print(this.board.getTileList().get(position).getDisplayValue());
            } else {
                System.out.print(" " + this.board.getTileList().get(position).getDisplayValue() + " ");
            }
            if ((position + 1) % this.ruleSet.getNumCols() == 0) {
                System.out.println();
            }
            position++;
        }
    }

    // EFFECTS: processes user action
    private void processAction(String action) {
        if (action.equals("reveal")) {
            processReveal();
        } else if (action.equals("flag")) {
            processFlag();
        } else {
            System.out.println("Invalid action - please select a valid action\n");
        }
    }

    // REQUIRES: numCols in ruleSet > 0
    // MODIFIES: this
    // EFFECTS: selects the currently selected column
    private void chooseColumn() {
        boolean chooseColumn = true;
        while (chooseColumn) {
            System.out.println("Choose the column of the tile to be revealed:\n");
            int col = input.nextInt();
            if (0 < col && col <= this.ruleSet.getNumCols()) {
                chooseColumn = false;
                this.coordX = col;
            } else {
                System.out.println("Cannot select invalid column\n");
            }
        }
    }

    // REQUIRES: numRows in ruleSet > 0
    // MODIFIES: this
    // EFFECTS: selects the currently selected row
    private void chooseRow() {
        boolean chooseRow = true;
        while (chooseRow) {
            System.out.println("Choose the row of the tile to be revealed:\n");
            int row = input.nextInt();
            if (0 < row && row <= this.ruleSet.getNumRows()) {
                chooseRow = false;
                this.coordY = row;

            } else {
                System.out.println("Cannot select invalid row\n");
            }
        }
    }

    // REQUIRES: numRows and numCols in ruleSet are both > 0,
    //           this.board.getTileList().size() = numRows * numCols
    // MODIFIES: Tile in board
    // EFFECTS: reveals the selected tile
    private void processReveal() {
        chooseColumn();
        chooseRow();
        convertCoordsToIndex(this.coordX, this.coordY);
        if (this.board.getTileList().get(this.index).getState() == 0) {
            this.board.getTileList().get(index).setState(1);
        } else if (this.board.getTileList().get(this.index).getState() == 2) {
            System.out.println("Cannot reveal flagged tiles!\n");
        } else {
            System.out.println("Cannot reveal a tile that is already revealed!\n");
        }
        this.numUnrevealedTiles = 0;
        for (Tile t : this.board.getTileList()) {
            if (!(t.getState() == 1)) {
                this.numUnrevealedTiles++;
            }
        }
    }

    // REQUIRES: numRows and numCols in ruleSet are both > 0
    //           this.board.getTileList().size() = numRows * numCols
    // MODIFIES: Tile in board
    // EFFECTS: flags the selected tile
    private void processFlag() {
        chooseColumn();
        chooseRow();
        convertCoordsToIndex(this.coordX, this.coordY);
        if (this.board.getTileList().get(this.index).getState() == 0) {
            if (this.ruleSet.isFlagLimit()) {
                if (numFlags == this.ruleSet.getMaxFlags()) {
                    System.out.println("Cannot exceed flag limit!\n");
                } else {
                    this.board.getTileList().get(index).setState(2);
                    this.numFlags++;
                }
            } else {
                this.board.getTileList().get(index).setState(2);
            }
        } else if (this.board.getTileList().get(this.index).getState() == 2) {
            this.board.getTileList().get(index).setState(0);
            this.numFlags--;
        } else {
            System.out.println("Cannot flag a revealed tile!\n");
        }
    }

    // REQUIRES: x, y, numRows, numCols in ruleSet all > 0
    // MODIFIES: this
    // EFFECTS: converts the row and column of a tile
    //          into the corresponding index in board
    private void convertCoordsToIndex(int x, int y) {
        this.index = ((y - 1) * this.ruleSet.getNumCols() + x - 1);
    }

    // EFFECTS: displays the possible actions the user can perform
    private void displayActions() {
        System.out.println("\n Choose an action:");
        System.out.println("\"reveal\" - reveal a tile");
        System.out.println("\"flag\" - flag a tile");
    }

    // REQUIRES: 0 <= this.index < this.board.getTileList().size()
    // MODIFIES: this
    // EFFECTS: checks if game is over and handles the game over sequence
    private void checkGameIsOver() {
        List<Tile> tileList = board.getTileList();
        if (tileList.get(index).isMine() && (tileList.get(index).getState() == 1)) {
            displayBoard();
            System.out.println("Hit a mine...");
            this.ruleSet.gameFinished(false);
            this.gameIsActive = false;
        } else if (this.numUnrevealedTiles == this.numMines) {
            displayBoard();
            System.out.println("You win!");
            this.ruleSet.gameFinished(true);
            this.gameIsActive = false;
        }
    }
}
