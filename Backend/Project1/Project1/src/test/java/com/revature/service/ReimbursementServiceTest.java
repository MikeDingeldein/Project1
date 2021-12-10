package com.revature.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tika.Tika;
import org.apache.tika.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.io.IOUtil;

import com.revature.DAO.ReimbursementDAO;
import com.revature.exceptions.ReimbursementImageNotFoundException;
import com.revature.exceptions.ReimbursementNotFound;
import com.revature.exceptions.UnautherizedException;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class ReimbursementServiceTest {

	private ReimbursementService sut;

	@Test
	public void getAllReimbursmentsTestPositive() throws SQLException {
		ReimbursementDAO mockReimbursementDao = mock(ReimbursementDAO.class); // fake object

		Reimbursement rembursement1 = new Reimbursement(10, 50.25, "2021-11-22 00:00:00", "2021-11-22 00:00:00",
				"Approved", "TRAVEL", "McCall", 1, 4);
		Reimbursement rembursement2 = new Reimbursement(25, 25.25, "2021-11-12 00:00:00", "2021-11-22 00:00:00",
				"Approved", "LODGING", "McCall", 1, 4);
		Reimbursement rembursement3 = new Reimbursement(100, 258.25, "2021-11-26 00:00:00", "2021-11-22 00:00:00",
				"Denied", "FOOD", "McCall", 3, 4);

		List<Reimbursement> rembursementsFromDao = new ArrayList<>();
		rembursementsFromDao.add(rembursement1);
		rembursementsFromDao.add(rembursement2);
		rembursementsFromDao.add(rembursement3);

		when(mockReimbursementDao.getAllReimbursements()).thenReturn(rembursementsFromDao);

		ReimbursementService reimbursementService = new ReimbursementService(mockReimbursementDao);

		List<Reimbursement> testReimbursement = reimbursementService.getAllReimbursements();
// (id, amount, submitted, resolved, status, type, description, author, resolver);
		System.out.println(testReimbursement);

		List<Reimbursement> expectedReimbursements = new ArrayList<>();
		expectedReimbursements.add(new Reimbursement(10, 50.25, "2021-11-22 00:00:00", "2021-11-22 00:00:00",
				"Approved", "TRAVEL", "McCall", 1, 4));
		expectedReimbursements.add(new Reimbursement(25, 25.25, "2021-11-12 00:00:00", "2021-11-22 00:00:00",
				"Approved", "LODGING", "McCall", 1, 4));
		expectedReimbursements.add(new Reimbursement(100, 258.25, "2021-11-26 00:00:00", "2021-11-22 00:00:00",
				"Denied", "FOOD", "McCall", 3, 4));

		Assertions.assertEquals(expectedReimbursements, testReimbursement);
	}

	@Test
	public void getReimbursementByEmployeePositive() throws SQLException, ReimbursementNotFound {

		// Arrange	
		User mockUser = new User(10, "firstName", "lastName", "username", "password", "employee");
		
		ReimbursementDAO mockReimbursementDao = mock(ReimbursementDAO.class); // fake object

		Reimbursement rembursement1 = new Reimbursement(10, 50.25, "2021-11-22 00:00:00", "2021-11-22 00:00:00",
				"Approved", "TRAVEL", "McCall", mockUser.getId(), 4);
		Reimbursement rembursement2 = new Reimbursement(25, 25.25, "2021-11-12 00:00:00", "2021-11-22 00:00:00",
				"Approved", "LODGING", "McCall", mockUser.getId(), 4);
		Reimbursement rembursement3 = new Reimbursement(100, 258.25, "2021-11-26 00:00:00", "2021-11-22 00:00:00",
				"Denied", "FOOD", "McCall", mockUser.getId(), 4);

		List<Reimbursement> mockRembursementsFromDao = new ArrayList<>();
		mockRembursementsFromDao.add(rembursement1);
		mockRembursementsFromDao.add(rembursement2);
		mockRembursementsFromDao.add(rembursement3);

//		if (mockUser.getUserRole() == "Manager") {
			when(mockReimbursementDao.getReimbursementByEmployee(mockUser.getId())).thenReturn(mockRembursementsFromDao);
//		}
		

		ReimbursementService reimbursementService = new ReimbursementService(mockReimbursementDao); // Remember to add

		// Act

		List<Reimbursement> actual = reimbursementService.getReimbursements(mockUser);
//		System.out.println(actual);

		List<Reimbursement> expectedReimbursements = new ArrayList<>();
		expectedReimbursements.add(new Reimbursement(10, 50.25, "2021-11-22 00:00:00", "2021-11-22 00:00:00",
				"Approved", "TRAVEL", "McCall", 10, 4));
		expectedReimbursements.add(new Reimbursement(25, 25.25, "2021-11-12 00:00:00", "2021-11-22 00:00:00",
				"Approved", "LODGING", "McCall", 10, 4));
		expectedReimbursements.add(new Reimbursement(100, 258.25, "2021-11-26 00:00:00", "2021-11-22 00:00:00",
				"Denied", "FOOD", "McCall", 10, 4));

		// Assert

		Assertions.assertEquals(expectedReimbursements, actual);
	}
	
	@Test
	public void getReimbursementByManagerPositive() throws SQLException, ReimbursementNotFound {

		// Arrange	
		User mockUser = new User(10, "firstName", "lastName", "username", "password", "manager");
		
		ReimbursementDAO mockReimbursementDao = mock(ReimbursementDAO.class); // fake object

		Reimbursement rembursement1 = new Reimbursement(10, 50.25, "2021-11-22 00:00:00", "2021-11-22 00:00:00",
				"Approved", "TRAVEL", "McCall", mockUser.getId(), 4);
		Reimbursement rembursement2 = new Reimbursement(25, 25.25, "2021-11-12 00:00:00", "2021-11-22 00:00:00",
				"Approved", "LODGING", "McCall", mockUser.getId(), 4);
		Reimbursement rembursement3 = new Reimbursement(100, 258.25, "2021-11-26 00:00:00", "2021-11-22 00:00:00",
				"Denied", "FOOD", "McCall", mockUser.getId(), 4);

		List<Reimbursement> mockRembursementsFromDao = new ArrayList<>();
		mockRembursementsFromDao.add(rembursement1);
		mockRembursementsFromDao.add(rembursement2);
		mockRembursementsFromDao.add(rembursement3);

//		if (mockUser.getUserRole() == "Manager") {
			when(mockReimbursementDao.getAllReimbursements()).thenReturn(mockRembursementsFromDao);
//		}
		

		ReimbursementService reimbursementService = new ReimbursementService(mockReimbursementDao); // Remember to add

		// Act

		List<Reimbursement> actual = reimbursementService.getReimbursements(mockUser);
//		System.out.println(actual);

		List<Reimbursement> expectedReimbursements = new ArrayList<>();
		expectedReimbursements.add(new Reimbursement(10, 50.25, "2021-11-22 00:00:00", "2021-11-22 00:00:00",
				"Approved", "TRAVEL", "McCall", 10, 4));
		expectedReimbursements.add(new Reimbursement(25, 25.25, "2021-11-12 00:00:00", "2021-11-22 00:00:00",
				"Approved", "LODGING", "McCall", 10, 4));
		expectedReimbursements.add(new Reimbursement(100, 258.25, "2021-11-26 00:00:00", "2021-11-22 00:00:00",
				"Denied", "FOOD", "McCall", 10, 4));

		// Assert

		Assertions.assertEquals(expectedReimbursements, actual);
	}

	@Test
	public void editReimbursementByIdPositive() throws SQLException, ReimbursementNotFound {

		// Arrange
		ReimbursementDAO mockReimbursementDao = mock(ReimbursementDAO.class); // fake object

		when(mockReimbursementDao.updatedReimbursementById(10, "Approve", 5)).thenReturn(new Reimbursement(10, 50.25,
				"2021-11-22 00:00:00", "2021-11-22 00:00:00", "Pending", "TRAVEL", "McCall", 10, 0));

		ReimbursementService reimbursementService = new ReimbursementService(mockReimbursementDao);

		User mockUser = new User(5, "firstName", "lastName", "username", "password", "manager");

		// Act

		Reimbursement actual = reimbursementService.editReimbursementById("10", "Approve", mockUser);

		Reimbursement expectedReimbursement = new Reimbursement(10, 50.25, "2021-11-22 00:00:00", "2021-11-22 00:00:00",
				"Approve", "TRAVEL", "McCall", 10, 5);

		// Assert

		Assertions.assertEquals(expectedReimbursement, actual);

	}

	@Test
	public void addNewReimbursementPositive() throws SQLException, IOException {

		// Arrange
		ReimbursementDAO mockReimbursementDao = mock(ReimbursementDAO.class); // fake object

		InputStream mockReceipt = Test.class.getClass()
				.getResourceAsStream("C://Users/miked/OneDrive/Desktop/purchase-receipt-jpg.jpg");
		Tika tika = new Tika();
		String mimeType = tika.detect(mockReceipt);

		when(mockReimbursementDao.addNewReimbursement(10.99, "Unit Test", "OTHER", mockReceipt, 5))
				.thenReturn(new Reimbursement(10, 10.99, "2021-11-22 00:00:00", "2021-11-22 00:00:00", "Pending",
						"OTHER", "Unit Test", 10, 0));

		ReimbursementService reimbursementService = new ReimbursementService(mockReimbursementDao);

		User mockUser = new User(5, "firstName", "lastName", "username", "password", "Manager");

		// Act

		Reimbursement actual = reimbursementService.addNewReimbursement(mockUser, "10.99", "OTHER", "Unit Test",
				mockReceipt, mimeType);
		// User currentlyLoggedInUser, String reimAmount, String reimType, String
		// reimDescription, InputStream content, String mimeType

		Reimbursement expectedReimbursement = new Reimbursement(10, 10.99, "2021-11-22 00:00:00", "2021-11-22 00:00:00",
				"Pending", "OTHER", "Unit Test", 10, 0);

		// Assert

		Assertions.assertEquals(expectedReimbursement, actual);

	}

//	
//	
	@Test
	public void getImageFromReimbursementByIdPositive()
			throws SQLException, ReimbursementImageNotFoundException, UnautherizedException {

		// Arrange
		ReimbursementDAO mockReimbursementDao = mock(ReimbursementDAO.class); // fake object

		InputStream mockReceipt = IOUtils.toInputStream("fake receipt");

		when(mockReimbursementDao.getImageFromReimbursementById(1)).thenReturn(mockReceipt);

		ReimbursementService reimbursementService = new ReimbursementService(mockReimbursementDao);

		User mockUser = new User(5, "firstName", "lastName", "username", "password", "Employee");

		// Act

		InputStream actual = reimbursementService.getImageFromReimbursementById(mockUser, "1");

		// Assert

		Assertions.assertEquals(mockReceipt, actual);

	}
}
