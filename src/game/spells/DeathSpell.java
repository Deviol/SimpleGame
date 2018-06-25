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

    @Override
    public void activateSpecialEffectOnField() {

    }

    @Override
    public void activateSpecialEffectOnHero() {

    }
}
