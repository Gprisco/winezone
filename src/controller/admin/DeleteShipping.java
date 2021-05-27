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

import model.DriverManagerConnectionPool;
import model.ShippingBean;
import model.ShippingModelDM;
import routes.Routes;

/**
 * Servlet implementation class DeleteShipping
 */
@WebServlet(Routes.DELETE_SHIPPING)
public class DeleteShipping extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer shippingId = null;

		String previousUrl = request.getHeader("referer");
		previousUrl = previousUrl == null ? request.getContextPath() + Routes.SHIPPINGS : previousUrl;

		try {
			shippingId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(previousUrl);
			return;
		}

		try {
			Connection conn = ((DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager"))
					.getConnection();

			ShippingModelDM shippingModel = new ShippingModelDM(conn);
			shippingModel.destroy(shippingId);

			response.sendRedirect(previousUrl);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
}
