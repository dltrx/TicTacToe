import java.util.Arrays;

public class TicTacToeLogicHandler {
    private static String[][] ticTacToe = new String[5][5];
    private static boolean gameOver = false;

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

    protected static boolean isValidIndex(int rowIndex, int colIndex, String[][] ticTacToe) {
        if (rowIndex >= 2 && rowIndex < ticTacToe.length && colIndex >= 2 && colIndex < ticTacToe[0].length) {
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
                        TicTacToeBoardManager.printResult(ticTacToe, currentPlayer, checkForWin(ticTacToe, currentPlayer));
                        break;
                    }
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X"; // Switch player
                }
            }
        }
    }
}
