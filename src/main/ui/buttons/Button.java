package ui.buttons;

import ui.MenuGUI;

import javax.swing.*;

// Code based off of SimpleDrawingPlayer
// Represents a button added to a MenuGUI
public abstract class Button {
    protected JButton button;
    protected MenuGUI menuGUI;

    // EFFECTS: constructs a new button and appends it to the given parent JComponent and MenuGUI
    public Button(JComponent parent, MenuGUI menuGUI) {
        this.menuGUI = menuGUI;
        createButton(parent);
        button.setEnabled(true);
        addListener();
    }

    // MODIFIES: this
    // EFFECTS: creates the button
    protected abstract void createButton(JComponent parent);

    // MODIFIES: this
    // EFFECTS: adds a listener to this button;
    protected abstract void addListener();

    // MODIFIES: this
    // EFFECTS: enables the button
    public void enable() {
        button.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: disables the button
    public void disable() {
        button.setEnabled(false);
    }
}
