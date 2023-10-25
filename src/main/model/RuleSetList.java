package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Represents a list of rulesets
// Code for convertToJson() and convertRuleSetsToJson() are based off of toJson() and thingiesToJson() respectively
// Both methods are in the WorkRoom class from JsonSerializationDemo
public class RuleSetList {
    private final List<RuleSet> ruleSetList;
    private int selectedRuleSetIndex;

    // EFFECTS: constructs a list of rulesets containing a single default ruleset
    public RuleSetList() {
        ruleSetList = new ArrayList<>();
        addRuleSet();
    }

    // MODIFIES: this
    // EFFECTS: adds a default ruleset to the end of the list
    //          and sets it as the currently selected ruleset
    public void addRuleSet() {
        int sizeOfListBeforeAdding = this.ruleSetList.size();
        RuleSet add = new RuleSet();
        this.ruleSetList.add(add);
        this.selectedRuleSetIndex = sizeOfListBeforeAdding;
    }

    // REQUIRES: this.ruleSetList.size() > 0
    // MODIFIES: this
    // EFFECTS: removes the currently selected ruleset from the list
    //          unless it is the last ruleset in the list;
    //          sets the first ruleset as the currently selected ruleset
    public void removeRuleSet() {
        int size = this.ruleSetList.size();
        if (size > 1) {
            RuleSet currentRuleSet = getCurrentlySelectedRuleSet();
            this.ruleSetList.remove(currentRuleSet);
            this.selectedRuleSetIndex = 0;
        }
    }

    // EFFECTS: returns the currently selected ruleset
    public RuleSet getCurrentlySelectedRuleSet() {
        return this.ruleSetList.get(getSelectedRuleSetIndex());
    }

    // MODIFIES: the currently selected ruleset
    // EFFECTS: edits the name and rules of the currently selected ruleset
    public void editExistingRuleSet(String name, int numRows, int numCols,
                                    double mineProportion, boolean flagLimit, int maxFlags,
                                    double rangeChance, double questionMarkChance) {
        RuleSet selectedRuleSet = getCurrentlySelectedRuleSet();
        selectedRuleSet.setName(name);
        selectedRuleSet.setNumRows(numRows);
        selectedRuleSet.setNumCols(numCols);
        selectedRuleSet.setMineProportion(mineProportion);
        selectedRuleSet.setFlagLimit(flagLimit);
        selectedRuleSet.setMaxFlags(maxFlags);
        selectedRuleSet.setRangeChance(rangeChance);
        selectedRuleSet.setQuestionMarkChance(questionMarkChance);
    }

    // MODIFIES: the currently selected ruleset
    // EFFECTS: edits the name, rules, and stats of the currently selected ruleset
    public void editExistingRuleSet(String name, int numRows, int numCols, double mineProportion,
                                    boolean flagLimit, int maxFlags, double rangeChance, double questionMarkChance,
                                    int gamesPlayed, int gamesWon, double winPercent) {
        RuleSet selectedRuleSet = getCurrentlySelectedRuleSet();
        selectedRuleSet.setName(name);
        selectedRuleSet.setNumRows(numRows);
        selectedRuleSet.setNumCols(numCols);
        selectedRuleSet.setMineProportion(mineProportion);
        selectedRuleSet.setFlagLimit(flagLimit);
        selectedRuleSet.setMaxFlags(maxFlags);
        selectedRuleSet.setRangeChance(rangeChance);
        selectedRuleSet.setQuestionMarkChance(questionMarkChance);
        selectedRuleSet.setGamesPlayed(gamesPlayed);
        selectedRuleSet.setGamesWon(gamesWon);
        selectedRuleSet.setWinPercent(winPercent);
    }


    public List<RuleSet> getRuleSetList() {
        return ruleSetList;
    }

    public int getSelectedRuleSetIndex() {
        return selectedRuleSetIndex;
    }

    // REQUIRES: 0 <= index <= ruleSetList.size() - 1
    // MODIFIES: this
    // EFFECTS: sets the currently selected ruleset
    public void setSelectedRuleSetIndex(int index) {
        this.selectedRuleSetIndex = index;
    }

    // EFFECTS: converts RuleSetList to JSON
    public JSONObject convertToJson() {
        JSONObject jsonRuleSetList = new JSONObject();
        jsonRuleSetList.put("rulesets", convertRuleSetsToJson());
        jsonRuleSetList.put("selected ruleset", selectedRuleSetIndex);
        return jsonRuleSetList;
    }

    // EFFECTS: converts the list of rulesets to a JSON array
    private JSONArray convertRuleSetsToJson() {
        JSONArray jsonRuleSets = new JSONArray();
        for (RuleSet ruleSet : ruleSetList) {
            jsonRuleSets.put(ruleSet.convertToJson());
        }
        return jsonRuleSets;
    }
}
