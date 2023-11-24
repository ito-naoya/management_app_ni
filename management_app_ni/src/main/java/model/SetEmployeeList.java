package model;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Account;
import beans.Employee;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SetEmployeeList {

	public static void setEmployeeList(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		
		HttpSession session = req.getSession();
		Account account = (Account) session.getAttribute("account");
		
		if (account != null) {

			Employee employee = SelectEmployee.selectByAccountId(account.getAccountId());
			req.setAttribute("employee", employee);

			if (employee.getPosition().equals("課長") || employee.getPosition().equals("係長")) {

				ArrayList<Employee> employeeList  = SelectEmployee.selectByDepartmentId(employee.getDepartmentId());
				req.setAttribute("employeeList", employeeList);

			}

		}
		
	}

}
