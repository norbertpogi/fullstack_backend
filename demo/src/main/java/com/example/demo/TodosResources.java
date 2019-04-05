package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.todo.Todo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodosResources {
	
	@Autowired
	private TodoHardcodedService todoService;
	
	@GetMapping("users/{username}/todos")
	public List<Todo> getAllTOdos(@PathVariable String username) {
		
		return todoService.findAll();
		
	}
	
	@DeleteMapping("users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id ) {
		
		Todo todo =  todoService.deleteByid(id);
		if(todo != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
		
	}

}
