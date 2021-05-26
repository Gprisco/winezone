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
 * Servlet implementation class Catalogo
 */
@WebServlet(Routes.CATALOGO)
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int MAX_PAGE_LENGTH = 15;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn;
		Integer page = null;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			e.printStackTrace();

			page = 0;
		}

		int limit = page * MAX_PAGE_LENGTH;
		int offset = (page + 1) * MAX_PAGE_LENGTH;

		try {
			conn = ((DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager")).getConnection();

			WineModelDM wineModel = new WineModelDM(conn);

			Collection<WineBean> wines = wineModel.findAll(limit, offset);
			int winesCount = wineModel.count();

			int totalPages = (int) Math.ceil(winesCount / MAX_PAGE_LENGTH);

			request.setAttribute("wines", wines);
			request.setAttribute("winesCount", winesCount);

			request.setAttribute("previousPage", page - 1 < 0 ? 0 : page - 1);
			request.setAttribute("currentPage", page);
			request.setAttribute("nextPage", page + 1 > totalPages - 1 ? totalPages - 1 : page + 1);
			request.setAttribute("totalPages", totalPages);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Routes.CATALOGO_JSP);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
}
