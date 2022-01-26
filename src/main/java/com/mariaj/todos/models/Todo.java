package com.mariaj.todos.models;

//import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
//import javax.validation.constraints.Future;
//import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="todos")
public class Todo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Todo can not be blank")
	@Size(min=8, message="Todo must be at least 8 characters")
	private String description;
	
	@NotNull(message="Completion date is required")
//	@Future(message="Date must be in the future")
//	@FutureOrPresent(message="Date can not be in the past")
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private String byDate;
	
	public Boolean status;
	
	@NotBlank(message="Todo can not be blank")
	private String priority;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
//	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
//	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private Date updatedAt;

	@ManyToOne()
	@JoinColumn(name="user_id")
	private User userCreator;
	
	@ManyToOne
	@JoinColumn(name="user_id1")
	private User userAssignee;

	
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate(){
	    this.updatedAt = new Date();
	}
	
	
	public Todo() {
	}

	public Todo(Long id,
			@NotBlank(message = "Todo can not be blank") @Size(min = 8, message = "Todo must be at least 8 characters") String description,
			@NotNull(message = "Completion date is required") String byDate, Boolean status,
			@NotBlank(message = "Todo can not be blank") String priority, Date createdAt, Date updatedAt,
			User userCreator, User userAssignee) {
		super();
		this.id = id;
		this.description = description;
		this.byDate = byDate;
		this.status = status;
		this.priority = priority;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userCreator = userCreator;
		this.userAssignee = userAssignee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getByDate() {
		return byDate;
	}

	public void setByDate(String byDate) {
		this.byDate = byDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
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

	public User getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(User userCreator) {
		this.userCreator = userCreator;
	}

	public User getUserAssignee() {
		return userAssignee;
	}

	public void setUserAssignee(User userAssignee) {
		this.userAssignee = userAssignee;
	}

	
	
}
