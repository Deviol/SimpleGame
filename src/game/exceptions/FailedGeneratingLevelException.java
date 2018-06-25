package game.exceptions;

public class FailedGeneratingLevelException extends Exception{


    public FailedGeneratingLevelException() {}

    @Override
    public String toString() {
        String message = "Failed to generate level because of level's Author error!";
        return message;
    }
}
