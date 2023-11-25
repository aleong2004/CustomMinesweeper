package ui.buttons;

import ui.MenuGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Code based off of SimpleDrawingPlayer
// Represents a button that displays the stats of the currently selected ruleset when clicked
public class StatsButton extends Button {
    // EFFECTS: constructs a new StatsButton
    public StatsButton(JComponent parent, MenuGUI menuGUI) {
        super(parent, menuGUI);
    }

    // MODIFIES: this
    // EFFECTS: creates the button and appends it to the given parent JComponent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Stats");
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: adds an StatsButtonListener to this button
    @Override
    protected void addListener() {
        button.addActionListener(new StatsButtonListener());
    }

    // Represents an ActionListener that handles action events
    private class StatsButtonListener implements ActionListener {
        // EFFECTS: when this button is clicked, display the stats of the currently selected ruleset
        @Override
        public void actionPerformed(ActionEvent e) {
            menuGUI.processAction("stats");
        }
    }
}
