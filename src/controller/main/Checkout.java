package controller.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartBean;
import routes.Routes;

/**
 * Servlet implementation class Checkout
 */
@WebServlet(Routes.CHECKOUT)
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		CartBean cart = (CartBean) session.getAttribute("cart");

		int totalPrice = cart.getTotalPrice();

		request.setAttribute("totalPrice", totalPrice);

		RequestDispatcher dispatcher = request.getRequestDispatcher(Routes.CHECKOUT_JSP);
		dispatcher.forward(request, response);
	}
}
