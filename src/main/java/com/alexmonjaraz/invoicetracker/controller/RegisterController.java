package com.alexmonjaraz.invoicetracker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alexmonjaraz.invoicetracker.DAO.UserRepo;
import com.alexmonjaraz.invoicetracker.entity.NewUser;
import com.alexmonjaraz.invoicetracker.entity.User;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("")
	public String showRegisterForm(Model model) {
		NewUser user = new NewUser();
		
		model.addAttribute("newUser",user);
		return "register/registration-form";
	}
	
	@PostMapping("/save")
	public String saveNewUser(@Valid @ModelAttribute("newUser") NewUser newUser,
							BindingResult bindResult,
							Model model) {
		//check form/field validation errors, show error if bad.
		if (bindResult.hasErrors()) {
			return "register/registration-form";
		}
		//check if the user doesn't already exist, show error if bad.
		User existingUser = userRepo.findByUserName(newUser.getUserName());
		if (existingUser != null) {
			model.addAttribute("newUser", new NewUser());
			model.addAttribute("ExistingUserError", "That user name already exists.");
			return "register/registration-form";
		}
		//save and show confirm page if all OK
		User validUser = new User(newUser);
		userRepo.save(validUser);
		return "redirect:/login";
	}
}
