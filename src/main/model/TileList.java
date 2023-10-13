package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Represents a list of tiles which represents a board. The order of the list is the first
// row of tiles from the left to right, the next row from left to right, and so on.
public class TileList {
    private List<Tile> tileList;
    private RuleSet currentRuleSet;

    // EFFECTS: constructs a new list of tiles to represent the base board for a ruleset.
    //          The base board consists of only placeholder tiles but has the correct
    //          dimensions as specified by the given ruleset.
    public TileList(RuleSet ruleSet) {
        this.currentRuleSet = ruleSet;
        this.tileList = new ArrayList<>();
        int size = currentRuleSet.getNumRows() * currentRuleSet.getNumCols();
        for (int i = 0; i < size; i++) {
            this.tileList.add(new Tile(i));
        }
    }

    // REQUIRES: start is in [0, tileList.size() - 1]
    // MODIFIES: this
    // EFFECTS: generate proper tiles for the board of a given ruleset
    public void generateTiles(RuleSet ruleSet, int start, int seed) {
        this.currentRuleSet = ruleSet;
        this.tileList = new ArrayList<>();
        int size = currentRuleSet.getNumRows() * currentRuleSet.getNumCols();
        MineLayout mineLayout = new MineLayout(ruleSet);
        mineLayout.generateMines();
        mineLayout.randomizeMineLayout(start, seed);
        for (int i = 0; i < size; i++) {
            Random random = new Random();
            int nextSeed = random.nextInt();
            this.tileList.add(new Tile(ruleSet, mineLayout, i, nextSeed));
        }
    }

    // MODIFIES: all Tile in tileList
    // EFFECTS: sets the proper number of neighbouring mines for all tiles
    public void setNeighbouringMinesAllTiles() {
        for (Tile tile : this.tileList) {
            if (tile.getBoardSection() == 1) {
                tile.setNumNeighbouringMines(getNeighbouringMinesSectionOne(this.currentRuleSet, tile.getIndex()));
            } else if (tile.getBoardSection() == 2) {
                tile.setNumNeighbouringMines(getNeighbouringMinesSectionTwo(this.currentRuleSet, tile.getIndex()));
            } else if (tile.getBoardSection() == 3) {
                tile.setNumNeighbouringMines(getNeighbouringMinesSectionThree(this.currentRuleSet, tile.getIndex()));
            } else if (tile.getBoardSection() == 4) {
                tile.setNumNeighbouringMines(getNeighbouringMinesSectionFour(this.currentRuleSet, tile.getIndex()));
            } else if (tile.getBoardSection() == 5) {
                tile.setNumNeighbouringMines(getNeighbouringMinesSectionFive(this.currentRuleSet, tile.getIndex()));
            } else if (tile.getBoardSection() == 6) {
                tile.setNumNeighbouringMines(getNeighbouringMinesSectionSix(this.currentRuleSet, tile.getIndex()));
            } else if (tile.getBoardSection() == 7) {
                tile.setNumNeighbouringMines(getNeighbouringMinesSectionSeven(this.currentRuleSet, tile.getIndex()));
            } else if (tile.getBoardSection() == 8) {
                tile.setNumNeighbouringMines(getNeighbouringMinesSectionEight(this.currentRuleSet, tile.getIndex()));
            } else {
                tile.setNumNeighbouringMines(getNeighbouringMinesSectionNine(this.currentRuleSet, tile.getIndex()));
            }
        }
    }

    // REQUIRES: index corresponds to a tile in section 1
    // EFFECTS: returns the number of mines in neighbouring tiles
    public int getNeighbouringMinesSectionOne(RuleSet ruleSet, int index) {
        Tile rightTile = this.tileList.get(index + 1);
        int rightTileMine = (rightTile.isMine()) ? 1 : 0;
        Tile downTile = this.tileList.get(index + ruleSet.getNumCols());
        int downTileMine = (downTile.isMine()) ? 1 : 0;
        Tile downRightTile = this.tileList.get(index + ruleSet.getNumCols() + 1);
        int downRightTileMine = (downRightTile.isMine()) ? 1 : 0;
        return (rightTileMine + downTileMine + downRightTileMine);
    }

