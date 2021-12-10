package com.revature.DAO;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.DTO.AddOrUpdateReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.utility.JDBCUtility;

public class ReimbursementDAO {
	
	private Logger logger = LoggerFactory.getLogger(ReimbursementDAO.class); //for logging tests

	public List<Reimbursement> getAllReimbursements() throws SQLException {
		logger.info("getAllReimbursements() invoked in DAO");
		List<Reimbursement> listOfReimbursements = new ArrayList<>();

		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_status, reimb_type, reimb_description, reimb_author, reimb_resolver FROM ers_reimbursement ORDER BY reimb_id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				String submitted = rs.getString("reimb_submitted");
				String resolved = rs.getString("reimb_resolved");
				String status = rs.getString("reimb_status");
				String type = rs.getString("reimb_type");
				String description = rs.getString("reimb_description");
//				InputStream file = (InputStream) rs.getBlob("reimb_receipt"); // is blob correct
				int author = rs.getInt("reimb_author");
				int resolver = rs.getInt("reimb_resolver");

				Reimbursement r = new Reimbursement(id, amount, submitted, resolved, status, type, description, 
						author, resolver);
				listOfReimbursements.add(r);

			}

		}

		return listOfReimbursements;
	}

//	public Reimbursement addNewReimbursement(AddOrUpdateReimbursementDTO reimbursement) throws SQLException {
//		// TODO Auto-generated method stub
//		try (Connection con = JDBCUtility.getConnection()) {
//			String sql = "INSERT INTO ers_reimbursement "
//					+ " (reimb_amount, reimb_submitted, reimb_status, reimb_type, reimb_description, reimb_receipt, reimb_author) "
//					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
////			String sql = "INSERT INTO project1.ers_reimbursement "
////					+ " (reimb_amount, reimb_submitted, reimb_resolved, reimb_status, reimb_type, reimb_description, reimb_receipt, reimb_author, reimb_resolver) "
////					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
////			Timestamp ts = new Timestamp(System.currentTimeMillis());
//
//			pstmt.setDouble(1, reimbursement.getReimAmount());
////			pstmt.setString(2, reimbursement.getReimTime());  //TimeStamp.valueOf()
//			pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
////			pstmt.setString(3, reimbursement.getReimResolveTime());
//			pstmt.setString(3, "Pending");
//			pstmt.setString(4, reimbursement.getReimType());
//			pstmt.setString(5, reimbursement.getReimDescription());
//			pstmt.setBinaryStream(6, reimbursement.getReimReceipt());
//			pstmt.setInt(7, reimbursement.getReimAuthor());
////			pstmt.setInt(9, reimbursement.getReimApprover());
//
//			int numberOfRecordsInserted = pstmt.executeUpdate();
//			if (numberOfRecordsInserted != 1) {
//				throw new SQLException("Adding a new reimbursement was unsuccessful");
//			}
//
//			ResultSet rs = pstmt.getGeneratedKeys();
//			rs.next();
//			int automaticallyGeneratedId = rs.getInt(1);
//
//			return new Reimbursement(automaticallyGeneratedId, reimbursement.getReimAmount(),
//					reimbursement.getReimTime(), reimbursement.getReimResolveTime(), reimbursement.getReimStatus(),
//					reimbursement.getReimType(), reimbursement.getReimDescription(), reimbursement.getReimReceipt(),
//					reimbursement.getReimAuthor(), reimbursement.getReimApprover());
//		}
//
//	}

	public List<Reimbursement> getReimbursementByEmployee(int employeeId) throws SQLException {
		logger.info("getReimbursementByEmployee() invoked in DAO");
		// TODO Auto-generated method stub
		List<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_status, reimb_type, reimb_description, reimb_author, reimb_resolver"
					+ "  FROM ers_reimbursement WHERE reimb_author = ? ORDER BY reimb_id desc";
//			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
//				int id = rs.getInt("reimb_id");
//				double amount = rs.getDouble("reimb_amount");
//				String submitted = rs.getString("reimb_submitted");
//				String resolved = rs.getString("reimb_resolved");
//				String status = rs.getString("reimb_status");
//				String type = rs.getString("reimb_type");
//				String description = rs.getString("reimb_description");
//				Blob file = rs.getBlob("reimb_receipt"); // is blob correct
//				int author = rs.getInt("reimb_author");
//				int resolver = rs.getInt("reimb_resolver");

				Reimbursement r = new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"),
						rs.getString("reimb_submitted"), rs.getString("reimb_resolved"), rs.getString("reimb_status"),
						rs.getString("reimb_type"), rs.getString("reimb_description"), rs.getInt("reimb_author"),
//						(InputStream) rs.getBlob("reimb_receipt"), rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"));

				reimbursements.add(r);

			}
			return reimbursements;
		}

	}

