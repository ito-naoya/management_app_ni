package model;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.GeneralDao;

public class EmployeeCreate {

	private static final String INSERT_ACCOUNT_SQL = "INSERT INTO "
			+ "accountTable "
			+ "(accountName, password) "
			+ "VALUES "
			+ "(?, ?) ";

	private static final String SELECT_ACCOUNT_ID_SQL = "SELECT "
			+ "accountId "
			+ "FROM "
			+ "accountTable "
			+ "WHERE "
			+ "accountName = ?";

	private static final String SELECT_DEPARTMENT_ID_SQL = "SELECT "
			+ "departmentId "
			+ "FROM "
			+ "departmentTable "
			+ "WHERE "
			+ "department = ?";

	private static final String SELECT_POSITIONID_SQL = "SELECT "
			+ "positionId "
			+ "FROM "
			+ "positionTable "
			+ "WHERE "
			+ "position = ?";

	private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO "
			+ "employeeTable "
			+ "(department_id, position_id, account_id) "
			+ "VALUES "
			+ "(?, ?, ?)";

	public static int employeeCreate(String employeeName, String password, String department, String position)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {

		String hashedGenerator = HashGenerator.generateHash(password);

		ArrayList<Object> createParamList = new ArrayList<Object>() {
			{
				add(employeeName);
				add(hashedGenerator);
			}
		};

		ArrayList<Object> accountParamList = new ArrayList<Object>() {
			{
				add(employeeName);
			}
		};

		ArrayList<Object> departmentParamList = new ArrayList<Object>() {
			{
				add(department);
			}
		};

		ArrayList<Object> positionParamList = new ArrayList<Object>() {
			{
				add(position);
			}
		};

		try (Connection conn = DbConnection.getConnection();) {

			GeneralDao.executeUpdate(conn, INSERT_ACCOUNT_SQL, createParamList);
			ResultSet accountResult = GeneralDao.executeQuery(conn, SELECT_ACCOUNT_ID_SQL, accountParamList);
			ResultSet departmentResult = GeneralDao.executeQuery(conn, SELECT_DEPARTMENT_ID_SQL, departmentParamList);
			ResultSet positionResult = GeneralDao.executeQuery(conn, SELECT_POSITIONID_SQL, positionParamList);

			int accountId = 0;
			int departmentId = 0;
			int positionId = 0;

			while (accountResult.next()) {

				accountId = accountResult.getInt("accountId");

			}

			while (departmentResult.next()) {

				departmentId = departmentResult.getInt("departmentId");

			}

			while (positionResult.next()) {

				positionId = positionResult.getInt("positionId");

			}

			ArrayList<Object> enployeeParamList = new ArrayList<Object>();

			if (departmentId != 0 && positionId != 0 && accountId != 0) {

				enployeeParamList.add(departmentId);
				enployeeParamList.add(positionId);
				enployeeParamList.add(accountId);

			}

			int createNum = GeneralDao.executeUpdate(conn, INSERT_EMPLOYEE_SQL, enployeeParamList);
			return createNum;

		}

	}

}
