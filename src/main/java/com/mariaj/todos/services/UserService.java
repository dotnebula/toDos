package com.mariaj.todos.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariaj.todos.models.User;
import com.mariaj.todos.repositories.UserRepository;


@Service
public class UserService {
	@Autowired(required=true)
    private UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> user = userRepository.findById(id);
    	
    	if(user.isPresent()) {
            return user.get();
    	} else {
    	    return null;
    	}
    }
    
    public User updateUser(User userToUpdate) {
    	String hashed = BCrypt.hashpw(userToUpdate.getPassword(), BCrypt.gensalt());
        userToUpdate.setPassword(hashed);
		return userRepository.save(userToUpdate);
	}
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
}
