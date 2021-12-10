//Trinh Tran

package cpsc2150.extendedTicTacToe;

import java.util.*;

public class GameBoard extends AbsGameBoard implements IGameBoard {
    /**
     * GameBoard class contains the game board as a 2-d array implementation
     * @invariant MIN_ROW <= numRows <= MAX_ROW
     * @invariant MIN_COLUMN <= numColumns <= MAX_COLUMN
     * @invariant MIN_NUM_TO_WIN <= numToWin <= MAX_NUM_TO_WIN
     * @invariant numToWin <= numRows
     * @invariant numToWin <= numColumns
     * @invariant 0 <= numMarkersPlaced <= NUM_OF_ROWS * NUM_OF_COLUMNS
     *            AND numMarkersPlaced is the number of char in gameBoard that are not spaces ' '
     *
     * correspondence numRows = NUM_OF_ROWS
     * correspondence numColumns = NUM_OF_COLUMNS
     * correspondence numToWin = NUM_TO_WIN
     */
    private char [][] gameBoard;
    private int numRows;
    private int numColumns;
    private int numToWin;
    private int numMarkersPlaced;

    /**
     * Constructor
     * @param numRows int number of rows for GameBoard
     * @param numColumns int number of columns for GameBoard
     * @param numToWin int number of markers in a row to win
     *
     * @pre MIN_ROW <= numRows <= MAX_ROW
     *      MIN_COLUMN <= numColumns <= MAX_COLUMN
     *      MIN_NUM_TO_WIN <= numToWin <= MAX_NUM_TO_WIN
     *      numToWin <= numRows
     *      numToWin <= numColumns
     *
     * @post gameBoard is a numRows x numColumns 2-d array of characters
     *       all spaces will be filled with ' ' (a single blank space character)
     *       0,0 is top left corner
     *
     *       this.numRows = numRows
     *       this.numColumns = numColumns
     *       this.numToWin = numToWin
     *       numMarkersPlaced = 0
     */
    public GameBoard(int numRows, int numColumns, int numToWin) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numToWin = numToWin;
        numMarkersPlaced = 0;
        //traverse through the 2-array and setting everything to ' '
        gameBoard = new char[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                gameBoard[i][j] = ' ';
            }
        }
    }

    @Override
    public int getNumRows() {
        return numRows;
    }

    @Override
    public int getNumColumns() {
        return numColumns;
    }

    @Override
    public int getNumToWin() {
        return numToWin;
    }

    @Override
    public void placeMarker(BoardPosition marker, char player) {
        int row = marker.getRow();
        int column = marker.getColumn();

        gameBoard[row][column] = player;
        numMarkersPlaced++;
    }

    @Override
    public char whatsAtPos(BoardPosition pos) {
        return gameBoard[pos.getRow()][pos.getColumn()];
    }

    @Override
    public boolean checkForDraw() {
        if (numMarkersPlaced == numRows * numColumns) return true;
        return false;
    }

}
