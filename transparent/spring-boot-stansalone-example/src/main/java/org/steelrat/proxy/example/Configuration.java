package org.steelrat.proxy.example;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

	@Bean
	public AsyncProxyServletWrapper servletWrapper(){
		
		return new AsyncProxyServletWrapper();
	}
}
