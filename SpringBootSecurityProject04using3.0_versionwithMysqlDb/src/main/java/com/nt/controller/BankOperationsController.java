package com.nt.controller;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankOperationsController {

	@GetMapping("/welcome")
	 public  ResponseEntity<String>  showHome()
	 {
		 return new ResponseEntity<String>("Welcome to Home Page",HttpStatus.OK);
	 }
	@GetMapping("/offers")
	 public  ResponseEntity<String>  showOffers()
	 {
		 return new ResponseEntity<String>("Offers Page",HttpStatus.OK);
	 }
	
	
	@GetMapping("/loan_approve")
	@PreAuthorize("hasAuthority('MANAGER')")  //specifing authorization
	 public  ResponseEntity<String>   approveloan()
	 {
		   int amount=new Random().nextInt(12000000);
		 
		 return new ResponseEntity<String>("Loan Approved  the amount is::"+amount,HttpStatus.OK);
	 }
	
	

	@GetMapping("/balance")
	@PreAuthorize("hasAnyAuthority('MANAGER','CUSTOMER')")  //specifing authorization
	 public  ResponseEntity<String>    showBalance()
	 {
		   int amount=new Random().nextInt(1000000);
		 
		 return new ResponseEntity<String>("Balance is::"+amount,HttpStatus.OK);
	 }
	
	
	
	
	
}
