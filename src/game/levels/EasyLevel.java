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

    public void movingTo(HeroDirection direction) throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        if(forbiddenDirection == direction) {
            throw new ForbiddenDirectionException(direction);
        }

        super.movingTo(direction);
    }

    public void activateSpell() throws NoSpellFoundException {
        super.activateSpell();
    }

    public void showGameFieldToUser() {
        super.showGameFieldToUser();
    }

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
