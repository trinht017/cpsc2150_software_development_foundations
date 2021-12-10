//Trinh Tran

package cpsc2150.extendedTicTacToe;
import java.util.*;

public class GameScreen {

    /**
     * GameScreen class controls flow of the game. Prints to terminal and gets user input.
     */

    public static final int MIN_NUM_PLAYER = 2;
    public static final int MAX_NUM_PLAYER = 10;
    public static final char fastGame = 'F';
    public static final char memGame = 'M';

    private static IGameBoard gameBoard;

    /**
     * Prompt user for the number of rows for gameBoard and return it
     * @return int number of rows for gameBoard
     * @pre scanner is of System.in and it is initialized in main
     * @post getNumRowsFromUser = [user input for number of rows]
     *       MIN_ROW <= getNumRowsFromUser <= MAX_ROW
     *       gameBoard = #gameBoard
     */
    private static int getNumRowsFromUser(Scanner scanner) {
        String userInput;
        boolean isValid = false;
        int numRows;

        //keep prompting user for input until they enter a valid number of rows
        do {
            isValid = true;
            System.out.println("How many rows?");
            userInput = scanner.nextLine();
            numRows = Integer.parseInt(userInput);
            if (numRows < IGameBoard.MIN_ROW || numRows > IGameBoard.MAX_ROW) {
                System.out.println("Rows must be between " + IGameBoard.MIN_ROW + " and " + IGameBoard.MAX_ROW);
                isValid = false;
            }
        } while (!isValid);

        return numRows;
    }

    /**
     * Prompt user for the number of columns for gameBoard and return it
     * @return int number of columns for gameBoard
     * @pre scanner is of System.in and it is initialized in main
     * @post getNumColumnsFromUser = [user input for number of columns]
     *       MIN_COLUMN <= getNumColumnsFromUser <= MAX_COLUMN
     *       gameBoard = #gameBoard
     */
    private static int getNumColumnsFromUser(Scanner scanner) {
        String userInput;
        boolean isValid = false;
        int numColumns;

        //keep prompting user for input until they enter a valid number of columns
        do {
            isValid = true;
            System.out.println("How many columns?");
            userInput = scanner.nextLine();
            numColumns = Integer.parseInt(userInput);
            if (numColumns < IGameBoard.MIN_COLUMN || numColumns > IGameBoard.MAX_COLUMN) {
                System.out.println("Columns must be between " + IGameBoard.MIN_COLUMN + " and " + IGameBoard.MAX_COLUMN);
                isValid = false;
            }
        } while (!isValid);

        return numColumns;
    }

    /**
     * Prompt user for the number of tokens in a row to win and return it
     * @param numRows int number of rows in boardGame
     * @param numColumns int number of columns in boardGame
     * @return int number of tokens in a row to win the game
     * @pre MIN_ROW <= numRows <= MAX_ROW AND MIN_COLUMN <= numColumns <= MAX_COLUMN
     *      numRows is the number of rows of gameBoard
     *      numColumns is the number of columns of gameBoard
     *      scanner is of System.in and it is initialized in main
     * @post getNumToWinFromUser = [user input for the number of tokens to win]
     *       MIN_NUM_TO_WIN <= getNumToWinFromUser <= MAX_NUM_TO_WIN
     *       getNumToWinFromUser <= numRows
     *       getNumToWinFromUser <= numColumns
     *       gameBoard = #gameBoard
     */
    private static int getNumToWinFromUser(int numRows, int numColumns, Scanner scanner) {
        String userInput;
        boolean isValid = false;
        int numToWin;

        //keep prompting user for input until they enter a valid number of tokens in a row to win
        do {
            isValid = true;
            System.out.println("How many in a row to win?");
            userInput = scanner.nextLine();
            numToWin = Integer.parseInt(userInput);
            if (numToWin < IGameBoard.MIN_NUM_TO_WIN || numToWin >IGameBoard.MAX_NUM_TO_WIN) {
                System.out.println("The number in a row to win must be between " + IGameBoard.MIN_NUM_TO_WIN +
                                                                         " and " + IGameBoard.MAX_NUM_TO_WIN);
                isValid = false;
            }
            //number of tokens in a row to win cannot be greater than the number of rows in gameBoard
            else if (numToWin > numRows) {
                System.out.println("must be " + numRows + " or fewer");
                isValid = false;
            }
            //number of tokens in a row to win cannot be greater than the number columns in gameBoard
            else if (numToWin > numColumns) {
                System.out.println("must be " + numColumns + " or fewer");
                isValid = false;
            }
        } while (!isValid);

        return numToWin;
    }

