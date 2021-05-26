package controller.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import routes.Routes;
import model.*;

/**
 * Servlet implementation class Payment
 */
@WebServlet(Routes.PAYMENT)
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cardNumber = request.getParameter("cardNumber");
		String address = request.getParameter("address");

		if (address.length() < 3 || cardNumber.length() != 16) {
			response.sendError(400);
			return;
		}

		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("userId");
		CartBean cart = (CartBean) session.getAttribute("cart");

		if (userId == null || cart == null) {
			response.sendError(400);
			return;
		}

		try {
			Connection conn = ((DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager"))
					.getConnection();

			ShippingModelDM shippingModel = new ShippingModelDM(conn);
			ShippingWineModelDM shippingWineModel = new ShippingWineModelDM(conn);

			ShippingBean shippingBean = new ShippingBean();
			shippingBean.setIdUser(userId);
			shippingBean.setAddress(address);

			shippingModel.create(shippingBean);

			Iterator<WineBean> boughtWines = cart.getProducts().iterator();

			while (boughtWines.hasNext()) {
				WineBean currentWine = boughtWines.next();
				ShippingWineBean shippingWineBean = new ShippingWineBean();

				shippingWineBean.setIdShipping(shippingModel.getLastCreatedId());
				shippingWineBean.setVintage(currentWine.getPk().getVintage());
				shippingWineBean.setWine(currentWine.getPk().getWine());

				shippingWineModel.create(shippingWineBean);
			}

			synchronized (session) {
				cart.empty();
				session.setAttribute("cart", cart);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(Routes.PAYMENT_JSP);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
}
