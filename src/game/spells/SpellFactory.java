package game.spells;

import game.field.CustomGameField;
import game.hero.Hero;

public class SpellFactory {

    public static Spell getAppropriateSpell(String type, CustomGameField gameField, Hero hero) {
        if(type.equals("InfernoSpell")) {
            return new InfernoSpell(gameField, hero);
        }
        else if(type.equals("LuckySpell")) {
            return new LuckySpell(gameField, hero);
        }
        else if(type.equals("DeathSpell")) {
            return new DeathSpell(gameField, hero);
        }
        else {
            return new InfernoSpell(gameField, hero);
        }
    }
}
