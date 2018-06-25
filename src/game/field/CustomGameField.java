package game.field;

import game.exceptions.InvalidSectorException;

public class CustomGameField {

    private GameField gameField;

    public CustomGameField(GameField gameField) {
        this.gameField = gameField;
    }

    public void revealSectorOfFieldElements(int fromRow, int toRow, int fromCol, int toCol)
        throws InvalidSectorException {
        boolean isSectorValid = gameField.isMovingSafeAt(fromRow,toRow) &&
            gameField.isMovingSafeAt(fromCol, toCol);
        if(isSectorValid) {
            gameField.revealSectorOfFieldElements(fromRow, toRow, fromCol, toCol);
        }
        else {
            throw new InvalidSectorException(fromRow, toRow, fromCol, toCol);
        }
    }
}
