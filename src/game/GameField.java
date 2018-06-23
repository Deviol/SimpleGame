package game;

public class GameField {
    private FieldElement[] field;
    public static final int MAX_LENGTH_OF_FIELD = 15;

    public GameField() {
        initializeField();
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
