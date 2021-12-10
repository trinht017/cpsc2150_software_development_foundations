package cpsc2150.extendedTicTacToe;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoardMem {

    private IGameBoard MakeAGameBoard(int numRows, int numColumns, int numToWin) {
        return new GameBoardMem(numRows, numColumns, numToWin);
    }

    private String toString(Character [][] gb, int numRows, int numColumns) {

        StringBuilder str = new StringBuilder();
        str.append("   ");
        for (int i = 0; i < numColumns; i++) {
            if (i < 10) str.append(" ");
            str.append(i);
            str.append("|");
        }
        str.append('\n');

        for (int i = 0; i < numRows; i++) {
            if (i < 10) str.append(" ");
            str.append(i);
            str.append('|');
            for (int j = 0; j < numColumns; j++) {
                str.append(gb[i][j]);
                str.append(" |");
            }
            str.append('\n');
        }
        return str.toString();
    }


    @Test
    public void testConstructor_routine() {
        int numRows = 5;
        int numColumns = 8;
        int numToWin = 5;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                expectedGrid[i][j] = ' ';
            }
        }

        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testConstructor_boundary_min() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                expectedGrid[i][j] = ' ';
            }
        }

        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testConstructor_boundary_max() {
        int numRows = 100;
        int numColumns = 100;
        int numToWin = 25;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                expectedGrid[i][j] = ' ';
            }
        }

        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckSpace_validBounds_spaceNotTaken() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 0), 'O');
        gb.placeMarker(new BoardPosition(0, 1), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 0) expectedGrid[i][j] = 'O';
                else if (i == 0 && j == 1) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.checkSpace(new BoardPosition(1, 1)));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());

    }

    @Test
    public void testCheckSpace_outOfBounds() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                expectedGrid[i][j] = ' ';
            }
        }

        assertFalse(gb.checkSpace(new BoardPosition(3, 3)));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckSpace_validBounds_spaceTaken() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 1), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 1) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertFalse(gb.checkSpace(new BoardPosition(1, 1)));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());

    }

    @Test
    public void testCheckHorizontalWin_win_lastMarkerMiddle() {
        int numRows = 5;
        int numColumns = 5;
        int numToWin = 4;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        for (int j = 0; j <= 3; j++) {
            gb.placeMarker(new BoardPosition(2, j), 'X');
        }
        for (int j = 0; j <= 2; j++) {
            gb.placeMarker(new BoardPosition(3, j), 'O');
        }
        gb.placeMarker(new BoardPosition(3, 3), 'X');
        gb.placeMarker(new BoardPosition(3, 4), 'O');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 2 && j == 0) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 3) expectedGrid[i][j] = 'X';
                else if (i == 3 && j == 0) expectedGrid[i][j] = 'O';
                else if (i == 3 && j == 1) expectedGrid[i][j] = 'O';
                else if (i == 3 && j == 2) expectedGrid[i][j] = 'O';
                else if (i == 3 && j == 3) expectedGrid[i][j] = 'X';
                else if (i == 3 && j == 4) expectedGrid[i][j] = 'O';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.checkHorizontalWin(new BoardPosition(2, 2), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckHorizontalWin_win_lastMarkerRightEnd() {
        int numRows = 4;
        int numColumns = 4;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        for (int j = 0; j <= 2; j++) {
            gb.placeMarker(new BoardPosition(1, j), 'X');
        }
        for (int j = 1; j <= 2; j++) {
            gb.placeMarker(new BoardPosition(0, j), 'O');
        }

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 && j == 1) expectedGrid[i][j] = 'O';
                else if (i == 0 && j == 2) expectedGrid[i][j] = 'O';
                else if (i == 1 && j == 0) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 2) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.checkHorizontalWin(new BoardPosition(1, 2), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckHorizontalWin_noWin_noLeft_noRight() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 1), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 1) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertFalse(gb.checkHorizontalWin(new BoardPosition(1, 1), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckHorizontalWin_noWin_noLeft_rightNotEnough() {
        int numRows = 5;
        int numColumns = 5;
        int numToWin = 4;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(2, 0), 'O');
        for (int j = 1; j <=3; j++) {
            gb.placeMarker(new BoardPosition(2, j), 'X');
        }
        gb.placeMarker(new BoardPosition(2, 4), 'O');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 2 && j == 0) expectedGrid[i][j] = 'O';
                else if (i == 2 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 3) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 4) expectedGrid[i][j] = 'O';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertFalse(gb.checkHorizontalWin(new BoardPosition(2, 1), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }





    @Test
    public void testCheckVerticalWin_win_lastMarkerMiddle() {
        int numRows = 5;
        int numColumns = 5;
        int numToWin = 4;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        for (int i = 0; i <= 3; i++) {
            gb.placeMarker(new BoardPosition(i, 2), 'X');
        }
        for (int i = 0; i <= 2; i++) {
            gb.placeMarker(new BoardPosition(i, 3), 'O');
        }
        gb.placeMarker(new BoardPosition(3, 3), 'X');
        gb.placeMarker(new BoardPosition(4, 3), 'O');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 3 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 0 && j == 3) expectedGrid[i][j] = 'O';
                else if (i == 1 && j == 3) expectedGrid[i][j] = 'O';
                else if (i == 2 && j == 3) expectedGrid[i][j] = 'O';
                else if (i == 3 && j == 3) expectedGrid[i][j] = 'X';
                else if (i == 4 && j == 3) expectedGrid[i][j] = 'O';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.checkVerticalWin(new BoardPosition(2, 2), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckVerticalWin_win_lastMarkerBottomEnd() {
        int numRows = 4;
        int numColumns = 4;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        for (int i = 0; i <= 2; i++) {
            gb.placeMarker(new BoardPosition(i, 1), 'X');
        }
        for (int i = 1; i <= 2; i++) {
            gb.placeMarker(new BoardPosition(i, 2), 'O');
        }

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 2) expectedGrid[i][j] = 'O';
                else if (i == 2 && j == 2) expectedGrid[i][j] = 'O';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.checkVerticalWin(new BoardPosition(2, 1), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckVerticalWin_noWin_noUp_noDown() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 1), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 1) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertFalse(gb.checkVerticalWin(new BoardPosition(1, 1), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckVerticalWin_noWin_noUp_downNotEnough() {
        int numRows = 5;
        int numColumns = 5;
        int numToWin = 4;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(0, 2), 'O');
        for (int i = 1; i <=3; i++) {
            gb.placeMarker(new BoardPosition(i, 2), 'X');
        }
        gb.placeMarker(new BoardPosition(4, 2), 'O');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 && j == 2) expectedGrid[i][j] = 'O';
                else if (i == 1 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 3 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 4 && j == 2) expectedGrid[i][j] = 'O';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertFalse(gb.checkVerticalWin(new BoardPosition(1, 2), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckDiagonalWin_win_backslash_lastMarkerTopLeftEnd() {
        int numRows = 5;
        int numColumns = 5;
        int numToWin = 4;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (i == j) gb.placeMarker(new BoardPosition(i, j), 'X');
            }
        }
        gb.placeMarker(new BoardPosition(0, 3), 'O');
        gb.placeMarker(new BoardPosition(0, 4), 'O');
        gb.placeMarker(new BoardPosition(1, 4), 'O');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 && j == 0) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 3 && j == 3) expectedGrid[i][j] = 'X';
                else if (i == 0 && j == 3) expectedGrid[i][j] = 'O';
                else if (i == 0 && j == 4) expectedGrid[i][j] = 'O';
                else if (i == 1 && j == 4) expectedGrid[i][j] = 'O';
                else expectedGrid[i][j] = ' ';
            }
        }
        assertTrue(gb.checkDiagonalWin(new BoardPosition(0, 0), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckDiagonalWin_win_backslash_lastMarkerBottomLeftEnd() {
        int numRows = 5;
        int numColumns = 5;
        int numToWin = 4;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (i == j) gb.placeMarker(new BoardPosition(i, j), 'X');
            }
        }
        gb.placeMarker(new BoardPosition(0, 3), 'O');
        gb.placeMarker(new BoardPosition(0, 4), 'O');
        gb.placeMarker(new BoardPosition(1, 4), 'O');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 && j == 0) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 3 && j == 3) expectedGrid[i][j] = 'X';
                else if (i == 0 && j == 3) expectedGrid[i][j] = 'O';
                else if (i == 0 && j == 4) expectedGrid[i][j] = 'O';
                else if (i == 1 && j == 4) expectedGrid[i][j] = 'O';
                else expectedGrid[i][j] = ' ';
            }
        }
        assertTrue(gb.checkDiagonalWin(new BoardPosition(3, 3), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckDiagonalWin_win_backslash_lastMarkerMiddle() {
        int numRows = 5;
        int numColumns = 5;
        int numToWin = 4;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (i == j) gb.placeMarker(new BoardPosition(i, j), 'X');
            }
        }
        gb.placeMarker(new BoardPosition(0, 3), 'O');
        gb.placeMarker(new BoardPosition(0, 4), 'O');
        gb.placeMarker(new BoardPosition(1, 4), 'O');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 && j == 0) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 3 && j == 3) expectedGrid[i][j] = 'X';
                else if (i == 0 && j == 3) expectedGrid[i][j] = 'O';
                else if (i == 0 && j == 4) expectedGrid[i][j] = 'O';
                else if (i == 1 && j == 4) expectedGrid[i][j] = 'O';
                else expectedGrid[i][j] = ' ';
            }
        }
        assertTrue(gb.checkDiagonalWin(new BoardPosition(1, 1), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckDiagonalWin_win_forwardSlash_lastMarkerBottomLeftEnd() {
        int numRows = 5;
        int numColumns = 5;
        int numToWin = 4;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(4, 0), 'X');
        gb.placeMarker(new BoardPosition(3, 1), 'X');
        gb.placeMarker(new BoardPosition(2, 2), 'X');
        gb.placeMarker(new BoardPosition(1, 3), 'X');
        gb.placeMarker(new BoardPosition(0, 0), 'O');
        gb.placeMarker(new BoardPosition(0, 1), 'O');
        gb.placeMarker(new BoardPosition(1, 1), 'O');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 4 && j == 0) expectedGrid[i][j] = 'X';
                else if (i == 3 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 3) expectedGrid[i][j] = 'X';
                else if (i == 0 && j == 0) expectedGrid[i][j] = 'O';
                else if (i == 0 && j == 1) expectedGrid[i][j] = 'O';
                else if (i == 1 && j == 1) expectedGrid[i][j] = 'O';
                else expectedGrid[i][j] = ' ';
            }
        }
        assertTrue(gb.checkDiagonalWin(new BoardPosition(4, 0), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckDiagonalWin_win_forwardSlash_lastMarkerTopRightEnd() {
        int numRows = 5;
        int numColumns = 5;
        int numToWin = 4;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(4, 0), 'X');
        gb.placeMarker(new BoardPosition(3, 1), 'X');
        gb.placeMarker(new BoardPosition(2, 2), 'X');
        gb.placeMarker(new BoardPosition(1, 3), 'X');
        gb.placeMarker(new BoardPosition(0, 0), 'O');
        gb.placeMarker(new BoardPosition(0, 1), 'O');
        gb.placeMarker(new BoardPosition(1, 1), 'O');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 4 && j == 0) expectedGrid[i][j] = 'X';
                else if (i == 3 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 3) expectedGrid[i][j] = 'X';
                else if (i == 0 && j == 0) expectedGrid[i][j] = 'O';
                else if (i == 0 && j == 1) expectedGrid[i][j] = 'O';
                else if (i == 1 && j == 1) expectedGrid[i][j] = 'O';
                else expectedGrid[i][j] = ' ';
            }
        }
        assertTrue(gb.checkDiagonalWin(new BoardPosition(1, 3), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckDiagonalWin_win_forwardSlash_lastMarkerMiddle() {
        int numRows = 5;
        int numColumns = 5;
        int numToWin = 4;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(4, 0), 'X');
        gb.placeMarker(new BoardPosition(3, 1), 'X');
        gb.placeMarker(new BoardPosition(2, 2), 'X');
        gb.placeMarker(new BoardPosition(1, 3), 'X');
        gb.placeMarker(new BoardPosition(0, 0), 'O');
        gb.placeMarker(new BoardPosition(0, 1), 'O');
        gb.placeMarker(new BoardPosition(1, 1), 'O');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 4 && j == 0) expectedGrid[i][j] = 'X';
                else if (i == 3 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 3) expectedGrid[i][j] = 'X';
                else if (i == 0 && j == 0) expectedGrid[i][j] = 'O';
                else if (i == 0 && j == 1) expectedGrid[i][j] = 'O';
                else if (i == 1 && j == 1) expectedGrid[i][j] = 'O';
                else expectedGrid[i][j] = ' ';
            }
        }
        assertTrue(gb.checkDiagonalWin(new BoardPosition(2, 2), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckDiagonalWin_noWin_check_both_diagonals() {
        int numRows = 5;
        int numColumns = 5;
        int numToWin = 4;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(0, 0), 'X');
        gb.placeMarker(new BoardPosition(1, 1), 'X');
        gb.placeMarker(new BoardPosition(2, 2), 'X');
        gb.placeMarker(new BoardPosition(1, 3), 'X');
        gb.placeMarker(new BoardPosition(0, 4), 'X');
        gb.placeMarker(new BoardPosition(3, 3), 'O');
        gb.placeMarker(new BoardPosition(3, 4), 'O');
        gb.placeMarker(new BoardPosition(4, 3), 'O');
        gb.placeMarker(new BoardPosition(4, 4), 'O');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 && j == 0) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 2) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 3) expectedGrid[i][j] = 'X';
                else if (i == 0 && j == 4) expectedGrid[i][j] = 'X';
                else if (i == 3 && j == 3) expectedGrid[i][j] = 'O';
                else if (i == 3 && j == 4) expectedGrid[i][j] = 'O';
                else if (i == 4 && j == 3) expectedGrid[i][j] = 'O';
                else if (i == 4 && j == 4) expectedGrid[i][j] = 'O';
                else expectedGrid[i][j] = ' ';
            }
        }
        assertFalse(gb.checkDiagonalWin(new BoardPosition(2, 2), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckForDraw_draw_fullGrid() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(0, 0), 'X');
        gb.placeMarker(new BoardPosition(0, 1), 'O');
        gb.placeMarker(new BoardPosition(0, 2), 'O');
        gb.placeMarker(new BoardPosition(1, 0), 'O');
        gb.placeMarker(new BoardPosition(1, 1), 'X');
        gb.placeMarker(new BoardPosition(1, 2), 'X');
        gb.placeMarker(new BoardPosition(2, 0), 'X');
        gb.placeMarker(new BoardPosition(2, 1), 'X');
        gb.placeMarker(new BoardPosition(2, 2), 'O');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        expectedGrid[0][0] = 'X';
        expectedGrid[0][1] = 'O';
        expectedGrid[0][2] = 'O';
        expectedGrid[1][0] = 'O';
        expectedGrid[1][1] = 'X';
        expectedGrid[1][2] = 'X';
        expectedGrid[2][0] = 'X';
        expectedGrid[2][1] = 'X';
        expectedGrid[2][2] = 'O';

        assertTrue(gb.checkForDraw());
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckForDraw_noDraw_boundary_oneMarker() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 1), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 1) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertFalse(gb.checkForDraw());
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckForDraw_noDraw_boundary_almostFull() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(0, 0), 'O');
        gb.placeMarker(new BoardPosition(0, 1), 'X');
        gb.placeMarker(new BoardPosition(0, 2), 'O');
        gb.placeMarker(new BoardPosition(1, 0), 'O');
        gb.placeMarker(new BoardPosition(1, 1), 'X');
        gb.placeMarker(new BoardPosition(1, 2), 'X');
        gb.placeMarker(new BoardPosition(2, 1), 'O');
        gb.placeMarker(new BoardPosition(2, 2), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        expectedGrid[0][0] = 'O';
        expectedGrid[0][1] = 'X';
        expectedGrid[0][2] = 'O';
        expectedGrid[1][0] = 'O';
        expectedGrid[1][1] = 'X';
        expectedGrid[1][2] = 'X';
        expectedGrid[2][0] = ' ';
        expectedGrid[2][1] = 'O';
        expectedGrid[2][2] = 'X';

        assertFalse(gb.checkForDraw());
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testCheckForDraw_noDraw_routine() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(0, 0), 'X');
        gb.placeMarker(new BoardPosition(0, 1), 'O');
        gb.placeMarker(new BoardPosition(1, 0), 'O');
        gb.placeMarker(new BoardPosition(2, 1), 'X');
        gb.placeMarker(new BoardPosition(2, 2), 'X');


        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 && j == 0) expectedGrid[i][j] = 'X';
                else if (i == 0 && j == 1) expectedGrid[i][j] = 'O';
                else if (i == 1 && j == 0) expectedGrid[i][j] = 'O';
                else if (i == 2 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 2 && j == 2) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertFalse(gb.checkForDraw());
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testWhatsAtPos_boundary_column_min() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 0), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 0) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.whatsAtPos(new BoardPosition(1, 0)) == 'X');
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testWhatsAtPos_boundary_row_min() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(0, 1), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 && j == 1) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.whatsAtPos(new BoardPosition(0, 1)) == 'X');
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testWhatsAtPos_boundary_column_max() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 2), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 2) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.whatsAtPos(new BoardPosition(1, 2)) == 'X');
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testWhatsAtPos_boundary_row_max() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(2, 1), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 2 && j == 1) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.whatsAtPos(new BoardPosition(2, 1)) == 'X');
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testWhatsAtPos_routine() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 1), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 1) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.whatsAtPos(new BoardPosition(1, 1)) == 'X');
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testIsPlayerAtPos_false_routine() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 1), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 1) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertFalse(gb.isPlayerAtPos(new BoardPosition(1, 1), 'O'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testIsPlayerAtPos_true_boundary_column_min() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 0), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 0) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.isPlayerAtPos(new BoardPosition(1, 0), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testIsPlayerAtPos_true_boundary_row_min() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(0, 1), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 && j == 1) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.isPlayerAtPos(new BoardPosition(0, 1), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testIsPlayerAtPos_true_boundary_column_max() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 2), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 2) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.isPlayerAtPos(new BoardPosition(1, 2), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testIsPlayerAtPos_true_boundary_row_max() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(2, 1), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 2 && j == 1) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertTrue(gb.isPlayerAtPos(new BoardPosition(2, 1), 'X'));
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testPlaceMarker_routine_new_player() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 0), 'O');
        gb.placeMarker(new BoardPosition(0, 1), 'X');
        gb.placeMarker(new BoardPosition(1, 1), 'A');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 0) expectedGrid[i][j] = 'O';
                else if (i == 0 && j == 1) expectedGrid[i][j] = 'X';
                else if (i == 1 && j == 1) expectedGrid[i][j] = 'A';
                else expectedGrid[i][j] = ' ';
            }
        }

        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testPlaceMarker_boundary_row_min() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(0, 1), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 0 && j == 1) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testPlaceMarker_boundary_column_max() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 2), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 2) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testPlaceMarker_boundary_row_max() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(2, 1), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 2 && j == 1) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

    @Test
    public void testPlaceMarker_boundary_column_min() {
        int numRows = 3;
        int numColumns = 3;
        int numToWin = 3;

        IGameBoard gb = MakeAGameBoard(numRows, numColumns, numToWin);
        gb.placeMarker(new BoardPosition(1, 0), 'X');

        Character [][] expectedGrid = new Character[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i == 1 && j == 0) expectedGrid[i][j] = 'X';
                else expectedGrid[i][j] = ' ';
            }
        }
        assertEquals(toString(expectedGrid, numRows, numColumns), gb.toString());
    }

}
