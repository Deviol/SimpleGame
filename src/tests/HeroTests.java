package tests;

import game.Hero;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroTests {

    private Hero hero;

    @Before
    public void initializeHero() {
        hero = new Hero(50);
    }

    @Test
    public void testIncreaseHealthWithCaseSumOverTheLimitShouldAssignMaxHealth() {
        hero.increaseHealthWith(4);
        Assert.assertTrue(hero.getHealth() == 50);
    }

    @Test
    public void testDecreaseHealthWithCaseSumLessThanZeroShouldAssignZero() {
        hero.decreaseHealthWith(51);
        Assert.assertTrue(hero.getHealth() == 0);
    }
}
