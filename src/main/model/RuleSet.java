package model;

import org.json.JSONObject;

// Represents a custom ruleset for minesweeper.
// Customizable rules are:
// - Number of rows
// - Number of columns
// - Proportion of tiles on the board that are mines
// - Whether there is a limit on the number of flags that can be placed
// - If there is a flag limit, the maximum number of flags that can be displayed at a time
// - Chance of a tile displaying a range (e.g. '4' becomes '3-4' or '4-5')
// - Chance of a tile displaying a question mark (e.g. '2' becomes '?')
// Additionally, records the following statistics:
// - Number of games played on this ruleset
// - Number of games won on this ruleset
// - Win percentage on this ruleset
// Code for convertToJson() is based off of toJson() in the Thingy class from JsonSerializationDemo
public class RuleSet {
    public static final int MIN_ROWS_AND_COLUMNS = 5;
    public static final int MAX_ROWS_AND_COLUMNS = 20;
    public static final double MIN_MINE_PROPORTION = 0.1;
    public static final double MAX_MINE_PROPORTION = 0.5;
    public static final double MAX_RANGE_CHANCE = 0.5;
    public static final double MAX_QUESTION_MARK_CHANCE = 0.5;

    private String name;
    private int numRows;
    private int numCols;
    private double mineProportion;
    private boolean flagLimit;
    private int maxFlags;
    private double rangeChance;
    private double questionMarkChance;
    private int gamesPlayed;
    private int gamesWon;
    private double winPercent;

    // EFFECTS: constructs a default ruleset
    public RuleSet() {
        name = "default";
        numRows = MIN_ROWS_AND_COLUMNS;
        numCols = MIN_ROWS_AND_COLUMNS;
        mineProportion = MIN_MINE_PROPORTION;
        flagLimit = false;
        maxFlags = 10;
        rangeChance = 0.0;
        questionMarkChance = 0.0;
        gamesPlayed = 0;
        gamesWon = 0;
        winPercent = 0.0;
    }

    // MODIFIES: this
    // EFFECTS: increases gamesPlayed by 1;
    //          if win is true, increases gamesWon by 1, otherwise gamesWon stays the same;
    //          updates winPercent
    public void gameFinished(boolean win) {
        this.gamesPlayed++;
        if (win) {
            this.gamesWon++;
        }
        this.winPercent = (double) this.gamesWon / this.gamesPlayed;
        EventLog.getInstance().logEvent(new Event("Minesweeper game completed"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumRows() {
        return numRows;
    }

    // MODIFIES: this
    // EFFECTS: sets and constrains numRows to [MIN_ROWS_AND_COLUMNS, MAX_ROWS_AND_COLUMNS]
    public void setNumRows(int numRows) {
        if (numRows <= MIN_ROWS_AND_COLUMNS) {
            this.numRows = MIN_ROWS_AND_COLUMNS;
        } else {
            this.numRows = Math.min(numRows, MAX_ROWS_AND_COLUMNS);
        }
    }

    public int getNumCols() {
        return numCols;
    }

    // MODIFIES: this
    // EFFECTS: sets and constrains numCols to [MIN_ROWS_AND_COLUMNS, MAX_ROWS_AND_COLUMNS]
    public void setNumCols(int numCols) {
        if (numCols <= MIN_ROWS_AND_COLUMNS) {
            this.numCols = MIN_ROWS_AND_COLUMNS;
        } else {
            this.numCols = Math.min(numCols, MAX_ROWS_AND_COLUMNS);
        }
    }

    public double getMineProportion() {
        return mineProportion;
    }

    // MODIFIES: this
    // EFFECTS: sets and constrains mineProportion to [MIN_MINE_PROPORTION, MAX_MINE_PROPORTION]
    public void setMineProportion(double mineProportion) {
        if (mineProportion <= MIN_MINE_PROPORTION) {
            this.mineProportion = MIN_MINE_PROPORTION;
        } else {
            this.mineProportion = Math.min(mineProportion, MAX_MINE_PROPORTION);
        }
    }

    public boolean isFlagLimit() {
        return flagLimit;
    }

    public void setFlagLimit(boolean flagLimit) {
        this.flagLimit = flagLimit;
    }

    public int getMaxFlags() {
        return maxFlags;
    }

    // MODIFIES: this
    // EFFECTS: sets and constrains maxFlags to >= 0
    public void setMaxFlags(int maxFlags) {
        this.maxFlags = Math.max(maxFlags, 0);
    }

    public double getRangeChance() {
        return rangeChance;
    }

    // MODIFIES: this
    // EFFECTS: sets and constrains rangeChance to [0.0, MAX_RANGE_CHANCE]
    public void setRangeChance(double rangeChance) {
        if (rangeChance <= 0.0) {
            this.rangeChance = 0.0;
        } else {
            this.rangeChance = Math.min(rangeChance, MAX_RANGE_CHANCE);
        }
    }

    public double getQuestionMarkChance() {
        return questionMarkChance;
    }

    // MODIFIES: this
    // EFFECTS: sets and constrains questionMarkChance to [0.0, MAX_QUESTION_MARK_CHANCE]
    public void setQuestionMarkChance(double questionMarkChance) {
        if (questionMarkChance <= 0.0) {
            this.questionMarkChance = 0.0;
        } else {
            this.questionMarkChance = Math.min(questionMarkChance, MAX_QUESTION_MARK_CHANCE);
        }
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public double getWinPercent() {
        return winPercent;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void setWinPercent(double winPercent) {
        this.winPercent = winPercent;
    }

    // EFFECTS: converts RuleSet to JSON
    public JSONObject convertToJson() {
        JSONObject jsonRuleSet = new JSONObject();
        jsonRuleSet.put("name", name);
        jsonRuleSet.put("numRows", numRows);
        jsonRuleSet.put("numCols", numCols);
        jsonRuleSet.put("mineProportion", mineProportion);
        jsonRuleSet.put("flagLimit", flagLimit);
        jsonRuleSet.put("maxFlags", maxFlags);
        jsonRuleSet.put("rangeChance", rangeChance);
        jsonRuleSet.put("questionMarkChance", questionMarkChance);
        jsonRuleSet.put("gamesPlayed", gamesPlayed);
        jsonRuleSet.put("gamesWon", gamesWon);
        jsonRuleSet.put("winPercent", winPercent);
        return jsonRuleSet;
    }
}
