package game.tests;

import game.hero.Hero;
import game.enums.HeroDirection;
import game.hero.HeroPosition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroTests {

    private Hero hero;
    private HeroPosition heroPosition;
    private int randomMaxHealth;

    @Before
    public void initializeHero() {
        randomMaxHealth = 50;
        hero = new Hero(randomMaxHealth);
        heroPosition = new HeroPosition();
    }

    @Test
    public void testIncreaseHealthWithCaseSumOverTheLimitShouldAssignMaxHealth() {
        hero.increaseHealthWith(4);

        Assert.assertTrue(hero.getHealth() == randomMaxHealth);
    }

    @Test
    public void testDecreaseHealthWithCaseSumLessThanZeroShouldAssignZero() {
        hero.decreaseHealthWith(randomMaxHealth + 1);
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
