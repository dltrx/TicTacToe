import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    static String[][] ticTacToe = new String[5][5];

    public TicTacToe() {
        initializeArray();
    }

    private void initializeArray() {
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

    private static boolean isValidIndex(int rowIndex, int colIndex, String[][] ticTacToe) {
        if (rowIndex >= 2 && rowIndex < ticTacToe.length && colIndex >= 2 && colIndex < ticTacToe[0].length) {
            return ticTacToe[rowIndex][colIndex].equals(" ");
        }
        return false;
    }

    private static boolean isValidStart(String input) {
        // Add your validation criteria here
        return input.equalsIgnoreCase("start");
    }

    private static boolean isValidLetter(String input) {
        // Add your validation criteria here
        return input.matches("[XxOo]+");
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
        if (board[4][2].equals(player) && board[3][3].equals(player) && board[2][4].equals(player)) {
            return true;
        }
        return false;
    }

    public static void printTicTacToe() {
        for (String[] strings : ticTacToe) {
            // Iterate through each element in the current array
            for (String string : strings) {
                // Print the current element
                System.out.print(string + " ");
            }
            // Move to the next line after printing elements of each array
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
        boolean gameOver = false;

        for (int i = 2; i < ticTacToe.length; i++) {
            for (int j = 2; j < ticTacToe[i].length; j++) {
                Scanner scanner = new Scanner(System.in);

                System.out.println(" ");
                System.out.println("—————————————————————————————————————————");
                System.out.println("Current game:");
                printTicTacToe();
                System.out.println(" ");
                System.out.print("Enter X or O: ");
                String letter = scanner.next();

                while (!isValidLetter(letter)) {
                    System.out.print("Invalid! Please enter letter X or O: ");
                    letter = scanner.next();
                }

                System.out.print("Enter coordinates (e.g., 2 3): ");
                int rowIndex = scanner.nextInt();
                int colIndex = scanner.nextInt();

                while (!isValidIndex(rowIndex, colIndex, ticTacToe)) {
                    System.out.print("Invalid indices! Please enter indices within 2-4 (min: 2 2, max: 4 4): ");
                    rowIndex = scanner.nextInt();
                    colIndex = scanner.nextInt();
                }
                ticTacToe[rowIndex][colIndex] = letter;

                if (checkForWin(ticTacToe, letter)) {
                    System.out.println(" ");
                    printTicTacToe();
                    System.out.println(" ");
                    System.out.println("+——————————————————+");
                    System.out.println("| Player \"" + letter + "\" wins! |");
                    System.out.println("+——————————————————+");
                    gameOver = true;
                    break; // Exit the loop to end the game
                }
            }
            if (gameOver) {
                break; // Exit the outer loop if the game is over
            }
        }
        if (!gameOver) {
            printTicTacToe(); // Print final board if no winner
        }
    }
}
