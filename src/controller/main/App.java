package controller.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import routes.Routes;

/**
 * Servlet implementation class Main
 */
@WebServlet(Routes.APP_MAIN)
public class App extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn;

		try {
			conn = ((DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager")).getConnection();
			WineModelDM wineModel = new WineModelDM(conn);

			Collection<WineBean> wineBeans = wineModel.findAll(0, 10);

			request.setAttribute("wines", wineBeans.toArray());

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Routes.APP_MAIN_JSP);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
}
