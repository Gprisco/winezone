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
 * Servlet implementation class Cart
 */
@WebServlet(Routes.CART)
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		CartBean cart = (CartBean) session.getAttribute("cart");
		cart = cart == null ? new CartBean() : cart;

		request.setAttribute("cart", cart);
		request.setAttribute("wines", cart.getProducts().toArray());

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Routes.CART_JSP);
		dispatcher.forward(request, response);
	}
}
