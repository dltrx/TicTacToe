import java.util.Random;
import java.util.Scanner;

class TicTacToePlayerSetup {
    private static final Random random = new Random();
    private static String player1Symbol;
    private static String player2Symbol;

    public static String setupPlayers() {
        Scanner scanner = new Scanner(System.in);

        TicTacToeMessage.welcome();

        //Prompt to start the game
        TicTacToeMessage.ready();
        scanner.nextLine();

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

    public static String getPlayer1Symbol() {
        return player1Symbol;
    }

    public static String getPlayer2Symbol() {
        return player2Symbol;
    }

    public static String whoStarts() {
        int startingPlayerNumber;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Which player will start? Enter 1 or 2: ");

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



