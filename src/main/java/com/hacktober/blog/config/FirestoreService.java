package com.hacktober.blog.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SuppressWarnings("deprecation")
@Configuration
public class FirestoreService {

	@Bean
	public FirebaseApp initFirebaseApp() {
		
		try {
			
			FileInputStream serviceAccount = new FileInputStream("firebaseServiceAccountKey.json");
			FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
			return FirebaseApp.initializeApp(options);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
