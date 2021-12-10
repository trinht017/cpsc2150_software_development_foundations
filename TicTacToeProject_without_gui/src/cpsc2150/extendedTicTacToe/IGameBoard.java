//Trinh Tran
package cpsc2150.extendedTicTacToe;

/**
 * GameBoard is abstractly a grid with NUM_OF_ROWS by NUM_OF_COLUMNS
 *
 * Defines: NUM_OF_ROWS : Z - the number of rows of self
 *          NUM_OF_COLUMNS : Z - the number of columns of self
 *          NUM_TO_WIN : Z - the number of tokens in a row needed to win the game
 *
 * Initialization ensures: self is at most NUM_OF_ROWS by NUM_OF_COLUMNS
 *                         MIN_ROW <= NUM_OF_ROWS <= MAX_ROW AND
 *                         MIN_COLUMN <= NUM_OF_COLUMNS <= MAX_COLUMN AND
 *                         MIN_NUM_TO_WIN <= NUM_TO_WIN <= MAX_NUM_TO_WIN
 *                         NUM_TO_WIN <= NUM_OF_ROWS
 *                         NUM_TO_WIN <= NUM_OF_COLUMNS
 *
 * Constraints: MIN_ROW <= NUM_OF_ROWS <= MAX_ROW AND
 *              MIN_COLUMN <= NUM_OF_COLUMNS <= MAX_COLUMN AND
 *              MIN_NUM_TO_WIN <= NUM_TO_WIN <= MAX_NUM_TO_WIN
 *              NUM_TO_WIN <= NUM_OF_ROWS
 *              NUM_TO_WIN <= NUM_OF_COLUMNS
 */
public interface IGameBoard {

    public static final int MAX_ROW = 100;
    public static final int MIN_ROW = 3;
    public static final int MAX_COLUMN = 100;
    public static final int MIN_COLUMN = 3;
    public static final int MAX_NUM_TO_WIN = 25;
    public static final int MIN_NUM_TO_WIN = 3;

    /**
     * This method returns the number of rows of self
     * @return int NUM_OF_ROWS
     * @pre NONE
     * @post getNumRows = NUM_OF_ROWS
     *       self = #self
     *       NUM_OF_ROWS = #NUM_OF_ROWS
     *       NUM_OF_COLUMNS = #NUM_OF_COLUMNS
     *       NUM_TO_WIN = #NUM_TO_WIN
     */
    int getNumRows();

    /**
     * This method returns the number of columns of self
     * @return int NUM_OF_COLUMNS
     * @pre NONE
     * @post getNumColumns = NUM_OF_COLUMNS
     *       self = #self
     *       NUM_OF_ROWS = #NUM_OF_ROWS
     *       NUM_OF_COLUMNS = #NUM_OF_COLUMNS
     *       NUM_TO_WIN = #NUM_TO_WIN
     */
    int getNumColumns();

    /**
     * This method returns the number of tokens in a row to win
     * @return int NUM_TO_WIN
     * @pre NONE
     * @post getNumToWin = NUM_TO_WIN
     *       self = #self
     *       NUM_OF_ROWS = #NUM_OF_ROWS
     *       NUM_OF_COLUMNS = #NUM_OF_COLUMNS
     *       NUM_TO_WIN = #NUM_TO_WIN
     */
    int getNumToWin();

