package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crypt.Crypt;
import model.*;
import routes.Routes;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String redirectedPage = "";

		try {
			Connection conn = ((DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager"))
					.getConnection();

			checkLogin(conn, username, password);

			request.getSession().setAttribute(UserRoles.REGISTERED, true);

			redirectedPage = Routes.APP_MAIN;
		} catch (SQLException e) {
			Helpers.handleSQLException(e);
		} catch (Exception e) {
			System.out.println("Invalid username or password");
			redirectedPage = Routes.LOGIN;
		}

		response.sendRedirect(request.getContextPath() + redirectedPage);
	}

	private void checkLogin(Connection conn, String username, String password) throws Exception {
		UserModelDM userModel = new UserModelDM(conn);

		try {
			UserBean user = userModel.findOne(username);

			if (user == null)
				throw new Exception("Invalid username or password");
			else {
				if (!Crypt.validatePassword(password, user.getPassword()))
					throw new Exception("Invalid username or password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Internal Server Error");
		}
	}
}
