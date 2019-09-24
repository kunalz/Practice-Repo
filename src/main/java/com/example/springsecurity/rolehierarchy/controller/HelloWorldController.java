package com.example.springsecurity.rolehierarchy.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Secured("ROLE_ADMIN")
	@RequestMapping("/admin")
	public String helloToAdmin() {
		return "Hello Admin!!";
	}
	
	
	@Secured("ROLE_MANAGER")
	@RequestMapping("/manager")
	public String helloToManager() {
		return "Hello Manager!!";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping("/user")
	public String helloToUser() {
		return "Hello User!!";
	}
}
