package game;

public class LuckySpell implements Spell{

    private GameField gameField;
    private Hero hero;

    public LuckySpell(GameField gameField, Hero hero) {
        this.gameField = gameField;
        this.hero = hero;
    }

    @Override
    public void activateSpecialEffectOnField() {
        for (int i = 3; i < 8; i++) {
            gameField.setNewFieldElementCode(3, i, "exit");
        }
    }

    @Override
    public void activateSpecialEffectOnHero() {
        hero.increaseHealthWith(50);
    }
}
