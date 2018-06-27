package game.exceptions;

import game.field.GameField;

public class FailedGeneratingElementException extends Exception {

    private int rowIndex;

    private int columnIndex;

    private String elementCode;

    public FailedGeneratingElementException(int rowIndex, int columnIndex, String elementCode) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.elementCode = elementCode;
    }

    @Override
    public String toString() {
        return findReasonForException();
    }

    private String findReasonForException() {
        String result;
        boolean isRowIndexOutOfBounds =  rowIndex < 0 || rowIndex >= GameField.MAX_LENGTH_OF_FIELD;
        boolean isColumnIndexOutOfBounds = columnIndex < 0 || columnIndex >= GameField.MAX_LENGTH_OF_FIELD;

        if(isRowIndexOutOfBounds) {
            result = "Row index \"" + rowIndex + "\" is not valid!";
        }
        else if(isColumnIndexOutOfBounds) {
            result = "Column index \"" + columnIndex + "\" is not valid!";
        }
        else {
            result = "Element code \"" + elementCode + "\" is not a valid code";
        }

        return result;
    }

}
