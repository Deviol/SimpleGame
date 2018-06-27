package game.hero;

public class Hero {

    private int health;
    private int maxHealth;

    public Hero(int maxHealth) {
        this.maxHealth = maxHealth;
        health = maxHealth;
    }

    /**
     * Increases hero's current health
     * @param health additional health
     */
    public void increaseHealthWith(int health) {
        if(health < 0) {
            health = 0;
        }
        if(this.health + health < maxHealth) {
            this.health += health;
        }
        else {
            this.health = maxHealth;
        }
    }

    /**
     * Decreases hero's current health
     * @param health health to take
     */
    public void decreaseHealthWith(int health) {
        if(health < 0) {
            health = 0;
        }
        if(this.health - health >= 0) {
            this.health -= health;
        }
        else {
            this.health = 0;
        }
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
