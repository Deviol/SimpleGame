package game.tests;

import game.*;
import game.exceptions.ForbiddenDirectionException;
import game.exceptions.HeroStepOutOfGameFieldBoundsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HardLevelTests {

    private HardLevel hardLevel;
    private int numberOfAllowedSteps;
    private Hero hero;

    @Before
    public void initializeHardLevel() {
        int randomMaxHealth = 10;
        hero = new Hero(randomMaxHealth);
        HeroDirection firstForbiddenDirection = HeroDirection.RIGHT;
        HeroDirection secondForbiddenDirection = HeroDirection.DOWN;
        int healthCostForPlaying = 6;
        numberOfAllowedSteps = 3;
        hardLevel = new HardLevel(hero, firstForbiddenDirection,
            secondForbiddenDirection, numberOfAllowedSteps, healthCostForPlaying);
    }

    @Test(expected = ForbiddenDirectionException.class)
    public void testMovingRightShouldThrowException()
        throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        hardLevel.movingRight();
    }
    @Test(expected = ForbiddenDirectionException.class)
    public void testMovingDownShouldThrowException()
        throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        hardLevel.movingDown();
    }

    @Test(expected = Test.None.class)
    public void testMovingLeftShouldMakeLegalStep()
        throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        hardLevel.movingLeft();
    }

    @Test(expected = Test.None.class)
    public void testMovingUpShouldMakeLegalStep()
        throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        hardLevel.movingUp();
    }
    @Test
    public void testMovingUpShouldIncreaseHeroCurrentHealthByOne()
        throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException{
        // from method initializeHardLevel hero's health is reduced to 4
        int healthAfterMoving = 5;
        hardLevel.movingUp();
        Assert.assertTrue(hero.getHealth() == healthAfterMoving);
    }

    @Test
    public void testMovingLeftShouldIncreaseHeroCurrentHealthByOne()
            throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException{
        // from method initializeHardLevel hero's health is reduced to 4
        int healthAfterMoving = 5;
        hardLevel.movingLeft();
        Assert.assertTrue(hero.getHealth() == healthAfterMoving);
    }

    @Test
    public void testMovingLeftShouldChangeLevelStatusToFAILED()
            throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException{
        for (int i = 0; i <= numberOfAllowedSteps; i++) {
            hardLevel.movingLeft();
        }
        LevelStatus hardLevelStatus = hardLevel.getLevelStatus();

        Assert.assertTrue(hardLevelStatus == LevelStatus.FAILED);
    }

    @Test
    public void testMovingUpShouldChangeLevelStatusToFAILED()
            throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException{
        for (int i = 0; i <= numberOfAllowedSteps; i++) {
            hardLevel.movingUp();
        }
        LevelStatus hardLevelStatus = hardLevel.getLevelStatus();

        Assert.assertTrue(hardLevelStatus == LevelStatus.FAILED);
    }
}
