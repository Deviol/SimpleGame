package tests;

import game.*;
import org.junit.Before;
import org.junit.Test;

public class DefaultLevelTests {

    private DefaultLevel defaultLevel;
    private Hero hero;

    @Before
    public void initializeDefaultLevel() {
        int randomMaxHealth = 50;
        hero = new Hero(randomMaxHealth);
        defaultLevel = new DefaultLevel(hero);
    }

    @Test(expected = HeroStepOutOfGameFieldBoundsException.class)
    public void testMovingLeftShouldThrowException() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        int numberOfSteps = 8;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingLeft();
        }
    }
    @Test(expected = Test.None.class)
    public void testMovingLeftShouldMakeLegalSteps() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        int numberOfSteps = 7;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingLeft();
        }
    }

    @Test(expected = HeroStepOutOfGameFieldBoundsException.class)
    public void testMovingRightShouldThrowException() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        int numberOfSteps = 8;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingRight();
        }
    }

    @Test(expected = Test.None.class)
    public void testMovingRightShouldMakeLegalSteps() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        int numberOfSteps = 7;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingRight();
        }
    }

    @Test(expected = HeroStepOutOfGameFieldBoundsException.class)
    public void testMovingUpShouldThrowException() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        int numberOfSteps = 8;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingUp();
        }
    }

    @Test(expected = Test.None.class)
    public void testMovingUpShouldMakeLegalSteps() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        int numberOfSteps = 7;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingUp();
        }
    }

    @Test(expected = HeroStepOutOfGameFieldBoundsException.class)
    public void testMovingDownShouldThrowException() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        int numberOfSteps = 8;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingDown();
        }
    }

    @Test(expected = Test.None.class)
    public void testMovingDownShouldMakeLegalSteps() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        int numberOfSteps = 7;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingDown();
        }
    }

    @Test(expected = NoSpellFoundException.class)
    public void testActivateSpellShouldThrowException() {

    }
}