    /**
     * This method checks to see if a position is available in self
     * @param pos position to check space in
     * @return true iff pos is available in self, else return false
     * @pre none
     * @post checkSpace = true iff 0 <= Row < NUM_OF_ROWS and 0 <= Column < NUM_OF_COLUMNS and
     *                    the position is not filled (has ' ' character)
     *                  = false otherwise
     *       self = #self
     *       NUM_OF_ROWS = #NUM_OF_ROWS
     *       NUM_OF_COLUMNS = #NUM_OF_COLUMNS
     *       NUM_TO_WIN = #NUM_TO_WIN
     */
    default boolean checkSpace(BoardPosition pos) {
        int row = pos.getRow();
        int column = pos.getColumn();
        //checking if pos is within bounds and whatsAtPos should return ' ' if space is available
        if (row >= 0 && row < getNumRows()) {
            if (column >= 0 && column < getNumColumns()) {
                if(whatsAtPos(pos) == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method places the player in the board position marker
     * @param marker the position that will be placed
     * @param player the player that is placing the marker
     * @pre player is the player placing the marker
     *      marker is valid and available
     *
     * @post self = #self with player added at position marker
     *       NUM_OF_ROWS = #NUM_OF_ROWS
     *       NUM_OF_COLUMNS = #NUM_OF_COLUMNS
     *       NUM_TO_WIN = #NUM_TO_WIN
     */
    void placeMarker(BoardPosition marker, char player);

    /**
     * This method checks to see if the last position placed resulted in a winner
     * @param lastPos the last position played
     * @return true iff there is a winner, else returns false
     * @pre lastPos is the latest position that was played
     * @post checkForWinner = true iff lastPos played resulted in NUM_TO_WIN in
     *                        a row horizontally, diagonally, or vertically, false otherwise
     *       self = #self
     *       NUM_OF_ROWS = #NUM_OF_ROWS
     *       NUM_OF_COLUMNS = #NUM_OF_COLUMNS
     *       NUM_TO_WIN = #NUM_TO_WIN
     */
    default boolean checkForWinner(BoardPosition lastPos) {
        char player = whatsAtPos(lastPos);
        if (checkHorizontalWin(lastPos, player)) return true;
        if (checkVerticalWin(lastPos, player)) return true;
        if (checkDiagonalWin(lastPos, player)) return true;
        return false;
    }

    /**
     * This method checks to see if the game has resulted in a tie
     * @return true if the game is tied, false otherwise
     * @pre none
     * @post checkForDraw = true iff all positions are filled and no winner resulted, false otherwise
     *       self = #self
     *       NUM_OF_ROWS = #NUM_OF_ROWS
     *       NUM_OF_COLUMNS = #NUM_OF_COLUMNS
     *       NUM_TO_WIN = #NUM_TO_WIN
     */
    default boolean checkForDraw() {
        //traversing through 2-d array and checking if there is a ' '
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                if (whatsAtPos(new BoardPosition(i, j)) == ' ') return false;
            }
        }
        return true;
    }

    /**
     * This method checks to see if the last marker placed resulted in NUM_TO_WIN in a row horizontally
     * @param lastPos the last position played on the board
     * @param player the player that played the last position
     * @return true iff the last position played resulted in horizontal win, false otherwise
     * @pre lastPos is the latest position that was played
     *      player is the player that placed lastPos
     * @post checkHorizontalWin = true iff player got NUM_TO_WIN in a row horizontally, false otherwise
     *       self = #self
     *       NUM_OF_ROWS = #NUM_OF_ROWS
     *       NUM_OF_COLUMNS = #NUM_OF_COLUMNS
     *       NUM_TO_WIN = #NUM_TO_WIN
     */
    default boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        int row = lastPos.getRow();
        int column = lastPos.getColumn();
        int count = 1;
        int numColumns = getNumColumns();
        int numToWin = getNumToWin();

        //checking horizontally with column decreasing
        while(column > 0) {
            column--;
            if (isPlayerAtPos(new BoardPosition(row, column), player)) count++;
            else break;
            if (count == numToWin) return true;
        }

        column = lastPos.getColumn();

        //checking horizontally with column increasing
        while (column < numColumns - 1) {
            column++;
            if(isPlayerAtPos(new BoardPosition(row, column), player)) count++;
            else break;
            if (count == numToWin) return true;
        }

        return false;
    }


    /**
     * This method checks to see if the last marker placed resulted in NUM_TO_WIN in a row vertically
     * @param lastPos the last position played on the board
     * @param player the player that played the last position
     * @return true iff the last position played resulted in vertical win, false otherwise
     * @pre lastPos is the latest position that was played
     *      player is the player that placed lastPos
     * @post checkVerticalWin = true iff player got NUM_TO_WIN in a row vertically, false otherwise
     *       self = #self
     *       NUM_OF_ROWS = #NUM_OF_ROWS
     *       NUM_OF_COLUMNS = #NUM_OF_COLUMNS
     *       NUM_TO_WIN = #NUM_TO_WIN
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player) {
        int row = lastPos.getRow();
        int column = lastPos.getColumn();
        int count = 1;
        int numRows = getNumRows();
        int numToWin = getNumToWin();

        //checking vertically as row is decreasing
        while(row > 0) {
            row--;
            if (isPlayerAtPos(new BoardPosition(row, column), player)) count++;
            else break;
            if (count == numToWin) return true;
        }

        row = lastPos.getRow();

        //checking vertically as row is increasing
        while (row < numRows - 1) {
            row++;
            if(isPlayerAtPos(new BoardPosition(row, column), player)) count++;
            else break;
            if (count == numToWin) return true;
        }

        return false;
    }



    /**
     * This method checks to see if the last marker placed resulted in NUM_TO_WIN in a row diagonally
     * @param lastPos the last position played on the board
     * @param player the player that played the last position
     * @return true iff the last position played resulted in diagonal win, false otherwise
     * @pre lastPos is the latest position that was played
     *      player is the player that placed lastPos
     * @post checkDiagonalWin = true iff player got NUM_TO_WIN in a row diagonally, false otherwise
     *       self = #self
     *       NUM_OF_ROWS = #NUM_OF_ROWS
     *       NUM_OF_COLUMNS = #NUM_OF_COLUMNS
     *       NUM_TO_WIN = #NUM_TO_WIN
     */
    default boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        int row = lastPos.getRow();
        int column = lastPos.getColumn();
        int count = 1;
        int numRows = getNumRows();
        int numColumns = getNumColumns();
        int numToWin = getNumToWin();

        //checking for backslash diagonal with row and column increasing
        while (row < numRows - 1 && column < numColumns - 1) {
            row++;
            column++;
            if (isPlayerAtPos(new BoardPosition(row, column), player)) count++;
            else break;
            if (count == numToWin) return true;
        }
        //reset to lastPos
        row = lastPos.getRow();
        column = lastPos.getColumn();

        //checking for backslash diagonal with row and column decreasing
        while (row > 0 && column > 0) {
            row--;
            column--;
            if (isPlayerAtPos(new BoardPosition(row, column), player)) count++;
            else break;
            if (count == numToWin) return true;
        }

        //reset to initial position to check for forward slash diagonal
        row = lastPos.getRow();
        column = lastPos.getColumn();
        count = 1;

        //checking for forward slash diagonal with row decreasing and column increasing
        while (row > 0 && column < numColumns - 1) {
            row--;
            column++;
            if (isPlayerAtPos(new BoardPosition(row, column), player)) count++;
            else break;
            if (count == numToWin) return true;
        }
        //reset to lastPos
        row = lastPos.getRow();
        column = lastPos.getColumn();

        //checking for forward slash diagonal with row increasing and column decreasing
        while (row < numRows - 1 && column > 0) {
            row++;
            column--;
            if (isPlayerAtPos(new BoardPosition(row, column), player)) count++;
            else break;
            if (count == numToWin) return true;
        }

        return false;
    }


    /**
     * This method returns what is at a certain position
     * @param pos the position on the GameBoard to check
     * @return the player that is in pos
     * @pre pos is valid (within the bounds of BoardGame)
     * @post whatsAtPos = [player at pos] iff pos has a marker
     *       whatsAtPos = ' ' iff pos has no marker
     *       self = #self
     *       NUM_OF_ROWS = #NUM_OF_ROWS
     *       NUM_OF_COLUMNS = #NUM_OF_COLUMNS
     *       NUM_TO_WIN = #NUM_TO_WIN
     */
    char whatsAtPos(BoardPosition pos);


    /**
     * This method checks to see if player is at a certain position
     * @param pos the position to check for
     * @param player the player to check if is in pos
     * @return true if the player is at pos, false otherwise
     * @pre pos is within valid bounds
     * @post isPlayerAtPos = true iff player is at pos, false otherwise
     *       self = #self
     *       NUM_OF_ROWS = #NUM_OF_ROWS
     *       NUM_OF_COLUMNS = #NUM_OF_COLUMNS
     *       NUM_TO_WIN = #NUM_TO_WIN
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player) {
        return whatsAtPos(pos) == player;
    }

}
