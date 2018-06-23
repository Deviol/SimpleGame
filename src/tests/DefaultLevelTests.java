package tests;

import game.DefaultLevel;
import game.ForbiddenDirectionException;
import game.Hero;
import game.HeroStepOutOfGameFieldBoundsException;
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
    public void testMovingLeftShouldMakeLegalStep() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        int numberOfSteps = 7;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingLeft();
        }

    }
}
