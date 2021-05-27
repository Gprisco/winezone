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

			request.setAttribute("shippings", shippings);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Routes.SHIPPINGS_JSP);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}

	}
}
