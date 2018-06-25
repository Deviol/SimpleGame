package game.field;

import game.enums.FieldElementStatus;

public class GameField {
    private FieldElement[] field;
    public static final int MAX_LENGTH_OF_FIELD = 15;

    public GameField() {
        initializeField();
    }

    public String getFieldElementCodeAt(int row, int col) {
        return field[MAX_LENGTH_OF_FIELD * row + col].getCode();
    }

    public FieldElementStatus getFieldElementStatusAt(int row, int col) {
        return field[MAX_LENGTH_OF_FIELD * row + col].getStatus();
    }

    public void setNewFieldElementCode(int row, int col, String newCode) {
        field[MAX_LENGTH_OF_FIELD * row + col].setCode(newCode);
    }

    public void setFieldElementStatusAt(int row, int col,  FieldElementStatus status) {
        field[MAX_LENGTH_OF_FIELD * row + col].setStatus(status);
    }

    public boolean isMovingSafeAt(int row, int col) {
        boolean isColumnIndexInBounds = col > -1
                && col < GameField.MAX_LENGTH_OF_FIELD;
        boolean isRowIndexInBounds = row > -1
                && row < GameField.MAX_LENGTH_OF_FIELD;

        return isColumnIndexInBounds && isRowIndexInBounds;
    }

    public void revealSectorOfFieldElements(int fromRow, int toRow, int fromCol, int toCol) {
        for (int i = fromRow; i < toRow; i++) {
            for (int j = fromCol; j < toCol; j++) {
                field[MAX_LENGTH_OF_FIELD * i + j].setStatus(FieldElementStatus.REVEALED);
            }
        }
    }

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
            case "death": {
                System.out.print("H->D");
                break;
            }
            default: {
                // TODO
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
            case "death": {
                System.out.print("D");
                break;
            }
            default: {
                // TODO
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
        //TODO put hero at 7,7
    }
}
