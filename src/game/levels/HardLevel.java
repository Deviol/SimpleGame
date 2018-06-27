package game.levels;

import game.defaultlevel.DefaultLevel;
import game.hero.Hero;
import game.enums.HeroDirection;
import game.enums.LevelStatus;
import game.exceptions.*;

public class HardLevel extends DefaultLevel {

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

    public void movingTo(HeroDirection direction) throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        boolean isForbiddenDirection = isForbiddenDirection(direction);
        if(isForbiddenDirection) {
            throw new ForbiddenDirectionException(direction);
        }
        updateAdditionalHealthForEachStepIfNecessary();
        makingMoveBasedOnAllowedSteps(direction);
    }

    public void activateSpell() throws NoSpellFoundException {
        super.activateSpell();
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

    public void showGameFieldToUser() {
        super.showGameFieldToUser();
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

    private void makingMoveBasedOnAllowedSteps(HeroDirection direction)
            throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        if(numberOfAllowedSteps > 0) {
            moving(direction);
            numberOfAllowedSteps--;
            super.increaseHeroHealthBy(additionalHealthForEachStep);
        }
        else {
            super.setLevelStatus(LevelStatus.FAILED);
        }
    }

    private void moving(HeroDirection direction)
            throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        switch (direction) {
            case LEFT: {
                super.movingTo(HeroDirection.LEFT);
                break;
            }
            case RIGHT: {
                super.movingTo(HeroDirection.RIGHT);
                break;
            }
            case UP: {
                super.movingTo(HeroDirection.UP);
                break;
            }
            default: {
                super.movingTo(HeroDirection.DOWN);
                break;
            }
        }
    }
}
