package model;

import java.util.Random;

// Represents a tile on a board.
// The state field is an integer in the range [0, 2] that represents the state of the tile:
// State 0 - tile is unrevealed
// State 1 - tile is revealed
// State 2 - tile is flagged
// The boardSection field is an integer in the range [1, 9] that corresponds to what
// section of the board the tile is in. The sections of any given board are as follows:
// - Section 1 is the top-left corner
// - Section 2 is the topmost row, excluding the ends
// - Section 3 is the top-right corner
// - Section 4 is the leftmost column, excluding the ends
// - Section 5 is the middle of the board (i.e. not on the edge of the board)
// - Section 6 is the rightmost column, excluding the ends
// - Section 7 is the bottom-left corner
// - Section 8 is the bottommost row, excluding the ends
// - Section 9 is the bottom-right corner
public class Tile {
    private String displayValue;
    private int state;
    private boolean mine;
    private int index;
    private int boardSection;
    private int numNeighbouringMines;
    private boolean range;
    private boolean questionMark;

    // REQUIRES: index >= 0
    // EFFECTS: constructs a placeholder unrevealed tile at the given index
    public Tile(int index) {
        this.displayValue = "0";
        this.state = 0;
        this.mine = false;
        this.index = index;
        this.boardSection = 0;
        this.numNeighbouringMines = 0;
        this.range = false;
        this.questionMark = false;
    }

    // REQUIRES: 0 <= index <= ruleSetList.size() - 1
    // EFFECTS: constructs a new unrevealed tile at the given index using a given ruleset
    public Tile(RuleSet ruleSet, MineLayout mineLayout, int index, int seed) {
        this.index = index;
        setBoardSection(ruleSet);
        this.state = 0;
        this.mine = mineLayout.getMineLayout().get(index);
        Random random = new Random();
        random.setSeed(seed);
        double firstRandomDouble = random.nextDouble();
        double secondRandomDouble = random.nextDouble();
        this.range = (firstRandomDouble < ruleSet.getRangeChance());
        this.questionMark = (secondRandomDouble < ruleSet.getQuestionMarkChance());
    }

    // REQUIRES: numRows and numCols in ruleSet are both > 0
    // MODIFIES: this
    // EFFECTS: sets boardSection according to the class-level comment
    private void setBoardSection(RuleSet ruleSet) {
        if (this.index == 0) {
            this.boardSection = 1;
        } else if (this.index == (ruleSet.getNumCols() - 1)) {
            this.boardSection = 3;
        } else if (this.index == ((ruleSet.getNumRows() - 1) * ruleSet.getNumCols())) {
            this.boardSection = 7;
        } else if (this.index == (ruleSet.getNumRows() * ruleSet.getNumCols() - 1)) {
            this.boardSection = 9;
        } else if (this.index < (ruleSet.getNumCols() - 1)) {
            this.boardSection = 2;
        } else if (this.index % ruleSet.getNumCols() == 0) {
            this.boardSection = 4;
        } else if (this.index % ruleSet.getNumCols() == (ruleSet.getNumCols() - 1)) {
            this.boardSection = 6;
        } else if (this.index > ((ruleSet.getNumRows() - 1) * ruleSet.getNumCols())) {
            this.boardSection = 8;
        } else {
            this.boardSection = 5;
        }
    }

    // MODIFIES: this
    // EFFECTS: updates displayValue
    public void updateDisplayValue(int seed) {
        if (mine) {
            this.displayValue = "mine";
        } else if (questionMark) {
            this.displayValue = "?";
        } else if (range) {
            updateDisplayValueRange(seed);
        } else {
            this.displayValue = Integer.toString(this.numNeighbouringMines);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates displayValue to be a range
    public void updateDisplayValueRange(int seed) {
        Random random = new Random();
        random.setSeed(seed);
        int randomInt = random.nextInt(2);
        if (this.numNeighbouringMines == 0) {
            this.displayValue = "0-1";
        } else if (this.numNeighbouringMines == 8) {
            this.displayValue = "7-8";
        } else if (randomInt == 0) {
            String lower = Integer.toString(this.numNeighbouringMines - 1);
            String upper = Integer.toString(this.numNeighbouringMines);
            this.displayValue = lower + "-" + upper;
        } else {
            String lower = Integer.toString(this.numNeighbouringMines);
            String upper = Integer.toString(this.numNeighbouringMines + 1);
            this.displayValue = lower + "-" + upper;
        }
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public int getIndex() {
        return index;
    }

    public boolean isMine() {
        return mine;
    }

    public int getBoardSection() {
        return boardSection;
    }

    public void setNumNeighbouringMines(int numNeighbouringMines) {
        this.numNeighbouringMines = numNeighbouringMines;
    }

    public int getNumNeighbouringMines() {
        return this.numNeighbouringMines;
    }

    public boolean isRange() {
        return range;
    }

    public boolean isQuestionMark() {
        return questionMark;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
