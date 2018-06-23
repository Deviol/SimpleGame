package tests;

import game.Hero;
import game.HeroDirection;
import game.HeroPosition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroTests {

    private Hero hero;
    private HeroPosition heroPosition;

    @Before
    public void initializeHero() {
        int randomMaxHealth = 50;
        hero = new Hero(randomMaxHealth);
        heroPosition = new HeroPosition();
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

    @Test
    public void testUpdateHeroPositionCaseLEFTShouldDecrementColumnIndex() {
        int expectedColumnIndex = HeroPosition.DEFAULT_INDEX_OF_HERO_POSITION - 1;
        heroPosition.updateHeroPosition(HeroDirection.LEFT);
        Assert.assertTrue(heroPosition.getColumnIndex() == expectedColumnIndex);
    }

    @Test
    public void testUpdateHeroPositionDefaultCaseShouldIncrementColumnIndex() {
        int expectedColumnIndex = HeroPosition.DEFAULT_INDEX_OF_HERO_POSITION + 1;
        heroPosition.updateHeroPosition(HeroDirection.RIGHT);
        Assert.assertTrue(heroPosition.getColumnIndex() == expectedColumnIndex);
    }
}
