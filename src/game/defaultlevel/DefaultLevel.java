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

    public DefaultLevel(Hero hero) {
        this.gameField = new GameField();
        this.hero = hero;
        levelStatus = LevelStatus.RUNNING;
        heroPosition = new HeroPosition();
    }

    public void movingTo(HeroDirection direction) throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        boolean isMovingSafe = isMovingSafeAtDirection(direction);
        if(isMovingSafe) {
            makeOldStepUsed();
            makeSafeMoveTo(direction);
        }
        else {
            throw new HeroStepOutOfGameFieldBoundsException(heroPosition, direction);
        }
    }

    public void activateSpell() throws NoSpellFoundException {
        int heroRow = heroPosition.getRowIndex();
        int heroCol = heroPosition.getColumnIndex();
        String spellCode = gameField.getFieldElementCodeAt(heroRow, heroCol);
        String[] spellParts = spellCode.split(":");
        if(spellParts.length == 3 && spellParts[1].equals("spell")) {
            CustomGameField customGameField = new CustomGameField(gameField);
            Spell currentSpell = SpellFactory.getAppropriateSpell(spellParts[2], customGameField, hero);
            currentSpell.activateSpecialEffectOnField();
            currentSpell.activateSpecialEffectOnHero(); //fix
            gameField.setNewFieldElementCode(heroRow, heroCol, "hero");
        }
        else {
            throw new NoSpellFoundException(heroPosition);
        }
    }

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

    public void showGameFieldToUser() {
        gameField.printGameFieldForUserView();
    }

    public LevelStatus getLevelStatus() {
        return levelStatus;
    }

    protected void setLevelStatus(LevelStatus levelStatus) {
        this.levelStatus = levelStatus;
    }

    protected int getHeroHealth() {
        return hero.getHealth();
    }

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
