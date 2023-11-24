package beans;

public class Account {
	private int accountId;
	private String employeeName;
	private String password;
	private String department;
	private String position;

	public Account() {

	}

	public Account(int accountId) {

		this.accountId = accountId;

	}

	public Account(String employeeName, String password, String department, String position) {
		
		this.employeeName = employeeName;
		this.password = password;
		this.department = department;
		this.position = position;
		
	}

	public Account(int accountId, String department, String position, String employeeName) {
		
		this.accountId = accountId;
		this.department = department;
		this.position = position;
		this.employeeName = employeeName;
		
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