    /**
     * prompt the user if they want fast game or a memory efficient game and return the corresponding character
     * @return char F if fast game or M if memory efficient game
     * @pre scanner is of System.in and it is initialized in main
     * @post getGameBoardInfo = F iff user wants fast game
     *                          M iff user wants memory efficient game
     *       gameBoard = #gameBoard
     */
    private static char getGameBoardInfo(Scanner scanner) {
        String userInput;
        boolean isValid = false;
        char typeOfBoard;

        //keep prompting user for input until they enter valid character
        do {
            isValid = true;
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
            userInput = scanner.nextLine();
            userInput = userInput.toUpperCase();
            typeOfBoard = userInput.charAt(0);
            if (typeOfBoard != fastGame && typeOfBoard != memGame) {
                System.out.println("Please enter " + fastGame + " or " + memGame);
                isValid = false;
            }
        } while (!isValid);

        return typeOfBoard;
    }

    /**
     * create the game board
     * @pre scanner is of System.in and it is initialized in main
     * @post gameBoard = GameBoard iff user input fastGame (F)
     *                 = GameBoardMem iff user input memGame (M)
     */
    private static void createGameBoard(Scanner scanner) {
        int numRows = getNumRowsFromUser(scanner);
        int numColumns = getNumColumnsFromUser(scanner);
        int numToWin = getNumToWinFromUser(numRows, numColumns, scanner);
        int typeOfBoard = getGameBoardInfo(scanner);

        if (typeOfBoard == fastGame) {
            gameBoard = new GameBoard(numRows, numColumns, numToWin);
        }
        else {
            gameBoard = new GameBoardMem(numRows, numColumns, numToWin);
        }
    }

    /**
     * prompt the user for the number of players and return it
     * @return int number of players
     * @pre scanner is of System.in and it is initialized in main
     * @post getNumPlayerFromUser = [user input for number of players]
     *       MIN_NUM_PLAYERS <= getNumPlayerFromUser <= MAX_NUM_PLAYER
     *       gameBoard = #gameBoard
     */
    private static int getNumPlayerFromUser(Scanner scanner) {
        int numPlayers;
        String userInput;
        boolean isValid = false;

        //keep prompting user for input until they enter a valid number of players
        do {
            isValid = true;
            System.out.println("How many players?");
            userInput = scanner.nextLine();
            numPlayers = Integer.parseInt(userInput);
            if (numPlayers < MIN_NUM_PLAYER) {
                System.out.println("Must be at least " + MIN_NUM_PLAYER + " players");
                isValid = false;
            }
            else if (numPlayers > MAX_NUM_PLAYER){
                System.out.println("Must be " + MAX_NUM_PLAYER + " players or fewer");
                isValid = false;
            }
        } while (!isValid);

        return numPlayers;
    }

    /**
     * prompt user for players and store them into a List of characters and return the List
     * @return List<Character> list of players
     * @pre scanner is of System.in and it is initialized in main
     * @Post storePlayers = [List of Characters that represent players]
     *       no duplicates in the List, players must be distinct
     *       gameBoard = #gameBoard
     */
    private static List<Character> storePlayers(Scanner scanner) {
        int numPlayers = getNumPlayerFromUser(scanner);
        String userInput;
        boolean isValid = false;

        List<Character> players = new ArrayList<>();
        Character player;
        //adding the players into List from player 1 to player n
        for (int i = 1; i <= numPlayers; i++) {
            //keep prompting user for input until they enter a valid player
            do {
                isValid = true;
                System.out.println("Enter the character to represent player " + i);
                userInput = scanner.nextLine();
                userInput = userInput.toUpperCase();
                player = userInput.charAt(0);
                //if player is not already in the list, add the player
                if (!players.contains(player)) {
                    players.add(player);
                } else {
                    isValid = false;
                    System.out.println(player + " is already taken as a player token!");
                }
            } while (!isValid);
        }

        return players;
    }

