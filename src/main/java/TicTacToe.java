public class TicTacToe {

    public static void main(String[] args) {

        TicTacToeLogicHandler.initializeArray();

        String currentPlayer = TicTacToePlayerSetup.setupPlayers();

        TicTacToeLogicHandler.gameLoop(currentPlayer);
    }
}
