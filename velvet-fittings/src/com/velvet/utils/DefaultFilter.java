package com.velvet.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * This class has been created to turn off the default url pattern mapping for static
 * resources like css,js,html and images.
 * 
 * @author RMS Team
 *
 */
public class DefaultFilter implements Filter {

	private RequestDispatcher defaultRequestDispatcher;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		defaultRequestDispatcher.forward(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.defaultRequestDispatcher = filterConfig.getServletContext()
				.getNamedDispatcher("default");
	}

}
