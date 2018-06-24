package tests;

import game.HardLevel;
import game.Hero;
import game.HeroDirection;
import org.junit.Before;
import org.junit.Test;

public class HardLevelTests {

    private HardLevel hardLevel;

    @Before
    public void initializeHardLevel() {
        int randomMaxHealth = 10;
        Hero hero = new Hero(randomMaxHealth);
        HeroDirection firstForbiddenDirection = HeroDirection.RIGHT;
        HeroDirection secondForbiddenDirection = HeroDirection.DOWN;
        int healthCostForPlaying = 3;
        int numberOfAllowedSteps = 3;
        hardLevel = new HardLevel(hero, firstForbiddenDirection,
            secondForbiddenDirection, numberOfAllowedSteps, healthCostForPlaying);
    }

    
}
