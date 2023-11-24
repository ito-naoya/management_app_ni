package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Employee;
import dao.GeneralDao;

public class SelectEmployee {
	
	private static final String SELECT_EMPLOYEE_BASE_SQL = "SELECT "
			+ "A1.accountId, "
			+ "A1.employeeName, "
			+ "D1.departmentId, "
			+ "D1.department, "
			+ "P1.positionId, "
			+ "P1.position "
			+ "FROM "
			+ "employeeTable "
			+ "AS E1 "
			+ "INNER JOIN "
			+ "departmentTable "
			+ "AS D1 "
			+ "ON "
			+ "E1.department_id = D1.departmentId "
			+ "INNER JOIN "
			+ "positionTable "
			+ "AS P1 "
			+ "ON "
			+ "E1.position_id = P1.positionId "
			+ "INNER JOIN "
			+ "accountTable "
			+ "AS A1 "
			+ "ON "
			+ "E1.account_id = A1.accountId "
			+ "WHERE ";

	private static final String SELECT_BY_ACCOUNT_ID_SQL = SELECT_EMPLOYEE_BASE_SQL
			+ "A1.accountId = ?";
	
	private static final String SELECT_BY_DEPARTMENT_ID_SQL = SELECT_EMPLOYEE_BASE_SQL
			+ "E1.department_id = ?";

	public static Employee selectByAccountId(int accountId) throws ClassNotFoundException, SQLException {

		ArrayList<Object> paramList = new ArrayList<Object>() {
			{				
				//取得したい社員のIDをリストに追加
				add(accountId);
			}
		};
		
		//emploeeをnullで初期化
		Employee employee = null;

		try (Connection conn = DbConnection.getConnection();
				ResultSet result = GeneralDao.executeQuery(conn, SELECT_BY_ACCOUNT_ID_SQL, paramList);) {

			while (result.next()) {

				employee = new Employee(

						//社員のIDを取得
						result.getInt("accountId"),
						
						//社員の名前を取得
						result.getString("employeeName"),
						
						//社員の所属部署IDを取得
						result.getInt("departmentId"),
						
						//社員の所属部署を取得
						result.getString("department"),
						
						//社員の役職IDを取得
						result.getInt("positionId"),
						
						//社員の役職を取得
						result.getString("position"));
			}

		}

		return employee;

	}
	
	

	public static ArrayList<Employee> selectByDepartmentId(int departmentId)
			throws ClassNotFoundException, SQLException {

		ArrayList<Object> paramList = new ArrayList<Object>() {
			{				
				//取得したい所属部署IDをリストに追加
				add(departmentId);
			}
		};

		try (Connection conn = DbConnection.getConnection();
				ResultSet result = GeneralDao.executeQuery(conn, SELECT_BY_DEPARTMENT_ID_SQL, paramList);) {

			ArrayList<Employee> employeeList = new ArrayList<Employee>();

			while (result.next()) {

				Employee employee = new Employee(

						//社員のIDを取得
						result.getInt("accountId"),
						
						//社員の名前を取得
						result.getString("employeeName"),
						
						//社員の所属部署IDを取得
						result.getInt("departmentId"),
						
						//社員の所属部署を取得
						result.getString("department"),
						
						//社員の役職IDを取得
						result.getInt("positionId"),
						
						//社員の役職を取得
						result.getString("position"));

				employeeList.add(employee);

			}

			return employeeList;

		}

	}

}
