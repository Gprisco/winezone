package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserRoles;
import routes.Routes;

public class AdminFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;

		boolean isAuthorized = Helpers.isAuthorized(hrequest, hresponse, UserRoles.ADMIN);

		if (isAuthorized)
			chain.doFilter(hrequest, hresponse);
		else
			hresponse.sendRedirect(Routes.BASE_URL + Routes.APP_MAIN);
	}
}
