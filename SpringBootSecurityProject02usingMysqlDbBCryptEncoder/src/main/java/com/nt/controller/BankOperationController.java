package com.nt.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankOperationController {

	
	@GetMapping("/")
	public String showHomePage()
	{ 
		 //return Lvn
		return "welcome";
	}
	
	@GetMapping("/balance")
	public String showBalancePage(Map<String,Object> map)
	{  
		int amount=new Random().nextInt(1000000);
		 //return Lvn
		map.put("amount",amount);
		return "show_balance";
	}
	
	@GetMapping("/offers")
	public String showOffers()
	{  
		//return lvn
		return "offers";
	}
	
	
	
	@GetMapping("/loanApprove")
	public String approveLoan(Map<String,Object> map)
	{  
		int amount=new Random().nextInt(1000000);
		 //return Lvn
		map.put("amount",amount);
		return "loan";
	}
	
	@GetMapping("/denied")
	public String showAccessDeniedPage()
	{  
		// return Lvn(logical view name)

		return "authorization_failed";
	}
	
}
