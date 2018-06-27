package game.spells;

import game.field.CustomGameField;
import game.hero.Hero;
import game.exceptions.InvalidSectorException;

public class LuckySpell implements Spell {

    private CustomGameField gameField;
    private Hero hero;

    public LuckySpell(CustomGameField gameField, Hero hero) {
        this.gameField = gameField;
        this.hero = hero;
    }

    /**
     * Applying the spell effect on the field cells
     */
    @Override
    public void activateSpecialEffectOnField() {
        try {
            gameField.revealSectorOfFieldElements(3, 8, 13, 5 );
        } catch (InvalidSectorException e) {
            System.out.println(e);
        }

    }

    /**
     * Applying the spell effect on hero's health
     */
    @Override
    public void activateSpecialEffectOnHero() {
        hero.increaseHealthWith(50);
    }
}
