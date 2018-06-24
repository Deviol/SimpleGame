package game;

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

    public void movingLeft() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        boolean isMovingSafe = isMovingSafeAtDirection(HeroDirection.LEFT);
        if(isMovingSafe) {
            makeOldStepUsed();
            moveHeroToTheNewPosition(HeroDirection.LEFT);
        }
        else {
            int badIndex = heroPosition.getColumnIndex() - 1;
            throw new HeroStepOutOfGameFieldBoundsException(badIndex, IndexType.COLUMN);
        }
    }

    public void movingRight() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        boolean isMovingSafe = isMovingSafeAtDirection(HeroDirection.RIGHT);
        if(isMovingSafe) {
            makeOldStepUsed();
            moveHeroToTheNewPosition(HeroDirection.RIGHT);
        }
        else {
            int badIndex = heroPosition.getColumnIndex() + 1;
            throw new HeroStepOutOfGameFieldBoundsException(badIndex, IndexType.COLUMN);
        }
    }

    public void movingUp() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        boolean isMovingSafe = isMovingSafeAtDirection(HeroDirection.UP);
        if(isMovingSafe) {
            makeOldStepUsed();
            moveHeroToTheNewPosition(HeroDirection.UP);
        }
        else {
            int badIndex = heroPosition.getRowIndex() - 1;
            throw new HeroStepOutOfGameFieldBoundsException(badIndex, IndexType.ROW);
        }
    }

    public void movingDown() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        boolean isMovingSafe = isMovingSafeAtDirection(HeroDirection.DOWN);
        if(isMovingSafe) {
            makeOldStepUsed();
            moveHeroToTheNewPosition(HeroDirection.DOWN);
        }
        else {
            int badIndex = heroPosition.getRowIndex() + 1;
            throw new HeroStepOutOfGameFieldBoundsException(badIndex, IndexType.ROW);
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
            throw new NoSpellFoundException(heroPosition);
        }
    }

    public void generateLevel() {
        try {
            generateElementAt(2, 3, "spell:InfernoSpell");
            generateElementAt(4, 9, "exit");
            generateElementAt(14, 5, "death");
            generateElementAt(13, 13, "exit");
        } catch (FailedGeneratingElementException e) {
            //TODO fix
            System.out.println("invalid operation");
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

    public LevelStatus getLevelStatus() {
        return levelStatus;
    }

    public void setLevelStatus(LevelStatus levelStatus) {
        this.levelStatus = levelStatus;
    }

    public int getHeroHealth() {
        return hero.getHealth();
    }
    public int getHeroMaxHealth() {
        return hero.getMaxHealth();
    }
    public void increaseHeroHealthBy(int health) {
        hero.increaseHealthWith(health);
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
