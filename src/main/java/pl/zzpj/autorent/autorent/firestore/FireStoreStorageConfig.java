package pl.zzpj.autorent.autorent.firestore;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FireStoreStorageConfig {
    @Bean
    public FirebaseApp getFirebaseApp(@Value("${firebase.credential.path}") String credentialPath) throws IOException {

        var serviceAccount = new FileInputStream(credentialPath);
        var credentials = GoogleCredentials.fromStream(serviceAccount);
        var options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .setStorageBucket("autorent-a82d9.appspot.com")
                .build();
        return FirebaseApp.initializeApp(options);
    }
}
