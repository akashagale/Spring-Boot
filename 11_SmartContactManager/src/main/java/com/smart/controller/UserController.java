package com.smart.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository; 
	
	// method for adding common for response
	@ModelAttribute
	public void addCommonData(Model model,Principal principal) {
		String userName = principal.getName();
		System.out.println("USERNAME : "+userName);
		User user = userRepository.getUserByUserName(userName);
		System.out.println("NAME : "+user.getName());
		
		model.addAttribute("user", user);
	}
	
	
	// dashboard home
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal) {
// 		common data is fetch from addCommonData(..) method up 	
//		String userName = principal.getName();
//		System.out.println("USERNAME : "+userName);
//		User user = userRepository.getUserByUserName(userName);
//		System.out.println("NAME : "+user.getName());		
//		model.addAttribute("user", user);
		model.addAttribute("title", "User Dashboard");
		return "normal/user_dashboard";
	}
	
	// open add contact form controller
	
	@RequestMapping("/add-contact")
	public String openAddContactForm(Model model,Principal principal) {
		model.addAttribute("title1", "Add Contact");
		model.addAttribute("contact", new Contact());
		return "normal/add_contact_form";
	}
	
	
	
	//	processing add contact form
	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute Contact contact,
			@RequestParam("profileImage") MultipartFile file ,
			Principal principal,HttpSession session) {
		
		try {
		String name = principal.getName();
		
		// processing and uploading file ----> profileImage
			if (file.isEmpty()) {
					// if file is empty then try our message
				System.out.println("File is empty");
				//	throw new Exception("File is empty Exception");   
			}else {
					// if file is empty then try our message
					
				contact.setImage(file.getOriginalFilename());
				File file2 = new ClassPathResource("static/image").getFile();
				//
				Path path = Paths.get(file2.getAbsolutePath()+
						File.separator+file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, 
						StandardCopyOption.REPLACE_EXISTING);  // in..target..option
				
				System.out.println("Image is uploaded");
			}
		
			
			
		User user = this.userRepository.getUserByUserName(name);

		contact.setUser(user);
		user.getContacts().add(contact);
		
		
		this.userRepository.save(user);
		System.out.println("DATA "+contact);
		
		System.out.println("Added to database");
		
		// message success
		session.setAttribute("message", 
				new Message("Your contact is added !! Add more", "success") );
	
		}catch (Exception e) {
			
		// message error
		session.setAttribute("message", 
				new Message("Something went wrong"+e.getMessage(), "danger") );
			
			
			System.out.println("ERROR "+e.getMessage());
			e.printStackTrace();
		}
		
		return "normal/add_contact_form";
	}
}
