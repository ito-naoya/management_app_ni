package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Account;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.EmployeeCreate;
import model.SelectDepartment;
import model.SelectPosition;

@WebServlet("/create")
public class EmployeeCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeCreateController() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Account loginAccount = (Account) session.getAttribute("account");

		if (loginAccount == null) {

			res.sendRedirect("top");

		} else {

			try {

				ArrayList<String> departmentList = SelectDepartment.selectDepartmentAll();
				req.setAttribute("departmentList", departmentList);

				ArrayList<String> positionList = SelectPosition.selectPositionAll();
				req.setAttribute("positionList", positionList);

			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();

			} catch (Exception e) {

				e.printStackTrace();

			}

			String view = "/WEB-INF/views/employeeCreateView.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(view);
			dispatcher.forward(req, res);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String employeeName = req.getParameter("employeeName");
		String password = req.getParameter("password");
		String department = req.getParameter("department");
		String position = req.getParameter("position");
		
		Account newAccount = new Account(employeeName, password, department, position);

		try {

			int createNum = EmployeeCreate.employeeCreate(newAccount);
			req.setAttribute("employeeCreateMsg", createNum + "件の従業員情報を追加しました。");

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		String view = "/WEB-INF/views/createCompleteView.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, res);

	}

}
