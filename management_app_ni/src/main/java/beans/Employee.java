package beans;

public class Employee {
	private int accountId;
	private String employeeName;
	private String password;
	private int departmentId;
	private String department;
	private int positionId;
	private String position;

	public Employee() {

	};

	public Employee(int accountId) {

		this.accountId = accountId;

	};

	public Employee(int accountId, String employeeName, int departmentId, String department, int positionId, String position) {
		this.accountId = accountId;
		this.employeeName = employeeName;
		this.departmentId = departmentId;
		this.department = department;
		this.positionId = positionId;
		this.position = position;
	}

	public Employee(String employeeName, String password, String department, String position) {

		this.employeeName = employeeName;
		this.password = password;
		this.department = department;
		this.position = position;

	}

	public Employee(int accountId, String department, String position, String employeeName, String password) {

		this.accountId = accountId;
		this.department = department;
		this.position = position;
		this.employeeName = employeeName;
		this.password = password;

	}

	public int getAccountId() {

		return accountId;

	}

	public void setAccountId(int accountId) {

		this.accountId = accountId;

	}

	public String getEmployeeName() {

		return employeeName;

	}

	public void setEmployeeName(String employeeName) {

		this.employeeName = employeeName;

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDepartmentId() {

		return departmentId;

	}

	public void setDepartmentId(int departmentId) {

		this.departmentId = departmentId;

	}

	public String getDepartment() {

		return department;

	}

	public void setDepartment(String department) {

		this.department = department;

	}

	public int getPositionId() {

		return positionId;

	}

	public void setPositionId(int positionId) {

		this.positionId = positionId;

	}

	public String getPosition() {

		return position;

	}

	public void setPosition(String position) {

		this.position = position;

	}

}
