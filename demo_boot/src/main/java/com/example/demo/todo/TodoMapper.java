package com.example.demo.todo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoMapper {
    public List<TodoVo> selectTodoList();

    public TodoVo selectTodo(Long id);

    public int insertTodo(TodoVo todo);

    public int updateTodo(TodoVo todo);

    public int deleteTodo(Long id);

    public int deleteAllTodo();
}
