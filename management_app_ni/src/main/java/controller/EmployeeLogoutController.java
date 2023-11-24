package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EmployeeCertification;

@WebServlet("/logout")
public class EmployeeLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeLogoutController() {

		super();

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//人事管理アプリからログアウト
		EmployeeCertification.logout(req);

		//トップページへリダイレクト
		res.sendRedirect("top");

	}

}
