package game.defaultlevel;

import game.enums.FieldElementStatus;
import game.enums.HeroDirection;
import game.enums.LevelStatus;
import game.exceptions.*;
import game.field.CustomGameField;
import game.field.GameField;
import game.hero.Hero;
import game.hero.HeroPosition;
import game.spells.Spell;
import game.spells.SpellFactory;

public class DefaultLevel {

    private GameField gameField;
    private Hero hero;
    private HeroPosition heroPosition;
    private LevelStatus levelStatus;

    /**
     * Level class that everybody who wants to create his
     * own custom level class must extend
     */
    public DefaultLevel(Hero hero) {
        this.gameField = new GameField();
        this.hero = hero;
        levelStatus = LevelStatus.RUNNING;
        heroPosition = new HeroPosition();
    }

    /**
     * Moving hero on the field
     * @param direction Direction where the hero should move
     * @throws HeroStepOutOfGameFieldBoundsException If passing a direction leads to step off the field
     * the current exception is thrown
     * @throws ForbiddenDirectionException It is thrown in derived classes where someone may choose
     * to forbid a direction which will be passed as argument
     */
    public void movingTo(HeroDirection direction) throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        boolean isMovingSafe = isMovingSafeAtDirection(direction);
        if (isMovingSafe) {
            makeOldStepUsed();
            makeSafeMoveTo(direction);
        }
        else {
            throw new HeroStepOutOfGameFieldBoundsException(heroPosition, direction);
        }
    }

    /**
     * Creates a spell and activates its effect
     * @throws NoSpellFoundException Hero stepping on non spell cell and user typing the command for
     * activating the spell will cause the current exception to be thrown
     */
    public void activateSpell() throws NoSpellFoundException {
        String spellCode = gameField.getFieldElementCodeAt(heroPosition.getRowIndex(),
            heroPosition.getColumnIndex());
        //spellParts must have syntax hero:spell:<name of spell>
        //Always of length 3
        String[] spellParts = spellCode.split(":");

        if(spellParts.length == 3 && spellParts[1].equals("spell")) {
            CustomGameField customGameField = new CustomGameField(gameField);
            Spell currentSpell = SpellFactory.getAppropriateSpell(spellParts[2], customGameField, hero);

            currentSpell.activateSpecialEffectOnField();
            currentSpell.activateSpecialEffectOnHero();

            gameField.setNewFieldElementCode(heroPosition.getRowIndex(),
                heroPosition.getColumnIndex(), "hero");
        }
        else {
            throw new NoSpellFoundException(heroPosition);
        }
    }

    /**
     * Putting game defined element on the field
     * @throws FailedGeneratingLevelException When one of the inner generating methods fails
     * the current exception is thrown
     */
    public void generateLevel() throws FailedGeneratingLevelException{
        try {
            generateElementAt(2, 3, "spell:InfernoSpell");
            generateElementAt(4, 9, "exit");
            generateElementAt(14, 5, "death");
            generateElementAt(13, 13, "exit");
        } catch (FailedGeneratingElementException e) {
            System.out.println(e);
            throw new FailedGeneratingLevelException();
        }

    }

    /**
     * Generating a single game defined element on the field
     * @param row Row coordinate
     * @param col Column coordinate
     * @param newCode The code of the game element(default is "empty")
     * @throws FailedGeneratingElementException When bad coordinates or wrong code
     * is passed as an argument the current exception is thrown.
     */
    public void generateElementAt(int row, int col, String newCode)
        throws FailedGeneratingElementException {
        boolean isNewCodeValid = newCode.startsWith("spell:") ||
            newCode.equals("exit") || newCode.equals("death");
        boolean isPositionValid = gameField.isMovingSafeAt(row, col);
        if(isNewCodeValid && isPositionValid) {
            gameField.setNewFieldElementCode(row, col, newCode);
        }
        else {
            throw new FailedGeneratingElementException(row, col , newCode);
        }
    }

    /**
     * Printing the game field to the standard output
     */
    public void showGameFieldToUser() {
        gameField.printGameFieldForUserView();
    }

    /**
     * getter for the level status
     */
    public LevelStatus getLevelStatus() {
        return levelStatus;
    }

    /**
     * setter for the level status
     */
    protected void setLevelStatus(LevelStatus levelStatus) {
        this.levelStatus = levelStatus;
    }


    /**
     * Getter for hero's health
     */
    protected int getHeroHealth() {
        return hero.getHealth();
    }

    /**
     * getter for hero's max health
     */
    protected int getHeroMaxHealth() {
        return hero.getMaxHealth();
    }

    protected void increaseHeroHealthBy(int health) {
        hero.increaseHealthWith(health);
    }

    private void makeSafeMoveTo(HeroDirection direction) {
        switch(direction) {
            case LEFT: {
                moveHeroToTheNewPosition(HeroDirection.LEFT);
                break;
            }
            case RIGHT: {
                moveHeroToTheNewPosition(HeroDirection.RIGHT);
                break;
            }
            case UP: {
                moveHeroToTheNewPosition(HeroDirection.UP);
                break;
            }
            default: {
                moveHeroToTheNewPosition(HeroDirection.DOWN);
                break;
            }
        }
    }

    private boolean isMovingSafeAtDirection(HeroDirection direction) {
        switch (direction) {
            case LEFT: {
                int newCol = heroPosition.getColumnIndex() - 1;
                return gameField.isMovingSafeAt(heroPosition.getRowIndex(), newCol);
            }
            case RIGHT: {
                int newCol = heroPosition.getColumnIndex() + 1;
                return gameField.isMovingSafeAt(heroPosition.getRowIndex(), newCol);
            }
            case UP: {
                int newRow = heroPosition.getRowIndex() - 1;
                return gameField.isMovingSafeAt(newRow, heroPosition.getColumnIndex());
            }
            default: {
                int newRow = heroPosition.getRowIndex() + 1;
                return gameField.isMovingSafeAt(newRow, heroPosition.getColumnIndex());
            }
        }
    }

    private void makeOldStepUsed() {
        int heroRow = heroPosition.getRowIndex();
        int heroCol = heroPosition.getColumnIndex();
        gameField.setNewFieldElementCode(heroRow, heroCol, "used");
    }

    private void moveHeroToTheNewPosition(HeroDirection direction) {
        heroPosition.updateHeroPosition(direction);
        int heroRow = heroPosition.getRowIndex();
        int heroCol = heroPosition.getColumnIndex();
        gameField.setFieldElementStatusAt(heroRow, heroCol, FieldElementStatus.REVEALED);
        String currentCode = gameField.getFieldElementCodeAt(heroRow, heroCol);
        if(currentCode.equals("empty")) {
            gameField.setNewFieldElementCode(heroRow, heroCol, "hero");
        }
        else if(currentCode.contains("spell:")) {
            String newCode = "hero:" + gameField.getFieldElementCodeAt(heroRow, heroCol);
            gameField.setNewFieldElementCode(heroRow, heroCol, newCode);
        }
        else if(currentCode.equals("exit")) {
            levelStatus = LevelStatus.PASSED;
            gameField.setNewFieldElementCode(heroRow, heroCol, "hero:exit");
        }
        else {
            levelStatus = LevelStatus.FAILED;
            gameField.setNewFieldElementCode(heroRow, heroCol, "hero:death");
        }

    }
}