    /**
     * prompt user for the row and column that they want to place their marker on gameBoard and return the BoardPosition
     * @param player Character that is picking the row and column
     * @return BoardPosition the position that the player picked
     * @pre player is the current player and is a valid player
     *      scanner is of System.in and it is initialized in main
     * @post getPlayerPos = [user input for position on gameBoard]
     *                    = position is available
     *       gameBoard = #gameBoard
     */
    private static BoardPosition getPlayerPos(Character player, Scanner scanner) {
        String userInput;
        boolean isValid = true;
        int userRow, userColumn;
        BoardPosition userPos;
        //keep prompting the user to enter a position until they enter a valid position
        do {
            System.out.println(gameBoard.toString());
            if (!isValid) System.out.println("That space is unavailable, please pick again");
            isValid = true;
            System.out.println("Player " + player + " Please enter your ROW" );
            userInput = scanner.nextLine();
            userRow = Integer.parseInt(userInput);
            System.out.println("Player " + player + " Please enter your COLUMN");
            userInput = scanner.nextLine();
            userColumn = Integer.parseInt(userInput);
            userPos = new BoardPosition(userRow, userColumn);
            if (!gameBoard.checkSpace(userPos)) {
                isValid = false;
            }
        } while (!isValid);

        return userPos;
    }

    /**
     * prompt the user if they want to play again and returns true or false
     * @return boolean true iff player wants to play again, false otherwise
     * @pre scanner is of System.in and it is initialized in main
     * @post userInputPlayAgain = true iff user wants to play again, false otherwise
     *       gameBoard = #gameBoard;
     */
    private static boolean userInputPlayAgain(Scanner scanner) {
        String userInput;

        System.out.println("would you like to play again? Y/N");
        userInput = scanner.nextLine();
        userInput = userInput.toUpperCase();

        if (userInput.equals("Y")) return true;
        return false;
    }

    /**
     * controls the game flow, player takes turn to place marker, and a check for winner or draw for each turn
     * @param players List<Character> a List of players that are playing the game
     * @return boolean true iff the game resulted in a win or draw, false otherwise
     * @pre MIN_NUM_PLAYER <= length of List players <= MAX_NUM_PLAYER
     *      players is a list of players that is playing the game
     *      scanner is of System.in and it is initialized in main
     * @post playGame = true iff there is a winner or a draw, false otherwise
     *       gameBoard = #gameBoard with markers being placed as the game is being played
     */
    private static void playGame(List<Character> players, Scanner scanner) {
        char player;
        BoardPosition userPos;

        for (int i = 0; i < players.size(); i++) {
            player = players.get(i);
            userPos = getPlayerPos(player, scanner);
            //place marker, check for winner and check for draw
            gameBoard.placeMarker(userPos, player);
            if (gameBoard.checkForWinner(userPos)) {
                System.out.println("Player " + player + " Wins!");
                return;
            } else if (gameBoard.checkForDraw()) {
                System.out.println("It's a draw!");
                return;
            }
            //set turn back to player 1
            if (i == players.size() - 1) i = -1;
        }
    }

    /**
     * controls the flow of game
     * @param args command-line arguments
     * @pre NONE
     * @post we don't write bugs. Game runs, it is playable, and it does not crash and burn
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean userWantsToPlay = true;
        //prompt user for players and store it in list
        List<Character> players = storePlayers(scanner);
        //prompt user for the numRows, numColumns, numToWin, and typeOfBoard and create it
        createGameBoard(scanner);

        while (userWantsToPlay) {
            //prompt user for pos, place marker at pos, check for winner, check for draw until the game is over
            playGame(players, scanner);
            System.out.println(gameBoard.toString());
            //ask user if they want to play again
            if (userInputPlayAgain(scanner)) {
                players = storePlayers(scanner);
                createGameBoard(scanner);
            } else {
                userWantsToPlay = false;
            }
        }
    }


}
