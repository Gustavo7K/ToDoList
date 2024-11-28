package com.gustavohenrique.todolist.controller;

import com.gustavohenrique.todolist.entity.Todo;
import com.gustavohenrique.todolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    ResponseEntity<List<Todo>> create(@Valid @RequestBody Todo todo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(todoService.create(todo));
    }

    @GetMapping
    List<Todo> list() {
        return todoService.list();
    }

    /* TODO: 28/11/2024
    @GetMapping("{id}")
    List<Todo> searchById(@PathVariable UUID id) {
        return todoService.searchById(id);
    }*/

    /*@GetMapping("/todos/{realizado}")
    List<Todo> list(@PathVariable("true") boolean realizado) {
        return todoService.list();
    }*/

    @PutMapping("{id}")
    List<Todo> update(@PathVariable UUID id,
                      @RequestBody @Valid Todo todo) {
        return todoService.update(id, todo);
    }

    @DeleteMapping("{id}")
    List<Todo> delete(@PathVariable(value = "id") UUID id) {
        return todoService.delete(id);
    }
}
