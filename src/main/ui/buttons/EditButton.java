package ui.buttons;

import ui.MenuGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a button that edits the currently selected ruleset when clicked
public class EditButton extends Button {
    // EFFECTS: constructs a new EditButton
    public EditButton(JComponent parent, MenuGUI menuGUI) {
        super(parent, menuGUI);
    }

    // MODIFIES: this
    // EFFECTS: creates the button and appends it to the given parent JComponent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Edit");
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: adds an EditButtonListener to this button
    @Override
    protected void addListener() {
        button.addActionListener(new EditButtonListener());
    }

    // Represents an ActionListener that handles action events
    private class EditButtonListener implements ActionListener {
        // EFFECTS: when this button is clicked, edit the currently selected ruleset
        @Override
        public void actionPerformed(ActionEvent e) {
            menuGUI.processAction("edit");
        }
    }
}
