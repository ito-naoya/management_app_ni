
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.GeneralDao;

public class EmployeeDelete {

	private static final String DELETE_EMPLOYEE_SQL = "DELETE "
			+ "FROM "
			+ "employeeTable "
			+ "WHERE "
			+ "account_Id = ?";

	private static final String DELETE_ACCOUNT_SQL = "DELETE "
			+ "FROM "
			+ "accountTable "
			+ "WHERE "
			+ "accountId = ?";

	public static int employeeDelete(Integer accountId) throws ClassNotFoundException, SQLException {

		ArrayList<Object> paramList = new ArrayList<Object>() {
			{
				//削除対象の社員のIDをリストに追加
				add(accountId);
			}
		};

		int deleteNum;

		try (Connection conn = DbConnection.getConnection();) {

			//削除対象の社員情報を削除
			GeneralDao.executeUpdate(conn, DELETE_EMPLOYEE_SQL, paramList);
			deleteNum = GeneralDao.executeUpdate(conn, DELETE_ACCOUNT_SQL, paramList);

		}

		return deleteNum;
	}

}