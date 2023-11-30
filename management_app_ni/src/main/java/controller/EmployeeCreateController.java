package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import model.EmployeeCreate;
import model.SelectDepartment;
import model.SelectPosition;

@WebServlet("/create")
public class EmployeeCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String message = "";

	public EmployeeCreateController() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();

		//ログイン中の社員をセッションから取得
		Employee loginedEmployee = (Employee) session.getAttribute("employee");

		//ログインしている社員がいる？
		if (loginedEmployee != null) {

			try {

				if (message != "")
					req.setAttribute("errorMsg", message);

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

			String view = "/WEB-INF/views/employeeCreateView.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(view);
			dispatcher.forward(req, res);

		} else {

			//トップページへリダイレクト(ログインページ)
			res.sendRedirect("top");
			
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//新規社員の名前を取得
		String employeeName = req.getParameter("employeeName");

		//新規社員のパスワードを取得
		String password = req.getParameter("password");

		//新規社員の所属部署を取得
		String department = req.getParameter("department");

		//新規社員の役職を取得
		String position = req.getParameter("position");

		//新規社員情報をNEW
		Employee newEmployee = new Employee(employeeName, password, department, position);

		try {

			//新規社員を追加
			message = EmployeeCreate.employeeCreate(newEmployee);

			if (message.equals("password is defective") ||
					message.equals("department is defective") ||
					message.equals("employeeName is defective")) {

				doGet(req, res);

			} else {

				//追加完了メッセージをviewに渡す
				req.setAttribute("employeeCreateMsg", message);

				String view = "/WEB-INF/views/createCompleteView.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(view);
				dispatcher.forward(req, res);

			}

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
