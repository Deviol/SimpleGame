package game.tests;

import game.enums.HeroDirection;
import game.enums.LevelStatus;
import game.exceptions.ForbiddenDirectionException;
import game.exceptions.HeroStepOutOfGameFieldBoundsException;
import game.hero.Hero;
import game.levels.HardLevel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HardLevelTests {

    private HardLevel hardLevel;
    private int numberOfAllowedSteps;
    private Hero hero;
    private HeroDirection firstForbiddenDirection;
    private HeroDirection secondForbiddenDirection;
    private HeroDirection legalDirection;

    @Before
    public void initializeHardLevel() {
        int randomMaxHealth = 10;
        hero = new Hero(randomMaxHealth);
        firstForbiddenDirection = HeroDirection.RIGHT;
        secondForbiddenDirection = HeroDirection.DOWN;
        int healthCostForPlaying = 6;
        numberOfAllowedSteps = 3;
        hardLevel = new HardLevel(hero, firstForbiddenDirection,
            secondForbiddenDirection, numberOfAllowedSteps, healthCostForPlaying);
        legalDirection = HeroDirection.LEFT;
    }

    @Test(expected = ForbiddenDirectionException.class)
    public void testMovingToShouldThrowException()
        throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        hardLevel.movingTo(firstForbiddenDirection);
    }

    @Test(expected = Test.None.class)
    public void testMovingTotShouldMakeLegalStep()
        throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        hardLevel.movingTo(legalDirection);
    }

    @Test
    public void testMovingToShouldIncreaseHeroCurrentHealthByOne()
        throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException{
        // from method initializeHardLevel hero's health is reduced to 4
        int healthAfterMoving = 5;
        hardLevel.movingTo(legalDirection);
        Assert.assertTrue(hero.getHealth() == healthAfterMoving);
    }


    @Test
    public void testMovingToShouldChangeLevelStatusToFAILED()
            throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException{
        for (int i = 0; i <= numberOfAllowedSteps; i++) {
            hardLevel.movingTo(legalDirection);
        }
        LevelStatus hardLevelStatus = hardLevel.getLevelStatus();

        Assert.assertTrue(hardLevelStatus == LevelStatus.FAILED);
    }
}
