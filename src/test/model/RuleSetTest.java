package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.RuleSet.*;
import static org.junit.jupiter.api.Assertions.*;

// Unit tests for RuleSet class
class RuleSetTest {
    RuleSet testRuleSet;

    @BeforeEach
    void runBefore() {
        testRuleSet = new RuleSet();
    }

    @Test
    void testBeyondLowerBound() {
        testRuleSet.setNumRows(-1);
        testRuleSet.setNumCols(3);
        testRuleSet.setMineProportion(-0.1);
        testRuleSet.setMaxFlags(-2);
        testRuleSet.setRangeChance(-0.2);
        testRuleSet.setQuestionMarkChance(-0.3);
        assertEquals("default", testRuleSet.getName());
        assertEquals(MIN_ROWS_AND_COLUMNS, testRuleSet.getNumRows());
        assertEquals(MIN_ROWS_AND_COLUMNS, testRuleSet.getNumCols());
        assertEquals(MIN_MINE_PROPORTION, testRuleSet.getMineProportion());
        assertFalse(testRuleSet.isFlagLimit());
        assertEquals(0, testRuleSet.getMaxFlags());
        assertEquals(0.0, testRuleSet.getRangeChance());
        assertEquals(0.0, testRuleSet.getQuestionMarkChance());
        assertEquals(0, testRuleSet.getGamesPlayed());
        assertEquals(0, testRuleSet.getGamesWon());
        assertEquals(0.0, testRuleSet.getWinPercent());
    }

    @Test
    void testLowerBound() {
        testRuleSet.setNumRows(MIN_ROWS_AND_COLUMNS);
        testRuleSet.setNumCols(MIN_ROWS_AND_COLUMNS);
        testRuleSet.setMineProportion(MIN_MINE_PROPORTION);
        testRuleSet.setMaxFlags(0);
        testRuleSet.setRangeChance(0.0);
        testRuleSet.setQuestionMarkChance(0.0);
        assertEquals("default", testRuleSet.getName());
        assertEquals(MIN_ROWS_AND_COLUMNS, testRuleSet.getNumRows());
        assertEquals(MIN_ROWS_AND_COLUMNS, testRuleSet.getNumCols());
        assertEquals(MIN_MINE_PROPORTION, testRuleSet.getMineProportion());
        assertFalse(testRuleSet.isFlagLimit());
        assertEquals(0, testRuleSet.getMaxFlags());
        assertEquals(0.0, testRuleSet.getRangeChance());
        assertEquals(0.0, testRuleSet.getQuestionMarkChance());
        assertEquals(0, testRuleSet.getGamesPlayed());
        assertEquals(0, testRuleSet.getGamesWon());
        assertEquals(0.0, testRuleSet.getWinPercent());
    }

    @Test
    void testWithinBounds() {
        testRuleSet.setName("12 by 9");
        testRuleSet.setNumRows(12);
        testRuleSet.setNumCols(9);
        testRuleSet.setMineProportion(0.25);
        testRuleSet.setFlagLimit(true);
        testRuleSet.setRangeChance(0.13);
        testRuleSet.setQuestionMarkChance(0.27);
        assertEquals("12 by 9", testRuleSet.getName());
        assertEquals(12, testRuleSet.getNumRows());
        assertEquals(9, testRuleSet.getNumCols());
        assertEquals(0.25, testRuleSet.getMineProportion());
        assertTrue(testRuleSet.isFlagLimit());
        assertEquals(10, testRuleSet.getMaxFlags());
        assertEquals(0.13, testRuleSet.getRangeChance());
        assertEquals(0.27, testRuleSet.getQuestionMarkChance());
        assertEquals(0, testRuleSet.getGamesPlayed());
        assertEquals(0, testRuleSet.getGamesWon());
        assertEquals(0.0, testRuleSet.getWinPercent());
    }

    @Test
    void testUpperBound() {
        testRuleSet.setNumRows(MAX_ROWS_AND_COLUMNS);
        testRuleSet.setNumCols(MAX_ROWS_AND_COLUMNS);
        testRuleSet.setMineProportion(MAX_MINE_PROPORTION);
        testRuleSet.setRangeChance(MAX_RANGE_CHANCE);
        testRuleSet.setQuestionMarkChance(MAX_QUESTION_MARK_CHANCE);
        assertEquals("default", testRuleSet.getName());
        assertEquals(MAX_ROWS_AND_COLUMNS, testRuleSet.getNumRows());
        assertEquals(MAX_ROWS_AND_COLUMNS, testRuleSet.getNumCols());
        assertEquals(MAX_MINE_PROPORTION, testRuleSet.getMineProportion());
        assertFalse(testRuleSet.isFlagLimit());
        assertEquals(10, testRuleSet.getMaxFlags());
        assertEquals(MAX_RANGE_CHANCE, testRuleSet.getRangeChance());
        assertEquals(MAX_QUESTION_MARK_CHANCE, testRuleSet.getQuestionMarkChance());
        assertEquals(0, testRuleSet.getGamesPlayed());
        assertEquals(0, testRuleSet.getGamesWon());
        assertEquals(0.0, testRuleSet.getWinPercent());
    }

    @Test
    void testBeyondUpperBound() {
        testRuleSet.setNumRows(30);
        testRuleSet.setNumCols(50);
        testRuleSet.setMineProportion(1.1);
        testRuleSet.setMaxFlags(20);
        testRuleSet.setRangeChance(120);
        testRuleSet.setQuestionMarkChance(0.8);
        assertEquals("default", testRuleSet.getName());
        assertEquals(MAX_ROWS_AND_COLUMNS, testRuleSet.getNumRows());
        assertEquals(MAX_ROWS_AND_COLUMNS, testRuleSet.getNumCols());
        assertEquals(MAX_MINE_PROPORTION, testRuleSet.getMineProportion());
        assertFalse(testRuleSet.isFlagLimit());
        assertEquals(20, testRuleSet.getMaxFlags());
        assertEquals(MAX_RANGE_CHANCE, testRuleSet.getRangeChance());
        assertEquals(MAX_QUESTION_MARK_CHANCE, testRuleSet.getQuestionMarkChance());
        assertEquals(0, testRuleSet.getGamesPlayed());
        assertEquals(0, testRuleSet.getGamesWon());
        assertEquals(0.0, testRuleSet.getWinPercent());
    }

    @Test
    void testWinGame() {
        boolean win = true;
        testRuleSet.gameFinished(win);
        assertEquals(1, testRuleSet.getGamesPlayed());
        assertEquals(1, testRuleSet.getGamesWon());
        assertEquals(1.0, testRuleSet.getWinPercent());
    }

    @Test
    void testLoseGame() {
        boolean lose = false;
        testRuleSet.gameFinished(lose);
        assertEquals(1, testRuleSet.getGamesPlayed());
        assertEquals(0, testRuleSet.getGamesWon());
        assertEquals(0.0, testRuleSet.getWinPercent());
    }

    @Test
    void testPlayMultipleGames() {
        boolean win = true;
        boolean lose = false;
        testRuleSet.gameFinished(win);
        testRuleSet.gameFinished(win);
        testRuleSet.gameFinished(lose);
        testRuleSet.gameFinished(win);
        testRuleSet.gameFinished(lose);
        testRuleSet.gameFinished(lose);
        testRuleSet.gameFinished(win);
        testRuleSet.gameFinished(win);
        assertEquals(8, testRuleSet.getGamesPlayed());
        assertEquals(5, testRuleSet.getGamesWon());
        assertEquals(((double) 5 / 8), testRuleSet.getWinPercent());
    }
}