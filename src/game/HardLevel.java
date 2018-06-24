package game;

public class HardLevel extends DefaultLevel{

    private Hero hero;
    private HeroDirection firstForbiddenDirection;
    private HeroDirection secondForbiddenDirection;
    int numberOfAllowedSteps;
    int healthCostForPlaying;

    public HardLevel(Hero hero, HeroDirection firstForbiddenDirection,
                 HeroDirection secondForbiddenDirection,
                 int numberOfAllowedSteps, int healthCostForPlaying) {
        super(hero);
        this.firstForbiddenDirection = firstForbiddenDirection;
        this.secondForbiddenDirection = secondForbiddenDirection;
        this.numberOfAllowedSteps = numberOfAllowedSteps;
        this.healthCostForPlaying = healthCostForPlaying;
    }
    
}
