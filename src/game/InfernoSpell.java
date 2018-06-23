package game;

public class InfernoSpell implements Spell{

    private GameField gameField;
    private Hero hero;

    public InfernoSpell(GameField gameField, Hero hero) {
        this.gameField = gameField;
        this.hero = hero;
    }

    @Override
    public void activateSpecialEffectOnField() {

    }

    @Override
    public void activateSpecialEffectOnHero(int coolDown) {

    }
}
