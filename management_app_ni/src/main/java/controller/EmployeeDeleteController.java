package controller;

import java.io.IOException;
import java.sql.SQLException;

import beans.Employee;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.EmployeeDelete;

@WebServlet("/delete")
public class EmployeeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String message = "";

	public EmployeeDeleteController() {

		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();

		//ログイン中の社員情報をセッションから取得
		Employee loginedEmployee = (Employee) session.getAttribute("employee");

		//ログインしている社員がいる？
		if (loginedEmployee != null) {

			//削除対象の社員のIDを取得
			int accountId = Integer.parseInt(req.getParameter("accountId"));

			try {

				//削除対象の社員情報を削除
				message = EmployeeDelete.employeeDelete(accountId);
				req.setAttribute("employeeDeleteMsg", message);

			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();

			} catch (Exception e) {

				e.printStackTrace();

			}

			String view = "/WEB-INF/views/deleteCompleteView.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(view);
			dispatcher.forward(req, res);

		} else {

			//トップページへリダイレクト(ログインページ)
			res.sendRedirect("top");

		}

	}

}
