package com.example.demo.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("a")
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoMapper mapper;

    @Override
    public List<TodoVo> getTodoList() {
        return mapper.selectTodoList();
    }

    @Override
    public TodoVo getTodo(Long id) {
        return mapper.selectTodo(id);
    }

    @Override
    public int registerTodo(TodoVo paramVo) {
        return mapper.insertTodo(paramVo);
    }

    @Override
    public int modifyTodo(TodoVo paramVo) {
        return mapper.updateTodo(paramVo);
    }

    @Override
    public int deleteTodo(Long id) {
        return mapper.deleteTodo(id);
    }

    @Override
    public int deleteAllTodo() {
        // TODO Auto-generated method stub
        return mapper.deleteAllTodo();
    }

}
