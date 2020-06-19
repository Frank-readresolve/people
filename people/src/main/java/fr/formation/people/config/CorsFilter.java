package fr.formation.people.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {
    private final String allowedOrigin;

    public CorsFilter(String allowedOrigin) {
	this.allowedOrigin = allowedOrigin;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
	    ServletResponse servletResponse, FilterChain chain)
	    throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setHeader("Access-Control-Allow-Origin", allowedOrigin);
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers",
			"x-requested-with, Authorization");
		chain.doFilter(servletRequest, servletResponse);
    }
}
