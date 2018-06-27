package game.exceptions;

import game.field.GameField;

public class InvalidSectorException extends Exception {

    private int fromRowIndex;

    private int toRowIndex;

    private int fromColumnIndex;

    private int toColumnIndex;

    public InvalidSectorException(int fromRowIndex, int toRowIndex,
                                  int fromColumnIndex, int toColumnIndex) {
        this.fromRowIndex = fromRowIndex;
        this.toRowIndex = toRowIndex;
        this.fromColumnIndex = fromColumnIndex;
        this.toColumnIndex = toColumnIndex;
    }

    /**
     * Converting the exception to type string
     * @return Returns the message for the exception
     */
    @Override
    public String toString() {
        String badIndexName = finReasonForException();
        String message = badIndexName + "is not a valid index!";
        return message;
    }

    private String finReasonForException() {
        boolean isFromRowIndexInvalid =
            fromRowIndex < 0 || GameField.MAX_LENGTH_OF_FIELD <= fromRowIndex;
        boolean isToRowIndexInvalid =
                toRowIndex < 0 || GameField.MAX_LENGTH_OF_FIELD <= toRowIndex;
        boolean isFromColumnIndexInvalid =
                fromColumnIndex < 0 || GameField.MAX_LENGTH_OF_FIELD <= fromColumnIndex;
        boolean isToColumnIndexInvalid =
                toColumnIndex < 0 || GameField.MAX_LENGTH_OF_FIELD <= toColumnIndex;

        String result;
        if(isFromRowIndexInvalid) {
            result = "fromRow: \"" + fromRowIndex + "\" ";
        }
        else if(isToRowIndexInvalid) {
            result = "toRow: \"" + toRowIndex + "\" ";
        }
        else if(isFromColumnIndexInvalid) {
            result = "fromCol: \"" + fromColumnIndex + "\" ";
        }
        else {
            result = "toCol: \"" + toColumnIndex + "\" ";
        }
        return result;
    }
}
