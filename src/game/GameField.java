package game;

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
