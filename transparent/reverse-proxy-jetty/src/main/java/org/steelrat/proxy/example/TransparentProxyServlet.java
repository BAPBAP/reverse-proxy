package org.steelrat.proxy.example;

import java.io.IOException;
import java.util.concurrent.Executor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.proxy.AsyncProxyServlet;
import org.eclipse.jetty.util.HttpCookieStore;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

/**
 * Represents a transparent {@code HTTP Proxy} sitting in front of
 * {@code Any REST Service}.
 * <p>
 * <b>Implementation notes:</b> although we don't need any custom configuration
 * at the moment, this class reserves a space in case one becomes necessary one
 * day.
 *
 */
public class TransparentProxyServlet extends AsyncProxyServlet.Transparent {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DONE");
		super.service(request, response);
	}

	@Override
	protected HttpClient createHttpClient() throws ServletException {
		ServletConfig config = getServletConfig();

		SslContextFactory scf = new SslContextFactory();
		scf.setTrustAll(true);

		HttpClient client = new HttpClient(scf);

		// Redirects must be proxied as is, not followed
		client.setFollowRedirects(false);

		// Must not store cookies, otherwise cookies of different clients will
		// mix
		client.setCookieStore(new HttpCookieStore.Empty());

		Executor executor;
		String value = config.getInitParameter("maxThreads");
		if (value == null || "-".equals(value)) {
			executor = (Executor) getServletContext().getAttribute(
					"org.eclipse.jetty.server.Executor");
			if (executor == null)
				throw new IllegalStateException("No server executor for proxy");
		} else {
			QueuedThreadPool qtp = new QueuedThreadPool(Integer.parseInt(value));
			String servletName = config.getServletName();
			int dot = servletName.lastIndexOf('.');
			if (dot >= 0)
				servletName = servletName.substring(dot + 1);
			qtp.setName(servletName);
			executor = qtp;
		}

		client.setExecutor(executor);

		value = config.getInitParameter("maxConnections");
		if (value == null)
			value = "256";
		client.setMaxConnectionsPerDestination(Integer.parseInt(value));

		value = config.getInitParameter("idleTimeout");
		if (value == null)
			value = "30000";
		client.setIdleTimeout(Long.parseLong(value));

		value = config.getInitParameter("timeout");
		if (value == null)
			value = "60000";
		this.setTimeout(Long.parseLong(value));

		value = config.getInitParameter("requestBufferSize");
		if (value != null)
			client.setRequestBufferSize(Integer.parseInt(value));

		value = config.getInitParameter("responseBufferSize");
		if (value != null)
			client.setResponseBufferSize(Integer.parseInt(value));

		try {
			client.start();

			// Content must not be decoded, otherwise the client gets confused
			client.getContentDecoderFactories().clear();

			return client;
		} catch (Exception x) {
			throw new ServletException(x);
		}
	}

}
