package game.levels;

import game.defaultlevel.DefaultLevel;
import game.hero.Hero;
import game.enums.HeroDirection;
import game.exceptions.*;

public class EasyLevel extends DefaultLevel {

    private HeroDirection forbiddenDirection;

    public EasyLevel(Hero hero, HeroDirection forbiddenDirection) {
        super(hero);
        this.forbiddenDirection = forbiddenDirection;
    }

    /**
     * Moving hero on the field
     * @param direction Direction where the hero should move
     * @throws HeroStepOutOfGameFieldBoundsException If passing a direction leads to step off the field
     * the current exception is thrown
     * @throws ForbiddenDirectionException It is thrown when the
     * the forbidden directions is passed.
     */
    public void movingTo(HeroDirection direction) throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        if(forbiddenDirection == direction) {
            throw new ForbiddenDirectionException(direction);
        }

        super.movingTo(direction);
    }

    /**
     * Creates a spell and activates its effect
     * @throws NoSpellFoundException Hero stepping on non spell cell and user typing the command for
     * activating the spell will cause the current exception to be thrown
     */
    public void activateSpell() throws NoSpellFoundException {
        super.activateSpell();
    }

    /**
     * Printing the game field to the standard output
     */
    public void showGameFieldToUser() {
        super.showGameFieldToUser();
    }

    /**
     * Putting game defined element on the field
     * @throws FailedGeneratingLevelException When one of the inner generating methods fails
     * the current exception is thrown
     */
    public void generateLevel() throws FailedGeneratingLevelException {
        super.generateLevel();
        try {
            super.generateElementAt(2, 1, "spell:LuckySpell");
            super.generateElementAt(4, 8, "death");
            super.generateElementAt(5, 5, "spell:LuckySpell");
            super.generateElementAt(7, 11, "exit");
        } catch(FailedGeneratingElementException e) {
            System.out.println(e);
            throw new FailedGeneratingLevelException();
        }
    }
}
