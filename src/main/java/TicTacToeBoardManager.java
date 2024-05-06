import java.util.Scanner;

public class TicTacToeBoardManager {

    public static void printBoard(String[][] ticTacToe) {
        TicTacToeMessage.gameTitle();
        for (String[] strings : ticTacToe) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
        System.out.println(" ");
    }

    public static void printResult(String[][] ticTacToe, String player, boolean win) {
        printBoard(ticTacToe);
        if (win) {
            TicTacToeMessage.playerWins(player);
        } else {
            TicTacToeMessage.draw();
        }
    }

    public static void playerTurn(String[][] ticTacToe, String currentPlayer) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Player " + currentPlayer + "'s turn!");
        System.out.print("Enter coordinates (e.g., 2 3): ");
        int rowIndex = scanner.nextInt();
        int colIndex = scanner.nextInt();

        while (!TicTacToeLogicHandler.isValidIndex(rowIndex, colIndex, ticTacToe)) {
            System.out.print("Invalid indices! Please enter indices within 2-4 (min: 2 2, max: 4 4): ");
            rowIndex = scanner.nextInt();
            colIndex = scanner.nextInt();
        }
        ticTacToe[rowIndex][colIndex] = currentPlayer;
    }
}
