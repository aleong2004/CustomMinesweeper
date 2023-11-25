package ui.buttons;

import ui.MenuGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Code based off of SimpleDrawingPlayer
// Represents a button that saves data to a JSON file when clicked
public class SaveButton extends Button {
    // EFFECTS: constructs a new SaveButton
    public SaveButton(JComponent parent, MenuGUI menuGUI) {
        super(parent, menuGUI);
    }

    // MODIFIES: this
    // EFFECTS: creates the button and appends it to the given parent JComponent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save");
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: adds an SaveButtonListener to this button
    @Override
    protected void addListener() {
        button.addActionListener(new SaveButtonListener());
    }

    // Represents an ActionListener that handles action events
    private class SaveButtonListener implements ActionListener {
        // EFFECTS: when this button is clicked, save data to a JSON file
        @Override
        public void actionPerformed(ActionEvent e) {
            menuGUI.processAction("save");
        }
    }
}
