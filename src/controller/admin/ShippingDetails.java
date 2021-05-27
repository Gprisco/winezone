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
 * Servlet implementation class ShippingDetails
 */
@WebServlet(Routes.SHIPPING_DETAILS)
public class ShippingDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer shippingId = null;

		try {
			shippingId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + Routes.SHIPPINGS);
			return;
		}

		try {
			Connection conn = ((DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager"))
					.getConnection();

			ShippingModelDM shippingModel = new ShippingModelDM(conn);

			ShippingBean shipping = shippingModel.findByPk(shippingId);

			request.setAttribute("shipping", shipping);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Routes.SHIPPING_DETAILS_JSP);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
}
