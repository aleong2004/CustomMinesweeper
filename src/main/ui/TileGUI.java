package ui;

import model.Tile;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Represents the graphic for a tile
public class TileGUI {
    private Tile tile;
    private JLabel label;
    private GameBoardGUI game;

    // Images
    private ImageIcon unrevealed;
    private ImageIcon flag;
    private ImageIcon zero;
    private ImageIcon one;
    private ImageIcon two;
    private ImageIcon three;
    private ImageIcon four;
    private ImageIcon five;
    private ImageIcon six;
    private ImageIcon seven;
    private ImageIcon eight;
    private ImageIcon zeroToOne;
    private ImageIcon oneToTwo;
    private ImageIcon twoToThree;
    private ImageIcon threeToFour;
    private ImageIcon fourToFive;
    private ImageIcon fiveToSix;
    private ImageIcon sixToSeven;
    private ImageIcon sevenToEight;
    private ImageIcon questionMark;

    // EFFECTS: constructs the graphic for an unrevealed tile
    public TileGUI(Tile tile, GameBoardGUI parent) {
        tileImageSetup();
        this.tile = tile;
        this.game = parent;
        this.label = new JLabel(unrevealed);
        this.label.addMouseListener(new TileListener());
    }

    // MODIFIES: this
    // EFFECTS: setups images that correspond to the tile's possible display values
    private void tileImageSetup() {
        unrevealed = new ImageIcon("./data/unrevealed.png");
        flag = new ImageIcon("./data/flag.png");
        zero = new ImageIcon("./data/zero-alternate.png");
        one = new ImageIcon("./data/one.png");
        two = new ImageIcon("./data/two.png");
        three = new ImageIcon("./data/three.png");
        four = new ImageIcon("./data/four.png");
        five = new ImageIcon("./data/five.png");
        six = new ImageIcon("./data/six.png");
        seven = new ImageIcon("./data/seven.png");
        eight = new ImageIcon("./data/eight.png");
        zeroToOne = new ImageIcon("./data/zero-one.png");
        oneToTwo = new ImageIcon("./data/one-two.png");
        twoToThree = new ImageIcon("./data/two-three.png");
        threeToFour = new ImageIcon("./data/three-four.png");
        fourToFive = new ImageIcon("./data/four-five.png");
        fiveToSix = new ImageIcon("./data/five-six.png");
        sixToSeven = new ImageIcon("./data/six-seven.png");
        sevenToEight = new ImageIcon("./data/seven-eight.png");
        questionMark = new ImageIcon("./data/questionmark.png");
    }

    // MODIFIES: this
    // EFFECTS: updates the displayed graphic
    //          - if the tile is unrevealed, graphic is set to an unrevealed tile
    //          - if the tile is flagged, graphic is set to a flagged tile
    //          - if the tile is revealed and should display a range, updateLabelRange() is called;
    //          - if the tile is revealed and should not display a range, updateLabelNoRange() is called;
    public void updateLabel() {
        this.label.setIcon(null);
        if (this.tile.getState() == 0) {
            this.label.setIcon(unrevealed);
        } else if (this.tile.getState() == 2) {
            this.label.setIcon(flag);
        } else if (this.tile.getDisplayValue().equals("mine")) {
            this.label.setIcon(new ImageIcon("./data/5f7.jpeg"));
        } else if (this.tile.isRange()
                && !this.tile.isQuestionMark()) {
            updateLabelRange();
        } else {
            updateLabelNoRange();
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the displayed graphic to the image corresponding to the display value of the tile
    private void updateLabelNoRange() {
        if (this.tile.getDisplayValue().equals("0")) {
            this.label.setIcon(zero);
        } else if (this.tile.getDisplayValue().equals("1")) {
            this.label.setIcon(one);
        } else if (this.tile.getDisplayValue().equals("2")) {
            this.label.setIcon(two);
        } else if (this.tile.getDisplayValue().equals("3")) {
            this.label.setIcon(three);
        } else if (this.tile.getDisplayValue().equals("4")) {
            this.label.setIcon(four);
        } else if (this.tile.getDisplayValue().equals("5")) {
            this.label.setIcon(five);
        } else if (this.tile.getDisplayValue().equals("6")) {
            this.label.setIcon(six);
        } else if (this.tile.getDisplayValue().equals("7")) {
            this.label.setIcon(seven);
        } else if (this.tile.getDisplayValue().equals("8")) {
            this.label.setIcon(eight);
        } else {
            this.label.setIcon(questionMark);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the displayed graphic to the image corresponding to the display value of the tile
    private void updateLabelRange() {
        if (this.tile.getDisplayValue().equals("0-1")) {
            this.label.setIcon(zeroToOne);
        } else if (this.tile.getDisplayValue().equals("1-2")) {
            this.label.setIcon(oneToTwo);
        } else if (this.tile.getDisplayValue().equals("2-3")) {
            this.label.setIcon(twoToThree);
        } else if (this.tile.getDisplayValue().equals("3-4")) {
            this.label.setIcon(threeToFour);
        } else if (this.tile.getDisplayValue().equals("4-5")) {
            this.label.setIcon(fourToFive);
        } else if (this.tile.getDisplayValue().equals("5-6")) {
            this.label.setIcon(fiveToSix);
        } else if (this.tile.getDisplayValue().equals("6-7")) {
            this.label.setIcon(sixToSeven);
        } else {
            this.label.setIcon(sevenToEight);
        }
    }

    public JLabel getLabel() {
        return label;
    }

    // Represents a MouseListener that handles mouse events
    private class TileListener extends MouseAdapter {
        // EFFECTS: if this TileGUI is left-clicked, reveal this TileGUI,
        //          if this TileGUI is right-clicked, flag this TileGUI,
        //          if a different mouse button is pressed do nothing
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 1) {
                game.processAction("reveal", tile.getIndex());
            } else if (e.getButton() == 3) {
                game.processAction("flag", tile.getIndex());
            }
        }
    }
}
