package game;

import javafx.util.Pair;

public class DeathSpell implements Spell{

    private GameField gameField;
    private Hero hero;

    public DeathSpell(GameField gameField, Hero hero) {
        this.gameField = gameField;
        this.hero = hero;
    }

    @Override
    public void activateSpecialEffectOnField() {
        Pair<Integer, Integer> heroPosition = findHeroCoordinates();
        int heroRow = heroPosition.getKey();
        int heroCol = heroPosition.getValue();
        surroundHeroWithDeathOnAllDirectionButLEFT(heroRow, heroCol);
        gameField.revealSectorOfFieldElements(0, 1, 10, 13);
    }

    @Override
    public void activateSpecialEffectOnHero() {
        hero.decreaseHealthWith(15);
    }

    private Pair<Integer, Integer> findHeroCoordinates() {
        for (int i = 0; i < GameField.MAX_LENGTH_OF_FIELD; i++) {
            for (int j = 0; j < GameField.MAX_LENGTH_OF_FIELD; j++) {
                if(gameField.getFieldElementCodeAt(i, j).equals("hero")) {
                    return new Pair<>(i, j);
                }
            }
        }
        int defaultRow = HeroPosition.DEFAULT_INDEX_OF_HERO_POSITION;
        int defaultCol = HeroPosition.DEFAULT_INDEX_OF_HERO_POSITION;
        return new Pair<>(defaultRow, defaultCol);
    }
    private void surroundHeroWithDeathOnAllDirectionButLEFT(int heroRow, int heroCol) {
        int newRow;
        String newCode = "death";

        //death on UP
        newRow = heroRow - 1;
        gameField.setNewFieldElementCode(newRow, heroRow, newCode);

        //death on DOWN
        newRow = heroRow + 1;
        gameField.setNewFieldElementCode(newRow, heroRow, newCode);

        //death on RIGHT
        int newCol = heroCol + 1;
        gameField.setNewFieldElementCode(heroRow, newCol, newCode);
    }
}
