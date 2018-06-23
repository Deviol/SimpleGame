package game;

public class DefaultLevel {

    private GameField gameField;
    private Hero hero;
    private LevelStatus levelStatus;
    private HeroPosition heroPosition;

    public DefaultLevel(Hero hero) {
        this.gameField = new GameField();
        this.hero = hero;
        levelStatus = LevelStatus.RUNNING;
        heroPosition = new HeroPosition();
    }

    public void movingLeft() throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        boolean isMovingSafe = isMovingSafeAtDirection(HeroDirection.LEFT);
        if(isMovingSafe) {
            makeOldStepUsed();
            moveHeroToTheNewPosition(HeroDirection.LEFT);
        }
        else {
            throw new HeroStepOutOfGameFieldBoundsException();
        }
    }

    public void movingRight() throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        boolean isMovingSafe = isMovingSafeAtDirection(HeroDirection.RIGHT);
        if(isMovingSafe) {
            makeOldStepUsed();
            moveHeroToTheNewPosition(HeroDirection.RIGHT);
        }
        else {
            throw new HeroStepOutOfGameFieldBoundsException();
        }
    }

    public void movingUp() throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        boolean isMovingSafe = isMovingSafeAtDirection(HeroDirection.UP);
        if(isMovingSafe) {
            makeOldStepUsed();
            moveHeroToTheNewPosition(HeroDirection.UP);
        }
        else {
            throw new HeroStepOutOfGameFieldBoundsException();
        }
    }

    public void movingDown() throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        boolean isMovingSafe = isMovingSafeAtDirection(HeroDirection.DOWN);
        if(isMovingSafe) {
            makeOldStepUsed();
            moveHeroToTheNewPosition(HeroDirection.DOWN);
        }
        else {
            throw new HeroStepOutOfGameFieldBoundsException();
        }
    }

    public void activateSpell() throws NoSpellFoundException {
        int heroRow = heroPosition.getRowIndex();
        int heroCol = heroPosition.getColumnIndex();
        String spellCode = gameField.getFieldElementCodeAt(heroRow, heroCol);
        String[] spellParts = spellCode.split(":");
        if(spellParts.length == 3 && spellParts[1].equals("spell")) {
            Spell currentSpell = SpellFactory.getAppropriateSpell(spellParts[2], gameField, hero);
            currentSpell.activateSpecialEffectOnField();
            currentSpell.activateSpecialEffectOnHero(5); //fix
            gameField.setNewFieldElementCode(heroRow, heroCol, "hero");
        }
        else {
            throw new NoSpellFoundException();
        }
    }

    public void generateLevel() {
        generateElementAt(2, 3, "spell:InfernoSpell");
        generateElementAt(4, 9, "exit");
        generateElementAt(14, 5, "death");
        generateElementAt(13, 13, "exit");
    }

    public void generateElementAt(int row, int col, String newCode) {
        boolean isNewCodeValid = newCode.contains("spell:") ||
                newCode.equals("exit") || newCode.equals("death");
        boolean isPositionValid = gameField.isMovingSafeAt(row, col);
        if(isNewCodeValid && isPositionValid) {
            gameField.setNewFieldElementCode(row, col, newCode);
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
        else if(currentCode.equals("death")) {
            levelStatus = LevelStatus.FAILED;
            gameField.setNewFieldElementCode(heroRow, heroCol, "hero:death");
        }
        else {
            //TODO има ли нужда
        }
    }
}
