package com.deliveryapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import com.deliveryapp.constants.Constants;

/**
 * This class loads the properties from the application.properties
 * 
 * @author mohd.shadab
 */
public class PropertiesExtractor {
	
	/** The variable properties */
	private static Properties properties;
	
	/** Static block : for loading the properties at the run time */
	static {
		properties = new Properties();
		URL url = PropertiesExtractor.class.getClassLoader().getResource(Constants.CONSTANT_APPLICATION_PROPERTIES);
		try {
			properties.load(new FileInputStream(url.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function to fetch the value for the particular key from the application.properties
	 * 
	 * @param key 
	 * @return value
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * The private Constructor
	 */
	private PropertiesExtractor() {

	}
}
