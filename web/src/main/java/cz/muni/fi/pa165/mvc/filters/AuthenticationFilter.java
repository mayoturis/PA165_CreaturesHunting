package cz.muni.fi.pa165.mvc.filters;

import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Marek Turis
 */
@WebFilter(urlPatterns = {
		"/weapon/*",
		"/area/*",
		"/monster/*",
		"/user/list", "/user/arsenal", "/user/addWeapon", "/user/removeFromArsenal/*",
		"/"})
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) r;
		HttpServletResponse response = (HttpServletResponse) s;

		Object emailObject = request.getSession().getAttribute("authenticatedEmail");
		if (emailObject == null) {
			response.sendRedirect("/pa165/user/login");
			return;
		}

		String email = (String) emailObject;

		UserFacade userFacade = WebApplicationContextUtils.getWebApplicationContext(r.getServletContext()).getBean(UserFacade.class);

		UserDTO user = userFacade.findByEmail(email);

		if (user == null) {
			response.sendRedirect("/pa165/user/login");
			return;
		}

		request.setAttribute("authenticatedUser", user);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}