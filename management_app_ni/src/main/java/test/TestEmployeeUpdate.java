package test;

class TestEmployeeUpdate {

	//	@Test
	//	//正しい情報が入力された時
	//	void testEmpUpdate() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
	//		Employee employee = new Employee(2, "第1部署", "課長", "課長2", "password");
	//		int updateNum = EmployeeUpdate.employeeUpdate(employee);
	//		assertEquals(1, updateNum);
	//	}

	//	@Test
	//	//パスワードの文字数が８文字未満の場合
	//	void testPassIsUnder8() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
	//		Employee employee = new Employee(2, "第2部署", "課長", "課長2", "pass");
	//		String result = EmployeeUpdate.employeeUpdate(employee);
	//		assertEquals("password is defective", result);
	//	}

	//	@Test
	//	//パスワードの文字数が16文字より多い場合
	//	void testPassIsOver16() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
	//		Employee employee = new Employee(2, "第2部署", "課長", "課長2", "passwordpasswordpassword");
	//		String result = EmployeeUpdate.employeeUpdate(employee);
	//		assertEquals("password is defective", result);
	//	}

	//	@Test
	//	//所属部署情報が空文字の場合
	//	void testEmpUpdate() throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
	//		Employee employee = new Employee(2, "", "課長", "課長2", "password123");
	//		String result = EmployeeUpdate.employeeUpdate(employee);
	//		assertEquals("department is defective", result);
	//	}

}
