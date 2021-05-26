package controller.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserRoles;
import routes.Routes;

/**
 * Servlet implementation class Logout
 */
@WebServlet(Routes.LOGOUT)
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		synchronized (session) {
			session.removeAttribute(UserRoles.REGISTERED);
			session.removeAttribute(UserRoles.ADMIN);
			session.removeAttribute("username");
			session.removeAttribute("userId");
		}

		response.sendRedirect(request.getContextPath() + Routes.LOGIN);
	}
}
