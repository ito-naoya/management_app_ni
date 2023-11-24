package model;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Account;
import dao.GeneralDao;

public class EmployeeCreate {

	private static final String INSERT_ACCOUNT_SQL = "INSERT INTO "
			+ "accountTable "
			+ "(employeeName, password) "
			+ "VALUES "
			+ "(?, ?) ";

	private static final String SELECT_ACCOUNT_ID_SQL = "SELECT "
			+ "accountId "
			+ "FROM "
			+ "accountTable "
			+ "WHERE "
			+ "employeeName = ?";

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

	public static int employeeCreate(Account newAccount)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {

		//パスワードのハッシュ化
		String hashedGenerator = HashGenerator.generateHash(newAccount.getPassword());

		ArrayList<Object> accountParamList = new ArrayList<Object>() {
			{
				//新規社員の名前をリストに追加
				add(newAccount.getEmployeeName());

				//新規社員のパスワードをリストに追加
				add(hashedGenerator);
			}
		};

		ArrayList<Object> employeeParamList = new ArrayList<Object>() {
			{
				//新規社員の名前をリストに追加
				add(newAccount.getEmployeeName());
			}
		};

		ArrayList<Object> departmentParamList = new ArrayList<Object>() {
			{
				//新規社員の所属部署をリストに追加
				add(newAccount.getDepartment());
			}
		};

		ArrayList<Object> positionParamList = new ArrayList<Object>() {
			{
				//新規社員の役職をリストに追加
				add(newAccount.getPosition());
			}
		};

		try (Connection conn = DbConnection.getConnection();) {

			//新規社員のアカウントを追加
			GeneralDao.executeUpdate(conn, INSERT_ACCOUNT_SQL, accountParamList);

			//新規社員のIDを取得
			ResultSet accountResult = GeneralDao.executeQuery(conn, SELECT_ACCOUNT_ID_SQL, employeeParamList);

			//新規社員の所属部署のIDを取得
			ResultSet departmentResult = GeneralDao.executeQuery(conn, SELECT_DEPARTMENT_ID_SQL, departmentParamList);

			//新規社員の役職のIDを取得
			ResultSet positionResult = GeneralDao.executeQuery(conn, SELECT_POSITIONID_SQL, positionParamList);

			int accountId = 0;
			int departmentId = 0;
			int positionId = 0;

			while (accountResult.next()) {

				//新規社員のIDを取得
				accountId = accountResult.getInt("accountId");

			}

			while (departmentResult.next()) {

				//新規社員の所属部署を取得
				departmentId = departmentResult.getInt("departmentId");

			}

			while (positionResult.next()) {

				//新規社員の役職を取得
				positionId = positionResult.getInt("positionId");

			}

			ArrayList<Object> enployeeParamList = new ArrayList<Object>();

			if (departmentId != 0 && positionId != 0 && accountId != 0) {

				//新規社員の所属部署のDIをリストに追加
				enployeeParamList.add(departmentId);

				//新規社員の役職のIDをリストに追加
				enployeeParamList.add(positionId);

				//新規社員のIDをリストに追加
				enployeeParamList.add(accountId);

			}

			//新規社員情報を追加
			int createNum = GeneralDao.executeUpdate(conn, INSERT_EMPLOYEE_SQL, enployeeParamList);
			return createNum;

		}

	}

}
