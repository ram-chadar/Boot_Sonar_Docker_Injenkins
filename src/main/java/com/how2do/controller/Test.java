package com.how2do.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.how2do.model.Employee;

@RestController
public class Test {
	
	@RequestMapping("/")
	public String msg() {
		return "App Started";
	}
	
	@RequestMapping("/mydata")
	public Employee myata() {
		return new Employee(1, "Ram");
	}

}
