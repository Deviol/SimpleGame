package game.exceptions;

public class FailedGeneratingLevelException extends Exception{

    private FailedGeneratingElementException failedGeneratingElementException;

    public FailedGeneratingLevelException(FailedGeneratingElementException failedGeneratingElementException) {
        this.failedGeneratingElementException = failedGeneratingElementException;
    }

    /**
     * Converting the exception to type string
     * @return Returns the message for the exception
     */
    @Override
    public String toString() {
        return "Failed to generate level due to "
            + failedGeneratingElementException.getEndMessage() + " in the inner generating methods!";
    }
}
