package com.myapp.tuto.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/")
	public String redirectToAccueil() {
		return "redirect:/accueil";
	}
	
	
	@GetMapping("/accueil")
	public String accueil(@AuthenticationPrincipal User user, Model model) {
		// so that the index page receive the user object. 
		model.addAttribute("user", user);
		return "index";
	}
	
	@GetMapping("/profile")
	public String profile(@AuthenticationPrincipal User user, Model model) {
		
		if (user != null) {
			model.addAttribute("user", user);
			return "administration/profile";
		}
		return "redirect:/index";
	}
	
	@GetMapping("/administration")
	public String administration(@AuthenticationPrincipal User user, Model model) {
		if (user != null && user.getAuthorities().stream().anyMatch( a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			model.addAttribute("user", user);
			return "administration/adminstration";
		}
		
		return "administration/error";
	}
}
