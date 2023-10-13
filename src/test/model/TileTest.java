package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Tile
public class TileTest {
    RuleSetList testRuleSetList;
    MineLayout testMineLayout;
    Tile testTile;
    Tile testTile2;

    @BeforeEach
    void runBefore() {
        testRuleSetList = new RuleSetList();
        testRuleSetList.editExistingRuleSet("custom", 10, 10, 0.5, false, 10, 0.4, 0.4);
        testMineLayout = new MineLayout(testRuleSetList.getCurrentlySelectedRuleSet());
        testMineLayout.generateMines();
        testMineLayout.randomizeMineLayout(26, 1111);
    }

    @Test
    void testSectionOne() {
        testTile = new Tile(testRuleSetList.getCurrentlySelectedRuleSet(), testMineLayout, 0, 1000);
        testTile.setNumNeighbouringMines(2);
        testTile.setState(2);
        testTile.updateDisplayValue(123);
        assertEquals("mine", testTile.getDisplayValue());
        assertEquals(2, testTile.getState());
        assertEquals(0, testTile.getIndex());
        assertEquals(1, testTile.getBoardSection());
        assertTrue(testTile.isMine());
        assertEquals(2, testTile.getNumNeighbouringMines());
        assertFalse(testTile.isRange());
        assertFalse(testTile.isQuestionMark());
    }

    @Test
    void testSectionTwo() {
        testTile = new Tile(testRuleSetList.getCurrentlySelectedRuleSet(), testMineLayout, 4, 2002);
        testTile.setNumNeighbouringMines(5);
        testTile.setState(1);
        testTile.updateDisplayValue(123);
        assertEquals("?", testTile.getDisplayValue());
        assertEquals(1, testTile.getState());
        assertEquals(4, testTile.getIndex());
        assertEquals(2, testTile.getBoardSection());
        assertFalse(testTile.isMine());
        assertEquals(5, testTile.getNumNeighbouringMines());
        assertFalse(testTile.isRange());
        assertTrue(testTile.isQuestionMark());
    }

    @Test
    void testSectionThree() {
        testTile = new Tile(testRuleSetList.getCurrentlySelectedRuleSet(), testMineLayout, 9, 31702);
        testTile.setNumNeighbouringMines(2);
        testTile.updateDisplayValue(123);
        assertEquals("mine", testTile.getDisplayValue());
        assertEquals(0, testTile.getState());
        assertEquals(9, testTile.getIndex());
        assertEquals(3, testTile.getBoardSection());
        assertTrue(testTile.isMine());
        assertEquals(2, testTile.getNumNeighbouringMines());
        assertTrue(testTile.isRange());
        assertFalse(testTile.isQuestionMark());
        testTile2 = new Tile(19);
        testTile2.setNumNeighbouringMines(0);
        testTile2.updateDisplayValue(123);
        assertEquals("0", testTile2.getDisplayValue());
        assertEquals(0, testTile2.getState());
        assertEquals(19, testTile2.getIndex());
        assertEquals(0, testTile2.getBoardSection());
        assertFalse(testTile2.isMine());
        assertEquals(0, testTile2.getNumNeighbouringMines());
        assertFalse(testTile2.isRange());
        assertFalse(testTile2.isQuestionMark());
    }

    @Test
    void testSectionFour() {
        testTile = new Tile(testRuleSetList.getCurrentlySelectedRuleSet(), testMineLayout, 20, 31702);
        testTile.setNumNeighbouringMines(2);
        testTile.updateDisplayValue(123);
        assertEquals("2-3", testTile.getDisplayValue());
        assertEquals(0, testTile.getState());
        assertEquals(20, testTile.getIndex());
        assertEquals(4, testTile.getBoardSection());
        assertFalse(testTile.isMine());
        assertEquals(2, testTile.getNumNeighbouringMines());
        assertTrue(testTile.isRange());
        assertFalse(testTile.isQuestionMark());
    }

