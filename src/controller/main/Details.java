package controller.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import routes.Routes;

/**
 * Servlet implementation class Details
 */
@WebServlet(Routes.DETAILS)
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String wineName = request.getParameter("wine");
		Integer vintage = null;

		try {
			vintage = Integer.parseInt(request.getParameter("vintage"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + Routes.CATALOGO);
			return;
		}

		try {
			Connection conn = ((DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager"))
					.getConnection();

			WineModelDM wineModel = new WineModelDM(conn);

			WineBean wine = wineModel.findByPk(new WinePrimaryKey(wineName, vintage));

			request.setAttribute("wine", wine);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Routes.DETAILS_JSP);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
}
