package com.example.demo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.todo.Todo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodosResources {

	@Autowired
	private TodoHardcodedService todoService;

	//getall
	@GetMapping("users/{username}/todos")
	public List<Todo> getAllTOdos(@PathVariable String username) {

		return todoService.findAll();

	}

	//delete by id
	@DeleteMapping("users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id ) {

		Todo todo =  todoService.deleteByid(id);
		if(todo != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();

	}

	//get by id
	@GetMapping("users/{username}/todos/{id}")
	public Todo getTOdosById(@PathVariable String username, @PathVariable long id) {		
		return todoService.findById(id);

	}

	//update by id
	@PutMapping("users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo ) {
		Todo todoUpdated = todoService.save(todo);
		return new ResponseEntity<Todo>(todoUpdated, HttpStatus.OK);

	}


	//create new
	@PostMapping("users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo ) {
		Todo createdTod = todoService.save(todo);
		
		//location
		//get current resource url
		//{id}
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build().expand(createdTod.getId()).toUri();
		
		return ResponseEntity.created(uri).build();

	}


}
