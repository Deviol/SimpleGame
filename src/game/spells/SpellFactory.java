package game.spells;

import game.field.CustomGameField;
import game.hero.Hero;

/**
 * Used for creating appropriate Spell objects
 */
public class SpellFactory {

    /**
     *
     * @param type Name of the Spell class
     * @param gameField custom game field as an argument for the spell constructor
     * @param hero Hero for the spell constructor
     * @return Returns the spell
     */
    public static Spell getAppropriateSpell(String type, CustomGameField gameField, Hero hero) {
        switch (type) {
            case "InfernoSpell": {
                return new InfernoSpell(gameField, hero);
            }
            case "LuckySpell": {
                return new LuckySpell(gameField, hero);
            }
            case "DeathSpell": {
                return new DeathSpell(gameField, hero);
            }
            default: {
                return new InfernoSpell(gameField, hero);
            }
        }
    }
}
