package org.steelrat.proxy.example;

import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class AsyncProxyServletWrapper implements DisposableBean {

	private Class<? extends Servlet> servletClass;

	@Value("${mapping}")
	private String servletName;

	private Properties initParameters = new Properties();

	private String beanName;

	private Servlet servletInstance;

	private ServletContext servletContext;

	/**
	 * Set the class of the servlet to wrap. Needs to implement
	 * {@code javax.servlet.Servlet}.
	 * 
	 * @see javax.servlet.Servlet
	 */
	public void setServletClass(Class<? extends Servlet> servletClass) {
		this.servletClass = servletClass;
	}

	/**
	 * Set the name of the servlet to wrap. Default is the bean name of this
	 * controller.
	 */
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}

	/**
	 * Specify init parameters for the servlet to wrap, as name-value pairs.
	 */
	public void setInitParameters(Properties initParameters) {
		this.initParameters = initParameters;
	}

	public void setBeanName(String name) {
		this.beanName = name;
	}

	private synchronized void afterPropertiesSet(ServletContext servletContext)
			throws Exception {
		if (servletInstance == null)
			if (this.servletClass == null) {
				throw new IllegalArgumentException("'servletClass' is required");
			}
		if (this.servletName == null) {
			this.servletName = this.beanName;
		}
		this.servletInstance = this.servletClass.newInstance();
		this.servletInstance.init(new DelegatingServletConfig());
	}

	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.servletContext = request.getServletContext();
		afterPropertiesSet(servletContext);
		this.servletInstance.service(request, response);
	}

	/**
	 * Destroy the wrapped Servlet instance.
	 * 
	 * @see javax.servlet.Servlet#destroy()
	 */
	@Override
	public void destroy() {
		this.servletInstance.destroy();
	}

	private class DelegatingServletConfig implements ServletConfig {

		@Override
		public String getServletName() {
			return servletName;
		}

		@Override
		public ServletContext getServletContext() {
			return AsyncProxyServletWrapper.this.servletContext;
		}

		@Override
		public String getInitParameter(String paramName) {
			return initParameters.getProperty(paramName);
		}

		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Enumeration<String> getInitParameterNames() {
			return (Enumeration) initParameters.keys();
		}
	}

	public ServletContext getServletContext() {
		return null;
	}

}
