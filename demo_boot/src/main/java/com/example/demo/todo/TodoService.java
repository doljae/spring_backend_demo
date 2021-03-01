package com.example.demo.todo;

import java.util.List;

public interface TodoService {
	public List<TodoVo> getTodoList();

	public TodoVo getTodo(Long id);

	public int registerTodo(TodoVo paramVo);

	public int modifyTodo(TodoVo paramVo);

	public int deleteTodo(Long id);
	public int deleteAllTodo();
}
