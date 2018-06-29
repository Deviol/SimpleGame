package game.exceptions;

import game.field.GameField;

public class FailedGeneratingElementException extends Exception {

    private int rowIndex;
    private int columnIndex;
    private String elementCode;
    private String endMessage;
    public FailedGeneratingElementException(int rowIndex, int columnIndex, String elementCode) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.elementCode = elementCode;
        endMessage = "wrong index";
    }

    /**
     * Converting the exception to type string
     * @return Returns the message for the exception
     */
    @Override
    public String toString() {
        return findReasonForException();
    }

    private String findReasonForException() {
        String result;
        boolean isRowIndexOutOfBounds =  rowIndex < 0 || rowIndex >= GameField.MAX_LENGTH_OF_FIELD;
        boolean isColumnIndexOutOfBounds = columnIndex < 0 || columnIndex >= GameField.MAX_LENGTH_OF_FIELD;

        if (isRowIndexOutOfBounds) {
            result = "Row index \"" + rowIndex + "\" is not valid!";
        }
        else if (isColumnIndexOutOfBounds) {
            result = "Column index \"" + columnIndex + "\" is not valid!";
        }
        else {
            result = "Element code \"" + elementCode + "\" is not a valid code";
            endMessage = "wrong element code";
        }

        return result;
    }

    public String getEndMessage() {
        return endMessage;
    }
}
