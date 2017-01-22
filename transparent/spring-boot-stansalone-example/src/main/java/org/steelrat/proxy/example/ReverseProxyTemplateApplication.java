package org.steelrat.proxy.example;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAutoConfiguration
@Component
@ComponentScan
public class ReverseProxyTemplateApplication implements CommandLineRunner, ApplicationContextAware {

	private ApplicationContext ctx;
	
	public static void main(String[] args) {
		SpringApplication.run(ReverseProxyTemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//AsyncProxyServletWrapper proxyServletWrapper = ctx.getBean(AsyncProxyServletWrapper.class);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ctx = applicationContext;
	}
}
