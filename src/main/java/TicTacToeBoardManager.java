import java.util.Scanner;

public class TicTacToeBoardManager {

    public static void printResult(String[][] ticTacToe, String player, boolean win) {
        printBoard(ticTacToe);
        if (win) {
            TicTacToeMessage.playerWins(player);
        } else {
            TicTacToeMessage.draw();
        }
        TicTacToeMessage.currentLeaderboard();

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("Choose one of the options below:");
            System.out.println("1. Check Global Leaderboard");
            System.out.println("2. Check Game History");
            System.out.println("3. Skip this part");
            System.out.print("Enter your choice (1-3): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number between 1 and 3.");
                scanner.next(); // Clear the buffer after invalid input
            }
            switch (choice) {
                case 1:
                    TicTacToeMessage.globalLeaderboard();
                    break;
                case 2:
                    TicTacToeMessage.gameHistory();
                    break;
            }
        } while (choice != 3);
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