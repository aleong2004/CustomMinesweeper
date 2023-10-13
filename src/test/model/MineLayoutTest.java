package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit Tests for MineLayout
public class MineLayoutTest {
    RuleSetList testRuleSetList;
    MineLayout testMineLayout;

    @BeforeEach
    void runBefore() {
        testRuleSetList = new RuleSetList();
    }

    @Test
    void testDefaultRuleSet() {
        testMineLayout = new MineLayout(testRuleSetList.getCurrentlySelectedRuleSet());
        assertEquals(25, testMineLayout.getMineLayout().size());
        assertFalse(testMineLayout.getMineLayout().get(0));   // These asserts check that each item is false
        assertFalse(testMineLayout.getMineLayout().get(1));   //
        assertFalse(testMineLayout.getMineLayout().get(2));   //
        assertFalse(testMineLayout.getMineLayout().get(3));   //
        assertFalse(testMineLayout.getMineLayout().get(4));   //
        assertFalse(testMineLayout.getMineLayout().get(5));   //
        assertFalse(testMineLayout.getMineLayout().get(6));   //
        assertFalse(testMineLayout.getMineLayout().get(7));   //
        assertFalse(testMineLayout.getMineLayout().get(8));   //
        assertFalse(testMineLayout.getMineLayout().get(9));   //
        assertFalse(testMineLayout.getMineLayout().get(10));  //
        assertFalse(testMineLayout.getMineLayout().get(11));  //
        assertFalse(testMineLayout.getMineLayout().get(12));  //
        assertFalse(testMineLayout.getMineLayout().get(13));  //
        assertFalse(testMineLayout.getMineLayout().get(14));  //
        assertFalse(testMineLayout.getMineLayout().get(15));  //
        assertFalse(testMineLayout.getMineLayout().get(16));  //
        assertFalse(testMineLayout.getMineLayout().get(17));  //
        assertFalse(testMineLayout.getMineLayout().get(18));  //
        assertFalse(testMineLayout.getMineLayout().get(19));  //
        assertFalse(testMineLayout.getMineLayout().get(20));  //
        assertFalse(testMineLayout.getMineLayout().get(21));  //
        assertFalse(testMineLayout.getMineLayout().get(22));  //
        assertFalse(testMineLayout.getMineLayout().get(23));  //
        assertFalse(testMineLayout.getMineLayout().get(24));  //
        testMineLayout.generateMines();
        assertEquals(2, testMineLayout.getNumMines());
        assertEquals(25, testMineLayout.getMineLayout().size());
        assertTrue(testMineLayout.getMineLayout().get(0));    // These asserts check that the first 2 items
        assertTrue(testMineLayout.getMineLayout().get(1));    // are true and the rest are false
        assertFalse(testMineLayout.getMineLayout().get(2));   //
        assertFalse(testMineLayout.getMineLayout().get(3));   //
        assertFalse(testMineLayout.getMineLayout().get(4));   //
        assertFalse(testMineLayout.getMineLayout().get(5));   //
        assertFalse(testMineLayout.getMineLayout().get(6));   //
        assertFalse(testMineLayout.getMineLayout().get(7));   //
        assertFalse(testMineLayout.getMineLayout().get(8));   //
        assertFalse(testMineLayout.getMineLayout().get(9));   //
        assertFalse(testMineLayout.getMineLayout().get(10));  //
        assertFalse(testMineLayout.getMineLayout().get(11));  //
        assertFalse(testMineLayout.getMineLayout().get(12));  //
        assertFalse(testMineLayout.getMineLayout().get(13));  //
        assertFalse(testMineLayout.getMineLayout().get(14));  //
        assertFalse(testMineLayout.getMineLayout().get(15));  //
        assertFalse(testMineLayout.getMineLayout().get(16));  //
        assertFalse(testMineLayout.getMineLayout().get(17));  //
        assertFalse(testMineLayout.getMineLayout().get(18));  //
        assertFalse(testMineLayout.getMineLayout().get(19));  //
        assertFalse(testMineLayout.getMineLayout().get(20));  //
        assertFalse(testMineLayout.getMineLayout().get(21));  //
        assertFalse(testMineLayout.getMineLayout().get(22));  //
        assertFalse(testMineLayout.getMineLayout().get(23));  //
        assertFalse(testMineLayout.getMineLayout().get(24));  //
        testMineLayout.randomizeMineLayout(1, 1111);
        assertEquals(25, testMineLayout.getMineLayout().size());
        assertTrue(testMineLayout.getMineLayout().get(0));    // These asserts check that the list has been
        assertFalse(testMineLayout.getMineLayout().get(1));   // randomized (using a set seed)
        assertFalse(testMineLayout.getMineLayout().get(2));   //
        assertFalse(testMineLayout.getMineLayout().get(3));   //
        assertFalse(testMineLayout.getMineLayout().get(4));   //
        assertFalse(testMineLayout.getMineLayout().get(5));   //
        assertFalse(testMineLayout.getMineLayout().get(6));   //
        assertFalse(testMineLayout.getMineLayout().get(7));   //
        assertFalse(testMineLayout.getMineLayout().get(8));   //
        assertFalse(testMineLayout.getMineLayout().get(9));   //
        assertFalse(testMineLayout.getMineLayout().get(10));  //
        assertFalse(testMineLayout.getMineLayout().get(11));  //
        assertFalse(testMineLayout.getMineLayout().get(12));  //
        assertTrue(testMineLayout.getMineLayout().get(13));   //
        assertFalse(testMineLayout.getMineLayout().get(14));  //
        assertFalse(testMineLayout.getMineLayout().get(15));  //
        assertFalse(testMineLayout.getMineLayout().get(16));  //
        assertFalse(testMineLayout.getMineLayout().get(17));  //
        assertFalse(testMineLayout.getMineLayout().get(18));  //
        assertFalse(testMineLayout.getMineLayout().get(19));  //
        assertFalse(testMineLayout.getMineLayout().get(20));  //
        assertFalse(testMineLayout.getMineLayout().get(21));  //
        assertFalse(testMineLayout.getMineLayout().get(22));  //
        assertFalse(testMineLayout.getMineLayout().get(23));  //
        assertFalse(testMineLayout.getMineLayout().get(24));  //
    }

