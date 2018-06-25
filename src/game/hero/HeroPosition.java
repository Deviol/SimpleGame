package game.hero;

import game.enums.HeroDirection;

public class HeroPosition {

    private int rowIndex;

    private int columnIndex;

    public final static int DEFAULT_INDEX_OF_HERO_POSITION = 7;

    public HeroPosition() {
        this.rowIndex = DEFAULT_INDEX_OF_HERO_POSITION;
        this.columnIndex = DEFAULT_INDEX_OF_HERO_POSITION;
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
                rowIndex--;
                break;
            }
            case DOWN: {
                rowIndex++;
                break;
            }
            case LEFT: {
                columnIndex--;
                break;
            }
            default: {
                columnIndex++;
                break;
            }
        }
    }
}
