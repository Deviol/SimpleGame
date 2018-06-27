package game.exceptions;

public class FailedGeneratingLevelException extends Exception{


    public FailedGeneratingLevelException() {}

    /**
     * Converting the exception to type string
     * @return Returns the message for the exception
     */
    @Override
    public String toString() {
        String message = "Failed to generate level because of level's Author error!";
        return message;
    }
}
