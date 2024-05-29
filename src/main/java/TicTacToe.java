import firebase.FirebaseInitializer;

public class TicTacToe {

    public static void main(String[] args) {

        FirebaseInitializer.initializeFirebase();

        TicTacToeLogicHandler.initializeArray();

        String currentPlayer = TicTacToePlayerSetup.setupPlayers();

        TicTacToeLogicHandler.gameLoop(currentPlayer);

        TicTacToeLogicHandler.playAgainOrExit(args);
    }
}