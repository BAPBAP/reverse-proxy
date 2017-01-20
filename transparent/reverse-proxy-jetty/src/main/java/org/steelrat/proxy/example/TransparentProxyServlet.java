package org.steelrat.proxy.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.proxy.ProxyServlet;

/**
 * Represents a transparent {@code HTTP Proxy} sitting in front of {@code Any REST Service}.
 * <p>
 * <b>Implementation notes:</b> although we don't need any custom configuration at the moment,
 * this class reserves a space in case one becomes necessary one day.
 *
 */
public class TransparentProxyServlet extends ProxyServlet.Transparent {
	
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DONE");
		super.service(request, response);
	}
	
}
