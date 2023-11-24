package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EmployeeCertification;

@WebServlet("/login")
public class EmployeeLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeLoginController() {

		super();

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String employeeName = req.getParameter("employeeName");
		String password = req.getParameter("password");

		try {

			EmployeeCertification.login(req, employeeName, password);

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		res.sendRedirect("top");

	}

}
