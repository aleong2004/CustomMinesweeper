package persistence;

import model.RuleSetList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads a RuleSetList from a JSON file
// Code based off of the JsonReader class from JsonSerializationDemo
public class DataReader {
    private String sourceData;

    // EFFECTS: constructs a reader to read from a file
    public DataReader(String source) {
        this.sourceData = source;
    }

    // EFFECTS: reads RuleSetList from a file and returns it. IOException is
    //          thrown if an error occurs while reading data from the file
    public RuleSetList read() throws IOException {
        String data = readFile(sourceData);
        JSONObject jsonObject = new JSONObject(data);
        return parseRuleSetList(jsonObject);
    }

    // EFFECTS: converts source file to a string and returns it
    private String readFile(String sourceData) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(sourceData), StandardCharsets.UTF_8)) {
            stream.forEach(str -> stringBuilder.append(str));
        }
        return stringBuilder.toString();
    }

    // EFFECTS: parses RuleSetList from a JSON object and returns it
    private RuleSetList parseRuleSetList(JSONObject jsonObject) {
        RuleSetList ruleSetList = new RuleSetList();
        setRuleSets(ruleSetList, jsonObject);
        setSelectedRuleSetIndex(ruleSetList, jsonObject);
        return ruleSetList;
    }

    // MODIFIES: ruleSetList
    // EFFECTS: parses RuleSets from a JSON object and adds them to ruleSetList
    private void setRuleSets(RuleSetList ruleSetList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("rulesets");
        for (Object json : jsonArray) {
            JSONObject nextRuleSet = (JSONObject) json;
            String name = nextRuleSet.getString("name");
            int numRows = nextRuleSet.getInt("numRows");
            int numCols = nextRuleSet.getInt("numCols");
            double mineProportion = nextRuleSet.getDouble("mineProportion");
            boolean flagLimit = nextRuleSet.getBoolean("flagLimit");
            int maxFlags = nextRuleSet.getInt("maxFlags");
            double rangeChance = nextRuleSet.getDouble("rangeChance");
            double questionMarkChance = nextRuleSet.getDouble("questionMarkChance");
            int gamesPlayed = nextRuleSet.getInt("gamesPlayed");
            int gamesWon = nextRuleSet.getInt("gamesWon");
            double winPercent = nextRuleSet.getDouble("winPercent");
            ruleSetList.addRuleSet();
            ruleSetList.editExistingRuleSet(name, numRows, numCols, mineProportion,
                    flagLimit, maxFlags, rangeChance, questionMarkChance, gamesPlayed, gamesWon, winPercent);
        }
        ruleSetList.setSelectedRuleSetIndex(0);
        ruleSetList.removeRuleSet();
    }

    // MODIFIES: ruleSetList
    // EFFECTS: parses the index of the currently selected ruleset
    //          from a JSON object and sets it in ruleSetList
    private void setSelectedRuleSetIndex(RuleSetList ruleSetList, JSONObject jsonObject) {
        int index = jsonObject.getInt("selected ruleset");
        ruleSetList.setSelectedRuleSetIndex(index);
    }
}
