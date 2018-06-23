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
