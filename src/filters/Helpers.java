package filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Helpers {
	public static boolean isAuthorized(HttpServletRequest hrequest, HttpServletResponse hresponse, String role) {
		// check the token from session
		HttpSession session = hrequest.getSession(false);
		boolean loggedIn = session != null && session.getAttribute(role) != null;

		return loggedIn;
	}
}
