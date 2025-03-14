package com.validate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExController {
	
	@RequestMapping("/example")
	public String example() {
		
		return "example";
	}
	
}
