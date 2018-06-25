package game;

import game.enums.HeroDirection;
import game.hero.Hero;
import game.levels.DefaultLevel;
import game.levels.EasyLevel;
import game.levels.HardLevel;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

/**
Not refactor, not test, and not correct at this stage of development
Should not be used!
 */
public class Game {

    private Hero hero;
    private Queue<String> levelNames;
    private DefaultLevel currentLevel = null;

    public Game() {
        hero = new Hero(100);
        levelNames = new ArrayDeque<>();

    }

    private void loadNextLevel() {
        if(levelNames.isEmpty()) {
            String currentLevelName = levelNames.poll();
            pickRightLevel(currentLevelName);
        }
    }

    private void pickRightLevel(String currentLevelName) {
        switch (currentLevelName) {
            case "EasyLevel": {
                loadEasyLevel();
            }
            case "HardLevel": {
                loadHardLevel();
            }
            default: {
                currentLevel = new DefaultLevel(hero);
            }
        }
    }
    private void loadEasyLevel() {
        HeroDirection forbiddenDirection = generateRandomDirection();
        currentLevel = new EasyLevel(hero, forbiddenDirection);
    }
    private void loadHardLevel() {
        int numberOfAllowedSteps = 100;
        int healthCostForPlaying = 10;
        HeroDirection firstForbiddenDirection = generateRandomDirection();
        HeroDirection secondForbiddenDirection = generateRandomDirection();
        int gameCoefficient = 3;
        while(firstForbiddenDirection.equals(secondForbiddenDirection)) {
            secondForbiddenDirection = generateRandomDirection();
        }
        boolean isHeroOnLessThanHalfHealth =
            hero.getHealth() < hero.getMaxHealth() / 2;
        boolean isHeroOnLessThanQuarterHealth =
            hero.getHealth() < hero.getMaxHealth() / 4;
        boolean isHeroOnMoreThan75PercentsHealth =
            hero.getHealth() > hero.getMaxHealth() * 0.75;

        if(isHeroOnLessThanQuarterHealth) {
            numberOfAllowedSteps = hero.getHealth() * gameCoefficient;
            healthCostForPlaying = hero.getHealth() / gameCoefficient;
        }
        else if(isHeroOnLessThanHalfHealth) {
            gameCoefficient--;
            numberOfAllowedSteps = hero.getHealth() * gameCoefficient;
            healthCostForPlaying = hero.getHealth() / gameCoefficient;
        }
        else if(isHeroOnMoreThan75PercentsHealth) {
            gameCoefficient += 2;
            numberOfAllowedSteps = hero.getHealth() / gameCoefficient;
            gameCoefficient -= 2;
            healthCostForPlaying = hero.getHealth() / gameCoefficient;
        }
        currentLevel = new HardLevel(hero, firstForbiddenDirection,
            secondForbiddenDirection, numberOfAllowedSteps, healthCostForPlaying);
    }
    private HeroDirection generateRandomDirection() {
        int length = HeroDirection.values().length;
        Random rand = new Random();
        int randomIndex = rand.nextInt(length);
        HeroDirection[] directions = HeroDirection.values();
        return  directions[randomIndex];
    }
}
