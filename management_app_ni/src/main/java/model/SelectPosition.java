package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.GeneralDao;

public class SelectPosition {

	private static final String SELECT_POSITION_SQL = "SELECT "
			+ "position "
			+ "FROM "
			+ "positionTable";

	public static ArrayList<String> selectPositionAll() throws SQLException, ClassNotFoundException {

		ArrayList<String> positionList = new ArrayList<String>();

		try (Connection conn = DbConnection.getConnection();
				ResultSet result = GeneralDao.executeQuery(conn, SELECT_POSITION_SQL, new ArrayList<Object>());) {

			while (result.next()) {

				//役職データをリストに追加
				positionList.add(result.getString("position"));

			}

		}

		return positionList;

	}

}
