package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Account;
import beans.Employee;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.EmployeeUpdate;
import model.SelectDepartment;
import model.SelectEmployee;
import model.SelectPosition;

@WebServlet("/edit")
public class EmployeeEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeEditController() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Account loginStatus = (Account) session.getAttribute("account");

		if (loginStatus == null) {

			res.sendRedirect("top");

		} else {

			int accountId = Integer.parseInt(req.getParameter("accountId"));

			try {

				Employee employee = SelectEmployee.selectByAccountId(accountId);
				req.setAttribute("employee", employee);

				ArrayList<String> departmentList = SelectDepartment.selectDepartmentAll();
				req.setAttribute("departmentList", departmentList);

				ArrayList<String> positionList = SelectPosition.selectPositionAll();
				req.setAttribute("positionList", positionList);

			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();

			} catch (Exception e) {

				e.printStackTrace();

			}

			String view = "/WEB-INF/views/employeeEditView.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(view);
			dispatcher.forward(req, res);

		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String department = req.getParameter("department");
		String position = req.getParameter("position");
		String employeeName = req.getParameter("employeeName");
		int accountId = Integer.parseInt(req.getParameter("accountId"));

		try {

			int employeeUpdateNum = EmployeeUpdate.employeeUpdate(accountId, department, position, employeeName);
			req.setAttribute("employeeUpdateMsg", employeeUpdateNum + "件の従業員情報を更新しました。");

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		String view = "/WEB-INF/views/editCompleteView.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, res);

	}

}
