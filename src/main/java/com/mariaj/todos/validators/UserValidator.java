package com.mariaj.todos.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mariaj.todos.models.User;
import com.mariaj.todos.repositories.UserRepository;


@Component
public class UserValidator implements Validator {
	@Autowired
	private UserRepository userRepository;
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
//            errors.rejectValue("passwordConfirmation", "Match");
            errors.rejectValue("password", "Match", "Passwords Do Not Match!");
        }
        
        if(this.userRepository.existsByEmail(user.getEmail())){
//        	errors.rejectValue("email", "Unique");
        	errors.rejectValue("email", "Unique", "Email has already been registered. Please use another one!!");
        }

    }
}
