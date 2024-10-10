package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class Controller {
	
	@GetMapping("/hello")
	public String  Hello()
	{
		
		return "hello World";
	}

	
	
	@GetMapping("/payment")
	public String  payment()
	{
		
		return "payment branch code";
	}

	
	
	
	@GetMapping("/hello")
	public String  Hello1()
	{
		
		return "hello World";
	}

}
