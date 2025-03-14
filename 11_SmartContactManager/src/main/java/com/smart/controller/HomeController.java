package com.smart.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "home";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About - Smart Contact Manager");
		return "about";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register - Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(Model model, @Valid @ModelAttribute("user") User user,
			BindingResult result1,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, 
			HttpSession session) {
		try {
			model.addAttribute("title", "Register - Smart Contact Manager");
//			model.addAttribute("user", new User());
			System.out.println("Agreement ==" + agreement);
			System.out.println("User ==" + user.toString());

			if (!agreement) {
				System.out.println("You have not agreed terms and conditions!!");
				throw new Exception("You have not agreed terms and conditions!!");
			}

			if (result1.hasErrors()) {
				System.out.println("ERROR "+result1);
				model.addAttribute("user",user);
				return "signup";
			}

			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));	

			User result = this.userRepository.save(user);

			model.addAttribute("user", new User());
			model.addAttribute("msg", true);
			session.setAttribute("message", new Message("Successfully Register!!", "alert-success"));
			return "signup";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something went wrong!!" + e.getMessage(), "alert-error"));
			return "signup";
		}

	}

	
	
	// handler for custom login
	@RequestMapping("/signin")
	public String customLogin(Model model) {
		model.addAttribute("title", "LoginPage");
		return "login";
	}
	
	
	@RequestMapping("/fail")
	public String Loginfail(Model model) {
		model.addAttribute("title", "Login fail");
		return "failpage";
	}
	
	
	@GetMapping("/test")
	@ResponseBody
	public String test() {

		User user = new User();
		user.setName("Rohit");
		user.setEmail("rohit@gmail.com");
		user.setPassword("1234");
		user.setRole("Manager");
		user.setAbout("Mumbai");
		user.setEnabled(true);
		user.setImageUrl("not available");

		Contact contact = new Contact();
		user.getContacts().add(contact);

		userRepository.save(user);

		return "Working";
	}

}
