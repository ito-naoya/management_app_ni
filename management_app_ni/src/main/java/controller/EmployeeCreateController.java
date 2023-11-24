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

		//ログイン中のユーザーをセッションから取得
		Account loginAccount = (Account) session.getAttribute("account");

		//ログインしているユーザーがいる？
		if (loginAccount == null) {

			//トップページへリダイレクト(ログインページ)
			res.sendRedirect("top");

		} else {

			try {

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
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//新規従業員の名前を取得
		String employeeName = req.getParameter("employeeName");
		
		//新規従業員のパスワードを取得
		String password = req.getParameter("password");
		
		//新規従業員の所属部署を取得
		String department = req.getParameter("department");
		
		//新規従業員の役職を取得
		String position = req.getParameter("position");

		//新規アカウントをNEW
		Account newAccount = new Account(employeeName, password, department, position);

		try {

			//新規アカウントを追加
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
