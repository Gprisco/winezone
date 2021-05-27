package controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collection;

import model.DriverManagerConnectionPool;
import model.ShippingModelDM;
import model.ShippingBean;
import routes.Routes;

/**
 * Servlet implementation class Shippings
 */
@WebServlet(Routes.SHIPPINGS)
public class Shippings extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int MAX_PAGE_LENGTH = 30;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer page = null;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			e.printStackTrace();

			page = 0;
		}

		page = page >= 0 ? page : 0;

		int limit = page * MAX_PAGE_LENGTH;
		int offset = (page + 1) * MAX_PAGE_LENGTH;

		try {
			Connection conn = ((DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager"))
					.getConnection();

			ShippingModelDM shippingModel = new ShippingModelDM(conn);

			Collection<ShippingBean> shippings = shippingModel.findAll(limit, offset);
			int shippingsCount = shippingModel.count();

			int totalPages = (int) Math.ceil(shippingsCount / MAX_PAGE_LENGTH);

			request.setAttribute("winesCount", shippingsCount);
			request.setAttribute("shippings", shippings);

			request.setAttribute("previousPage", page - 1 < 0 ? 0 : page - 1);
			request.setAttribute("currentPage", page);
			request.setAttribute("nextPage", page + 1 > totalPages - 1 ? totalPages - 1 : page + 1);
			request.setAttribute("totalPages", totalPages > 0 ? totalPages : 1);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Routes.SHIPPINGS_JSP);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
}
