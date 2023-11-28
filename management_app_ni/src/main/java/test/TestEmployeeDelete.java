package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.EmployeeDelete;

class TestEmployeeDelete {

	@Test
	//存在するIDが渡された時
	void testEmpDelete() throws ClassNotFoundException, SQLException {
		String result = EmployeeDelete.employeeDelete(1);
		assertEquals(1, result);
	}

	@Test
	//存在しないIDが渡された時
	void testEmpDeleteIsZero() throws ClassNotFoundException, SQLException {
		String result = EmployeeDelete.employeeDelete(0);
		assertEquals("0件の社員情報を削除しました。", result);
	}

}
