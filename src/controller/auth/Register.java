package controller.auth;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crypt.Crypt;
import model.*;
import routes.Routes;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			Connection conn = ((DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager"))
					.getConnection();

			UserModelDM userModel = new UserModelDM(conn);

			UserBean user = new UserBean();
			user.setEmail(username);
			user.setPassword(Crypt.generatePasswordHash(password));

			userModel.create(user);
			System.out.println("User creation...");

			HttpSession session = request.getSession();

			synchronized (session) {
				session.setAttribute(UserRoles.REGISTERED, true);
				session.setAttribute("username", user.getEmail());
				session.setAttribute("userId", user.getId());
			}

			response.sendRedirect(request.getContextPath() + Routes.APP_MAIN);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(403);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			response.sendError(500);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
}
