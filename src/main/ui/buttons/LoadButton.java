package ui.buttons;

import ui.MenuGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Code based off of SimpleDrawingPlayer
// Represents a button that loads in data from a JSON file when clicked
public class LoadButton extends Button {
    // EFFECTS: constructs a new LoadButton
    public LoadButton(JComponent parent, MenuGUI menuGUI) {
        super(parent, menuGUI);
    }

    // MODIFIES: this
    // EFFECTS: creates the button and appends it to the given parent JComponent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load");
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: adds an LoadButtonListener to this button
    @Override
    protected void addListener() {
        button.addActionListener(new LoadButtonListener());
    }

    // Represents an ActionListener that handles action events
    private class LoadButtonListener implements ActionListener {
        // EFFECTS: when this button is clicked, load in data from a JSON file
        @Override
        public void actionPerformed(ActionEvent e) {
            menuGUI.processAction("load");
        }
    }
}
