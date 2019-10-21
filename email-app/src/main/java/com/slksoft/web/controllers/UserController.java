package com.slksoft.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.slksoft.entity.User;
import com.slksoft.service.UserService;


@Controller
public class UserController {

	@Autowired
	UserService service;

	@GetMapping("/register")
	public String addnewUser(Model model) {
		return "register";
	}
	
	@PostMapping("/save-user")
	public String saveUser(User u,Model model) {
		service.addNewUser(u);
		return "redirect: ./login";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		User user= (User) model.getAttribute("user");
		if(user != null) {
			return "redirect: ./inbox";
		}
		else {
			return "login";
		}
	
	}
	
	@PostMapping("/login-user")
	public String  loginUser(@RequestParam("email") String email, @RequestParam("password") String password,Model model) {
		model.addAttribute("user", service.loginUser(email, password));
		return "redirect: ./inbox";
	}
	
	
	@GetMapping("/logout")
	public String logoutUser(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate(); 
         return "redirect:/";  
     } 
	
	@GetMapping("/inbox")
	public String inbox(Model model) {
		User user= (User) model.getAttribute("user");
		if(user == null) {
			return "redirect: ./login";
		}
		return "dashboard";
		
	
	}
	
	
	
	
	
	
	}

		
	


