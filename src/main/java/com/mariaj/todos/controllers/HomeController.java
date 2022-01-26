package com.mariaj.todos.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mariaj.todos.models.Todo;
import com.mariaj.todos.models.User;
import com.mariaj.todos.services.MainService;
import com.mariaj.todos.services.UserService;
import com.mariaj.todos.validators.UserUpdateValidator;
import com.mariaj.todos.validators.UserValidator;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MainService mainService;
	@Autowired
	private UserValidator userValidator;
	
	private String loggedInUserEmail = "";

	private String[] prior = {
			"Urgent", "Very High", "High", "Medium", "Take Your Time"
		};
	
	@RequestMapping("/todos")
	public String displayTodos(@ModelAttribute("todo") Todo todo, HttpSession session, Model model){
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; 
		}
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		List<Todo> todosTop = mainService.getByStatusFalseDesc();
		model.addAttribute("todosTop", todosTop);
		List<Todo> todosBottom = mainService.getByStatusTrueDesc();
		model.addAttribute("todosBottom", todosBottom);
		return "dashboard.jsp";
	}
	
	@GetMapping("/todos/new")
    public String newTodo(@ModelAttribute("todo") Todo todo, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; 
		}
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("users", this.mainService.getUsers());
        return "/todos/newToDo.jsp";
    }

	@PostMapping("/todos/create") 
	public String createTodo(@Valid @ModelAttribute("todo") Todo todo, BindingResult result, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; 
		}
		User user = userService.findUserById(userId);
		todo.setUserCreator(user);
		todo.setStatus(false);
		if(result.hasErrors()) {		
			model.addAttribute("user", user);
    		model.addAttribute("users", this.mainService.getUsers());
			return "/todos/newToDo.jsp";
		}
		this.mainService.createTodo(todo);
		return "redirect:/todos";	
	}
	
	@GetMapping("/todos/{id}")
    public String showTodo(@PathVariable("id") Long id, HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; 
		}
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		Todo todo = mainService.getTodo(id);
        model.addAttribute("todo", todo);
        session.setAttribute("todoId", id);
        return "/todos/showToDo.jsp";
    }
	
	@GetMapping("/todos/{id}/edit")
    public String editTodo(@PathVariable("id") Long id, HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; 
		}
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("users", this.mainService.getUsers());
    	Todo todo = mainService.getTodo(id);
        model.addAttribute("todo", todo);
        model.addAttribute("priorities", prior);
        return "/todos/editToDo.jsp";
    }
	
	@PostMapping("/todos/{id}/update")
    public String updateTodo(@Valid @ModelAttribute("todo") Todo todo, BindingResult result, @PathVariable("id") Long id, HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; 
		}
		User user = userService.findUserById(userId);
		todo.setStatus(false);
		todo.setUserCreator(user);
    	if (result.hasErrors()) {
    		model.addAttribute("user", user);
    		model.addAttribute("users", this.mainService.getUsers());
    		model.addAttribute("todo", todo);
    		model.addAttribute("priorities", prior);
            return "/todos/editToDo.jsp";
        } else {
        	this.mainService.updateTodo(todo);
        	return "redirect:/todos";
        }
    }
	
	@GetMapping("/users/{id}/createdtodos")
    public String showUserCreatedTodos(@PathVariable("id") Long id, @ModelAttribute("todo") Todo todo, HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; 
		}
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		List<Todo> todos = mainService.getTodosDesc();
		model.addAttribute("todos", todos);
		User todosCreator = userService.findUserById(id);
        model.addAttribute("todosCreator", todosCreator);
        return "/users/toDosCreated.jsp";
    }
	
	@GetMapping("/users/{id}/assignedtodos")
    public String showUserAssignedTodos(@PathVariable("id") Long id, @ModelAttribute("todo") Todo todo, HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; 
		}
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		List<Todo> todos = mainService.getTodosDesc();
		model.addAttribute("todos", todos);
		User todosAssignee = userService.findUserById(id);
        model.addAttribute("todosAssignee", todosAssignee);
        return "/users/toDosAssigned.jsp";
    }
	
	@RequestMapping("/users/todos")
	public String displayUsersTodos(@ModelAttribute("todo") Todo todo, HttpSession session, Model model){
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; 
		}
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		List<Todo> todos = mainService.getTodosDesc();
		model.addAttribute("todos", todos);
		return "/users/userToDos.jsp";
	}
	
	@RequestMapping("/users/{id}/profile")
	public String displayProfile(@ModelAttribute("userForm") User userForm, @PathVariable("id") Long id, HttpSession session, Model model){
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		return "/users/userProfile.jsp";
	}
	
	@RequestMapping(value="/users/profile/{id}/update", method=RequestMethod.POST)
    public String updateUser(@Valid @ModelAttribute("userForm") User userToUpdate, BindingResult result, @PathVariable("id") Long id, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/"; 
		}
		User loggedInUser = userService.findUserById(id);
		loggedInUserEmail = userToUpdate.email;
		if (loggedInUser.email.equals(loggedInUserEmail)) {
			System.out.println("got here");
			UserUpdateValidator.validate(userToUpdate, result);
	    	if(result.hasErrors()) {
	    		User user = userService.findUserById(id);
	    		model.addAttribute("user", user);
				return "/users/userProfile.jsp";
			}
		} 
		else {
			System.out.println("got here instead");
			userValidator.validate(userToUpdate, result);
			if(result.hasErrors()) {
	    		User user = userService.findUserById(id);
	    		model.addAttribute("user", user);
				return "/users/userProfile.jsp";
			}
		}
		this.userService.updateUser(userToUpdate);
		return 	"redirect:/users/{id}/profile";
	}
		
	@DeleteMapping("/todos/{id}/delete")
    public String destroy(@PathVariable("id") Long id) {
    	mainService.deleteTodo(id);
        return "redirect:/todos";
    }
	
	@DeleteMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
    	mainService.deleteUser(id);
        return "redirect:/";
    }

	@GetMapping("/todos/{id}/markAsDone")
    public String markAsDone(@PathVariable("id") Long id, HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		Todo todo = mainService.getTodo(id);
        model.addAttribute("todo", todo);
        todo.setStatus(true);
        this.mainService.updateTodo(todo);
        return "redirect:/todos";
    }
	
	@GetMapping("/todos/{id}/markAsDoneUser")
    public String markAsDoneShowUser(@PathVariable("id") Long id, HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		Todo todo = mainService.getTodo(id);
        model.addAttribute("todo", todo);
        todo.setStatus(true);
        this.mainService.updateTodo(todo);
        return "redirect:/users/todos";
    }

	@GetMapping("/todos/{id}/statusShow")
    public String markAsDoneShow(@PathVariable("id") Long id, HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		Todo todo = mainService.getTodo(id);
        model.addAttribute("todo", todo);
        if (todo.status == false) {
        todo.setStatus(true);
        this.mainService.updateTodo(todo);
        return "redirect:/todos/{id}";
        }else {
        	todo.setStatus(false);
            this.mainService.updateTodo(todo);
            return "redirect:/todos/{id}";
        }
    }
 
    @GetMapping("/todos/LowToHigh")
	public String createdAtLowToHigh(@ModelAttribute("todo") Todo todo, HttpSession session, Model model){
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		List<Todo> todosTop = mainService.getByStatusFalseAsc();
		model.addAttribute("todosTop", todosTop);
		List<Todo> todosBottom = mainService.getByStatusTrueDesc();
		model.addAttribute("todosBottom", todosBottom);
		return "dashboard.jsp";
	}
    
    @GetMapping("/todos/HighToLow")
	public String createdAtHighToLow(@ModelAttribute("todo") Todo todo, HttpSession session, Model model){
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		List<Todo> todosTop = mainService.getByStatusFalseDesc();
		model.addAttribute("todosTop", todosTop);
		List<Todo> todosBottom = mainService.getByStatusTrueDesc();
		model.addAttribute("todosBottom", todosBottom);
		return "dashboard.jsp";
	}
    
	@GetMapping("/todos/dateLowToHigh")
	public String dateLowToHigh(@ModelAttribute("todo") Todo todo, HttpSession session, Model model){
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		List<Todo> todosTop = mainService.getDateLowToHigh();
		model.addAttribute("todosTop", todosTop);
		List<Todo> todosBottom = mainService.getByStatusTrueDesc();
		model.addAttribute("todosBottom", todosBottom);
		return "dashboard.jsp";
	}
    
    @GetMapping("/todos/dateHighToLow")
	public String dateHighToLow(@ModelAttribute("todo") Todo todo, HttpSession session, Model model){
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		List<Todo> todosTop = mainService.getDateHighToLow();
		model.addAttribute("todosTop", todosTop);
		List<Todo> todosBottom = mainService.getByStatusTrueDesc();
		model.addAttribute("todosBottom", todosBottom);
		return "dashboard.jsp";
	} 
			
    @GetMapping("/todos/doneLowToHigh")
	public String createdAtLowToHighDone(@ModelAttribute("todo") Todo todo, HttpSession session, Model model){
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		List<Todo> todosTop = mainService.getByStatusFalseDesc();
		model.addAttribute("todosTop", todosTop);
		List<Todo> todosBottom = mainService.getByStatusTrueDoneAsc();
		model.addAttribute("todosBottom", todosBottom);
		return "dashboard.jsp";
	}
    
    @GetMapping("/todos/doneHighToLow")
	public String createdAtHighToLowDone(@ModelAttribute("todo") Todo todo, HttpSession session, Model model){
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		List<Todo> todosTop = mainService.getByStatusFalseDesc();
		model.addAttribute("todosTop", todosTop);
		List<Todo> todosBottom = mainService.getByStatusTrueDoneDesc();
		model.addAttribute("todosBottom", todosBottom);
		return "dashboard.jsp";
	}
    
	@GetMapping("/todos/dateDoneLowToHigh")
	public String dateLowToHighDone(@ModelAttribute("todo") Todo todo, HttpSession session, Model model){
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		List<Todo> todosTop = mainService.getByStatusFalseDesc();
		model.addAttribute("todosTop", todosTop);
		List<Todo> todosBottom = mainService.getDateLowToHighDone();
		model.addAttribute("todosBottom", todosBottom);
		return "dashboard.jsp";
	}
    
    @GetMapping("/todos/dateDoneHighToLow")
	public String dateHighToLowDone(@ModelAttribute("todo") Todo todo, HttpSession session, Model model){
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		List<Todo> todosTop = mainService.getByStatusFalseDesc();
		model.addAttribute("todosTop", todosTop);
		List<Todo> todosBottom = mainService.getDateHighToLowDone();
		model.addAttribute("todosBottom", todosBottom);
		return "dashboard.jsp";
	}
}
