package game.exceptions;

import game.enums.HeroDirection;
import game.hero.HeroPosition;

public class HeroStepOutOfGameFieldBoundsException extends Exception{

    private HeroPosition heroPosition;
    private HeroDirection direction;

    public HeroStepOutOfGameFieldBoundsException(HeroPosition heroPosition, HeroDirection direction) {
        this.heroPosition = heroPosition;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return findReasonForBadDirection();
    }

    private String findReasonForBadDirection() {
        String message;
        int badIndex;
        switch (direction) {
            case LEFT: {
                badIndex = heroPosition.getColumnIndex() - 1;
                message = "Failed moving left!\nIndex \"" + badIndex + "\" is not a valid column index!";
                return message;
            }
            case RIGHT: {
                badIndex = heroPosition.getColumnIndex() + 1;
                message = "Failed moving right!\nIndex \"" + badIndex + "\" is not a valid column index!";
                return message;
            }
            case UP: {
                badIndex = heroPosition.getRowIndex() - 1;
                message = "Failed moving up!\nIndex \"" + badIndex + "\" is not a valid row index!";
                return message;
            }
            default: {
                badIndex = heroPosition.getRowIndex() + 1;
                message = "Failed moving down!\nIndex \"" + badIndex + "\" is not a valid row index!";
                return message;
            }
        }
    }

}
