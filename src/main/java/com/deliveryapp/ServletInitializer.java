package com.deliveryapp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * This class binds Servlet, Filter and ServletContextInitializer beans from the
 * application context to the server.
 * 
 * @author mohd.shadab
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DeliveryAppApplication.class);
	}

}
