package com.mariaj.todos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mariaj.todos.models.User;
import com.mariaj.todos.services.UserService;
import com.mariaj.todos.validators.UserValidator;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;

	public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
	}

	@RequestMapping("/")
    public String registerForm(@ModelAttribute("user") User user) {
        return "logandreg.jsp";
    }
	
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	userValidator.validate(user, result);
    	if(result.hasErrors()) {
			return "logandreg.jsp";
		}
		User newUser = userService.registerUser(user);
		session.setAttribute("userId", newUser.getId());
		return 	"redirect:/todos";
	}
	
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirect) {
		boolean isAuthenticated = userService.authenticateUser(email, password);
		if(isAuthenticated) {
			User u = userService.findByEmail(email);
			session.setAttribute("userId", u.getId());
			return "redirect:/todos";
		}else{
			redirect.addFlashAttribute("error", "Invalid Credentials. Please try again.");
			return "redirect:/";
		}
	}
	
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
