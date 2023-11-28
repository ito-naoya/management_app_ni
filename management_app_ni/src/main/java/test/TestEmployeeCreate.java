package test;

class TestEmployeeCreate {

	//	(String employeeName, String password, String department, String position)

	//	@Test
	//	//正しく情報が入力されている場合
	//	void testEmployeeCreate() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
	//		Employee employee = new Employee("新規社員", "password", "第1部署", "");
	//		int result = EmployeeCreate.employeeCreate(employee);
	//		assertEquals(1, result);
	//	}

	//	@Test 
	//	//パスワードが８文字未満の場合
	//	void testPassIsUnder8() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
	//		Employee employee = new Employee("テスト社員", "aaaaaaa", "第1部署", "");
	//		Object result = EmployeeCreate.employeeCreate(employee);
	//		assertEquals("password is defective", result);
	//	}

	//	@Test
	//	//パスワードが17文字以上の場合
	//		void testPassIsOver16() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
	//			Employee employee = new Employee("テスト社員", "aaaaaaaaaaaaaaaaa", "第1部署", "");
	//			Object result = EmployeeCreate.employeeCreate(employee);
	//			assertEquals("password is defective", result);
	//		}

	//	@Test
	//	//所属部署が空文字の場合
	//	void testDepIsEmpty() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
	//		Employee employee = new Employee("テスト社員", "aaaaaaaaa", "", "");
	//		Object result = EmployeeCreate.employeeCreate(employee);
	//		assertEquals("department is defective", result);
	//	}

}
