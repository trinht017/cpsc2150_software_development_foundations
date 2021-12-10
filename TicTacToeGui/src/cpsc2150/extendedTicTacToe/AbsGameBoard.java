//Trinh Tran

package cpsc2150.extendedTicTacToe;

public abstract class AbsGameBoard implements IGameBoard{
    public static final int MAX_SINGLE_DIGIT = 10;
    /**
     * This method provides a string representation of the contents of the game board
     * @return a string representation of the game board
     * @pre NONE
     * @post toString = [a string representation of the game board]
     */
    @Override
    public String toString() {

        int numRows = getNumRows();
        int numColumns = getNumColumns();

        StringBuilder str = new StringBuilder();
        str.append("   ");
        for (int i = 0; i < numColumns; i++) {
            if (i < MAX_SINGLE_DIGIT) str.append(" ");
            str.append(i);
            str.append("|");
        }
        str.append('\n');

        for (int i = 0; i < numRows; i++) {
            if (i < MAX_SINGLE_DIGIT) str.append(" ");
            str.append(i);
            str.append('|');
            for (int j = 0; j < numColumns; j++) {
                str.append(whatsAtPos(new BoardPosition(i, j)));
                str.append(" |");
            }
            str.append('\n');
        }
        return str.toString();
    }
}