//	public List<Reimbursement> getReimbursementByStatus(String status) throws SQLException {
//		logger.info("getReimbursementByStatus() invoked in DAO");
//		// TODO Auto-generated method stub
//		List<Reimbursement> reimbursements = new ArrayList<>();
//		try (Connection con = JDBCUtility.getConnection()) {
//			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status = ? ORDER BY reimb_id desc";
//			PreparedStatement pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, status);
//			ResultSet rs = pstmt.executeQuery();
//
//			while (rs.next()) {
////				int id = rs.getInt("reimb_id");
////				double amount = rs.getDouble("reimb_amount");
////				String submitted = rs.getString("reimb_submitted");
////				String resolved = rs.getString("reimb_resolved");
////				String status = rs.getString("reimb_status");
////				String type = rs.getString("reimb_type");
////				String description = rs.getString("reimb_description");
////				Blob file = rs.getBlob("reimb_receipt"); // is blob correct
////				int author = rs.getInt("reimb_author");
////				int resolver = rs.getInt("reimb_resolver");
//
//				Reimbursement r = new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"),
//						rs.getString("reimb_submitted"), rs.getString("reimb_resolved"), rs.getString("reimb_status"),
//						rs.getString("reimb_type"), rs.getString("reimb_description"),
//						(InputStream) rs.getBlob("reimb_receipt"), rs.getInt("reimb_author"),
//						rs.getInt("reimb_resolver"));
//
//				reimbursements.add(r);
//
//			}
//		}
//
//		return reimbursements;
//	}

	public Reimbursement getReimbursementById(int id) throws SQLException {
		logger.info("getReimbursementById() invoked in DAO");
		// TODO Auto-generated method stub
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_status, reimb_type, reimb_description, reimb_author, reimb_resolver"
					+ " from ers_reimbursement WHERE reimb_id = ? ORDER BY reimb_id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"),
						rs.getString("reimb_submitted"), rs.getString("reimb_resolved"), rs.getString("reimb_status"),
						rs.getString("reimb_type"), rs.getString("reimb_description"),
						 rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"));

			} else {
				return null;
			}
		}

	}

	public Reimbursement updatedReimbursementById(int id, String reimStatus, int reimApprover) throws SQLException { // this
																														// could
																														// be
																														// void
																														// ?
		// TODO Auto-generated method stub
		logger.info("updatedReimbursementById() invoked in DAO");
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "UPDATE ers_reimbursement SET reimb_resolved = ?, reimb_status = ?, reimb_resolver = ? "
					+ "WHERE reimb_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setString(2, reimStatus);
			pstmt.setInt(3, reimApprover);
			pstmt.setInt(4, id);

			int numberOfRecordsUpdated = pstmt.executeUpdate();

			if (numberOfRecordsUpdated != 1) {
				throw new SQLException("Unable to Update");
			}
		}

		return new Reimbursement(id, reimStatus, reimApprover);
	}

	public Reimbursement updatedReimbursementById(int id, AddOrUpdateReimbursementDTO reimbursement)
			throws SQLException { // this could be void ?
		// TODO Auto-generated method stub
		logger.info("updatedReimbursementById() invoked in DAO");
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "UPDATE ers_reimbursement SET reimb_resolved = ?, reimb_status = ?, reimb_resolver = ? "
					+ "WHERE reimb_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setString(2, reimbursement.getReimStatus());
			pstmt.setInt(3, reimbursement.getReimApprover());
			pstmt.setInt(4, id);

			int numberOfRecordsUpdated = pstmt.executeUpdate();

			if (numberOfRecordsUpdated != 1) {
				throw new SQLException("Unable to Update");
			}
		}

		return new Reimbursement(id, reimbursement.getReimStatus(), reimbursement.getReimApprover());
	}

	public Reimbursement addNewReimbursement(double amount, String reimDescription, String reimType,
			InputStream content, int employeeId) throws SQLException {
		// TODO Auto-generated method stub
		logger.info("addNewReimbursement() invoked in DAO");
		try (Connection con = JDBCUtility.getConnection()) {

			con.setAutoCommit(false); // Turn off autocommit for large files

			String sql = "INSERT INTO ers_reimbursement "
					+ " (reimb_amount, reimb_submitted, reimb_status, reimb_type, reimb_description, reimb_receipt, reimb_author) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstmt.setDouble(1, amount);

			pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

			pstmt.setString(3, "Pending");
			pstmt.setString(4, reimType);
			pstmt.setString(5, reimDescription);
			pstmt.setBinaryStream(6, content);
			pstmt.setInt(7, employeeId);

			int numberOfRecordsInserted = pstmt.executeUpdate();
			if (numberOfRecordsInserted != 1) {
				throw new SQLException("Adding a new reimbursement was unsuccessful");
			}

			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			int automaticallyGeneratedId = rs.getInt(1);

			con.commit(); // commit

			return new Reimbursement(automaticallyGeneratedId, amount, reimType, reimDescription, employeeId);
		}
	}

	public InputStream getImageFromReimbursementById(int id) throws SQLException {
		// TODO Auto-generated method stub
		logger.info("getImageFromReimbursementById() invoked in DAO");
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT reimb_receipt FROM ers_reimbursement WHERE reimb_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				InputStream image = rs.getBinaryStream("reimb_receipt");
				return image;
			}
			return null;
		}

	}

}
