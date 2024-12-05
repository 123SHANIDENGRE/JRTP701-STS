package com.nt.Rest;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishMessageOperationsController {

	private static  Logger  log=LoggerFactory.getLogger(WishMessageOperationsController.class);
	  
	  @GetMapping("/greet")
	public ResponseEntity<String>  showMessage()
	{  
		  log.debug("At the begining of showMessage() method");
		  log.debug("At the end of showMessage() method");
		  return  new ResponseEntity<String>("Good Morning",HttpStatus.OK);
	
		  
	}

	
}
