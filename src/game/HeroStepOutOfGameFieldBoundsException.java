package game;

public class HeroStepOutOfGameFieldBoundsException extends Exception{

    private int index;
    private IndexType indexType;

    public HeroStepOutOfGameFieldBoundsException(int index, IndexType indexType) {
        this.index = index;
        this.indexType = indexType;
    }

    @Override
    public String toString() {
        String type = convertIndexTypeToString();
        String indexMessage = String.valueOf(index);
        String resultMessage = indexMessage + " is not a valid " + type + " index!";
        return resultMessage;
    }

    private String convertIndexTypeToString() {
        if(indexType == IndexType.COLUMN) {
            return "column";
        }
        return "row";
    }
}
