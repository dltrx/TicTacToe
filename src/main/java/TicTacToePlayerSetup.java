import java.util.Random;
import java.util.Scanner;

public class TicTacToePlayerSetup {
    private static final Random random = new Random();
    private static String player1Symbol;
    private static String player2Symbol;
    private static String player1Nickname;
    private static String player2Nickname;

    public static String getPlayer1Symbol() {
        return player1Symbol;
    }

    public static String getPlayer2Symbol() {
        return player2Symbol;
    }

    public static String getPlayer1Nickname() {
        return player1Nickname;
    }

    public static String getPlayer2Nickname() {
        return player2Nickname;
    }

    public static String setupPlayers() {
        Scanner scanner = new Scanner(System.in);

        if (TicTacToeLogicHandler.getPlayer1Score() >= 50 || TicTacToeLogicHandler.getPlayer2Score() >= 50 ) {

            TicTacToeMessage.askToChangeNicknames();

            String changeNick = scanner.nextLine().trim();
            while (!changeNick.equalsIgnoreCase("yes") && !changeNick.equalsIgnoreCase("no")) {
                System.out.print("Invalid choice! Please type 'yes' or 'no': ");
                changeNick = scanner.nextLine().trim();
            }
            if (changeNick.equalsIgnoreCase("yes")) {

                promptForNewNicknames();
                TicTacToeMessage.resetScore();

                String score = scanner.nextLine().trim();
                while (!score.equalsIgnoreCase("yes") && !score.equalsIgnoreCase("no")) {
                    System.out.print("Invalid choice! Please type 'yes' or 'no': ");
                    score = scanner.nextLine().trim();
                }
                if (score.equalsIgnoreCase("yes")) {
                    TicTacToeLogicHandler.setPlayer1Score(0);
                    TicTacToeLogicHandler.setPlayer2Score(0);
                }
            }
        } else {
            TicTacToeMessage.welcome();

            //Prompt to start the game
            TicTacToeMessage.ready();
            scanner.nextLine();

            // Prompt for nicknames
            promptForNicknames();
        }

        // Prompt for randomization
        TicTacToeMessage.randomize();
        scanner.nextLine();

        // Randomly determine which player is playing X and which is playing O
        int randomPlayer = random.nextInt(2) + 1; // Generates 1 or 2 randomly
        player1Symbol = (randomPlayer == 1) ? "X" : "O";
        player2Symbol = (randomPlayer == 1) ? "O" : "X";

        TicTacToeMessage.willPlayAs();

        return whoStarts();
    }

    public static void promptForNicknames() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the nickname for Player #1: ");
        player1Nickname = scanner.nextLine().trim();

        System.out.print("Enter the nickname for Player #2: ");
        player2Nickname = scanner.nextLine().trim();
    }

    public static void promptForNewNicknames() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.print("Enter a new nickname for Player #1: ");
        player1Nickname = scanner.nextLine().trim();

        System.out.print("Enter a new nickname for Player #2: ");
        player2Nickname = scanner.nextLine().trim();
    }

    public static String whoStarts() {
        int startingPlayerNumber;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Which player starts? Enter 1 or 2: ");

            // Check if the next token is an integer
            if (scanner.hasNextInt()) {
                startingPlayerNumber = scanner.nextInt();

                // Check if the input is within valid range
                if (startingPlayerNumber == 1 || startingPlayerNumber == 2) {
                    break; // Exit loop if valid input is provided
                } else {
                    System.out.println("Invalid input! Please enter 1 or 2.");
                }
            } else {
                // Clear the invalid input from the scanner
                scanner.next();
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
        return (startingPlayerNumber == 1) ? player1Symbol : player2Symbol;
    }
}