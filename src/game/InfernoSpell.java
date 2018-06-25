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
        gameField.revealSectorOfFieldElements(4, 7, 1, 5 );
        gameField.setNewFieldElementCode(13, 1, "exit");
        boolean isHeroOnMoreThan75PercentsHealth = hero.getHealth() > hero.getMaxHealth() * 0.75;
        if(isHeroOnMoreThan75PercentsHealth) {
            gameField.setNewFieldElementCode(12, 11, "death");
            gameField.setNewFieldElementCode(6, 8, "death");
        }
        else {
            gameField.setNewFieldElementCode(12, 11, "spell:InfernoSpell");
            gameField.setNewFieldElementCode(6, 8, "spell:InfernoSpell");
        }
    }

    @Override
    public void activateSpecialEffectOnHero() {
        hero.decreaseHealthWith(10);
        boolean isHeroOnLessThanHalfHealth = hero.getHealth() < hero.getMaxHealth() / 2;
        if(isHeroOnLessThanHalfHealth) {
            hero.increaseHealthWith(25);
            gameField.setNewFieldElementCode(8, 8, "death");
        }
    }
}
