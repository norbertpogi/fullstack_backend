package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.todo.Todo;

@Service
public class TodoHardcodedService {
	
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int idCounter = 0;
	
	static {
		todos.add(new Todo(idCounter++, "becomeFullStack", "learn to dance", new Date(), false));
		todos.add(new Todo(idCounter++, "becomeExpert", "learn to about microservice 2", new Date(), false));
		todos.add(new Todo(idCounter++, "becomeMaster", "learn to architect", new Date(), false));
		todos.add(new Todo(idCounter++, "becomeMaster", "learn to about angular", new Date(), false));
	}
	
	public List<Todo> findAll() {
		return todos;
		
	}
	
	public Todo deleteByid(long id) {
		System.out.println(">>>>>>>>>>>>>>>>>      " + id);
		Todo todo = findById(id);
		if(null == todo) {
			return null;
		}
		if (todos.remove(todo)) {
			return todo;
		}
		return null;
	}
	
//	public Todo updateById(long id) {
//		Todo todo = findById(id);
//		if(null == todo) {
//			return null;
//		}
//		if (todos.(todo)) {
//			return todo;
//		}
//		return null;
//	}

	public Todo findById(long id) {
		for(Todo todo : todos) {
			if(id == todo.getId()) {
				return todo;
			}
		}
		return null;
	}
}
