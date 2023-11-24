package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Employee;
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

	public static int employeeUpdate(Employee updateEmployee)
			throws ClassNotFoundException, SQLException {

		ArrayList<Object> departmentParamList = new ArrayList<Object>() {
			{
				//更新後の所属部署をリストに追加
				add(updateEmployee.getDepartment());
			}
		};

		ArrayList<Object> positionParamList = new ArrayList<Object>() {
			{
				//更新後の役職をリストに追加
				add(updateEmployee.getPosition());
			}
		};

		ArrayList<Object> accountParamList = new ArrayList<Object>() {
			{
				//更新後の社員の名前をリストに追加
				add(updateEmployee.getEmployeeName());
				
				//更新対象の社員のIDをリストに追加
				add(updateEmployee.getAccountId());
			}
		};

		int updateNum = 0;

		try (Connection conn = DbConnection.getConnection();) {

			//更新後の所属部署のIDを取得
			ResultSet departmentResult = GeneralDao.executeQuery(conn, SELECT_DEPARTMENT_ID_SQL, departmentParamList);
			
			//更新後の役職のIDを取得
			ResultSet positionResult = GeneralDao.executeQuery(conn, SELECT_POSITION_ID_SQL, positionParamList);

			int departmentId = 0;
			int positionId = 0;

			while (departmentResult.next()) {
				
				//更新後の所属部署のIDを取得
				departmentId = departmentResult.getInt("departmentId");

			}

			while (positionResult.next()) {

				//更新後の役職のIDを取得
				positionId = positionResult.getInt("positionId");

			}

			ArrayList<Object> employeeParamList = new ArrayList<Object>();

			if (departmentId != 0 && positionId != 0) {

				//更新後の所属部署のIDをリストに追加
				employeeParamList.add(departmentId);
				
				//更新後の役職のIDをリストに追加
				employeeParamList.add(positionId);
				
				//更新対象の社員のIDをリストに追加
				employeeParamList.add(updateEmployee.getAccountId());

				
				//社員情報を更新
				GeneralDao.executeUpdate(conn, UPDATE_EMPLOYEE_SQL, employeeParamList);
				updateNum = GeneralDao.executeUpdate(conn, UPDATE_ACCOUNT_SQL, accountParamList);

			}

		}

		return updateNum;

	}

}
