package game.exceptions;

import game.IndexType;

public class HeroStepOutOfGameFieldBoundsException extends Exception{

    private int index;
    private IndexType indexType;

    public HeroStepOutOfGameFieldBoundsException(int index, IndexType indexType) {
        this.index = index;
        this.indexType = indexType;
    }

    @Override
    public String toString() {
        String type = String.valueOf(indexType).toLowerCase();
        String indexMessage = String.valueOf(index);
        String resultMessage = indexMessage + " is not a valid " + type + " index!";
        return resultMessage;
    }
}
