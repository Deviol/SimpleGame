package game;

public class HardLevel extends DefaultLevel{

    private Hero hero;
    private HeroDirection firstForbiddenDirection;
    private HeroDirection secondForbiddenDirection;
    private int numberOfAllowedSteps;
    private int healthCostForPlaying;
    private int additionalHealthForEachStep;
    public HardLevel(Hero hero, HeroDirection firstForbiddenDirection,
                 HeroDirection secondForbiddenDirection,
                 int numberOfAllowedSteps, int healthCostForPlaying) {
        super(hero);
        this.firstForbiddenDirection = firstForbiddenDirection;
        this.secondForbiddenDirection = secondForbiddenDirection;
        this.numberOfAllowedSteps = numberOfAllowedSteps;
        this.healthCostForPlaying = healthCostForPlaying;
        additionalHealthForEachStep = 0;
    }

    public void movingLeft() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        boolean isForbiddenDirection = isForbiddenDirection(HeroDirection.LEFT);
        if(isForbiddenDirection) {
            throw new ForbiddenDirectionException();
        }

        super.movingLeft();
    }

    public void movingRight() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        boolean isForbiddenDirection = isForbiddenDirection(HeroDirection.RIGHT);
        if(isForbiddenDirection ) {
            throw new ForbiddenDirectionException();
        }
        super.movingRight();
    }

    public void movingUp() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        boolean isForbiddenDirection = isForbiddenDirection(HeroDirection.UP);
        if(isForbiddenDirection ) {
            throw new ForbiddenDirectionException();
        }
        super.movingUp();
    }

    public void movingDown() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        boolean isForbiddenDirection = isForbiddenDirection(HeroDirection.DOWN);
        if(isForbiddenDirection ) {
            throw new ForbiddenDirectionException();
        }
        super.movingDown();
    }

    public void activateSpell() throws NoSpellFoundException {
        super.activateSpell();
    }

    private boolean isForbiddenDirection(HeroDirection direction) {
        return direction == firstForbiddenDirection ||
            direction == secondForbiddenDirection;
    }
}
