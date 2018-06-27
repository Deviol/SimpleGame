package game.spells;

import game.field.CustomGameField;
import game.hero.Hero;

public class DeathSpell implements Spell {

    private CustomGameField gameField;
    private Hero hero;

    public DeathSpell(CustomGameField gameField, Hero hero) {
        this.gameField = gameField;
        this.hero = hero;
    }

    /**
     * Applying the spell effect on the field cells
     */
    @Override
    public void activateSpecialEffectOnField() {

    }

    /**
     * Applying the spell effect on hero's health
     */
    @Override
    public void activateSpecialEffectOnHero() {

    }
}
