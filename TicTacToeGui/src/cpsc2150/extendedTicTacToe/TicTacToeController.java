package cpsc2150.extendedTicTacToe;

import java.util.*;
/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private TicTacToeView screen;

    public static final int MAX_PLAYERS = 10;

    private Character[] players = {'X', 'O', 'A', 'M', 'K', 'T', 'N', 'Q', 'E', 'F'};;
    private int numPlayers;
    private int currPlayer;
    private boolean isOver;
    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model.
     *       numPlayers = np
     *       currPlayer = 0
     *       isOver = false
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;

        numPlayers = np;
        currPlayer = 0;
        isOver = false;
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        //start new game if there is a winner or tie
        if (isOver) {
            newGame();
            return;
        }

        BoardPosition pos = new BoardPosition(row, col);
        Character player = players[currPlayer];
        
        screen.setMessage("It is " + player + "\'s turn. ");
        //return if space is not available
        if (!curGame.checkSpace(pos)) {
            screen.setMessage("spot not available! ");
            return;
        }
        curGame.placeMarker(pos, player);
        screen.setMarker(row, col, player);
        //check for winner/tie
        if (curGame.checkForWinner(pos)) {
            screen.setMessage(player + " won! \npress any button to start a new game ");
            isOver = true;
        }
        else if (curGame.checkForDraw()) {
            screen.setMessage("It is a draw! \npress any button to start a new game ");
            isOver = true;
        }
        //only go to the next turn if the game is still going
        if (!isOver) {
            currPlayer = (currPlayer + 1) % numPlayers;
            screen.setMessage("It is " + players[currPlayer] + "\'s turn. ");
        }
    }

    private void newGame() {
        // You do not need to make any changes to this code.
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}