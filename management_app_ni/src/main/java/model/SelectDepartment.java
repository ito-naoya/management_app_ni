package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.GeneralDao;

public class SelectDepartment {

	private static final String SELECT_DEPARTMENT_SQL = "SELECT "
			+ "department "
			+ "FROM "
			+ "departmentTable";

	public static ArrayList<String> selectDepartmentAll() throws SQLException, ClassNotFoundException {
		
		ArrayList<String> departmentList = new ArrayList<String>();

		try (Connection conn = DbConnection.getConnection();
				ResultSet result = GeneralDao.executeQuery(conn, SELECT_DEPARTMENT_SQL, new ArrayList<Object>());) {

			while (result.next()) {

				//所属部署データをリストに追加
				departmentList.add(result.getString("department"));

			}

		}

		return departmentList;

	}
	
}
