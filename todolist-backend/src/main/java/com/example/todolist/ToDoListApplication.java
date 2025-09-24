package com.example.todolist;

import com.example.todolist.repositories.TodoMapper;
import com.example.todolist.models.TodoItem;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@SpringBootApplication
public class ToDoListApplication {

	public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        try(SqlSession session = sqlSessionFactory.openSession()){
            TodoMapper mapper = session.getMapper(TodoMapper.class);
            List<TodoItem> todos = mapper.findAll();
            todos.forEach(System.out::println);
        }
/*
        try(SqlSession session = sqlSessionFactory.openSession()){
            TodoMapper mapper = session.getMapper(TodoMapper.class);
            TodoItem newTodo = new TodoItem();
            newTodo.setTask("Manual MyBatis");
            newTodo.setCompleted(false);
            mapper.insert(newTodo);
            session.commit();
            System.out.println("Inserted todo with ID: " + newTodo.getId());
        }*/
        SpringApplication.run(ToDoListApplication.class, args);
	}
}
