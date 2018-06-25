package game.exceptions;

import game.HeroPosition;

public class NoSpellFoundException extends Exception{

    private HeroPosition heroPosition;

    public NoSpellFoundException(HeroPosition heroPosition) {
        this.heroPosition = heroPosition;
    }

    @Override
    public String toString() {
        String heroRowMessage = String.valueOf(heroPosition.getRowIndex());
        String heroColMessage = String.valueOf(heroPosition.getColumnIndex());
        String message = "There is no spell at position ("
            + heroRowMessage + ", " + heroColMessage + ")!";
        return message;
    }
}
