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

	//取得した社員情報保持する際のコンストラクター
	public Employee(int accountId, String employeeName, int departmentId, String department, int positionId,
			String position) {
		this.accountId = accountId;
		this.employeeName = employeeName;
		this.departmentId = departmentId;
		this.department = department;
		this.positionId = positionId;
		this.position = position;
	}

	//新規社員追加時のコンストラクター
	public Employee(String employeeName, String password, String department, String position) {

		setEmployeeName(employeeName);
		setPassword(password);
		setDepartment(department);
		this.position = position;

	}

	//社員情報更新時のコンストラクター
	public Employee(int accountId, String department, String position, String employeeName, String password) {

		this.accountId = accountId;
		setDepartment(department);
		this.position = position;
		setEmployeeName(employeeName);
		setPassword(password);

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

		if(!employeeName.equals("") &&
			employeeName != null &&
			employeeName.length() > 0) {
			this.employeeName = employeeName;
		} else {
			this.employeeName = null;
		}

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {

		if (!password.equals("") && 
			 password != null &&
			 password.length() > 8 && 
			 password.length() < 16) {
			this.password = password;
		} else {
			this.password = null;
		}

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

		if (!department.equals("")
				&& department != null) {
			this.department = department;
		} else {
			this.department = null;
		}

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
