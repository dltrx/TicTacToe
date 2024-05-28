import java.util.Arrays;

public class TicTacToeMessage {

    protected static void welcome() {
        System.out.println();
        printDynamicBoarder("Welcome to Tic Tac Toe!");
        System.out.println();
        System.out.println("QUICK GUIDE:");
        System.out.println("* To place the symbol, you have to input the appropriate coordinates from 2 to 4! (firstly horizontal, then vertical) *");
        System.out.println("* If the player wins - he gets 100 points, draw - 50 points for each! *");
    }

    protected static void ready() {
        System.out.println();
        System.out.println("Are you ready? Press \"Enter\" to START!");
    }

    protected static void askToChangeNicknames() {
        System.out.println();
        System.out.print("Do you want to change nicknames? ");
    }

    protected static void resetScore() {
        System.out.println();
        System.out.print("Do you want to reset the score for both players? ");
    }

    protected static void randomize() {
        System.out.println();
        System.out.println("Press \"Enter\" to randomize the symbols!");
    }

    protected static void willPlayAs() {
        printDynamicBoarder("Player #1 \"" + TicTacToePlayerSetup.getPlayer1Nickname() + "\" will play as \"" + TicTacToePlayerSetup.getPlayer1Symbol() + "\"",
                "Player #2 \"" + TicTacToePlayerSetup.getPlayer2Nickname() + "\" will play as \"" + TicTacToePlayerSetup.getPlayer2Symbol() + "\"");
        System.out.println();
    }

    protected static void playerWins(String player) {
        String winnerNickname = player.equals(TicTacToePlayerSetup.getPlayer1Symbol()) ?
                TicTacToePlayerSetup.getPlayer1Nickname() :
                TicTacToePlayerSetup.getPlayer2Nickname();

        printDynamicBoarder("Player \"" + winnerNickname + "\" wins!");
    }

    protected static void draw() {
        printDynamicBoarder("It's a draw!");
    }

    protected static void gameTitle() {
        if (TicTacToeLogicHandler.isGameOver()) {
            System.out.println();
            printDynamicBoarder("GAME OVER!");
        } else {
            System.out.println();
            printDynamicBoarder("Current Game Board");
        }
    }

    protected static void currentLeaderboard() {
        System.out.println();
        printDynamicBoarder("CURRENT LEADERBOARD",
                "Player #1 " + TicTacToePlayerSetup.getPlayer1Nickname() + " score: " + TicTacToeLogicHandler.getPlayer1Score(),
                "Player #2 " + TicTacToePlayerSetup.getPlayer2Nickname() + " score: " + TicTacToeLogicHandler.getPlayer2Score());
    }

    protected static void playingAgainOrExit() {
        System.out.println();
        System.out.print("Do you want to play again? (Type 'again' to play again, 'exit' to quit): ");
    }

    private static void printDynamicBoarder(String... lines) {
        // Calculate the maximum length among all lines
        int maxLength = Arrays.stream(lines)
                .mapToInt(String::length)
                .max()
                .orElse(0);

        // Create the border
        String border = "*".repeat(maxLength + 4); // 4 = 2 for spaces before and after message + 2 for initial and final *

        // Print the border and each line with appropriate padding
        System.out.println(border);
        for (String line : lines) {
            System.out.println("* " + padRight(line, maxLength) + " *");
        }
        System.out.println(border);
    }

    private static String padRight(String line, int maxLength) {
        // Pad the line with spaces on the right to make its length equal to maxLength
        return line + " ".repeat(maxLength - line.length());
    }
}