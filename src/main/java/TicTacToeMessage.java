public class TicTacToeMessage {

    protected static void welcome() {
        System.out.println();
        System.out.println("+—————————————————————————+");
        System.out.println("| Welcome to Tic Tac Toe! |");
        System.out.println("+—————————————————————————+");
        System.out.println();
    }

    protected static void ready() {
        System.out.println("Are you ready? Press \"Enter\" to START!");
    }

    protected static void randomize() {
        System.out.println("Press \"Enter\" to randomize the symbols!");
    }

    protected static void willPlayAs() {
        System.out.println("+———————————————————————————+");
        System.out.println("| Player 1 will play as \"" + TicTacToePlayerSetup.getPlayer1Symbol() + "\" |");
        System.out.println("| Player 2 will play as \"" + TicTacToePlayerSetup.getPlayer2Symbol() + "\" |");
        System.out.println("+———————————————————————————+");
        System.out.println();
    }

    protected static void playerWins(String player) {
        System.out.println("+——————————————————+");
        System.out.println("| Player \"" + player + "\" wins! |");
        System.out.println("+——————————————————+");
    }

    protected static void draw() {
        System.out.println("+——————————————+");
        System.out.println("| It's a draw! |");
        System.out.println("+——————————————+");
    }

    protected static void gameTitle() {
        if (TicTacToeLogicHandler.isGameOver()) {
            System.out.println();
            System.out.println("—————————————————————————————————————————");
            System.out.println("GAME OVER!");
        } else {
            System.out.println();
            System.out.println("—————————————————————————————————————————");
            System.out.println("Current game:");
        }
    }
}
