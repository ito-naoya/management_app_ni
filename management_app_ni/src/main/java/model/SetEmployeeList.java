package model;

import java.sql.SQLException;

import beans.Employee;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SetEmployeeList {

	public static void setEmployeeList(HttpServletRequest req) throws ClassNotFoundException, SQLException {

		HttpSession session = req.getSession();

		//ログイン中の社員をセッションから取得
		Employee loginEmployee = (Employee) session.getAttribute("employee");

		//ログインしている社員がいる？
		if (loginEmployee != null) {

			//ログインしている社員の情報を取得
			Employee employee = SelectEmployee.selectByAccountId(loginEmployee.getAccountId());
			req.setAttribute("employee", employee);

			//ログインしている社員の役職が係長 or 課長？
			if (employee.getPosition().equals("課長") || employee.getPosition().equals("係長")) {

				//社員データを取得
				Object employeeList = SelectEmployee.selectByDepartmentId(employee.getDepartmentId());
				req.setAttribute("employeeList", employeeList);

			}

		}

	}

}
