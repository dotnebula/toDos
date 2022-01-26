package com.mariaj.todos.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="First Name can not be blank")
	@Size(max=15, message="First name must be up to 10 characters")
	public String firstName;
	 
	@NotBlank(message="Last Name can not be blank")
	@Size(max=20, message="Last name must be up to 25 characters")
	public String lastName;
	
	@NotBlank(message="Email required")
	@Email(message="Email must be valid")
	public String email;
	
	@Size(min=8, message="Password must be at least 8 characters")
	private String password;
	
	@Transient
	private String passwordConfirmation;
	 
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	

	@OneToMany(mappedBy="userCreator", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Todo> createdTodos;
	
	@OneToMany(mappedBy="userAssignee", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Todo> assignedTodos;
	
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate(){
	    this.updatedAt = new Date();
	}
	 
	public User() {
	}

	public User(Long id,
			@NotBlank(message = "First Name can not be blank") @Size(max = 15, message = "First name must be up to 10 characters") String firstName,
			@NotBlank(message = "Last Name can not be blank") @Size(max = 30, message = "Last name must be up to 25 characters") String lastName,
			@NotBlank(message = "Email required") @Email(message = "Email must be valid") String email,
			@Size(min = 8, message = "Password must be at least 8 characters") String password,
			String passwordConfirmation, Date createdAt, Date updatedAt, List<Todo> createdTodos,
			List<Todo> assignedTodos) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdTodos = createdTodos;
		this.assignedTodos = assignedTodos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Todo> getCreatedTodos() {
		return createdTodos;
	}

	public void setCreatedTodos(List<Todo> createdTodos) {
		this.createdTodos = createdTodos;
	}

	public List<Todo> getAssignedTodos() {
		return assignedTodos;
	}

	public void setAssignedTodos(List<Todo> assignedTodos) {
		this.assignedTodos = assignedTodos;
	}

}
