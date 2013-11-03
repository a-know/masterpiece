package com.aknow.masterpiece.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public final class EncodingFilter implements Filter{

	private String encoding = null;

	public void init (FilterConfig config) throws ServletException{
		this.encoding = config.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException{
		req.setCharacterEncoding(this.encoding);
		chain.doFilter(req, resp);
	}

	public void destroy(){
		this.encoding = null;
	}
}
