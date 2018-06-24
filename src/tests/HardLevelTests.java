package tests;

import game.*;
import org.junit.Before;
import org.junit.Test;

public class HardLevelTests {

    private HardLevel hardLevel;
    private Hero hero;

    @Before
    public void initializeHardLevel() {
        int randomMaxHealth = 10;
        hero = new Hero(randomMaxHealth);
        HeroDirection firstForbiddenDirection = HeroDirection.RIGHT;
        HeroDirection secondForbiddenDirection = HeroDirection.DOWN;
        int healthCostForPlaying = 3;
        int numberOfAllowedSteps = 3;
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
}
