package tests;

import game.*;
import org.junit.Before;
import org.junit.Test;

public class EasyLevelTests {

    private EasyLevel easyLevel;
    private Hero hero;

    @Before
    public void initializeEasyLevel() {
        int maxHealth = 50;
        HeroDirection forbiddenDirection = HeroDirection.UP;
        hero = new Hero(maxHealth);
        easyLevel = new EasyLevel(hero, forbiddenDirection);
    }

    @Test(expected = ForbiddenDirectionException.class)
    public void testMovingUpShouldThrowException() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        easyLevel.movingUp();
    }

}
