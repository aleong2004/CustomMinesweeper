package persistence;

import model.RuleSet;
import model.RuleSetList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for DataWriter
public class DataWriterTest {
    DataReader testDataReader;
    DataWriter testDataWriter;
    RuleSetList testRuleSetList;
    RuleSetList savedRuleSetList;

    @Test
    void testInvalidFileLocation() {
        testDataWriter = new DataWriter(".data/invalidFile.json");
        try {
            testDataWriter.write(new RuleSetList());
            fail("Expected a FileNotFoundException");
        } catch (FileNotFoundException e) {
            // test passes
        }
    }

    @Test
    void testDataWriter() {
        testDataReader = new DataReader("./data/testDataWriter.json");
        testDataWriter = new DataWriter("./data/testDataWriter.json");
        testRuleSetList = new RuleSetList();
        savedRuleSetList = new RuleSetList();
        testRuleSetList.addRuleSet();
        testRuleSetList.editExistingRuleSet("misery", 20, 6, 0.314159, true, 4, 0.1, 0.3, 16, 13, 0.8125);
        testRuleSetList.addRuleSet();
        testRuleSetList.editExistingRuleSet("suffering", 17, 19, 0.42, false, 1, 0.254, 0.172, 5, 2, 0.4);
        testRuleSetList.addRuleSet();
        testRuleSetList.editExistingRuleSet("defenestration", 14, 13, 0.21, false, 999, 0.02, 0.03, 32, 31, 0.96875);
        testRuleSetList.setSelectedRuleSetIndex(0);
        testRuleSetList.removeRuleSet();
        testRuleSetList.setSelectedRuleSetIndex(1);
        try {
            testDataWriter.write(testRuleSetList);
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        }
        try {
            savedRuleSetList = testDataReader.read();
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
        List<RuleSet> savedRuleSets = savedRuleSetList.getRuleSetList();
        assertEquals(3, savedRuleSets.size());
        assertEquals("misery", savedRuleSets.get(0).getName());
        assertEquals(20, savedRuleSets.get(0).getNumRows());
        assertEquals(6, savedRuleSets.get(0).getNumCols());
        assertEquals(0.314159, savedRuleSets.get(0).getMineProportion());
        assertTrue(savedRuleSets.get(0).isFlagLimit());
        assertEquals(4, savedRuleSets.get(0).getMaxFlags());
        assertEquals(0.1, savedRuleSets.get(0).getRangeChance());
        assertEquals(0.3, savedRuleSets.get(0).getQuestionMarkChance());
        assertEquals(16, savedRuleSets.get(0).getGamesPlayed());
        assertEquals(13, savedRuleSets.get(0).getGamesWon());
        assertEquals(0.8125, savedRuleSets.get(0).getWinPercent());
        assertEquals("suffering", savedRuleSets.get(1).getName());
        assertEquals(17, savedRuleSets.get(1).getNumRows());
        assertEquals(19, savedRuleSets.get(1).getNumCols());
        assertEquals(0.42, savedRuleSets.get(1).getMineProportion());
        assertFalse(savedRuleSets.get(1).isFlagLimit());
        assertEquals(1, savedRuleSets.get(1).getMaxFlags());
        assertEquals(0.254, savedRuleSets.get(1).getRangeChance());
        assertEquals(0.172, savedRuleSets.get(1).getQuestionMarkChance());
        assertEquals(5, savedRuleSets.get(1).getGamesPlayed());
        assertEquals(2, savedRuleSets.get(1).getGamesWon());
        assertEquals(0.4, savedRuleSets.get(1).getWinPercent());
        assertEquals("defenestration", savedRuleSets.get(2).getName());
        assertEquals(14, savedRuleSets.get(2).getNumRows());
        assertEquals(13, savedRuleSets.get(2).getNumCols());
        assertEquals(0.21, savedRuleSets.get(2).getMineProportion());
        assertFalse(savedRuleSets.get(2).isFlagLimit());
        assertEquals(999, savedRuleSets.get(2).getMaxFlags());
        assertEquals(0.02, savedRuleSets.get(2).getRangeChance());
        assertEquals(0.03, savedRuleSets.get(2).getQuestionMarkChance());
        assertEquals(32, savedRuleSets.get(2).getGamesPlayed());
        assertEquals(31, savedRuleSets.get(2).getGamesWon());
        assertEquals(0.96875, savedRuleSets.get(2).getWinPercent());
        assertEquals(1, savedRuleSetList.getSelectedRuleSetIndex());
    }
}
