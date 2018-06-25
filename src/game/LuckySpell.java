package game;

import game.exceptions.InvalidSectorException;

public class LuckySpell implements Spell{

    private CustomGameField gameField;
    private Hero hero;

    public LuckySpell(CustomGameField gameField, Hero hero) {
        this.gameField = gameField;
        this.hero = hero;
    }

    @Override
    public void activateSpecialEffectOnField() {
        try {
            gameField.revealSectorOfFieldElements(3, 8, 13, 5 );
        } catch (InvalidSectorException e) {
            System.out.println(e);
        }

    }

    @Override
    public void activateSpecialEffectOnHero() {
        hero.increaseHealthWith(50);
    }
}
