package com.validate.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.validate.entities.LoginData;

@Controller
public class MyController {

	@GetMapping("/form")
	public String openForm(Model m) {
		System.out.println("opening form");
		m.addAttribute("loginData", new LoginData());
		return "form";
	}

	@PostMapping("/process")
	public String processForm(@Valid @ModelAttribute("loginData") LoginData loginData,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) 
		{
			System.out.println(bindingResult);
			 return "form";
		}
		System.out.println(loginData);
		return "success";
	}
}