    // REQUIRES: index corresponds to a tile in section 2
    // EFFECTS: returns the number of mines in neighbouring tiles
    public int getNeighbouringMinesSectionTwo(RuleSet ruleSet, int index) {
        Tile leftTile = this.tileList.get(index - 1);
        int leftTileMine = (leftTile.isMine()) ? 1 : 0;
        Tile rightTile = this.tileList.get(index + 1);
        int rightTileMine = (rightTile.isMine()) ? 1 : 0;
        Tile downLeftTile = this.tileList.get(index + ruleSet.getNumCols() - 1);
        int downLeftTileMine = (downLeftTile.isMine()) ? 1 : 0;
        Tile downTile = this.tileList.get(index + ruleSet.getNumCols());
        int downTileMine = (downTile.isMine()) ? 1 : 0;
        Tile downRightTile = this.tileList.get(index + ruleSet.getNumCols() + 1);
        int downRightTileMine = (downRightTile.isMine()) ? 1 : 0;
        return (leftTileMine + rightTileMine + downLeftTileMine + downTileMine + downRightTileMine);
    }

    // REQUIRES: index corresponds to a tile in section 3
    // EFFECTS: returns the number of mines in neighbouring tiles
    public int getNeighbouringMinesSectionThree(RuleSet ruleSet, int index) {
        Tile leftTile = this.tileList.get(index - 1);
        int leftTileMine = (leftTile.isMine()) ? 1 : 0;
        Tile downLeftTile = this.tileList.get(index + ruleSet.getNumCols() - 1);
        int downLeftTileMine = (downLeftTile.isMine()) ? 1 : 0;
        Tile downTile = this.tileList.get(index + ruleSet.getNumCols());
        int downTileMine = (downTile.isMine()) ? 1 : 0;
        return (leftTileMine + downLeftTileMine + downTileMine);
    }

    // REQUIRES: index corresponds to a tile in section 4
    // EFFECTS: returns the number of mines in neighbouring tiles
    public int getNeighbouringMinesSectionFour(RuleSet ruleSet, int index) {
        Tile upTile = this.tileList.get(index - ruleSet.getNumCols());
        int upTileMine = (upTile.isMine()) ? 1 : 0;
        Tile upRightTile = this.tileList.get(index - ruleSet.getNumCols() + 1);
        int upRightTileMine = (upRightTile.isMine()) ? 1 : 0;
        Tile rightTile = this.tileList.get(index + 1);
        int rightTileMine = (rightTile.isMine()) ? 1 : 0;
        Tile downTile = this.tileList.get(index + ruleSet.getNumCols());
        int downTileMine = (downTile.isMine()) ? 1 : 0;
        Tile downRightTile = this.tileList.get(index + ruleSet.getNumCols() + 1);
        int downRightTileMine = (downRightTile.isMine()) ? 1 : 0;
        return (upTileMine + upRightTileMine + rightTileMine + downTileMine + downRightTileMine);
    }

    // REQUIRES: index corresponds to a tile in section 5
    // EFFECTS: returns the number of mines in neighbouring tiles
    public int getNeighbouringMinesSectionFive(RuleSet ruleSet, int index) {
        Tile upLeftTile = this.tileList.get(index - ruleSet.getNumCols() - 1);
        int upLeftMine = (upLeftTile.isMine()) ? 1 : 0;
        Tile upTile = this.tileList.get(index - ruleSet.getNumCols());
        int upTileMine = (upTile.isMine()) ? 1 : 0;
        Tile upRightTile = this.tileList.get(index - ruleSet.getNumCols() + 1);
        int upRightMine = (upRightTile.isMine()) ? 1 : 0;
        Tile leftTile = this.tileList.get(index - 1);
        int leftMine = (leftTile.isMine()) ? 1 : 0;
        Tile rightTile = this.tileList.get(index + 1);
        int rightMine = (rightTile.isMine()) ? 1 : 0;
        Tile downLeftTile = this.tileList.get(index + ruleSet.getNumCols() - 1);
        int downLeftMine = (downLeftTile.isMine()) ? 1 : 0;
        Tile downTile = this.tileList.get(index + ruleSet.getNumCols());
        int downMine = (downTile.isMine()) ? 1 : 0;
        Tile downRightTile = this.tileList.get(index + ruleSet.getNumCols() + 1);
        int downRightMine = (downRightTile.isMine()) ? 1 : 0;
        return (upLeftMine + upTileMine + upRightMine + leftMine + rightMine + downLeftMine + downMine + downRightMine);

    }

