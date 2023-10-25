package persistence;

import model.RuleSetList;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes a RuleSetList to a JSON file
// Code based off of the JsonWriter class from JsonSerializationDemo
public class DataWriter {
    private PrintWriter writer;
    private String destination;
    private static final int INDENT = 4;

    // EFFECTS: constructs a writer with designated file destination
    public DataWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of RuleSetList to file, throws
    //          FileNotFoundException if file destination cannot be opened for writing
    public void write(RuleSetList ruleSetList) throws FileNotFoundException {
        writer = new PrintWriter(destination);
        JSONObject json = ruleSetList.convertToJson();
        writer.print(json.toString(INDENT));
        writer.close();
    }
}