package ui.buttons;

import ui.MenuGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Code based off of SimpleDrawingPlayer
// Represents a button that removes the currently selected ruleset when clicked
public class RemoveButton extends Button {
    // EFFECTS: constructs a new RemoveButton
    public RemoveButton(JComponent parent, MenuGUI menuGUI) {
        super(parent, menuGUI);
    }

    // MODIFIES: this
    // EFFECTS: creates the button and appends it to the given parent JComponent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Remove");
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: adds an RemoveButtonListener to this button
    @Override
    protected void addListener() {
        button.addActionListener(new RemoveButtonListener());
    }

    // Represents an ActionListener that handles action events
    private class RemoveButtonListener implements ActionListener {
        // EFFECTS: when this button is clicked, remove the currently selected ruleset
        @Override
        public void actionPerformed(ActionEvent e) {
            menuGUI.processAction("remove");
        }
    }
}
