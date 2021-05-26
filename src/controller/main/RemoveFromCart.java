package controller.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartBean;
import model.DriverManagerConnectionPool;
import model.WineBean;
import model.WineModelDM;
import model.WinePrimaryKey;
import routes.Routes;

/**
 * Servlet implementation class RemoveFromCart
 */
@WebServlet(Routes.REMOVE_FROM_CART)
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		synchronized (session) {
			String wineName = null;
			Integer vintage = null;

			try {
				wineName = request.getParameter("wine");
				vintage = Integer.parseInt(request.getParameter("vintage"));
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + Routes.APP_MAIN);
				return;
			}

			try {
				Connection conn = ((DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager"))
						.getConnection();

				WineModelDM wineModel = new WineModelDM(conn);

				WineBean wine = wineModel.findByPk(new WinePrimaryKey(wineName, vintage));

				if (wine != null) {
					CartBean cart = (CartBean) session.getAttribute("cart");

					if (cart != null)
						cart.removeFromCart(wine);
					else
						cart = new CartBean(); // If cart is not in session yet, we create it

					session.setAttribute("cart", cart);
				}

				String previousUrl = request.getHeader("referer");
				previousUrl = previousUrl == null ? Routes.APP_MAIN : previousUrl;

				response.sendRedirect(previousUrl);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}
}
