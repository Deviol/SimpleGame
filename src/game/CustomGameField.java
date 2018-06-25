package game;

public class CustomGameField {

    private GameField gameField;
    public CustomGameField(GameField gameField) {
        this.gameField = gameField;
    }

    public void revealSectorOfFieldElements(int fromRow, int toRow, int fromCol, int toCol) {
        gameField.revealSectorOfFieldElements(fromRow, toRow, fromCol, toCol);
    }
}
