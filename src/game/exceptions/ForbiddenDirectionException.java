package game.exceptions;

import game.enums.HeroDirection;

public class ForbiddenDirectionException extends Exception{

    private HeroDirection heroDirection;

    public ForbiddenDirectionException(HeroDirection heroDirection) {
        this.heroDirection = heroDirection;
    }

    /**
     * Converting the exception to type string
     * @return Returns the message for the exception
     */
    @Override
    public String toString() {
        String direction = String.valueOf(heroDirection).toLowerCase();
        String message = "Moving " + direction + " is not allowed!";

        return message;
    }
}
