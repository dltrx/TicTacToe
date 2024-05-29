package firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseInitializer.class);

    private static Firestore firestore;

    public static void initializeFirebase() {
        if (FirebaseApp.getApps().isEmpty()) { // Check if Firebase has already been initialized
            try {
                FileInputStream serviceAccount = new FileInputStream("src/main/resources/key/tictactoegloballeaderboard-firebase-adminsdk-d6sno-cc8b825d72.json");
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
                firestore = FirestoreClient.getFirestore();
            } catch (IOException e) {
                logger.error("An error occurred while performing file operations: {}", e.getMessage());
            }
        } else {
            firestore = FirestoreClient.getFirestore(); // Get the existing Firestore instance
        }
    }

    public static Firestore getFirestore() {
        if (firestore == null) {
            initializeFirebase();
        }
        return firestore;
    }
}