package ui.buttons;

import ui.MenuGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a button that adds a default ruleset when clicked
public class AddButton extends Button {
    // EFFECTS: constructs a new AddButton
    public AddButton(JComponent parent, MenuGUI menuGUI) {
        super(parent, menuGUI);
    }

    // MODIFIES: this
    // EFFECTS: creates the button and appends it to the given parent JComponent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add");
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: adds an AddButtonListener to this button
    @Override
    protected void addListener() {
        button.addActionListener(new AddButtonListener());
    }

    // Represents an ActionListener that handles action events
    private class AddButtonListener implements ActionListener {
        // EFFECTS: when this button is clicked, add a default ruleset
        @Override
        public void actionPerformed(ActionEvent e) {
            menuGUI.processAction("add");
        }
    }
}
