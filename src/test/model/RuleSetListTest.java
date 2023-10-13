package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.RuleSet.MIN_MINE_PROPORTION;
import static model.RuleSet.MIN_ROWS_AND_COLUMNS;
import static org.junit.jupiter.api.Assertions.*;

// Unit tests for RuleSetList
public class RuleSetListTest {
    RuleSetList testRuleSetList;

    @BeforeEach
    void runBefore() {
        testRuleSetList = new RuleSetList();
        testRuleSetList.addRuleSet();
        testRuleSetList.addRuleSet();
        testRuleSetList.editExistingRuleSet("edited3", 10, 10, 0.15, true, 20, 0.1, 0.0);
        testRuleSetList.setSelectedRuleSetIndex(1);
        testRuleSetList.editExistingRuleSet("edited2", 18, 20, 0.2, false, 21, 0.0, 0.15);
    }

    @Test
    void testAddAndRemoveMultipleRuleSets() {
        List<RuleSet> testListInitial;
        List<RuleSet> testListAfterRemoving;
        testListInitial = testRuleSetList.getRuleSetList();
        assertEquals(3, testListInitial.size());
        testRuleSetList.setSelectedRuleSetIndex(2);
        testRuleSetList.removeRuleSet();
        testRuleSetList.removeRuleSet();
        testRuleSetList.removeRuleSet();
        testListAfterRemoving = testRuleSetList.getRuleSetList();
        assertEquals(1, testListAfterRemoving.size());
        RuleSet first = testListInitial.get(0);
        assertEquals("edited2", first.getName());
        assertEquals(18, first.getNumRows());
        assertEquals(20, first.getNumCols());
        assertEquals(0.2, first.getMineProportion());
        assertFalse(first.isFlagLimit());
        assertEquals(21, first.getMaxFlags());
        assertEquals(0.0, first.getRangeChance());
        assertEquals(0.15, first.getQuestionMarkChance());
    }

    @Test
    void testGetCurrentlySelectedRuleSet() {
        testRuleSetList.setSelectedRuleSetIndex(1);
        RuleSet first = testRuleSetList.getCurrentlySelectedRuleSet();
        testRuleSetList.setSelectedRuleSetIndex(0);
        RuleSet second = testRuleSetList.getCurrentlySelectedRuleSet();
        assertEquals("edited2", first.getName());
        assertEquals(18, first.getNumRows());
        assertEquals(20, first.getNumCols());
        assertEquals(0.2, first.getMineProportion());
        assertFalse(first.isFlagLimit());
        assertEquals(21, first.getMaxFlags());
        assertEquals(0.0, first.getRangeChance());
        assertEquals(0.15, first.getQuestionMarkChance());
        assertEquals("default", second.getName());
        assertEquals(MIN_ROWS_AND_COLUMNS, second.getNumRows());
        assertEquals(MIN_ROWS_AND_COLUMNS, second.getNumCols());
        assertEquals(MIN_MINE_PROPORTION, second.getMineProportion());
        assertFalse(second.isFlagLimit());
        assertEquals(10, second.getMaxFlags());
        assertEquals(0.0, second.getRangeChance());
        assertEquals(0.0, second.getQuestionMarkChance());
    }

    @Test
    void testEditMultipleRuleSets() {
        List<RuleSet> testList = testRuleSetList.getRuleSetList();
        RuleSet first = testList.get(0);
        RuleSet second = testList.get(1);
        RuleSet third = testList.get(2);
        assertEquals("default", first.getName());
        assertEquals(MIN_ROWS_AND_COLUMNS, first.getNumRows());
        assertEquals(MIN_ROWS_AND_COLUMNS, first.getNumCols());
        assertEquals(MIN_MINE_PROPORTION, first.getMineProportion());
        assertFalse(first.isFlagLimit());
        assertEquals(10, first.getMaxFlags());
        assertEquals(0.0, first.getRangeChance());
        assertEquals(0.0, first.getQuestionMarkChance());
        assertEquals("edited2", second.getName());
        assertEquals(18, second.getNumRows());
        assertEquals(20, second.getNumCols());
        assertEquals(0.2, second.getMineProportion());
        assertFalse(second.isFlagLimit());
        assertEquals(21, second.getMaxFlags());
        assertEquals(0.0, second.getRangeChance());
        assertEquals(0.15, second.getQuestionMarkChance());
        assertEquals("edited3", third.getName());
        assertEquals(10, third.getNumRows());
        assertEquals(10, third.getNumCols());
        assertEquals(0.15, third.getMineProportion());
        assertTrue(third.isFlagLimit());
        assertEquals(20, third.getMaxFlags());
        assertEquals(0.1, third.getRangeChance());
        assertEquals(0.0, third.getQuestionMarkChance());
    }
}
