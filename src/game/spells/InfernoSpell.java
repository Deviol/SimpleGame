package game.spells;

import game.field.CustomGameField;
import game.hero.Hero;
import game.exceptions.InvalidSectorException;

public class InfernoSpell implements Spell {

    private CustomGameField gameField;
    private Hero hero;

    public InfernoSpell(CustomGameField gameField, Hero hero) {
        this.gameField = gameField;
        this.hero = hero;
    }

    @Override
    public void activateSpecialEffectOnField() {
        try {
            gameField.revealSectorOfFieldElements(4, 7, 1, 5 );
            boolean isHeroOnMoreThan75PercentsHealth = hero.getHealth() > hero.getMaxHealth() * 0.75;
            if(isHeroOnMoreThan75PercentsHealth) {
                gameField.revealSectorOfFieldElements(5, 1, 3, 12 );
            }
        } catch(InvalidSectorException e) {
            System.out.println(e);
        }

    }

    @Override
    public void activateSpecialEffectOnHero() {
        hero.decreaseHealthWith(10);
        boolean isHeroOnLessThanHalfHealth = hero.getHealth() < hero.getMaxHealth() / 2;
        if(isHeroOnLessThanHalfHealth) {
            hero.increaseHealthWith(25);
        }
    }
}
