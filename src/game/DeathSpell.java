package game;

public class DeathSpell implements Spell{

    private GameField gameField;
    private Hero hero;

    public DeathSpell(GameField gameField, Hero hero) {
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
