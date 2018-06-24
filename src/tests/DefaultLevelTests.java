package tests;
import game.*;
import org.junit.Before;
import org.junit.Test;

public class DefaultLevelTests {

    private DefaultLevel defaultLevel;

    @Before
    public void initializeDefaultLevel() {
        int randomMaxHealth = 50;
        Hero hero = new Hero(randomMaxHealth);
        defaultLevel = new DefaultLevel(hero);
    }

    @Test(expected = HeroStepOutOfGameFieldBoundsException.class)
    public void testMovingLeftShouldThrowException() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        int numberOfSteps = 8;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingLeft();
        }
    }
    @Test(expected = Test.None.class)
    public void testMovingLeftShouldMakeLegalSteps() throws HeroStepOutOfGameFieldBoundsException,
        ForbiddenDirectionException {
        int numberOfSteps = 7;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingLeft();
        }
    }

    @Test(expected = HeroStepOutOfGameFieldBoundsException.class)
    public void testMovingRightShouldThrowException() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        int numberOfSteps = 8;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingRight();
        }
    }

    @Test(expected = Test.None.class)
    public void testMovingRightShouldMakeLegalSteps() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        int numberOfSteps = 7;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingRight();
        }
    }

    @Test(expected = HeroStepOutOfGameFieldBoundsException.class)
    public void testMovingUpShouldThrowException() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        int numberOfSteps = 8;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingUp();
        }
    }

    @Test(expected = Test.None.class)
    public void testMovingUpShouldMakeLegalSteps() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        int numberOfSteps = 7;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingUp();
        }
    }

    @Test(expected = HeroStepOutOfGameFieldBoundsException.class)
    public void testMovingDownShouldThrowException() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        int numberOfSteps = 8;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingDown();
        }
    }

    @Test(expected = Test.None.class)
    public void testMovingDownShouldMakeLegalSteps() throws HeroStepOutOfGameFieldBoundsException,
            ForbiddenDirectionException {
        int numberOfSteps = 7;
        for (int step = 0; step < numberOfSteps; step++) {
            defaultLevel.movingDown();
        }
    }

    @Test(expected = Test.None.class)
    public void testActivateSpellShouldCreateSpell() throws NoSpellFoundException{
        boolean isHeroOnSpell = true;
        defaultLevel.generateLevel();
        setHeroPositionOnTestingPosition(isHeroOnSpell);
        defaultLevel.activateSpell();
    }

    @Test(expected = NoSpellFoundException.class)
    public void testActivateSpellShouldThrowException() throws NoSpellFoundException{
        boolean isHeroOnSpell = false;
        defaultLevel.generateLevel();
        setHeroPositionOnTestingPosition(isHeroOnSpell);
        defaultLevel.activateSpell();
    }

    @Test(expected = FailedGeneratingElementException.class)
    public void testGenerateElementAtShouldThrowExceptionCaseInvalidIndices()
        throws FailedGeneratingElementException {
        int randomRow = 3;
        int badRandomCol = 15;
        defaultLevel.generateElementAt(randomRow, badRandomCol, "spell:InfernoSpell");
    }

    @Test(expected = FailedGeneratingElementException.class)
    public void testGenerateElementAtShouldThrowExceptionCaseInvalidElementCodeV1()
            throws FailedGeneratingElementException {
        int randomRow = 3;
        int randomCol = 14;
        defaultLevel.generateElementAt(randomRow, randomCol, "spell.InfernoSpell");
    }

    @Test(expected = FailedGeneratingElementException.class)
    public void testGenerateElementAtShouldThrowExceptionCaseInvalidElementCodeV2()
            throws FailedGeneratingElementException {
        int randomRow = 3;
        int randomCol = 14;
        defaultLevel.generateElementAt(randomRow, randomCol, "sspell:InfernoSpell");
    }

    @Test
    public void testGenerateElementAtShouldAssignNewCode()
            throws FailedGeneratingElementException {
        int randomRow = 3;
        int randomCol = 14;
        defaultLevel.generateElementAt(randomRow, randomCol, "spell:InfernoSpell");
    }

    private void setHeroPositionOnTestingPosition(boolean isHeroOnSpell) {
        int numberOfUpSteps;
        int numberOfLeftSteps;
        if(isHeroOnSpell) {
            numberOfUpSteps = 5;
            numberOfLeftSteps = 4;
        }
        else {
            numberOfUpSteps = 3;
            numberOfLeftSteps = 3;
        }
        makingSteps(numberOfUpSteps, numberOfLeftSteps);
    }

    private void makingSteps(int numberOfUpSteps, int numberOfLeftSteps) {
        for (int i = 0; i < numberOfUpSteps; i++) {
            try {
                defaultLevel.movingUp();
            } catch(HeroStepOutOfGameFieldBoundsException |
                ForbiddenDirectionException e) {
                System.out.println("Out of the game field!");
            }
        }
        for (int i = 0; i < numberOfLeftSteps; i++) {
            try {
                defaultLevel.movingLeft();
            } catch(HeroStepOutOfGameFieldBoundsException |
                ForbiddenDirectionException e) {
                System.out.println("Out of the game field!");
            }
        }
    }
}
