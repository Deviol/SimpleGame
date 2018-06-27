package game.tests;

import game.enums.FieldElementStatus;
import game.field.GameField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameFieldTests {

    private GameField gameField;

    @Before
    public void initializeGameField() {
        gameField = new GameField();
    }

    @Test
    public void testConstructorShouldCreateDefaultField() {
        boolean isCellDefaultElement = true;
        int length = GameField.MAX_LENGTH_OF_FIELD;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if(gameField.getFieldElementStatusAt(i, j) != FieldElementStatus.HIDDEN
                        || !gameField.getFieldElementCodeAt(i, j).equals("empty")) {
                    isCellDefaultElement = false;
                    break;
                }
            }
            if(!isCellDefaultElement) {
                break;
            }
        }
        Assert.assertTrue(isCellDefaultElement);
    }

    @Test
    public void testMethodIsMovingSafeForOutOfBoundsIndices() {
        boolean notSafeMove = gameField.isMovingSafeAt(0, 15);

        Assert.assertFalse(notSafeMove);
    }
}