    @Test
    void testSectionFive() {
        testTile = new Tile(testRuleSetList.getCurrentlySelectedRuleSet(), testMineLayout, 54, 31702);
        testTile.setNumNeighbouringMines(8);
        testTile.updateDisplayValue(123);
        assertEquals("7-8", testTile.getDisplayValue());
        assertEquals(0, testTile.getState());
        assertEquals(54, testTile.getIndex());
        assertEquals(5, testTile.getBoardSection());
        assertFalse(testTile.isMine());
        assertEquals(8, testTile.getNumNeighbouringMines());
        assertTrue(testTile.isRange());
        assertFalse(testTile.isQuestionMark());
        testTile2 = new Tile(testRuleSetList.getCurrentlySelectedRuleSet(), testMineLayout, 26, 31702);
        testTile2.setNumNeighbouringMines(0);
        testTile2.updateDisplayValue(123);
        assertEquals("0-1", testTile2.getDisplayValue());
        assertEquals(0, testTile2.getState());
        assertEquals(26, testTile2.getIndex());
        assertEquals(5, testTile2.getBoardSection());
        assertFalse(testTile2.isMine());
        assertEquals(0, testTile2.getNumNeighbouringMines());
        assertTrue(testTile2.isRange());
        assertFalse(testTile2.isQuestionMark());
    }

    @Test
    void testSectionSix() {
        testTile = new Tile(testRuleSetList.getCurrentlySelectedRuleSet(), testMineLayout, 49, 31702);
        testTile.setNumNeighbouringMines(4);
        testTile.updateDisplayValue(12345);
        assertEquals("3-4", testTile.getDisplayValue());
        assertEquals(0, testTile.getState());
        assertEquals(49, testTile.getIndex());
        assertEquals(6, testTile.getBoardSection());
        assertFalse(testTile.isMine());
        assertEquals(4, testTile.getNumNeighbouringMines());
        assertTrue(testTile.isRange());
        assertFalse(testTile.isQuestionMark());
    }

    @Test
    void testSectionSeven() {
        testTile = new Tile(testRuleSetList.getCurrentlySelectedRuleSet(), testMineLayout, 90, 1000);
        testTile.setNumNeighbouringMines(3);
        testTile.updateDisplayValue(12345);
        assertEquals("mine", testTile.getDisplayValue());
        assertEquals(0, testTile.getState());
        assertEquals(90, testTile.getIndex());
        assertEquals(7, testTile.getBoardSection());
        assertTrue(testTile.isMine());
        assertEquals(3, testTile.getNumNeighbouringMines());
        assertFalse(testTile.isRange());
        assertFalse(testTile.isQuestionMark());
    }

    @Test
    void testSectionEight() {
        testTile = new Tile(testRuleSetList.getCurrentlySelectedRuleSet(), testMineLayout, 91, 1000);
        testTile.setNumNeighbouringMines(1);
        testTile.updateDisplayValue(12345);
        assertEquals("1", testTile.getDisplayValue());
        assertEquals(0, testTile.getState());
        assertEquals(91, testTile.getIndex());
        assertEquals(8, testTile.getBoardSection());
        assertFalse(testTile.isMine());
        assertEquals(1, testTile.getNumNeighbouringMines());
        assertFalse(testTile.isRange());
        assertFalse(testTile.isQuestionMark());
    }

    @Test
    void testSectionNine() {
        testTile = new Tile(testRuleSetList.getCurrentlySelectedRuleSet(), testMineLayout, 99, 1000);
        testTile.setNumNeighbouringMines(0);
        testTile.updateDisplayValue(12345);
        assertEquals("mine", testTile.getDisplayValue());
        assertEquals(0, testTile.getState());
        assertEquals(99, testTile.getIndex());
        assertEquals(9, testTile.getBoardSection());
        assertTrue(testTile.isMine());
        assertEquals(0, testTile.getNumNeighbouringMines());
        assertFalse(testTile.isRange());
        assertFalse(testTile.isQuestionMark());
    }
}
