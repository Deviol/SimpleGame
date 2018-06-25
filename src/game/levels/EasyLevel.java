package game.levels;

import game.hero.Hero;
import game.enums.HeroDirection;
import game.exceptions.*;

public class EasyLevel extends DefaultLevel {

    private HeroDirection forbiddenDirection;

    public EasyLevel(Hero hero, HeroDirection forbiddenDirection) {
        super(hero);
        this.forbiddenDirection = forbiddenDirection;
    }

    public void movingLeft() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        if(forbiddenDirection == HeroDirection.LEFT) {
            throw new ForbiddenDirectionException(HeroDirection.LEFT);
        }

        super.movingLeft();
    }

    public void movingRight() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        if(forbiddenDirection == HeroDirection.RIGHT) {
            throw new ForbiddenDirectionException(HeroDirection.RIGHT);
        }

        super.movingRight();
    }

    public void movingUp() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        if(forbiddenDirection == HeroDirection.UP) {
            throw new ForbiddenDirectionException(HeroDirection.UP);
        }

        super.movingUp();
    }

    public void movingDown() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        if(forbiddenDirection == HeroDirection.DOWN) {
            throw new ForbiddenDirectionException(HeroDirection.DOWN);
        }

        super.movingDown();
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
