package model;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Account;
import dao.GeneralDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class EmployeeCertification {

	private static final String SELECT_ACCOUNT_SQL = "SELECT "
			+ "* "
			+ "FROM "
			+ "accountTable "
			+ "WHERE "
			+ "employeeName = ? "
			+ "AND "
			+ "password = ?";

	public static void login(HttpServletRequest req, String employeeName, String password)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {

		String hashedPassword = HashGenerator.generateHash(password);

		ArrayList<Object> paramList = new ArrayList<Object>() {
			{
				add(employeeName);
				add(hashedPassword);
			}
		};

		try (Connection conn = DbConnection.getConnection();
				ResultSet result = GeneralDao.executeQuery(conn, SELECT_ACCOUNT_SQL, paramList);) {

			Account account = null;

			while (result.next()) {

				account = new Account(

						result.getInt("accountId")

				);

			}

			HttpSession session = req.getSession();

			if (account != null)

				session.setAttribute("account", account);

		}

	}

	public static void logout(HttpServletRequest req) throws IOException {

		HttpSession session = req.getSession();

		session.invalidate();

	}

}
