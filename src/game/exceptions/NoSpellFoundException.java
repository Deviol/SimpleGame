package game.exceptions;

import game.hero.HeroPosition;

public class NoSpellFoundException extends Exception{

    private HeroPosition heroPosition;

    public NoSpellFoundException(HeroPosition heroPosition) {
        this.heroPosition = heroPosition;
    }

    /**
     * Converting the exception to type string
     * @return Returns the message for the exception
     */
    @Override
    public String toString() {
        String heroRowMessage = String.valueOf(heroPosition.getRowIndex());
        String heroColMessage = String.valueOf(heroPosition.getColumnIndex());

        String message = "There is no spell at position ("
            + heroRowMessage + ", " + heroColMessage + ")!";

        return message;
    }
}
