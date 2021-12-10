package com.revature.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.revature.DAO.ReimbursementDAO;
import com.revature.model.Reimbursement;

public class ReimbursementServiceTest {

	private ReimbursementService sut;

	
	@Test
	public void getReimbursmentsTestPositive() throws SQLException {
		ReimbursementDAO mockReimbursementDao = mock(ReimbursementDAO.class); // fake object
		

		Reimbursement rembursement1 = new Reimbursement(10, 50.25, "2021-11-22 00:00:00", "2021-11-22 00:00:00", "Approved", "TRAVEL", "McCall", 1, 4);
		Reimbursement rembursement2 = new Reimbursement(25, 25.25, "2021-11-12 00:00:00", "2021-11-22 00:00:00", "Approved", "LODGING", "McCall", 1, 4);
		Reimbursement rembursement3 = new Reimbursement(100, 258.25, "2021-11-26 00:00:00", "2021-11-22 00:00:00", "Denied", "FOOD", "McCall", 3, 4);

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
		expectedReimbursements.add(new Reimbursement(10, 50.25, "2021-11-22 00:00:00", "2021-11-22 00:00:00", "Approved", "TRAVEL", "McCall", null, 1, 4));
		expectedReimbursements.add(new Reimbursement(25, 25.25, "2021-11-12 00:00:00", "2021-11-22 00:00:00", "Approved", "LODGING", "McCall", null, 1, 4));
		expectedReimbursements.add(new Reimbursement(100, 258.25, "2021-11-26 00:00:00", "2021-11-22 00:00:00", "Denied", "FOOD", "McCall", null, 3, 4));

		Assertions.assertEquals(expectedReimbursements, testReimbursement);
	}
	
	@Test
	public void getReimbursementByEmployeePositive() {
		ReimbursementDAO mockReimbursementDao = mock(ReimbursementDAO.class); // fake object
		
		ReimbursementService reimbursementService = new ReimbursementService(mockReimbursementDao);
		
		
	}
//	
//	@Test
//	public void editReimbursementByIdPositive {
//		
//	}
//	
//	@Test
//	public void getReimbursementsPositive {
//		
//	}
//	
//	@Test
//	public void addNewReimbursementPositive {
//		
//	}
//	
//	
//	@Test
//	public void getImageFromReimbursementByIdPositive {
//		
//	}
}
