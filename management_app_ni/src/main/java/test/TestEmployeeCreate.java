package test;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import beans.Employee;
import model.EmployeeCreate;

class TestEmployeeCreate {

	//	(String employeeName, String password, String department, String position)

	@Test
	//正しく情報が入力されている場合
	void testEmployeeCreate() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		Employee employee = new Employee("新規社員", "password", "第1部署", "");
		String result = EmployeeCreate.employeeCreate(employee);
		assertEquals(1, result);
	}

	@Test
	//パスワードが８文字未満の場合
	void testPassIsUnder8() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		Employee employee = new Employee("テスト社員", "aaaaaaa", "第1部署", "");
		String result = EmployeeCreate.employeeCreate(employee);
		assertEquals("password is defective", result);
	}

	@Test
	//パスワードが17文字以上の場合
	void testPassIsOver16() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		Employee employee = new Employee("テスト社員", "aaaaaaaaaaaaaaaaa", "第1部署", "");
		String result = EmployeeCreate.employeeCreate(employee);
		assertEquals("password is defective", result);
	}

	@Test
	//所属部署が空文字の場合
	void testDepIsEmpty() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		Employee employee = new Employee("テスト社員", "aaaaaaaaa", "", "");
		String result = EmployeeCreate.employeeCreate(employee);
		assertEquals("department is defective", result);
	}

	@Test
	//社員名が空文字の場合
	void testEmpNameIsEmpty() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		Employee employee = new Employee("", "password123", "第1部署", "");
		String result = EmployeeCreate.employeeCreate(employee);
		assertEquals("employeeName is defective", result);
	}

}
