package model;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class WinezoneServletContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		DriverManagerConnectionPool dm = null;

		dm = new DriverManagerConnectionPool();

		ServletContext ctx = sce.getServletContext();

		ctx.setAttribute("DriverManager", dm);
		System.out.println("DriverManager creation...." + dm.toString());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();

		DriverManagerConnectionPool dm = (DriverManagerConnectionPool) context.getAttribute("DriverManager");
		System.out.println("DriverManager deletion...." + dm.toString());
	}
}
