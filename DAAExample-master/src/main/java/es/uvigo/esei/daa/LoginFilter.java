package es.uvigo.esei.daa;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebFilter(urlPatterns = { "/*", "/logout" })
public class LoginFilter implements Filter {
	@Override
	public void doFilter(
		ServletRequest request, 
		ServletResponse response,
		FilterChain chain
	) throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		try {
			if (isLogoutPath(httpRequest)) {
				removeTokenCookie(httpResponse);
				redirectToIndex(httpRequest, httpResponse);
			} else if (isIndexPath(httpRequest) || isCssPath(httpRequest) || isFontPath(httpRequest) || isJsPath(httpRequest) ) {
				chain.doFilter(request, response);
			} else if (isRestPath(httpRequest)) {
				httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);				
			} else {
				redirectToIndex(httpRequest, httpResponse);
			}
		} catch (IllegalArgumentException iae) {
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		} 
	}
	
	private boolean isLogoutPath(HttpServletRequest request) {
		return request.getServletPath().equals("/logout");
	}
	
	private boolean isIndexPath(HttpServletRequest request) {
		return request.getServletPath().equals("/index.html");
	}
	
	private boolean isCssPath(HttpServletRequest request) {
		return request.getServletPath().startsWith("/css");
	}
	
	private boolean isJsPath(HttpServletRequest request) {
		return request.getServletPath().startsWith("/js");
	}
	
	private boolean isRestPath(HttpServletRequest request) {
		return request.getServletPath().startsWith("/rest");
	}
	
	private boolean isFontPath(HttpServletRequest request) {
		return request.getServletPath().startsWith("/font-awesome");
	}

	private void redirectToIndex(
		HttpServletRequest request,
		HttpServletResponse response
	) throws IOException {
		response.sendRedirect(request.getContextPath());
	}

	
	
	private void removeTokenCookie(HttpServletResponse response) {
		final Cookie cookie = new Cookie("token", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	

	
	

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
