package ui.buttons;

import ui.MenuGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a button that processes the input given to the text field in its parent MenuGUI
public class EnterButton extends Button {
    // EFFECTS: constructs a new EnterButton
    public EnterButton(JComponent parent, MenuGUI menuGUI) {
        super(parent, menuGUI);
    }

    // MODIFIES: this
    // EFFECTS: creates the button and appends it to the given parent JComponent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Enter");
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: adds an EnterButtonListener to this button
    @Override
    protected void addListener() {
        button.addActionListener(new EnterButtonListener());
    }

    // Represents an ActionListener that handles action events
    private class EnterButtonListener implements ActionListener {
        // EFFECTS: when this button is clicked, process the input given to the text field in the parent MenuGUI
        @Override
        public void actionPerformed(ActionEvent e) {
            menuGUI.processAction("enter");
        }
    }
}
