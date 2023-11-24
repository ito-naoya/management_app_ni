package model;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Employee;
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

		//パスワードのハッシュ化
		String hashedPassword = HashGenerator.generateHash(password);

		ArrayList<Object> loginParamList = new ArrayList<Object>() {
			{
				//新規社員の名前をリストに追加
				add(employeeName);

				//新規社員のパスワードをリストに追加
				add(hashedPassword);
			}
		};

		try (Connection conn = DbConnection.getConnection();
				ResultSet result = GeneralDao.executeQuery(conn, SELECT_ACCOUNT_SQL, loginParamList);) {

			//アカウント情報をNULLで初期化
			Employee employee = null;

			while (result.next()) {

				employee = new Employee(

						//ログインしたい社員のIDを取得
						result.getInt("accountId")

				);

			}

			HttpSession session = req.getSession();

			if (employee != null)

				//ログインする社員のIDをセッションで管理
				session.setAttribute("employee", employee);

		}

	}

	public static void logout(HttpServletRequest req) throws IOException {

		HttpSession session = req.getSession();

		session.invalidate();

	}

}