    // REQUIRES: index corresponds to a tile in section 6
    // EFFECTS: returns the number of mines in neighbouring tiles
    public int getNeighbouringMinesSectionSix(RuleSet ruleSet, int index) {
        Tile upLeftTile = this.tileList.get(index - ruleSet.getNumCols() - 1);
        int upLeftTileMine = (upLeftTile.isMine()) ? 1 : 0;
        Tile upTile = this.tileList.get(index - ruleSet.getNumCols());
        int upTileMine = (upTile.isMine()) ? 1 : 0;
        Tile leftTile = this.tileList.get(index - 1);
        int leftTileMine = (leftTile.isMine()) ? 1 : 0;
        Tile downLeftTile = this.tileList.get(index + ruleSet.getNumCols() - 1);
        int downLeftTileMine = (downLeftTile.isMine()) ? 1 : 0;
        Tile downTile = this.tileList.get(index + ruleSet.getNumCols());
        int downTileMine = (downTile.isMine()) ? 1 : 0;
        return (upLeftTileMine + upTileMine + leftTileMine + downLeftTileMine + downTileMine);
    }

    // REQUIRES: index corresponds to a tile in section 7
    // EFFECTS: returns the number of mines in neighbouring tiles
    public int getNeighbouringMinesSectionSeven(RuleSet ruleSet, int index) {
        Tile upTile = this.tileList.get(index - ruleSet.getNumCols());
        int upTileMine = (upTile.isMine()) ? 1 : 0;
        Tile upRightTile = this.tileList.get(index - ruleSet.getNumCols() + 1);
        int upRightTileMine = (upRightTile.isMine()) ? 1 : 0;
        Tile rightTile = this.tileList.get(index + 1);
        int rightTileMine = (rightTile.isMine()) ? 1 : 0;
        return (upTileMine + upRightTileMine + rightTileMine);
    }

    // REQUIRES: index corresponds to a tile in section 8
    // EFFECTS: returns the number of mines in neighbouring tiles
    public int getNeighbouringMinesSectionEight(RuleSet ruleSet, int index) {
        Tile upLeftTile = this.tileList.get(index - ruleSet.getNumCols() - 1);
        int upLeftTileMine = (upLeftTile.isMine()) ? 1 : 0;
        Tile upTile = this.tileList.get(index - ruleSet.getNumCols());
        int upTileMine = (upTile.isMine()) ? 1 : 0;
        Tile upRightTile = this.tileList.get(index - ruleSet.getNumCols() + 1);
        int upRightTileMine = (upRightTile.isMine()) ? 1 : 0;
        Tile leftTile = this.tileList.get(index - 1);
        int leftTileMine = (leftTile.isMine()) ? 1 : 0;
        Tile rightTile = this.tileList.get(index + 1);
        int rightTileMine = (rightTile.isMine()) ? 1 : 0;
        return (upLeftTileMine + upTileMine + upRightTileMine + leftTileMine + rightTileMine);
    }

    // REQUIRES: index corresponds to a tile in section 9
    // EFFECTS: returns the number of mines in neighbouring tiles
    public int getNeighbouringMinesSectionNine(RuleSet ruleSet, int index) {
        Tile upLeftTile = this.tileList.get(index - ruleSet.getNumCols() - 1);
        int upLeftTileMine = (upLeftTile.isMine()) ? 1 : 0;
        Tile upTile = this.tileList.get(index - ruleSet.getNumCols());
        int upTileMine = (upTile.isMine()) ? 1 : 0;
        Tile leftTile = this.tileList.get(index - 1);
        int leftTileMine = (leftTile.isMine()) ? 1 : 0;
        return (upLeftTileMine + upTileMine + leftTileMine);
    }

    public List<Tile> getTileList() {
        return tileList;
    }
}
