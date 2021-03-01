package com.example.demo.todo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = {"http://localhost:8080/"})
public class TodoController {
	@Autowired
	@Qualifier("a")
	private TodoService service;

	private Logger logger = LogManager.getLogger(this.getClass());

	@GetMapping("")
	public List<TodoVo> getTodoList() {
		List<TodoVo> todos = service.getTodoList();
//		for (TodoVo todo : todos) {
//			System.out.println(todo);
//		}
		return todos;
	}

	// ResponseEntity를 이용해서 데이터와 status코드를 반환하는 방법
	@GetMapping("/{id}")
	public ResponseEntity<?> getTodo(@PathVariable("id") Long id) {
		TodoVo todo = service.getTodo(id);
		if (todo == null)
			return new ResponseEntity<>("Todo Not Found", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(todo);
	}

	@PostMapping("")
	public ResponseEntity<?> registerTodo(@RequestBody TodoVo paramVo) {
		int cnt = service.registerTodo(paramVo);
		if (cnt == 1)
			return ResponseEntity.status(HttpStatus.OK).body(service.getTodoList());
		// 500 에러
		return new ResponseEntity<>("Todo Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> modifyTodo(@PathVariable("id") Long id, @RequestBody TodoVo paramVo) {
		TodoVo todo = service.getTodo(id);
		if (todo == null)
			return new ResponseEntity<>("Todo Not Found", HttpStatus.INTERNAL_SERVER_ERROR);

		paramVo.setId(id);
		logger.debug(">> Update Todo 입력 " + paramVo);

		int cnt = service.modifyTodo(paramVo);
		if (cnt == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(service.getTodoList());
		}
		return new ResponseEntity<>("Todo Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id) {
		TodoVo todo = service.getTodo(id);
		if (todo == null)
			return new ResponseEntity<>("Todo Not Found", HttpStatus.INTERNAL_SERVER_ERROR);

		logger.debug(">> Delete Todo 입력 " + id);

		int cnt = service.deleteTodo(id);
		if (cnt == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(service.getTodoList());
		}
		return new ResponseEntity<>("Todo Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@DeleteMapping("")
	public ResponseEntity<?> deleteTodo() {
		int cnt = service.deleteAllTodo();
		System.out.println(cnt);
		logger.debug(">> Delete All Todo 입력 ");
		if (cnt >=0) {
			return ResponseEntity.status(HttpStatus.OK).body(service.getTodoList());
		}
		return new ResponseEntity<>("Todo Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
