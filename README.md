# My Personal Project

## Minesweeper Application

This application is the classic game of *Minesweeper* but with extra rules and game modifiers that can be **toggled** and **adjusted** by the user. These custom rulesets can be **saved** to a list of previously saved rulesets, then from that list, **loaded** in to play a customized game of *Minesweeper*. Some examples of these extra rules and game modifiers include:

- A limit on the number of flags (used to mark cells that contain a mine) that can be placed
- A random chance for a cell to, when revealed, have its cell number (the number of neighbouring cells that contain a mine) replaced with a '?'
- A random chance for a cell to, when revealed, have its cell number replaced by a range that contains the cell number (e.g. if a cell number is '4', it may be replaced by something like '4-5')


This application will be used by those who are familiar with *Minesweeper* but also seek challenges that go beyond the basic rules of the game.

As someone who plays the classic version of *Minesweeper* fairly often, I find that after playing the game enough, strategy and deductive reasoning stop being necessary as games become trivialized by recognizing patterns in the layout of revealed cell numbers. Consequently, my motivation for this project is to be able to introduce unique, customizable challenges to the classic game of *Minesweeper*.

## User Stories

- As a user, I want to be able to create a custom ruleset and add it to my list of saved rulesets
- As a user, I want to be able to view my list of saved rulesets
- As a user, I want to be able to delete a ruleset from my list of saved rulesets
- As a user, I want to be able to load in a saved ruleset and use it to play a game of *Minesweeper*
- As a user, I want to be able to view statistics about a specific ruleset, such as the number of games completed and my win percentage on that ruleset
- As a user, I want to be able to save my list of saved rulesets to a file
- As a user, I want to be able to load my list of saved rulesets from a file

## Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by pressing the "Remove" button, which will remove the currently selected ruleset
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by pressing the "Stats" button, which will display the statistics of the currently selected ruleset
- You can locate the visual component by pressing the "Play" button to start a game of *Minesweeper* using the currently selected ruleset (each tile is an image)
- You can save the state of my application by clicking the "Save" button in the main menu (the main menu is disabled when a *Minesweeper* game is running, so the state of the application can only be saved when there is not an active *Minesweeper* game)
- You can reload the state of my application by pressing the "Load" button in the main menu (the main menu is disabled when a *Minesweeper* game is running, so the state of the application can only be reloaded when there is not an active *Minesweeper* game)

## Phase 4: Task 2

Possible events that can occur when the program runs:
- "Ruleset added at index X", where X is the index of the added ruleset
- "Ruleset removed at index X", where X is the index of the removed ruleset
- "Ruleset edited at index X", where X is the index of the edited ruleset
- "Stats viewed for ruleset at index X", where X is the index of the ruleset whose stats were viewed
- "Saved data successfully"
- "Generated empty minesweeper board"
- "Generated randomized minesweeper board"
- "Minesweeper game completed"

## Phase 4: Task 3

If I had more time, the first change I would make would be to apply the Singleton Design Pattern to the "MenuGUI" class. Since "MenuGUI" represents the GUI of the main menu, there should only ever be one instance of "MenuGUI". In addition, each class that facilitates functionality provided by "MenuGUI" requires access to the same shared instance of "MenuGUI". Applying the Singleton Design Pattern to "MenuGUI" would ensure that there can only ever be one instance of "MenuGUI". The Singleton Design Pattern would also provide easy access to this single instance of "MenuGUI", which would be extremely helpful anytime I want to add functionality to "MenuGUI" in the future.