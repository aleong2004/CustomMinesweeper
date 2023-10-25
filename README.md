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