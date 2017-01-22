package org.steelrat.proxy.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index(HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req);
		return "Greetings from Spring Boot!";
	}

}