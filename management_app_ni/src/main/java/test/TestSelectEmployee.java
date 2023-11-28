package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.SelectEmployee;

class TestSelectEmployee {

	@Test
	//存在しないAccountIdの場合
	void testEmpIdIsNotExist() throws ClassNotFoundException, SQLException {
		Object result = SelectEmployee.selectByAccountId(100);
		assertEquals("accountId is defective", result);
	}

	@Test
	//存在しないDepartmentIdの場合
	void testDepIdIsNotExist() throws ClassNotFoundException, SQLException {
		String result = (String) SelectEmployee.selectByDepartmentId(0);
		assertEquals("departmentId is defective", result);
	}

}