    @Test
    void testCustomRuleSet() {
        testRuleSetList.editExistingRuleSet("custom", 7, 6, 0.5, false, 20, 0.2, 0.2);
        testMineLayout = new MineLayout(testRuleSetList.getCurrentlySelectedRuleSet());
        assertEquals(42, testMineLayout.getMineLayout().size());
        assertFalse(testMineLayout.getMineLayout().get(0));   // These asserts check that each item is false
        assertFalse(testMineLayout.getMineLayout().get(1));   //
        assertFalse(testMineLayout.getMineLayout().get(2));   //
        assertFalse(testMineLayout.getMineLayout().get(3));   //
        assertFalse(testMineLayout.getMineLayout().get(4));   //
        assertFalse(testMineLayout.getMineLayout().get(5));   //
        assertFalse(testMineLayout.getMineLayout().get(6));   //
        assertFalse(testMineLayout.getMineLayout().get(7));   //
        assertFalse(testMineLayout.getMineLayout().get(8));   //
        assertFalse(testMineLayout.getMineLayout().get(9));   //
        assertFalse(testMineLayout.getMineLayout().get(10));  //
        assertFalse(testMineLayout.getMineLayout().get(11));  //
        assertFalse(testMineLayout.getMineLayout().get(12));  //
        assertFalse(testMineLayout.getMineLayout().get(13));  //
        assertFalse(testMineLayout.getMineLayout().get(14));  //
        assertFalse(testMineLayout.getMineLayout().get(15));  //
        assertFalse(testMineLayout.getMineLayout().get(16));  //
        assertFalse(testMineLayout.getMineLayout().get(17));  //
        assertFalse(testMineLayout.getMineLayout().get(18));  //
        assertFalse(testMineLayout.getMineLayout().get(19));  //
        assertFalse(testMineLayout.getMineLayout().get(20));  //
        assertFalse(testMineLayout.getMineLayout().get(21));  //
        assertFalse(testMineLayout.getMineLayout().get(22));  //
        assertFalse(testMineLayout.getMineLayout().get(23));  //
        assertFalse(testMineLayout.getMineLayout().get(24));  //
        assertFalse(testMineLayout.getMineLayout().get(25));  //
        assertFalse(testMineLayout.getMineLayout().get(26));  //
        assertFalse(testMineLayout.getMineLayout().get(27));  //
        assertFalse(testMineLayout.getMineLayout().get(28));  //
        assertFalse(testMineLayout.getMineLayout().get(29));  //
        assertFalse(testMineLayout.getMineLayout().get(30));  //
        assertFalse(testMineLayout.getMineLayout().get(31));  //
        assertFalse(testMineLayout.getMineLayout().get(32));  //
        assertFalse(testMineLayout.getMineLayout().get(33));  //
        assertFalse(testMineLayout.getMineLayout().get(34));  //
        assertFalse(testMineLayout.getMineLayout().get(35));  //
        assertFalse(testMineLayout.getMineLayout().get(36));  //
        assertFalse(testMineLayout.getMineLayout().get(37));  //
        assertFalse(testMineLayout.getMineLayout().get(38));  //
        assertFalse(testMineLayout.getMineLayout().get(39));  //
        assertFalse(testMineLayout.getMineLayout().get(40));  //
        assertFalse(testMineLayout.getMineLayout().get(41));  //
        testMineLayout.generateMines();
        assertEquals(21, testMineLayout.getNumMines());
        assertEquals(42, testMineLayout.getMineLayout().size());
        assertTrue(testMineLayout.getMineLayout().get(0));    // These asserts check that the first 21 items
        assertTrue(testMineLayout.getMineLayout().get(1));    // are true and the rest are false
        assertTrue(testMineLayout.getMineLayout().get(2));    //
        assertTrue(testMineLayout.getMineLayout().get(3));    //
        assertTrue(testMineLayout.getMineLayout().get(4));    //
        assertTrue(testMineLayout.getMineLayout().get(5));    //
        assertTrue(testMineLayout.getMineLayout().get(6));    //
        assertTrue(testMineLayout.getMineLayout().get(7));    //
        assertTrue(testMineLayout.getMineLayout().get(8));    //
        assertTrue(testMineLayout.getMineLayout().get(9));    //
        assertTrue(testMineLayout.getMineLayout().get(10));   //
        assertTrue(testMineLayout.getMineLayout().get(11));   //
        assertTrue(testMineLayout.getMineLayout().get(12));   //
        assertTrue(testMineLayout.getMineLayout().get(13));   //
        assertTrue(testMineLayout.getMineLayout().get(14));   //
        assertTrue(testMineLayout.getMineLayout().get(15));   //
        assertTrue(testMineLayout.getMineLayout().get(16));   //
        assertTrue(testMineLayout.getMineLayout().get(17));   //
        assertTrue(testMineLayout.getMineLayout().get(18));   //
        assertTrue(testMineLayout.getMineLayout().get(19));   //
        assertTrue(testMineLayout.getMineLayout().get(20));   //
        assertFalse(testMineLayout.getMineLayout().get(21));  //
        assertFalse(testMineLayout.getMineLayout().get(22));  //
        assertFalse(testMineLayout.getMineLayout().get(23));  //
        assertFalse(testMineLayout.getMineLayout().get(24));  //
        assertFalse(testMineLayout.getMineLayout().get(25));  //
        assertFalse(testMineLayout.getMineLayout().get(26));  //
        assertFalse(testMineLayout.getMineLayout().get(27));  //
        assertFalse(testMineLayout.getMineLayout().get(28));  //
        assertFalse(testMineLayout.getMineLayout().get(29));  //
        assertFalse(testMineLayout.getMineLayout().get(30));  //
        assertFalse(testMineLayout.getMineLayout().get(31));  //
        assertFalse(testMineLayout.getMineLayout().get(32));  //
        assertFalse(testMineLayout.getMineLayout().get(33));  //
        assertFalse(testMineLayout.getMineLayout().get(34));  //
        assertFalse(testMineLayout.getMineLayout().get(35));  //
        assertFalse(testMineLayout.getMineLayout().get(36));  //
        assertFalse(testMineLayout.getMineLayout().get(37));  //
        assertFalse(testMineLayout.getMineLayout().get(38));  //
        assertFalse(testMineLayout.getMineLayout().get(39));  //
        assertFalse(testMineLayout.getMineLayout().get(40));  //
        assertFalse(testMineLayout.getMineLayout().get(41));  //
        testMineLayout.randomizeMineLayout(18, 1111);
        assertEquals(42, testMineLayout.getMineLayout().size());
        assertTrue(testMineLayout.getMineLayout().get(0));    // These asserts check that the list has been
        assertTrue(testMineLayout.getMineLayout().get(1));    // randomized (using a set seed)
        assertFalse(testMineLayout.getMineLayout().get(2));   //
        assertTrue(testMineLayout.getMineLayout().get(3));    //
        assertTrue(testMineLayout.getMineLayout().get(4));    //
        assertFalse(testMineLayout.getMineLayout().get(5));   //
        assertFalse(testMineLayout.getMineLayout().get(6));   //
        assertTrue(testMineLayout.getMineLayout().get(7));    //
        assertTrue(testMineLayout.getMineLayout().get(8));    //
        assertFalse(testMineLayout.getMineLayout().get(9));   //
        assertFalse(testMineLayout.getMineLayout().get(10));  //
        assertFalse(testMineLayout.getMineLayout().get(11));  //
        assertFalse(testMineLayout.getMineLayout().get(12));  //
        assertTrue(testMineLayout.getMineLayout().get(13));   //
        assertFalse(testMineLayout.getMineLayout().get(14));  //
        assertTrue(testMineLayout.getMineLayout().get(15));   //
        assertTrue(testMineLayout.getMineLayout().get(16));   //
        assertFalse(testMineLayout.getMineLayout().get(17));  //
        assertFalse(testMineLayout.getMineLayout().get(18));  //
        assertFalse(testMineLayout.getMineLayout().get(19));  //
        assertTrue(testMineLayout.getMineLayout().get(20));   //
        assertTrue(testMineLayout.getMineLayout().get(21));   //
        assertFalse(testMineLayout.getMineLayout().get(22));  //
        assertFalse(testMineLayout.getMineLayout().get(23));  //
        assertTrue(testMineLayout.getMineLayout().get(24));   //
        assertTrue(testMineLayout.getMineLayout().get(25));   //
        assertFalse(testMineLayout.getMineLayout().get(26));  //
        assertFalse(testMineLayout.getMineLayout().get(27));  //
        assertFalse(testMineLayout.getMineLayout().get(28));  //
        assertTrue(testMineLayout.getMineLayout().get(29));   //
        assertTrue(testMineLayout.getMineLayout().get(30));   //
        assertFalse(testMineLayout.getMineLayout().get(31));  //
        assertFalse(testMineLayout.getMineLayout().get(32));  //
        assertFalse(testMineLayout.getMineLayout().get(33));  //
        assertTrue(testMineLayout.getMineLayout().get(34));   //
        assertTrue(testMineLayout.getMineLayout().get(35));   //
        assertFalse(testMineLayout.getMineLayout().get(36));  //
        assertFalse(testMineLayout.getMineLayout().get(37));  //
        assertTrue(testMineLayout.getMineLayout().get(38));   //
        assertTrue(testMineLayout.getMineLayout().get(39));   //
        assertTrue(testMineLayout.getMineLayout().get(40));   //
        assertTrue(testMineLayout.getMineLayout().get(41));   //
    }
}
