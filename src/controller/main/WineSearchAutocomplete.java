package controller.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DriverManagerConnectionPool;
import model.WineBean;
import model.WineModelDM;
import routes.Routes;

/**
 * Servlet implementation class WineSearchAutocomplete
 */
@WebServlet(Routes.WINE_AUTOCOMPLETE)
public class WineSearchAutocomplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn;

		String query = request.getParameter("q");
		query = query == null ? "" : query;

		try {
			conn = ((DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager")).getConnection();

			WineModelDM wineModel = new WineModelDM(conn);

			Collection<WineBean> wines = wineModel.searchForWines(query, 0, 5);

			String json = JSON.stringifyWines(wines.iterator());
			
			response.setContentType("application/json");
			response.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
}
