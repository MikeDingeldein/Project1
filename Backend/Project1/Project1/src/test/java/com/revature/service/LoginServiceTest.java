package com.revature.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import javax.security.auth.login.FailedLoginException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.revature.DAO.LoginDAO;
import com.revature.model.User;

public class LoginServiceTest {

	private LoginService sut;

	@Test
	public void loginEmployeeTestPositive() throws SQLException, FailedLoginException {

		// Arrange
		LoginDAO mockLoginDao = mock(LoginDAO.class); // fake object
		
		User testUser = new User(100, "TestPerson", "TestPersonSon", "TestPerson", "TestPassword", "employee");
		
		when(mockLoginDao.getUserByUsernameAndPassword("TestPerson", "TestPassword")).thenReturn(testUser);
		
		LoginService loginService = new LoginService(mockLoginDao);
		
		User returnedUser = loginService.getUserByUsernameAndPassword("TestPerson", "TestPassword");
		
		User expectedUser = new User(100, "TestPerson", "TestPersonSon", "TestPerson", "TestPassword", "employee");
		
		Assertions.assertEquals(expectedUser, returnedUser);
		
	}
	
	@Test
	public void loginManagerTestPositive() throws SQLException, FailedLoginException {

		// Arrange
		LoginDAO mockLoginDao = mock(LoginDAO.class); // fake object
		
		User testUser = new User(100, "TestPerson", "TestPersonSon", "TestPerson", "TestPassword", "manager");
		
		when(mockLoginDao.getUserByUsernameAndPassword("TestPerson", "TestPassword")).thenReturn(testUser);
		
		LoginService loginService = new LoginService(mockLoginDao);
		
		User returnedUser = loginService.getUserByUsernameAndPassword("TestPerson", "TestPassword");
		
		User expectedUser = new User(100, "TestPerson", "TestPersonSon", "TestPerson", "TestPassword", "manager");
		
		Assertions.assertEquals(expectedUser, returnedUser);
		
	}
	
	@Test 
	public void loginTestIncorrectUsernameAndPasswordNegative() throws SQLException, FailedLoginException {

		// Arrange
		LoginDAO mockLoginDao = mock(LoginDAO.class); // fake object
		
		LoginService loginService = new LoginService(mockLoginDao);
		
		Assertions.assertThrows(FailedLoginException.class, () -> {
			loginService.getUserByUsernameAndPassword("vhjbvh", "vhjgbvj");
		});
		
	}
	
	@Test 
	public void loginTestIncorrectUsernameNegative() throws SQLException, FailedLoginException {

		// Arrange
		LoginDAO mockLoginDao = mock(LoginDAO.class); // fake object
		
		LoginService loginService = new LoginService(mockLoginDao);
		
		Assertions.assertThrows(FailedLoginException.class, () -> {
			loginService.getUserByUsernameAndPassword("1", "password");
		});
		
	}
	
	@Test 
	public void loginTestIncorrectPasswordNegative() throws SQLException, FailedLoginException {

		// Arrange
		LoginDAO mockLoginDao = mock(LoginDAO.class); // fake object
		
		LoginService loginService = new LoginService(mockLoginDao);
		
		Assertions.assertThrows(FailedLoginException.class, () -> {
			loginService.getUserByUsernameAndPassword("MikeDingeldein", "vhjgbvj");
		});
		
	}
	
}