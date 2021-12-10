package com.revature.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.ExceptionMapperCotroller;
import com.revature.controller.LoginController;
import com.revature.controller.ReimbursementController;
import com.revature.service.LoginService;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Logger logger =LoggerFactory.getLogger(Main.class);
		
		Javalin app = Javalin.create((config) -> {
			//tell backend to trust the localhost sites.
			config.enableCorsForOrigin("http://localhost:5500", "http://127.0.0.1.5500");
		});
			
			// Instantiate Controllers
			ReimbursementController controller1 = new ReimbursementController();
			controller1.registerEndPoints(app);
			
			LoginController controller2 = new LoginController();
			controller2.registerEndPoints(app);
			
			ExceptionMapperCotroller controller3 = new ExceptionMapperCotroller();
			controller3.mapException(app);
			
			
			
			app.start(); // Start the server on port 8080!
		
	}

}
