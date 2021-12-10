package com.revature.service;

import java.sql.SQLException;

import javax.security.auth.login.FailedLoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.DAO.LoginDAO;
import com.revature.DAO.ReimbursementDAO;
import com.revature.model.User;

public class LoginService {

	private Logger logger = LoggerFactory.getLogger(LoginService.class); //for logging tests
	
	private LoginDAO loginDAO;
	
	public LoginService() {
		this.loginDAO = new LoginDAO();
		
		}
	
//	Unit testing constructors
	public LoginService(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	
	public User getUserByUsernameAndPassword(String username, String password) throws SQLException, FailedLoginException {
		logger.info("getUserByUsernameAndPassword() invoked");
		
		User user = this.loginDAO.getUserByUsernameAndPassword(username, password);
		
		if (user == null) {
			throw new FailedLoginException("Incorrect username and/or password");
		}
		return user;
		
	}
}
