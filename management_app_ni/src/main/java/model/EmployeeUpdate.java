package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Account;
import dao.GeneralDao;

public class EmployeeUpdate {

	private static final String SELECT_DEPARTMENT_ID_SQL = "SELECT "
			+ "departmentId "
			+ "FROM "
			+ "departmentTable "
			+ "WHERE "
			+ "department = ?";

	private static final String SELECT_POSITION_ID_SQL = "SELECT "
			+ "positionId "
			+ "FROM "
			+ "positionTable "
			+ "WHERE "
			+ "position = ?";

	private static final String UPDATE_EMPLOYEE_SQL = "UPDATE "
			+ "employeeTable "
			+ "SET "
			+ "department_id = ?, "
			+ "position_id = ? "
			+ "WHERE "
			+ "account_id = ?";

	private static final String UPDATE_ACCOUNT_SQL = "UPDATE "
			+ "accountTable "
			+ "SET "
			+ "employeeName = ? "
			+ "WHERE "
			+ "accountId = ?";

	public static int employeeUpdate(Account updateAccount)
			throws ClassNotFoundException, SQLException {

		ArrayList<Object> departmentParamList = new ArrayList<Object>() {
			{
				add(updateAccount.getDepartment());
			}
		};

		ArrayList<Object> positionParamList = new ArrayList<Object>() {
			{
				add(updateAccount.getPosition());
			}
		};

		ArrayList<Object> accountParamList = new ArrayList<Object>() {
			{
				add(updateAccount.getEmployeeName());
				add(updateAccount.getAccountId());
			}
		};

		int updateNum = 0;

		try (Connection conn = DbConnection.getConnection();) {

			ResultSet departmentResult = GeneralDao.executeQuery(conn, SELECT_DEPARTMENT_ID_SQL, departmentParamList);
			ResultSet positionResult = GeneralDao.executeQuery(conn, SELECT_POSITION_ID_SQL, positionParamList);

			int departmentId = 0;
			int positionId = 0;

			while (departmentResult.next()) {

				departmentId = departmentResult.getInt("departmentId");

			}

			while (positionResult.next()) {

				positionId = positionResult.getInt("positionId");

			}

			ArrayList<Object> employeeParamList = new ArrayList<Object>();

			if (departmentId != 0 && positionId != 0) {

				employeeParamList.add(departmentId);
				employeeParamList.add(positionId);
				employeeParamList.add(updateAccount.getAccountId());

				GeneralDao.executeUpdate(conn, UPDATE_EMPLOYEE_SQL, employeeParamList);
				updateNum = GeneralDao.executeUpdate(conn, UPDATE_ACCOUNT_SQL, accountParamList);

			}

		}

		return updateNum;

	}

}
