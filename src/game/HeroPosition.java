package game;

public class HeroPosition {
    public final static int DEFAULT_INDEX_OF_HERO_POSITION = 7;
    private int rowIndex;
    private int columnIndex;

    public HeroPosition() {
        this.rowIndex = DEFAULT_INDEX_OF_HERO_POSITION;
        this.columnIndex = DEFAULT_INDEX_OF_HERO_POSITION;
    }

    protected void incrementRowIndexByOne() {
        rowIndex++;
    }

    protected void decrementRowIndexByOne() {
        rowIndex--;
    }

    protected void incrementColumnIndexByOne() {
        columnIndex++;
    }

    protected void decrementColumnIndexByOne() {
        columnIndex--;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
    public void updateHeroPosition(HeroDirection direction) {
        switch (direction) {
            case UP: {
                decrementRowIndexByOne();
                break;
            }
            case DOWN: {
                incrementRowIndexByOne();
                break;
            }
            case LEFT: {
                decrementColumnIndexByOne();
                break;
            }
            default: {
                incrementColumnIndexByOne();
                break;
            }
        }
    }
}
