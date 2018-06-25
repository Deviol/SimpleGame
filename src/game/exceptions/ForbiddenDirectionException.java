package game.exceptions;

import game.HeroDirection;

public class ForbiddenDirectionException extends Exception{

    private HeroDirection heroDirection;

    public ForbiddenDirectionException(HeroDirection heroDirection) {
        this.heroDirection = heroDirection;
    }

    @Override
    public String toString() {
        String direction = String.valueOf(heroDirection).toLowerCase();
        String message = "Moving " + direction + " is not allowed!";
        return message;
    }
}
