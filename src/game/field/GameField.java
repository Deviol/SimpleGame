package game.field;

import game.enums.FieldElementStatus;

/**
 * Represents a square game field from FieldElements
 */
public class GameField {

    private FieldElement[] field;

    public static final int MAX_LENGTH_OF_FIELD = 15;

    public GameField() {
        initializeField();
    }

    /**
     * Getting the code of an element
     * @param row Row index
     * @param col Column index
     * @return Returns code of the cell with these coordinates
     */
    public String getFieldElementCodeAt(int row, int col) {
        return field[MAX_LENGTH_OF_FIELD * row + col].getCode();
    }

    /**
     * Getting the status of an element
     * @param row Row index
     * @param col Column index
     * @return Returns code of the cell with these coordinates
     */
    public FieldElementStatus getFieldElementStatusAt(int row, int col) {
        return field[MAX_LENGTH_OF_FIELD * row + col].getStatus();
    }

    /**
     * Setting the code of an element
     * @param row Row index
     * @param col Column index
     * @param code new code
     */
    public void setNewFieldElementCode(int row, int col, String code) {
        field[MAX_LENGTH_OF_FIELD * row + col].setCode(code);
    }

    /**
     * Setting the code of an element
     * @param row Row index
     * @param col Column index
     * @param status new status
     */
    public void setFieldElementStatusAt(int row, int col,  FieldElementStatus status) {
        field[MAX_LENGTH_OF_FIELD * row + col].setStatus(status);
    }

    /**
     * Validates the passed arguments
     * @param row row index
     * @param col column index
     * @return boolean value indicating the validity of the arguments
     */
    public boolean isMovingSafeAt(int row, int col) {
        boolean isColumnIndexInBounds = col > -1
                && col < GameField.MAX_LENGTH_OF_FIELD;
        boolean isRowIndexInBounds = row > -1
                && row < GameField.MAX_LENGTH_OF_FIELD;

        return isColumnIndexInBounds && isRowIndexInBounds;
    }

    /**
     * Changing the status of set of elements to revealed
     * @param fromRow Starting row index
     * @param toRow Ending row index
     * @param fromCol Starting column index
     * @param toCol Ending column index
     */
    public void revealSectorOfFieldElements(int fromRow, int toRow, int fromCol, int toCol) {
        for (int i = fromRow; i < toRow; i++) {
            for (int j = fromCol; j < toCol; j++) {
                field[MAX_LENGTH_OF_FIELD * i + j].setStatus(FieldElementStatus.REVEALED);
            }
        }
    }

    /**
     * Prints to the standard output user-friendly game field different from
     * the actual one
     */
    public void printGameFieldForUserView() {
        for (int i = 0; i < GameField.MAX_LENGTH_OF_FIELD; i++) {
            for (int j = 0; j < GameField.MAX_LENGTH_OF_FIELD; j++) {
                if(getFieldElementStatusAt(i, j) == FieldElementStatus.HIDDEN) {
                    printHiddenFieldElement();
                }
                else {
                    printRevealedFieldElementAt(i, j);
                }
            }
        }
    }

    private void printHiddenFieldElement() {
        System.out.print("X");
    }
    private void printRevealedFieldElementAt(int row, int col) {
        String currentCode = getFieldElementCodeAt(row, col);
        if(currentCode.equals("hero")) {
            System.out.print("H");
        }
        else if(currentCode.equals("used")) {
            System.out.print("o");
        }
        else if(currentCode.contains("hero:")) {
            printSpecialSteppedElement(currentCode);
        }
        else {
            printSpecialNotSteppedElement(currentCode);
        }
    }

    private void printSpecialSteppedElement(String currentCode) {
        String[] currentCodeParts = currentCode.split(":");
        switch(currentCodeParts[1]) {
            case "spell": {
                System.out.print("H->?S");
                break;
            }
            case "exit": {
                System.out.print("H->E");
                break;
            }
            default: {
                System.out.print("H->D");
                break;
            }
        }
    }
    private void printSpecialNotSteppedElement(String currentCode) {
        switch(currentCode) {
            case "spell": {
                System.out.print("S");
                break;
            }
            case "exit": {
                System.out.print("E");
                break;
            }
            default: {
                System.out.print("D");
                break;
            }
        }
    }

    private void initializeField() {
        field = new FieldElement[MAX_LENGTH_OF_FIELD * MAX_LENGTH_OF_FIELD];
        for (int i = 0; i < MAX_LENGTH_OF_FIELD; i++) {
            for (int j = 0; j < MAX_LENGTH_OF_FIELD; j++) {
                field[MAX_LENGTH_OF_FIELD * i + j] = new FieldElement();
            }
        }
    }
}
