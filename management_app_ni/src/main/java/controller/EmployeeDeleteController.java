package controller;

import java.io.IOException;
import java.sql.SQLException;

import beans.Account;
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

	public EmployeeDeleteController() {

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

				int employeeDeleteNum = EmployeeDelete.employeeDelete(accountId);
				req.setAttribute("employeeDeleteMsg", employeeDeleteNum + "件の従業員情報を削除しました。");

			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();

			} catch (Exception e) {

				e.printStackTrace();

			}

			String view = "/WEB-INF/views/deleteCompleteView.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(view);
			dispatcher.forward(req, res);

		}

	}

}
