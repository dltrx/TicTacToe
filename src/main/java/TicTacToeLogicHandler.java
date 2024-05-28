import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeLogicHandler {
    private static final int MIN_ROW_INDEX = 2;
    private static final int MIN_COLUMN_INDEX = 2;
    private static String[][] ticTacToe = new String[5][5];
    private static boolean gameOver = false;
    private static int player1Score = 0;
    private static int player2Score = 0;

    protected static void initializeArray() {
        for (String[] strings : ticTacToe) {
            Arrays.fill(strings, " ");
        }

        ticTacToe = new String[][]{
                {"0", "1", "2", "3", "4"},
                {"1", "+", "—", "—", "—"},
                {"2", "|", " ", " ", " "},
                {"3", "|", " ", " ", " "},
                {"4", "|", " ", " ", " "}
        };
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static int getPlayer1Score() {
        return player1Score;
    }

    public static int getPlayer2Score() {
        return player2Score;
    }

    public static void setPlayer1Score(int player1Score) {
        TicTacToeLogicHandler.player1Score = player1Score;
    }

    public static void setPlayer2Score(int player2Score) {
        TicTacToeLogicHandler.player2Score = player2Score;
    }

    protected static boolean isValidIndex(int rowIndex, int colIndex, String[][] ticTacToe) {
        if (rowIndex >= MIN_ROW_INDEX && rowIndex < ticTacToe.length && colIndex >= MIN_COLUMN_INDEX && colIndex < ticTacToe[0].length) {
            return ticTacToe[rowIndex][colIndex].equals(" ");
        }
        return false;
    }

    private static boolean checkForWin(String[][] board, String player) {
        // Check rows
        for (int i = 2; i <= 4; i++) {
            if (board[i][2].equals(player) && board[i][3].equals(player) && board[i][4].equals(player)) {
                return true;
            }
        }

        // Check columns
        for (int j = 2; j <= 4; j++) {
            if (board[2][j].equals(player) && board[3][j].equals(player) && board[4][j].equals(player)) {
                return true;
            }
        }

        // Check diagonals
        if (board[2][2].equals(player) && board[3][3].equals(player) && board[4][4].equals(player)) {
            return true;
        }
        return board[4][2].equals(player) && board[3][3].equals(player) && board[2][4].equals(player);
    }

    protected static boolean checkForWinOrDraw(String[][] board, String player) {
        // Check for win
        if (checkForWin(board, player)) {
            return true;
        }

        // Check for draw
        for (int i = 2; i <= 4; i++) {
            for (int j = 2; j <= 4; j++) {
                if (board[i][j].equals(" ")) {
                    return false; // There are still empty cells, game is not a draw yet
                }
            }
        }
        // All cells are filled and no winner, it's a draw
        return true;
    }

    protected static void gameLoop(String currentPlayer) {
        for (int i = 2; i < ticTacToe.length; i++) {
            for (int j = 2; j < ticTacToe[i].length; j++) {

                while (!gameOver) {
                    TicTacToeBoardManager.printBoard(ticTacToe); // Print current board
                    TicTacToeBoardManager.playerTurn(ticTacToe, currentPlayer); // Player's turn

                    // Check for win or draw
                    if (checkForWinOrDraw(ticTacToe, currentPlayer)) {
                        gameOver = true; // Set gameOver to true to exit the loop
                        boolean win = checkForWin(ticTacToe, currentPlayer);

                        if (win) {
                            if (currentPlayer.equals(TicTacToePlayerSetup.getPlayer1Symbol())) {
                                player1Score += 100;
                            } else {
                                player2Score += 100;
                            }
                        } else {
                            player1Score += 50;
                            player2Score += 50;
                        }
                        TicTacToeBoardManager.printResult(ticTacToe, currentPlayer, win);
                        break;
                    }
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X"; // Switch player
                }
            }
        }
    }

    protected static void playAgainOrExit(String[] args) {
        boolean gameGoesOn = true;

        while (gameGoesOn) {
            Scanner scanner = new Scanner(System.in);
            TicTacToeMessage.playingAgainOrExit();
            String choice = scanner.nextLine().trim();

            while (!choice.equalsIgnoreCase("again") && !choice.equalsIgnoreCase("exit")) {
                System.out.print("Invalid choice! Please type 'again' or 'exit': ");
                choice = scanner.nextLine().trim();
            }

            if (choice.equalsIgnoreCase("exit")) {
                gameGoesOn = false;
            } else {
                gameOver = false;
                TicTacToe.main(args);
                break;
            }
        }
    }
}
