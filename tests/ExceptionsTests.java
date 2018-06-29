import game.enums.HeroDirection;
import game.exceptions.*;
import game.hero.HeroPosition;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExceptionsTests {

    private static NoSpellFoundException noSpellFoundException;
    private static InvalidSectorException invalidSectorException;
    private static HeroStepOutOfGameFieldBoundsException heroStepOutOfGameFieldBoundsException;
    private static ForbiddenDirectionException forbiddenDirectionException;
    private static FailedGeneratingLevelException failedGeneratingLevelException;
    private static FailedGeneratingElementException failedGeneratingElementException;

    @BeforeClass
    public static void initializeExceptions() {
        HeroPosition heroPosition = new HeroPosition();
        HeroDirection direction = HeroDirection.DOWN;

        noSpellFoundException = new NoSpellFoundException(heroPosition);

        //initializing with random indices
        int fromRow = 15;
        int toRow = 2;
        int fromCol = 12;
        int toCol = 0;
        invalidSectorException = new InvalidSectorException(fromRow, toRow, fromCol, toCol);

        heroStepOutOfGameFieldBoundsException =
            new HeroStepOutOfGameFieldBoundsException(heroPosition, direction);

        forbiddenDirectionException = new ForbiddenDirectionException(direction);

        failedGeneratingElementException = new FailedGeneratingElementException(3, 4, "spell:");

        failedGeneratingLevelException =
            new FailedGeneratingLevelException(failedGeneratingElementException);
    }

    @Test
    public void testNoSpellFoundException() {
        String message = "There is no spell at position (7, 7)!";
        String exceptionMessage = noSpellFoundException.toString();
        Assert.assertTrue(message.equals(exceptionMessage));
    }

    @Test
    public void testInvalidSectorException() {
        String message = "fromRow: \"15\" is not a valid index!";
        String exceptionMessage = invalidSectorException.toString();
        Assert.assertTrue(message.equals(exceptionMessage));
    }

    @Test
    public void testHeroStepOutOfGameFieldBoundsException() {
        String message = "Failed moving down!\nIndex \"8\" is not a valid row index!";
        String exceptionMessage = heroStepOutOfGameFieldBoundsException.toString();

        Assert.assertTrue(message.equals(exceptionMessage));
    }

    @Test
    public void testForbiddenDirectionException() {
        String message = "Moving down is not allowed!";
        String exceptionMessage = forbiddenDirectionException.toString();

        Assert.assertTrue(message.equals(exceptionMessage));
    }

    @Test
    public void testFailedGeneratingElementExceptionCaseInvalidCode() {
        failedGeneratingElementException = new FailedGeneratingElementException(3, 4, "spell:.");
        String message = "Element code \"spell:.\" is not a valid code";
        String exceptionMessage = failedGeneratingElementException.toString();

        Assert.assertTrue(exceptionMessage.equals(message));
    }

    @Test
    public void testFailedGeneratingElementExceptionCaseInvalidIndex() {
        failedGeneratingElementException =
            new FailedGeneratingElementException(3, 15, "spell:Inferno");
        String message = "Column index \"15\" is not valid!";
        String exceptionMessage = failedGeneratingElementException.toString();

        Assert.assertTrue(message.equals(exceptionMessage));
    }

    @Test
    public void testFailedGeneratingLevelException() {
        String message = "Failed to generate level due to wrong index in the inner generating methods!";
        String exceptionMessage = failedGeneratingLevelException.toString();
        Assert.assertTrue(message.equals(exceptionMessage));
    }
}
