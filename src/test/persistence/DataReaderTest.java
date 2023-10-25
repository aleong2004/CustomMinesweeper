package persistence;

import model.RuleSet;
import model.RuleSetList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for DataReader
public class DataReaderTest {
    DataReader testDataReader;
    RuleSetList testRuleSetList;

    @Test
    void testInvalidFileLocation() {
        testDataReader = new DataReader("./data/nonExistentFile.json");
        try {
            RuleSetList ruleSetList = testDataReader.read();
            fail("Expected an IOException");
        } catch (IOException e) {
            // test passes
        }
    }

    @Test
    void testDataReader() {
        testDataReader = new DataReader("./data/testDataReader.json");
        try {
            testRuleSetList = testDataReader.read();
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
        List<RuleSet> savedRuleSets = testRuleSetList.getRuleSetList();
        assertEquals(3, savedRuleSets.size());
        assertEquals("intense", savedRuleSets.get(0).getName());
        assertEquals(8, savedRuleSets.get(0).getNumRows());
        assertEquals(8, savedRuleSets.get(0).getNumCols());
        assertEquals(0.123456789, savedRuleSets.get(0).getMineProportion());
        assertFalse(savedRuleSets.get(0).isFlagLimit());
        assertEquals(0, savedRuleSets.get(0).getMaxFlags());
        assertEquals(0.01, savedRuleSets.get(0).getRangeChance());
        assertEquals(0.01, savedRuleSets.get(0).getQuestionMarkChance());
        assertEquals(8, savedRuleSets.get(0).getGamesPlayed());
        assertEquals(5, savedRuleSets.get(0).getGamesWon());
        assertEquals(0.625, savedRuleSets.get(0).getWinPercent());
        assertEquals("undying", savedRuleSets.get(1).getName());
        assertEquals(10, savedRuleSets.get(1).getNumRows());
        assertEquals(10, savedRuleSets.get(1).getNumCols());
        assertEquals(0.342, savedRuleSets.get(1).getMineProportion());
        assertTrue(savedRuleSets.get(1).isFlagLimit());
        assertEquals(8, savedRuleSets.get(1).getMaxFlags());
        assertEquals(0.1, savedRuleSets.get(1).getRangeChance());
        assertEquals(0.2, savedRuleSets.get(1).getQuestionMarkChance());
        assertEquals(6, savedRuleSets.get(1).getGamesPlayed());
        assertEquals(3, savedRuleSets.get(1).getGamesWon());
        assertEquals(0.5, savedRuleSets.get(1).getWinPercent());
        assertEquals("pain", savedRuleSets.get(2).getName());
        assertEquals(20, savedRuleSets.get(2).getNumRows());
        assertEquals(20, savedRuleSets.get(2).getNumCols());
        assertEquals(0.5, savedRuleSets.get(2).getMineProportion());
        assertTrue(savedRuleSets.get(2).isFlagLimit());
        assertEquals(0, savedRuleSets.get(2).getMaxFlags());
        assertEquals(0.5, savedRuleSets.get(2).getRangeChance());
        assertEquals(0.5, savedRuleSets.get(2).getQuestionMarkChance());
        assertEquals(4, savedRuleSets.get(2).getGamesPlayed());
        assertEquals(3, savedRuleSets.get(2).getGamesWon());
        assertEquals(0.75, savedRuleSets.get(2).getWinPercent());
        assertEquals(2, testRuleSetList.getSelectedRuleSetIndex());
    }
}
