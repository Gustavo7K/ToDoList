package com.gustavohenrique.todolist.controller;

import com.gustavohenrique.todolist.entity.Todo;
import com.gustavohenrique.todolist.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TodoController {
    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todos")
    List<Todo> create(@RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @GetMapping("/todos")
    List<Todo> list() {
        return todoService.list();
    }

    @GetMapping("/todos/{id}")
    List<Todo> list(@PathVariable(value = "id") UUID id) {
        return todoService.list();
    }

    /*@GetMapping("/todos/{realizado}")
    List<Todo> list(@PathVariable("true") boolean realizado) {
        return todoService.list();
    }*/

    /*@PutMapping("/todos/{id}")
    List<Todo> update(@RequestBody Todo todo) {
        return todoService.update(todo);
    }*/

    @DeleteMapping("/todos/{id}")
    List<Todo> delete(@PathVariable("id") UUID id) {
        return todoService.delete(id);
    }
}
