# SimpleGame
Game description:
User chooses to move at directions: left, right, up, down and to use spell
The current level is shown to the user as a matrix 15x15
with legend:
# X - hidden cell
# H->?S - Hero is on spell.Do you Want to use the spell?
# H->E - exit and end the current level
# H->D - hero dies, level failed
# o  - hero already been here

The actual matrix is different.
Legend for element codes:
# empty - empty cell
# hero - hero is on this cell
# exit - exit on this cell
# death - die on this cell
# hero:exit - hero is on exit cell
# hero:death - hero is on death cell
# hero:spell:<spell name> hero is on spell with name <spell name>

EveryBody who wants to create a level extends DefaultLevel class.
EveryBody who wants to create a spell extends Spell class.
