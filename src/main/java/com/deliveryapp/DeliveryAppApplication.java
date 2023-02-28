package com.deliveryapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * This is the main class for running the Spring Boot Applications
 * 
 * @author mohd.shadab
 */
@SpringBootApplication
public class DeliveryAppApplication {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		SpringApplication.run(DeliveryAppApplication.class, args);
		/**
		 * Instantiating the Google firebase instance and connecting it with the
		 * database
		 */
		try {
			ClassLoader classLoader = DeliveryAppApplication.class.getClassLoader();
			/** googleFirebaseAuthFile : Reading from application.properties */
			File authFile = new File(Objects
					.requireNonNull(classLoader.getResource(PropertiesExtractor.getProperty("googleFirebaseAuthFile")))
					.getFile());
			FileInputStream serviceAccount = new FileInputStream(authFile.getAbsolutePath());
			FirebaseOptions options;
			/** firebaseDatabaseURL : Reading from application.properties */
			options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl(PropertiesExtractor.getProperty("firebaseDatabaseURL")).build();
			FirebaseApp.initializeApp(options);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
