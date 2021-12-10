package com.revature.service;

import com.revature.exceptions.UnautherizedException;
import com.revature.model.User;

public class AutherizationService {

	public void autherizationEmployeeAndManager(User user) throws UnautherizedException {
		if (user == null || !(user.getUserRole().equals("employee") || user.getUserRole().equals("manager"))) {
			throw new UnautherizedException("You must be logged in and have a role of employee or manager for this resource");
		}
	}
	
	public void autherizeManager(User user) throws UnautherizedException {
		if (user == null || !user.getUserRole().equals("manager")) {
			throw new UnautherizedException("You must be logged in and have a role of manager for this resource");
		}
	}
	
	public void autherizeEmployee(User user) throws UnautherizedException {
		if (user == null || !user.getUserRole().equals("employee")) {
			throw new UnautherizedException("You must be logged in and have a role of enployee for this resourse");
		}
	}
	
}
