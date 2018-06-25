package game;

import game.exceptions.*;

public class HardLevel extends DefaultLevel{

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
            throw new ForbiddenDirectionException(HeroDirection.LEFT);
        }
        updateAdditionalHealthForEachStepIfNecessary();
        makingMoveBasedOnAllowedSteps(HeroDirection.LEFT);
    }

    public void movingRight() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        boolean isForbiddenDirection = isForbiddenDirection(HeroDirection.RIGHT);
        if(isForbiddenDirection) {
            throw new ForbiddenDirectionException(HeroDirection.RIGHT);
        }
        updateAdditionalHealthForEachStepIfNecessary();
        makingMoveBasedOnAllowedSteps(HeroDirection.RIGHT);
    }

    public void movingUp() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        boolean isForbiddenDirection = isForbiddenDirection(HeroDirection.UP);
        if(isForbiddenDirection) {
            throw new ForbiddenDirectionException(HeroDirection.UP);
        }
        updateAdditionalHealthForEachStepIfNecessary();
        makingMoveBasedOnAllowedSteps(HeroDirection.UP);
    }

    public void movingDown() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        boolean isForbiddenDirection = isForbiddenDirection(HeroDirection.DOWN);
        if(isForbiddenDirection) {
            throw new ForbiddenDirectionException(HeroDirection.DOWN);
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
        int heroHealth = super.getHeroHealth();
        int heroMaxHealth = super.getHeroMaxHealth();

        boolean isHeroOnLessThanHalfHealth = heroHealth < heroMaxHealth / 2;
        boolean isHeroOnLessThanQuarterHealth = heroHealth < heroMaxHealth / 4;

        if(isHeroOnLessThanQuarterHealth) {
            additionalHealthForEachStep = 2;
        }

        if(isHeroOnLessThanHalfHealth) {
            additionalHealthForEachStep = 1;
        }

    }

    public void generateLevel() throws FailedGeneratingLevelException {
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
            System.out.println(e);
            throw new FailedGeneratingLevelException();
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
            super.increaseHeroHealthBy(additionalHealthForEachStep);
        }
        else {
            super.setLevelStatus(LevelStatus.FAILED);
        }
    }

    public void showGameFieldToUser() {
        super.showGameFieldToUser();
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
