package firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirebaseService {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseService.class);

    private static FirebaseService instance = null;

    private final Firestore db;
    private final String currentGameId; // Hold the current game ID

    public FirebaseService() {
        this.db = FirebaseInitializer.getFirestore();
        this.currentGameId = UUID.randomUUID().toString();
    }

    public static FirebaseService getInstance() {
        if (instance == null) {
            instance = new FirebaseService();
        }
        return instance;
    }

    public void savePlayerData(String player1Nickname, int player1Score, String player2Nickname, int player2Score) {
        Map<String, Object> playerData = new HashMap<>();
        playerData.put("timestamp", Timestamp.now());
        playerData.put("player1Nickname", player1Nickname);
        playerData.put("player1Score", player1Score);
        playerData.put("player2Nickname", player2Nickname);
        playerData.put("player2Score", player2Score);

        DocumentReference docRef = db.collection("leaderboard").document(currentGameId);
        ApiFuture<WriteResult> result = docRef.set(playerData);

        try {
            System.out.println("Game saved with ID: " + currentGameId + ", at: " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error saving game data for game ID: {}", currentGameId, e);
        }
    }

    public void getGlobalLeaderboard() {
        try {
            // Query leaderboard collection to get all documents
            ApiFuture<QuerySnapshot> future = db.collection("leaderboard").get();

            // Get query snapshot synchronously
            QuerySnapshot querySnapshot = future.get();

            // Create a list to store player data
            List<PlayerData> playerDataList = new ArrayList<>();

            // Iterate over documents in the query snapshot
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                // Collect player 1 data

                PlayerData player1Data = new PlayerData(
                        document.getString("player1Nickname"),
                        document.getLong("player1Score"));

                // Collect player 2 data
                PlayerData player2Data = new PlayerData(
                        document.getString("player2Nickname"),
                        document.getLong("player2Score"));

                // Add player data to the list
                playerDataList.add(player1Data);
                playerDataList.add(player2Data);
            }

            // Sort player data list based on scores in descending order
            playerDataList.sort(Comparator.comparing(PlayerData::score).reversed());

            // Print the sorted leaderboard
            for (PlayerData playerData : playerDataList) {
                System.out.println("Player: " + playerData.nickname() + ", Score: " + playerData.score());
            }

        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error retrieving global leaderboard data", e);
        }
    }

    // Inner class to represent player data
    private record PlayerData(String nickname, long score) {}

    public void getGameHistory() {
        if (currentGameId == null) {
            System.out.println("No game data available. Start a new game.");
            return;
        }

        ApiFuture<QuerySnapshot> future = db.collection("leaderboard").get();
        try {
            QuerySnapshot querySnapshot = future.get();
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                System.out.println("Game ID: " + document.getId());

                Timestamp timestamp = document.getTimestamp("timestamp");
                assert timestamp != null;
                Date dateTime = timestamp.toDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String formattedDateTime = dateFormat.format(dateTime);
                System.out.println("Date&Time: " + formattedDateTime);

                System.out.println("Player: " + document.getString("player1Nickname") + ", Score: " + document.getLong("player1Score"));
                System.out.println("Player: " + document.getString("player2Nickname") + ", Score: " + document.getLong("player2Score"));
                System.out.println();
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error retrieving game history data", e);
        }
    }
}