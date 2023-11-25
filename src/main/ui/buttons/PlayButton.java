package ui.buttons;

import ui.MenuGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a button that initiates a game of minesweeper when clicked
public class PlayButton extends Button {
    // EFFECTS: constructs a new PlayButton
    public PlayButton(JComponent parent, MenuGUI menuGUI) {
        super(parent, menuGUI);
    }

    // MODIFIES: this
    // EFFECTS: creates the button and appends it to the given parent JComponent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Play");
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: adds an PlayButtonListener to this button
    @Override
    protected void addListener() {
        button.addActionListener(new PlayButtonListener());
    }

    // Represents an ActionListener that handles action events
    private class PlayButtonListener implements ActionListener {
        // EFFECTS: when this button is clicked, initiate a game of minesweeper
        @Override
        public void actionPerformed(ActionEvent e) {
            menuGUI.processAction("play");
        }
    }
}
