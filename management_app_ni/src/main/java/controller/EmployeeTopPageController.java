package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SetEmployeeList;

@WebServlet("/top")
public class EmployeeTopPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeTopPageController() {

		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {
			
			//社員リストの取得とセット
			SetEmployeeList.setEmployeeList(req);

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		String view = "/WEB-INF/views/employeeTopPageView.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, res);

	}
	
}