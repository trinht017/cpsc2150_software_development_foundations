package cpsc2150.extendedTicTacToe;

import java.util.*;

public class GameBoardMem extends AbsGameBoard implements IGameBoard{

    /**
     * GameBoardMem class contains the game board as a map implementation
     * @invariant MIN_ROW <= numRows <= MAX_ROW
     * @invariant MIN_COLUMN <= numColumns <= MAX_COLUMN
     * @invariant MIN_NUM_TO_WIN <= numToWin <= MAX_NUM_TO_WIN
     * @invariant numToWin <= numRows
     * @invariant numToWin <= numColumns
     * @invariant 0 <= NUM_MARKERS_PLACED <= NUM_OF_ROWS * NUM_OF_COLUMNS
     *            AND NUM_MARKERS_PLACED is the number of BoardPosition as values in gameBoard map
     *
     * correspondence numRows = NUM_OF_ROWS
     * correspondence numColumns = NUM_OF_COLUMNS
     * correspondence numToWin = NUM_TO_WIN
     */
    private Map<Character, List<BoardPosition>> gameBoard;
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
     * @post gameBoard is an empty HashMap
     *       this.numRows = numRows
     *       this.numColumns = numColumns
     *       this.numToWin = numToWin
     *       numMarkersPlaced = 0
     */
    public GameBoardMem(int numRows, int numColumns, int numToWin) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numToWin = numToWin;
        numMarkersPlaced = 0;
        gameBoard = new HashMap<>();
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
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (!gameBoard.containsKey(player)) return false;
        return gameBoard.get(player).contains(pos);
    }

    @Override
    public void placeMarker(BoardPosition marker, char player) {
        //add new player if not already in map, add marker to corresponding player
        if (!gameBoard.containsKey(player)) {
            gameBoard.put(player, new ArrayList<BoardPosition>());
        }
        gameBoard.get(player).add(marker);
        numMarkersPlaced++;
    }

    @Override
    public char whatsAtPos(BoardPosition pos) {
        //traversing through map and checking if pos is in map
        for (Map.Entry<Character, List<BoardPosition>> m : gameBoard.entrySet()) {
            if (m.getValue().contains(pos)) return m.getKey();
        }
        return ' ';
    }

    @Override
    public boolean checkForDraw() {
        if (numMarkersPlaced == numRows * numColumns) return true;
        return false;
    }
}
