package cz.muni.fi.pa165.mvc.filters;

import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.facade.UserFacade;
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
		"/user/list", "/user/doDelete/*",
		"/weapon/create",
		"/area/add", "/area/create", "/area/delete/*", "/area/removeMonsterFromArea",
		"/monster/add", "/monster/create", "/monster/delete/*", "/monster/removeWeaponFromMonster"})
public class AdminFilter implements Filter {

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

		if (!user.isAdmin()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().println("<html><body><h1>401 Unauthorized</h1> Only admin can view this page</body></html>");
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}