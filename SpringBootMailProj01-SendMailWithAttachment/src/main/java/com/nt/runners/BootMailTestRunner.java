package com.nt.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.service.IpurchaseOrder;

@Component
public class BootMailTestRunner   implements CommandLineRunner{

	
	
	@Autowired
	private IpurchaseOrder  service;
	@Override
	public void run(String... args) throws Exception {
		String msg=service.purchase(new String[] {"shirt","trouser","hat"},   new double[] {5000.0,6000.0,2000.0}, 
				                                                     new String[] {"shanidengre@gmail.com",   "officialshani16@gmail.com","shanidengre123@gmail.com"});
		
		
		System.out.println(msg);
	}

}
