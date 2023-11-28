package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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

	private static String message = "";

	public EmployeeEditController() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();

		//ログイン中の社員をセッションから取得
		Employee loginEmployee = (Employee) session.getAttribute("employee");

		//ログインしている社員がいる？
		if (loginEmployee == null) {

			//トップページへリダイレクト(ログインページ)
			res.sendRedirect("top");

		} else {

			//編集対象の社員のIDを取得
			int accountId = Integer.parseInt(req.getParameter("accountId"));

			try {
				
				if (message != "") req.setAttribute("errorMsg", message);

				//編集対象の社員情報を取得
				Employee employee = SelectEmployee.selectByAccountId(accountId);
				req.setAttribute("employee", employee);

				//部署データを全て取得
				ArrayList<String> departmentList = SelectDepartment.selectDepartmentAll();
				req.setAttribute("departmentList", departmentList);

				//役職データを全て取得
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

		//編集対象の社員のIDを取得
		int accountId = Integer.parseInt(req.getParameter("accountId"));

		//変更後の所属部署を取得
		String department = req.getParameter("department");

		//変更後のパスワードを取得
		String password = req.getParameter("password");

		//変更後の役職を取得
		String position = req.getParameter("position");

		//変更後の社員の名前を取得
		String employeeName = req.getParameter("employeeName");

		//変更後の社員情報をnew
		Employee updateEmployee = new Employee(accountId, department, position, employeeName, password);

		try {

			if (message.equals("password is defective") ||
				message.equals("department is defective") ||
				message.equals("employeeName is defective")) {

				doGet(req, res);

			} else {

				//社員情報の更新
				message = EmployeeUpdate.employeeUpdate(updateEmployee);
				req.setAttribute("employeeUpdateMsg", message);

				String view = "/WEB-INF/views/editCompleteView.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(view);
				dispatcher.forward(req, res);

			}

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
