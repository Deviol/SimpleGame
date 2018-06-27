package game.field;

import game.exceptions.InvalidSectorException;

/**
 * Wrapper class that is used to limit the access to the
 * functionality of GameField class.
 */
public class CustomGameField {

    private GameField gameField;

    public CustomGameField(GameField gameField) {
        this.gameField = gameField;
    }

    /**
     * Changing the status of set of elements to revealed
     * @param fromRow Starting row index
     * @param toRow Ending row index
     * @param fromCol Starting column index
     * @param toCol Ending column index
     * @throws InvalidSectorException When some of the indices are wrong the current
     * exception is thrown
     */
    public void revealSectorOfFieldElements(int fromRow, int toRow, int fromCol, int toCol)
        throws InvalidSectorException {
        boolean isSectorValid = gameField.isMovingSafeAt(fromRow,toRow) &&
            gameField.isMovingSafeAt(fromCol, toCol);
        if (isSectorValid) {
            gameField.revealSectorOfFieldElements(fromRow, toRow, fromCol, toCol);
        }
        else {
            throw new InvalidSectorException(fromRow, toRow, fromCol, toCol);
        }
    }
}
