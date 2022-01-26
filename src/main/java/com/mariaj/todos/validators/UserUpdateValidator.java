package com.mariaj.todos.validators;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

import org.springframework.validation.Errors;

import com.mariaj.todos.models.User;

public class UserUpdateValidator implements Validator {
	
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    public static void validate(Object target, Errors errors) {
        User user = (User) target;
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("password", "Match", "Passwords Do Not Match In Your Update!");
        }
    }

	@Override
	public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value,
			Class<?>... groups) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public BeanDescriptor getConstraintsForClass(Class<?> clazz) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T> T unwrap(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ExecutableValidator forExecutables() {
		// TODO Auto-generated method stub
		return null;
	}
}
