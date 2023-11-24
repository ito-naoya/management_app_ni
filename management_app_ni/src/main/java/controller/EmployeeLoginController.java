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

		//ログインする社員の名前を取得
		String employeeName = req.getParameter("employeeName");
		
		//ログインする社員のパスワードを取得
		String password = req.getParameter("password");

		try {

			//人事管理アプリにログイン
			EmployeeCertification.login(req, employeeName, password);

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		//トップエージにリダイレクト
		res.sendRedirect("top");

	}

}
