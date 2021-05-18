package controller;

import java.io.IOException;
import java.util.Collection;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Helpers;
import model.*;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;

		try {
			conn = ((DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager")).getConnection();

		} catch (SQLException e) {
			Helpers.handleSQLException(e);
		}

		WineModelDM wineModel = new WineModelDM(conn);

		try {
			Collection<WineBean> wineBeans = wineModel.findAll(0, 10);
			System.out.println("CIAO");
			for (WineBean wine : wineBeans) {
				System.out.println(wine.getPk().getWine());
			}
		} catch (SQLException ex) {
			Helpers.handleSQLException(ex);
		}
	}
}
