package com.mariaj.todos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariaj.todos.models.Todo;
import com.mariaj.todos.models.User;
import com.mariaj.todos.repositories.TodoRepository;
import com.mariaj.todos.repositories.UserRepository;


@Service
public class MainService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TodoRepository todoRepository;


	public MainService(UserRepository userRepository, TodoRepository todoRepository) {
		this.userRepository = userRepository;
		this.todoRepository = todoRepository;
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public List<User> getByEmailNot(String email) {
		return userRepository.findAll();
	}
	
	public List<Todo> getTodos() {
		return todoRepository.findAll();
	}
	
	public List<Todo> getTodosDesc() {
		return todoRepository.findAllByOrderByCreatedAtDesc();
	}
	
	public Todo getTodo(Long id) {
		Optional<Todo> todo = todoRepository.findById(id);
		if(todo.isPresent()) {
            return todo.get();
        } else {
            return null;
        }
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public Todo createTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	public Todo updateTodo(Todo todoToUpdate) {
		return todoRepository.save(todoToUpdate);
	}

	public List<Todo> getDateLowToHigh() {
    	return todoRepository.findByStatusFalseOrderByByDateAsc();
    }
	
	public List<Todo> getDateHighToLow() {
    	return todoRepository.findByStatusFalseOrderByByDateDesc();
    }
	
	public List<Todo> getDateLowToHighDone() {
    	return todoRepository.findByStatusTrueOrderByByDateAsc();
    }
	
	public List<Todo> getDateHighToLowDone() {
    	return todoRepository.findByStatusTrueOrderByByDateDesc();
    }
	
	
	public List<Todo> getByStatusFalseDesc() {
    	return todoRepository.findByStatusFalseOrderByCreatedAtDesc();
    }
	
	public List<Todo> getByStatusFalseAsc() {
    	return todoRepository.findByStatusFalseOrderByCreatedAtAsc();
    }
	
	public List<Todo> getByStatusTrueDoneAsc() {
    	return todoRepository.findByStatusTrueOrderByCreatedAtAsc();
    }
	
	public List<Todo> getByStatusTrueDoneDesc() {
    	return todoRepository.findByStatusTrueOrderByCreatedAtDesc();
    }
	
	public List<Todo> getByStatusTrueDesc() {
    	return todoRepository.findByStatusTrueOrderByCreatedAtDesc();
    }
	
	public String deleteTodo(Long id) {
		todoRepository.deleteById(id);
		return "Todo has been deleted";
	}
	
	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return "User has been deleted";
	}
	
	
}
