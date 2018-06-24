package game;

public class EasyLevel extends DefaultLevel{

    private Hero hero;
    private HeroDirection forbiddenDirection;

    public EasyLevel(Hero hero, HeroDirection forbiddenDirection) {
        super(hero);
        this.forbiddenDirection = forbiddenDirection;
    }

    public void movingLeft() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        if(forbiddenDirection == HeroDirection.LEFT) {
            throw new ForbiddenDirectionException();
        }
        super.movingLeft();
    }

    public void movingRight() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        if(forbiddenDirection == HeroDirection.RIGHT) {
            throw new ForbiddenDirectionException();
        }
        super.movingRight();
    }

    public void movingUp() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        if(forbiddenDirection == HeroDirection.UP) {
            throw new ForbiddenDirectionException();
        }
        super.movingUp();
    }

    public void movingDown() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        if(forbiddenDirection == HeroDirection.DOWN) {
            throw new ForbiddenDirectionException();
        }
        super.movingDown();
    }

    public void activateSpell() throws NoSpellFoundException {
        super.activateSpell();
    }

    public void generateLevel() {
        super.generateLevel();
        try {
            super.generateElementAt(2, 1, "spell:LuckySpell");
            super.generateElementAt(4, 8, "death");
            super.generateElementAt(5, 5, "spell:LuckySpell");
            super.generateElementAt(7, 11, "exit");
        } catch(FailedGeneratingElementException e) {
            //TODO throw another exception
        }
    }
}