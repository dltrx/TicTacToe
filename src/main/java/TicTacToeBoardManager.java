import java.util.Scanner;

public class TicTacToeBoardManager {

    public static void printResult(String[][] ticTacToe, String player, boolean win) {
        printBoard(ticTacToe);
        if (win) {
            TicTacToeMessage.playerWins(player);
            TicTacToeMessage.currentLeaderboard();
        } else {
            TicTacToeMessage.draw();
            TicTacToeMessage.currentLeaderboard();
        }
    }

    public static void printBoard(String[][] ticTacToe) {
        TicTacToeMessage.gameTitle();
        for (String[] strings : ticTacToe) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void playerTurn(String[][] ticTacToe, String currentPlayer) {
        Scanner scanner = new Scanner(System.in);

        String currentPlayerNickname = currentPlayer.equals(TicTacToePlayerSetup.getPlayer1Symbol()) ?
                TicTacToePlayerSetup.getPlayer1Nickname() :
                TicTacToePlayerSetup.getPlayer2Nickname();

        System.out.println("* It's " + currentPlayerNickname + "'s turn! *");
        System.out.print("Enter coordinates (horizontal number and then vertical, for example: 2 3): ");
        int rowIndex = scanner.nextInt();
        int colIndex = scanner.nextInt();

        while (!TicTacToeLogicHandler.isValidIndex(rowIndex, colIndex, ticTacToe)) {
            System.out.print("Invalid indices! Please enter each index within 2-4 (min: 2 2, max: 4 4): ");
            rowIndex = scanner.nextInt();
            colIndex = scanner.nextInt();
        }
        ticTacToe[rowIndex][colIndex] = currentPlayer;
    }
}

