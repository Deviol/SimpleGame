package tests;

import game.*;
import game.exceptions.ForbiddenDirectionException;
import game.exceptions.HeroStepOutOfGameFieldBoundsException;
import org.junit.Before;
import org.junit.Test;

public class EasyLevelTests {

    private EasyLevel easyLevel;

    @Before
    public void initializeEasyLevel() {
        int maxHealth = 50;
        HeroDirection forbiddenDirection = HeroDirection.UP;
        Hero hero = new Hero(maxHealth);
        easyLevel = new EasyLevel(hero, forbiddenDirection);
    }

    @Test(expected = ForbiddenDirectionException.class)
    public void testMovingUpShouldThrowException() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        easyLevel.movingUp();
    }

}
