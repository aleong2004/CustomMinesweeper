package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Represents the layout of mines for a board. 'true' represent a mine, 'false' represent no mine.
// The order of the list is the first row of tiles from the left to right, the next row from
// left to right, and so on. For example, a 3x3 board with a mine layout of
// true true false
// false false true
// false true true
// is represented as a list in the following order: true true false false false true false true true
public class MineLayout {
    private final RuleSet currentRuleSet;
    private List<Boolean> mineLayout;
    private int numMines;

    // EFFECTS: constructs a layout of no mines using a given ruleset
    public MineLayout(RuleSet ruleSet) {
        this.currentRuleSet = ruleSet;
        this.mineLayout = new ArrayList<>();
        int numRows = currentRuleSet.getNumRows();
        int numCols = currentRuleSet.getNumCols();
        for (int i = 0; i < (numRows * numCols); i++) {
            this.mineLayout.add(false);
        }
    }

    // REQUIRES: numRows and numCols in ruleSet are both > 0,
    //           mineProportion in ruleSet is in [MIN_MINE_PROPORTION, MAX_MINE_PROPORTION]
    // EFFECTS: creates an unrandomized mine layout
    //          - size of the list is numRows * numCols
    //          - first (numRows * numCols * mineProportion) items are true,
    //            all items after are false
    public void generateMines() {
        this.mineLayout = new ArrayList<>();
        int numRows = currentRuleSet.getNumRows();
        int numCols = currentRuleSet.getNumCols();
        double mineProportion = currentRuleSet.getMineProportion();
        this.numMines = (int)(numRows * numCols * mineProportion);
        for (int i = 0; i < this.numMines; i++) {
            this.mineLayout.add(true);
        }
        for (int i = this.numMines; i < (numRows * numCols); i++) {
            this.mineLayout.add(false);
        }
    }

    // REQUIRES: this.mineLayout is not empty, start is in [0, this.mineLayout.size() - 1]
    // MODIFIES: this
    // EFFECTS: randomizes the mine layout. There cannot be a mine
    //          at index start, which represents the first tile revealed
    public void randomizeMineLayout(int start, int seed) {
        List<Boolean> tempList = this.mineLayout;
        this.mineLayout = new ArrayList<>();
        Random random = new Random();
        random.setSeed(seed);
        while (!tempList.isEmpty()) {
            int upperbound = tempList.size();
            int randomInt = random.nextInt(upperbound);
            if (start == this.mineLayout.size()) {
                this.mineLayout.add(false);
                tempList.remove(tempList.size() - 1);
            } else {
                this.mineLayout.add(tempList.get(randomInt));
                tempList.remove(randomInt);
            }
        }
    }

    public List<Boolean> getMineLayout() {
        return this.mineLayout;
    }


    public int getNumMines() {
        return this.numMines;
    }
}
