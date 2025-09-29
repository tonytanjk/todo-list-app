package com.example.todolist.repositories;

import com.example.todolist.models.TodoItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoMapper {

    List<TodoItem> findAll();
    TodoItem findById(long id);
    void insert(TodoItem todoItem);
    void update(TodoItem todoItem);
    void deleteById(Long id);

}


/*User functions in specific TodoList.
(Should add common access for
general functions and roles for specific functions i.e Viewer, editor, owner) */