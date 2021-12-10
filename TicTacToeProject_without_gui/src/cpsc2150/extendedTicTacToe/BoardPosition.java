//Trinh Tran

package cpsc2150.extendedTicTacToe;
import java.util.*;

public class BoardPosition {
    /**
     * BoardPosition class Keeps track of an individual cell for a board
     */
    private int Row;
    private int Column;

    /**
     * Constructor
     * @param Row the row number
     * @param Column the column number
     * @pre none
     * @post Row = Row
     *       Column = Column
     */
    public BoardPosition(int Row, int Column) {
        this.Row = Row;
        this.Column = Column;
    }

    /**
     * @pre none
     * @post Row = #Row
     *       Column = #Column
     * @return Row number
     */
    public int getRow() {
        return Row;
    }

    /**
     * @pre none
     * @post Row = #Row
     *       Column = #Column
     * @return Column number
     */
    public int getColumn() {
        return Column;
    }

    /**
     * This method checks to see if two BoardPosition object is equal or not
     * @param obj the object passed in
     * @return true if two BoardPosition are equal
     * @pre NONE
     * @post if BoardPosition has same Row and Column, return true, else return false
     *       Row = #Row AND Column = #Column
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof BoardPosition)) return false;
        BoardPosition p = (BoardPosition)obj;
        return (p.Row == Row) && (p.Column == Column);
    }

    /**
     * This method provides a string representation of the contents of a BoardPosition
     * @return the string representation of the BoardPosition
     * @pre NONE
     * @post toString = [a string representation of BoardPosition formatted as "<row>,<column>"]
     *       AND Row = #Row AND Column = #Column
     */
    @Override
    public String toString() {
        return Row + "," + Column;
    }

}
