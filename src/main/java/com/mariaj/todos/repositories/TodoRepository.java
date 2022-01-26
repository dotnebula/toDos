package com.mariaj.todos.repositories;

import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mariaj.todos.models.Todo;


@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
	List<Todo> findAll();
	
//	@Query("SELECT i FROM Todo i Order By i.users.size DESC")
	List<Todo> findAllByOrderByByDateDesc();
//	@Query("SELECT i FROM Todo i Order By i.users.size ASC")
	List<Todo> findAllByOrderByByDateAsc();
	
	List<Todo> findAllByOrderByCreatedAtDesc();
	List<Todo> findAllByOrderByCreatedAtAsc();
	
	List<Todo> findByStatusFalseOrderByByDateAsc();
	List<Todo> findByStatusFalseOrderByByDateDesc();
	
	List<Todo> findByStatusTrueOrderByByDateAsc();
	List<Todo> findByStatusTrueOrderByByDateDesc();
	
	List<Todo> findByStatusFalseOrderByCreatedAtAsc();
	List<Todo> findByStatusFalseOrderByCreatedAtDesc();
	
	List<Todo> findByStatusTrueOrderByCreatedAtAsc();
	List<Todo> findByStatusTrueOrderByCreatedAtDesc();
	
	List<Todo> findByStatusFalse();
	
	List<Todo> findByStatusTrue();
	
}

