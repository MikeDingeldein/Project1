package com.revature.DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.model.User;
import com.revature.utility.JDBCUtility;

public class LoginDAO {
	
	private Logger logger = LoggerFactory.getLogger(LoginDAO.class); //for logging tests

	public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
		logger.info("getUserByUsernameAndPassword() invoked in DAO");
//		String passwordToHash = "password";
		String generatedPassword = null;

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(password.getBytes());

			byte[] bytes = md.digest();

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));

			}

			generatedPassword = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT * FROM ers_users WHERE ers_username = ? AND ers_password = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, generatedPassword);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("user_id");
				String firstName = rs.getString("user_first_name");
				String lastName = rs.getString("user_last_name");
				String user = rs.getString("ers_username"); // Can't reuse the variable
				String pass = null;
				String userRole = rs.getString("user_role");

				return new User(id, firstName, lastName, user, pass, userRole);

			} else {
				return null;
			}
		}
	}
}
