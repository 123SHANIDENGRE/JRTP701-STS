package com.nt.Rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ArithmeticOperationController {
	
	private static  Logger  log=LoggerFactory.getLogger(ArithmeticOperationController.class);
	@GetMapping("/div")
	public String div()
	{
		log.info("At begining of div() method");
		try {
			log.info("Performing Arithimetric Operation");
			float  result=100/0.0f;
			log.info("Arithimetric Operation completed successfully");
			return "result is ::"+result;
			
		}
		catch(Exception e)
		{
			 e.printStackTrace();
			 log.info("problem Arithmetic Operation::="+e.getMessage());
			 return "problem :"+e.getMessage();
		}
	}

}
