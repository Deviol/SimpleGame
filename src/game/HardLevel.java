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
        hero.decreaseHealthWith(healthCostForPlaying);
    }

    public void movingLeft() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        boolean isForbiddenDirection = isForbiddenDirection(HeroDirection.LEFT);
        if(isForbiddenDirection) {
            throw new ForbiddenDirectionException();
        }
        updateAdditionalHealthForEachStepIfNecessary();
        makingMoveBasedOnAllowedSteps(HeroDirection.LEFT);
    }

    public void movingRight() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        boolean isForbiddenDirection = isForbiddenDirection(HeroDirection.RIGHT);
        if(isForbiddenDirection) {
            throw new ForbiddenDirectionException();
        }
        updateAdditionalHealthForEachStepIfNecessary();
        makingMoveBasedOnAllowedSteps(HeroDirection.RIGHT);
    }

    public void movingUp() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        boolean isForbiddenDirection = isForbiddenDirection(HeroDirection.UP);
        if(isForbiddenDirection) {
            throw new ForbiddenDirectionException();
        }
        updateAdditionalHealthForEachStepIfNecessary();
        makingMoveBasedOnAllowedSteps(HeroDirection.UP);
    }

    public void movingDown() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        boolean isForbiddenDirection = isForbiddenDirection(HeroDirection.DOWN);
        if(isForbiddenDirection) {
            throw new ForbiddenDirectionException();
        }
        updateAdditionalHealthForEachStepIfNecessary();
        makingMoveBasedOnAllowedSteps(HeroDirection.DOWN);
    }

    public void activateSpell() throws NoSpellFoundException {
        super.activateSpell();
    }

    private boolean isForbiddenDirection(HeroDirection direction) {
        return direction == firstForbiddenDirection ||
            direction == secondForbiddenDirection;
    }

    private void updateAdditionalHealthForEachStepIfNecessary() {
        boolean isHeroLessThanHalfHealth =
                hero.getHealth() < hero.getMaxHealth() / 2;
        boolean isHeroLessThanQuarterHealth =
                hero.getHealth() < hero.getHealth() / 4;

        if(isHeroLessThanHalfHealth) {
            additionalHealthForEachStep = 1;
        }
        if(isHeroLessThanQuarterHealth) {
            additionalHealthForEachStep = 2;
        }
    }

    public void generateLevel() {
        super.generateLevel();
        try {
            super.generateElementAt(5, 4, "death");
            super.generateElementAt(3, 12, "death");
            super.generateElementAt(7, 10, "death");
            super.generateElementAt(5, 11, "spell:InfernoSpell");
            for (int i = 4; i < 12; i++) {
                super.generateElementAt(i, 5, "spell:InfernoSpell");
            }
        }
        catch(FailedGeneratingElementException e) {
            //TODO throw another exception
        }
    }

    public LevelStatus getLevelStatus() {
        return super.getLevelStatus();
    }
    private void makingMoveBasedOnAllowedSteps(HeroDirection direction)
            throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        if(numberOfAllowedSteps > 0) {
            movingTo(direction);
            numberOfAllowedSteps--;
            hero.increaseHealthWith(additionalHealthForEachStep);
        }
        else {
            super.setLevelStatus(LevelStatus.FAILED);
        }
    }

    private void movingTo(HeroDirection direction)
            throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        switch (direction) {
            case LEFT: {
                super.movingLeft();
                break;
            }
            case RIGHT: {
                super.movingRight();
                break;
            }
            case UP: {
                super.movingUp();
                break;
            }
            default: {
                super.movingDown();
                break;
            }
        }
    }
}
