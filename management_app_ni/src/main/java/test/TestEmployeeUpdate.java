package test;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import beans.Employee;
import model.EmployeeUpdate;

class TestEmployeeUpdate {

	@Test
	//正しい情報が入力された時
	void testEmpUpdate() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		Employee employee = new Employee(2, "第1部署", "課長", "課長2", "password123");
		String result = EmployeeUpdate.employeeUpdate(employee);
		assertEquals(1, result);
	}

	@Test
	//パスワードが8文字未満の場合
	void testPassIsUnder8() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		Employee employee = new Employee(2, "第2部署", "課長", "課長2", "pass");
		String result = EmployeeUpdate.employeeUpdate(employee);
		assertEquals("password is defective", result);
	}

	@Test
	//パスワードが16文字を超える場合
	void testPassIsOver16() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		Employee employee = new Employee(2, "第2部署", "課長", "課長2", "passwordpasswordpassword");
		String result = EmployeeUpdate.employeeUpdate(employee);
		assertEquals("password is defective", result);
	}

	@Test
	//所属部署情報が空文字の場合
	void testDepIsEmpty() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		Employee employee = new Employee(2, "", "課長", "課長2", "password123");
		String result = EmployeeUpdate.employeeUpdate(employee);
		assertEquals("department is defective", result);
	}

	@Test
	//社員名が空文字の場合
	void testEmpNameIsEmpty() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		Employee employee = new Employee(2, "第2部署", "課長", "", "password123");
		String result = EmployeeUpdate.employeeUpdate(employee);
		assertEquals("employeeName is defective", result);
	}

}
