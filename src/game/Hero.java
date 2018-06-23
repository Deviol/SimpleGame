package game;

public class Hero {
    private int health;
    private int maxHealth;

    public Hero(int maxHealth) {
        this.maxHealth = maxHealth;
        health = maxHealth;
    }

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
}
