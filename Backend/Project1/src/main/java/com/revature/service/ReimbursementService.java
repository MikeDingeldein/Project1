package com.revature.service;

import java.io.InputStream;
import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.DAO.ReimbursementDAO;
import com.revature.DTO.AddOrUpdateReimbursementDTO;
import com.revature.exceptions.ReimbursementImageNotFoundException;
import com.revature.exceptions.ReimbursementNotFound;
import com.revature.exceptions.UnautherizedException;
import com.revature.model.Reimbursement;
import com.revature.model.User;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementService {
	
	private Logger logger = LoggerFactory.getLogger(ReimbursementService.class); //for logging tests

	private ReimbursementDAO reimbersementDAO;

	public ReimbursementService() {
		this.reimbersementDAO = new ReimbursementDAO();
	}
	
//	Unit testing constructors
	public ReimbursementService(ReimbursementDAO reimbersementDAO) {
		this.reimbersementDAO = reimbersementDAO;
	}

	public List<Reimbursement> getAllReimbursements() throws SQLException {
		logger.info("getAllReimbursements() invoked");
		
		List<Reimbursement> reimbursements = this.reimbersementDAO.getAllReimbursements();

		return reimbursements;
	}

//	public Reimbursement addNewReimbursement(AddOrUpdateReimbursementDTO dto)
//			throws SQLException, InvalidParameterException {
//		// TODO Auto-generated method stub
//
//		Set<String> validType = new HashSet<>();
//		validType.add("LODGING");
//		validType.add("TRAVEL");
//		validType.add("FOOD");
//		validType.add("OTHER");
//		if (!validType.contains(dto.getReimType())) {
//			throw new InvalidParameterException("You have entered an invalid reimbursement type");
//		}
//		if (dto.getReimAmount() < 0) {
//			throw new InvalidParameterException("Reimbursement amount cannot be less than 0");
//		}
////		dto.setReimAmount(dto.getReimAmount());
////		dto.setReimTime(dto.getReimTime().trim());
////		dto.setReimResolveTime(dto.getReimResolveTime().trim());
////		dto.setReimStatus(dto.getReimStatus().trim());
//		dto.setReimType(dto.getReimType().trim());
//		dto.setReimDescription(dto.getReimDescription().trim());
//
//		Reimbursement insertedReimbursement = this.reimbersementDAO.addNewReimbursement(dto);
//
//		return insertedReimbursement;
//	}

	public List<Reimbursement> getReimbursementByEmployee(String userId) throws SQLException, ReimbursementNotFound {
		logger.info("getReimbursementByEmployee() invoked");
		
		// TODO Auto-generated method stub
		List<Reimbursement> reimbursements;
		try {
			int id = Integer.parseInt(userId);
			Reimbursement reimbursementToEdit = this.reimbersementDAO.getReimbursementById(id);
			if (reimbursementToEdit == null) {
				throw new ReimbursementNotFound("Reimbursement with id of " + userId + " was not found");
			}
			reimbursements = this.reimbersementDAO.getReimbursementByEmployee(id);

			return reimbursements;

		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Employee id provided is not an int convertable value");
		}
	}

//	public List<Reimbursement> getReimbursementByStatus(String status) throws SQLException {
//		logger.info("getReimbursementByStatus() invoked");
//		// TODO Auto-generated method stub
//		List<Reimbursement> reimbursements;
//		Set<String> validType = new HashSet<>();
//		validType.add("Pending");
//		validType.add("Approved");
//		validType.add("Denied");
//		if (!validType.contains(status)) {
//			throw new InvalidParameterException("You have entered an invalid reimbursement status");
//		}
//
//		reimbursements = this.reimbersementDAO.getReimbursementByStatus(status);
//
//		return reimbursements;
//
//	}

	public Reimbursement editReimbursementById(String reimId, String changedStatus, User currentlyLoggedInUser)
			throws SQLException, ReimbursementNotFound {
		logger.info("editReimbursementById() invoked");
		// TODO Auto-generated method stub
		try {
			int id = Integer.parseInt(reimId);
			Reimbursement reimbursementToEdit = this.reimbersementDAO.getReimbursementById(id);
			if (reimbursementToEdit == null) {
				throw new ReimbursementNotFound("Reimbursement with id of " + reimId + " was not found");
			}
			AddOrUpdateReimbursementDTO dto = new AddOrUpdateReimbursementDTO(changedStatus, currentlyLoggedInUser);
			Reimbursement updatedReimbursement = this.reimbersementDAO.updatedReimbursementById(id, dto);
//			Set<String> validType = new HashSet<>();
//			validType.add("Pending");
//			validType.add("Approved");
//			validType.add("Deny");
//			if (!validType.contains(changedStatus)) {
//				throw new InvalidParameterException("You have entered an invalid reimbursement status");
//			}

			dto.setReimStatus(dto.getReimStatus().trim());

			return updatedReimbursement;
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Employee id provided is not an int convertable value");
		}

	}

	public List<Reimbursement> getReimbursements(User currentlyLoggedInUser) throws SQLException {
		logger.info("getReimbursements() invoked");
		List<Reimbursement> reimbursements = null;

		if (currentlyLoggedInUser.getUserRole().equals("manager")) {
			reimbursements = this.reimbersementDAO.getAllReimbursements();
		} else if (currentlyLoggedInUser.getUserRole().equals("employee")) {
			reimbursements = this.reimbersementDAO.getReimbursementByEmployee(currentlyLoggedInUser.getId());
		}
		return reimbursements;
	}

	public Reimbursement addNewReimbursement(User currentlyLoggedInUser, String reimAmount, String reimType,
			String reimDescription, InputStream content, String mimeType) throws SQLException {
		logger.info("addNewReimbursement() invoked");
		// TODO Auto-generated method stub
		Set<String> validType = new HashSet<>();
		validType.add("LODGING");
		validType.add("TRAVEL");
		validType.add("FOOD");
		validType.add("OTHER");
		if (!validType.contains(reimType)) {
			throw new InvalidParameterException("You have entered an invalid reimbursement type");
		}

		double amount = Double.parseDouble(reimAmount);
		if (amount < 0) {
			throw new InvalidParameterException("Reimbursement amount cannot be less than 0");
		}

		Set<String> validFileType = new HashSet<>();
		validFileType.add("image/jpeg");
		validFileType.add("image/png");
		validFileType.add("image/gif");

		if (!validFileType.contains(mimeType)) {
			throw new InvalidParameterException("Receipt must be a PNG, GIF, or JPEG file type");
		}

		int employeeId = currentlyLoggedInUser.getId();

		Reimbursement insertedReimbursement = this.reimbersementDAO.addNewReimbursement(amount, 
				reimDescription, reimType, content, employeeId);

		return insertedReimbursement;
	}

	public InputStream getImageFromReimbursementById(User currentlyLoggedInUser, String reimbursementId)
			throws SQLException, ReimbursementImageNotFoundException, UnautherizedException {
		logger.info("getImageFromReimbursementById() invoked");
		try {
			int id = Integer.parseInt(reimbursementId);
			if (currentlyLoggedInUser.getUserRole().equals("employee")) {
				int employeeId = currentlyLoggedInUser.getId();
				List<Reimbursement> reimbursementsThatBelongToEmployee = this.reimbersementDAO
						.getReimbursementByEmployee(employeeId);
				
				Set<Integer> reimbursementIdsEncountered = new HashSet<>();
				for (Reimbursement r : reimbursementsThatBelongToEmployee) {
					reimbursementIdsEncountered.add(r.getReimId());

				}
				if (!reimbursementIdsEncountered.contains(id)) {
					throw new UnautherizedException("You cannot receipts that do not belong to yourself");
				}

			}
			InputStream image = this.reimbersementDAO.getImageFromReimbursementById(id);
			
			if (image == null) {
				throw new ReimbursementImageNotFoundException("Receipt was not found for reimbursement id " + id);

			}
			
			return image;
			
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Reimbursement id supplied must be an int");
		}
	}
}
