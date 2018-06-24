package game;

public class SpellFactory {

    public static Spell getAppropriateSpell(String type, GameField gameField, Hero hero) {
        if(type.equals("InfernoSpell")) {
            return new InfernoSpell(gameField, hero);
        }
        else if(type.equals("LuckySpell")) {
            return new LuckySpell(gameField, hero);
        }
        else if(type.equals("DeathSpell")) {
            return new LuckySpell(gameField, hero);
        }
        else {
            //TODO invalid spell handle it
            return new LuckySpell(gameField, hero);
        }
    }
}
